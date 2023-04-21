package mypack.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mypack.model.Post;
import mypack.payload.statistic.StatisticForCount;
import mypack.repository.custom.PostSearchCustomRepository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostSearchCustomRepository {
	@Query(value = "Select * from post where service_id in (:arr) and status = 'ACTIVE' and expiration_date >= :date", nativeQuery = true)
	List<Post> getJobByArrService(@Param("arr") List<Long> arr, @Param("date") Date date, Pageable page);

	@Query(value = "Select * from post where author = :id", nativeQuery = true)
	List<Post> getEmpListPost(@Param("id") Long empId);

	@Query(value = "Select sum(view_count) from post where author = :id", nativeQuery = true)
	Long getTotalViewPost(@Param("id") Long empId);

	@Query(value = "Select new mypack.payload.statistic.StatisticForCount(Month(c.createDate) as month, count(c) as value)  from Post c where YEAR(c.createDate) = :year group by Month(c.createDate) order by Month(c.createDate) asc")
	List<StatisticForCount> getCountNewPost(@Param("year") Integer year);

	@Query(value = "select * from post, (select post_id from view_post where date >= :stDate and date <= :endDate group by post_id order by count(*) desc limit :start,:limit) lst where id in (lst.post_id)", nativeQuery = true)
	List<Post> getHotPostByDates(@Param("stDate") Date stDate, @Param("endDate") Date endDate, @Param("start") Integer start, @Param("limit") Integer limit);
}
