package mypack.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypack.controller.exception.CommonRuntimeException;
import mypack.dto.OrderDTO;
import mypack.model.UserOrder;
import mypack.model.User;
import mypack.payload.ListWithPagingResponse;
import mypack.repository.OrderRepository;
import mypack.repository.ServiceRepository;
import mypack.repository.UserRepository;
import mypack.utility.Page;
import mypack.utility.datatype.EROrderStatus;
import mypack.utility.datatype.ERole;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ServiceRepository serviceRepository;

    @Transactional
    public Long createOrder(String empEmail, Long serviceId, Long durations) {

        Optional<User> optUser = userRepo.findByEmailAndRole(empEmail, ERole.ROLE_EMPLOYER);
        if (optUser.isEmpty())
            throw new CommonRuntimeException("User not found with Email: " + empEmail);
        User user = optUser.get();
        if (user.getEmailConfirm() == false)
            throw new CommonRuntimeException(
                    "You must confirm your email before you use our service !. Go Account and press send verify code button !");

        mypack.model.Service sv = user.getService();
        Optional<mypack.model.Service> opt = serviceRepository.findById(serviceId);
        if (opt.isEmpty())
            throw new CommonRuntimeException("Service not found ! Try again .");
        mypack.model.Service rentingService = opt.get();

        //
        if (sv == null || sv.getId() == rentingService.getId()) {
            // Do not rent any sevice
            UserOrder order = new UserOrder();
            order.setUser(user);
            order.setService(rentingService);
            order.setDuration(durations.intValue());
            order.setPrice(rentingService.getPrice());
            order.setTotal(durations.intValue() * rentingService.getPrice());
            order.setCurrency(rentingService.getCurrency());
            order.setStatus(EROrderStatus.WAIT_FOR_PAYMENT);
            order.setCreatedDate(new Date());

            order = orderRepo.save(order);
            return order.getId();

        } else {
            throw new CommonRuntimeException(
                    "You're currently using another service. If you want to change, you should cancel your current service then try again !");
        }
    }

    public ListWithPagingResponse<OrderDTO> getOrders(EROrderStatus status, Long empId, Page page) {

        User emp = userRepo.findById(empId).get();
        return new ListWithPagingResponse<>(page.getPageNumber() + 1, page.getTotalPage(), page.getPageSize(),
                orderRepo.findByUserAndStatus(emp, status, page.getPageRequest()).stream()
                        .map(order -> mapper.map(order, OrderDTO.class)).toList());
    }

    public Integer getCountOrder(EROrderStatus status, Long empId) {
        User emp = userRepo.findById(empId).get();

        return orderRepo.countByUserAndStatus(emp, status);
    }
}
