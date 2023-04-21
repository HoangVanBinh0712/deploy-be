package mypack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mypack.model.User;
import mypack.model.ViewPage;
import mypack.payload.statistic.StatisticForCount;

@Repository
public interface ViewPageRepository extends JpaRepository<ViewPage, Long> {

    @Query(value = "Select new mypack.payload.statistic.StatisticForCount(Month(c.date) as month, count(c) as value)  from ViewPage c where c.user = :id and YEAR(c.date) = :year group by Month(c.date)")
    List<StatisticForCount> getCountViewPage(@Param("id") User emp, @Param("year") Integer year);
}
