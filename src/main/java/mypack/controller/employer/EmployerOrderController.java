package mypack.controller.employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mypack.model.UserOrder_;
import mypack.service.OrderService;
import mypack.service.UserDetailsCustom;
import mypack.utility.Page;
import mypack.utility.PlatformPolicy;
import mypack.utility.datatype.EROrderStatus;

@RestController
@RequestMapping("api/employer/order")
public class EmployerOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getOrder(@AuthenticationPrincipal UserDetailsCustom emp,
            @RequestParam(value = "status", required = true) EROrderStatus status,
            @RequestParam(value = "sortByDateDescending", required = false) Boolean sortByDateDescending,
            @RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "limit", required = true) int limit) {
        if (page <= 0)
            page = 1;
        if (limit <= 0)
            limit = PlatformPolicy.DEFAULT_PAGE_SIZE;
        int itemCount = orderService.getCountOrder(status, emp.getId());

        Sort sort = Sort.unsorted();
        if (sortByDateDescending == null || sortByDateDescending)
            sort = sort.and(JpaSort.of(UserOrder_.createdDate).descending());
        else
            sort = sort.and(JpaSort.of(UserOrder_.createdDate).ascending());

        return ResponseEntity.ok(orderService.getOrders(status, emp.getId(), new Page(page, limit, itemCount, sort)));
    }
}
