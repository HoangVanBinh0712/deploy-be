package mypack.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mypack.controller.exception.CommonRuntimeException;
import mypack.dto.OrderDTO;
import mypack.payload.statistic.StatisticForCount;
import mypack.payload.statistic.SumTotalByYearMonthCurrencyStatus;
import mypack.repository.CVSubmitRepository;
//import mypack.repository.CommentRepository;
import mypack.repository.OrderRepository;
import mypack.repository.PostRepository;
import mypack.repository.UserRepository;
import mypack.repository.ViewPageRepository;
import mypack.utility.datatype.EROrderStatus;

@Service
public class AdminStatisticService {
	@Autowired
	UserRepository userRepo;

	@Autowired
	PostRepository postRepo;

	@Autowired
	ViewPageRepository viewPageRepository;

	@Autowired
	CVSubmitRepository cvSubmitRepository;

	// @Autowired
	// CommentRepository commentRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	private ModelMapper mapper;

	public List<StatisticForCount> getPostCountStatistc(Integer year) {
		// Count total view post
		List<StatisticForCount> res = postRepo.getCountNewPost(year);
		if (res.isEmpty())
			throw new CommonRuntimeException("Do not have any post to statistic !");
		return res;
	}

	public List<StatisticForCount> getCVSubmitCountStatistc(Integer year) {
		// Count total view Page

		List<StatisticForCount> vpc = cvSubmitRepository.getCountTotalCVSubmit(year);
		if (vpc.isEmpty())
			throw new CommonRuntimeException("Do not have any cv submit to statistic !");
		return vpc;
	}

	// public List<StatisticForCount> getCountComments(Integer year) {
	//
	// // Count total view Page
	//
	// List<StatisticForCount> vpc = commentRepository.getCountAllComments(year);
	//
	// if (vpc.isEmpty())
	// throw new CommonRuntimeException("Do not have any comment in any post !");
	//
	// return vpc;
	// }

	public List<StatisticForCount> getNewUserRegistered(Integer year) {
		// Count total view post
		List<StatisticForCount> res = userRepo.getCountNewUsers(year);
		if (res.isEmpty())
			throw new CommonRuntimeException("Do not have any user to statistic !");
		return res;
	}

	// Order -> Revenue
	public List<SumTotalByYearMonthCurrencyStatus> getTotalOrder(Integer year, EROrderStatus status) {
		// Count total view post
		List<SumTotalByYearMonthCurrencyStatus> res = orderRepository.getTotalUserOrderForAdmin(year, status);
		if (res.isEmpty())
			throw new CommonRuntimeException("Do not have any order to statistic !");
		return res;
	}

	public List<OrderDTO> getAllOrder(Integer page, EROrderStatus status) {
		Pageable pageable = PageRequest.of(page, 100);
		return orderRepository.findByStatus(status, pageable).stream().map(x -> mapper.map(x, OrderDTO.class)).toList();
	}
}
