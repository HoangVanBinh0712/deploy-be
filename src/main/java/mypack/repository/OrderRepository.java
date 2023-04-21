package mypack.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import mypack.model.UserOrder;
import mypack.payload.statistic.SumTotalByYearMonthCurrencyStatus;
import mypack.model.User;
import mypack.utility.datatype.EROrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Long> {
    Integer countByUserAndStatus(User employer, EROrderStatus status);

    List<UserOrder> findByUserAndStatus(User employer, EROrderStatus status, Pageable pageable);

    @Query(value = "Select new mypack.payload.statistic.SumTotalByYearMonthCurrencyStatus(Month(c.createdDate) as month, c.service.id, status ,currency, sum(c.total) as value)  from UserOrder c where YEAR(c.createdDate) = :year and status = :status group by Month(c.createdDate), c.service,currency order by Month(c.createdDate), c.service.id asc")
    List<SumTotalByYearMonthCurrencyStatus> getTotalUserOrderForAdmin(@Param("year") Integer year,
            @Param("status") EROrderStatus status);
}
