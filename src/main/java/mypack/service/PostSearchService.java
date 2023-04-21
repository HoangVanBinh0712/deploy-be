package mypack.service;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mypack.dto.PostDTO;
import mypack.payload.ListWithPagingResponse;
import mypack.repository.PostRepository;
import mypack.repository.ServiceRepository;
import mypack.utility.Page;
import mypack.utility.datatype.ECurrency;
import mypack.utility.datatype.EExperience;
import mypack.utility.datatype.EGender;
import mypack.utility.datatype.EMethod;
import mypack.utility.datatype.EMostViewType;
import mypack.utility.datatype.EPosition;
import mypack.utility.datatype.EServiceType;
import mypack.utility.datatype.EStatus;

@Service
public class PostSearchService {
	@Autowired
	PostRepository postRepository;

	@Autowired
	ServiceRepository serviceRepo;

	@Autowired
	ModelMapper modelMapper;

	public Long getCountBeforSearch(String keyword, Long recruit, Long salary, EMethod method, EPosition position,
			EExperience experience, EGender gender, ECurrency currency, Long authorId, Long industryId, Long cityId,
			EStatus status, Date expirationDate, Date startDate, Long serviceId) {
		return postRepository.postCountBeforeSearch(keyword, recruit, salary, method, position, experience, gender,
				currency, authorId, industryId, cityId, status, expirationDate, startDate, serviceId);
	}

	public ListWithPagingResponse<PostDTO> search(String keyword, Long recruit, Long salary, EMethod method,
			EPosition position, EExperience experience, EGender gender, ECurrency currency, Long authorId,
			Long industryId, Long cityId, EStatus status, Date expirationDate, Date startDate, Long serviceId,
			Page page) {

		return new ListWithPagingResponse<>(page.getPageNumber() + 1, page.getTotalPage(), page.getPageSize(),
				postRepository
						.postSearch(keyword, recruit, salary, method, position, experience, gender, currency, authorId,
								industryId, cityId, status, expirationDate, startDate, serviceId, page)
						.stream().map(p -> modelMapper.map(p, PostDTO.class)).toList());
	}

	public ListWithPagingResponse<PostDTO> getHotJob(int page, int limit) {
		// Get premium service
		List<mypack.model.Service> lstService = serviceRepo.findByType(EServiceType.PREMIUM);
		List<Long> lst = new ArrayList<>();
		for (mypack.model.Service sv : lstService) {
			lst.add(sv.getId());
		}
		return new ListWithPagingResponse<>(page, 1, limit,
				postRepository.getJobByArrService(lst, new Date(), PageRequest.of(page - 1, limit)).stream()
						.map(p -> modelMapper.map(p, PostDTO.class)).toList());

	}

	public ListWithPagingResponse<PostDTO> getMostView(EMostViewType type, int page, int limit) {
		LocalDate now = LocalDate.now();
		Date stDate, endDate;

		switch (type) {
		case YEAR: {

			LocalDate firstDay = now.with(firstDayOfYear()); // 2015-01-01
			LocalDate lastDay = now.with(lastDayOfYear()); // 2015-12-31
			stDate = Date.from(firstDay.atStartOfDay(ZoneId.systemDefault()).toInstant());
			endDate = Date.from(lastDay.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		}
		case MONTH: {
			int year = now.getYear();
			int month = now.getMonthValue(); // you can pass any value of month Like 1,2,3
			YearMonth yearMonth = YearMonth.of(year, month);
			LocalDate firstOfMonth = yearMonth.atDay(1);
			LocalDate lastOfMonth = yearMonth.atEndOfMonth();

			endDate = Date.from(lastOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
			stDate = Date.from(firstOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());

			break;
		}
		default:
			// week

			// Go backward to get Monday
			LocalDate monday = now;
			while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
				monday = monday.minusDays(1);
			}
			stDate = Date.from(monday.atStartOfDay(ZoneId.systemDefault()).toInstant());

			// Go forward to get Sunday
			LocalDate sunday = now;
			while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
				sunday = sunday.plusDays(1);
			}
			endDate = Date.from(sunday.atStartOfDay(ZoneId.systemDefault()).toInstant());
			break;
		}
		System.out.println(stDate);
		System.out.println(endDate);

		return new ListWithPagingResponse<>(page, 1, limit,
				postRepository.getHotPostByDates(stDate, endDate, (page-1) * limit, limit).stream()
						.map(p -> modelMapper.map(p, PostDTO.class)).toList());
	}
}
