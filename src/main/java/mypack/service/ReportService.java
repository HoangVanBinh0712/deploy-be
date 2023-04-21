package mypack.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypack.controller.exception.CommonRuntimeException;
import mypack.dto.ReportDTO;
import mypack.model.Post;
import mypack.model.Report;
import mypack.payload.BaseResponse;
import mypack.payload.ListWithPagingResponse;
import mypack.payload.report.ReportRequest;
import mypack.payload.statistic.StatisticForReport;
import mypack.repository.PostRepository;
import mypack.repository.ReportRepository;
import mypack.utility.ModelSorting;
import mypack.utility.Page;

@Service
public class ReportService {

	@Autowired
	private ReportRepository reportRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PostRepository postRepo;

	// admin only
	public ListWithPagingResponse<ReportDTO> searchReport(Long postId, Boolean handle, Date date, Date handleDate,
			Integer pageNumber, Integer limit, Integer orderBy, Boolean descending) {
		Long count = reportRepo.countBeforeSearchReport(postId, handle, date, handleDate);
		Page page = new Page(pageNumber, limit, count.intValue(), ModelSorting.getReportSort(orderBy, descending));

		return new ListWithPagingResponse<>(page.getPageNumber() + 1, page.getTotalPage(), page.getPageSize(),
				reportRepo.searchReport(postId, handle, date, handleDate, page).stream()
						.map(x -> mapper.map(x, ReportDTO.class)).toList());
	}

	public BaseResponse create(ReportRequest request) {
		Report rp = mapper.map(request, Report.class);
		Optional<Post> post = postRepo.findById(request.getPostId());
		if (post.isEmpty())
			throw new CommonRuntimeException("Post is not exists !");
		rp.setPost(post.get());
		rp.setDate(new Date());
		rp.setHandle(false);
		reportRepo.save(rp);
		return new BaseResponse(true, "Sumbit report success !");
	}

	public BaseResponse deleteReport(Long reportId) {
		Optional<Report> rp = reportRepo.findById(reportId);
		if (rp.isEmpty())
			throw new CommonRuntimeException("Report not found !");
		reportRepo.delete(rp.get());
		return new BaseResponse(true, "Success !");
	}

	public BaseResponse updateStatus(Long reportId, Boolean handle) {
		Optional<Report> rp = reportRepo.findById(reportId);
		if (rp.isEmpty())
			throw new CommonRuntimeException("Report not found !");
		Report report = rp.get();
		report.setHandle(handle);
		if (handle)
			report.setHandleDate(new Date());
		else
			report.setHandleDate(null);
		reportRepo.save(report);

		return new BaseResponse(true, "Success !");
	}

	public List<StatisticForReport> getStatisticReportByStatus(Integer year, Boolean handle) {
		if (handle != null)
			return reportRepo.getToTalReportByStatus(year, handle);
		return reportRepo.getToTalReport(year);

	}
}
