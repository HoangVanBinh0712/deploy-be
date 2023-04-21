package mypack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import mypack.model.CVSubmit;
import mypack.model.Post;
import mypack.model.pk.CVSubmitPK;
import mypack.payload.statistic.StatisticForCount;

@Repository
public interface CVSubmitRepository extends JpaRepository<CVSubmit, CVSubmitPK> {
        @Query(value = "select * from cvsubmit where user_id = :user_id and post_id = :post_id", nativeQuery = true)
        Optional<CVSubmit> findByUserAndPost(@Param("user_id") Long userId, @Param("post_id") Long postId);

        @Query(value = "select * from cvsubmit where post_id = :post_id order by match_percent desc limit :start,:limit", nativeQuery = true)
        List<CVSubmit> findByPost(@Param("post_id") Long postId, @Param("start") Integer start,
                        @Param("limit") Integer limit);

        @Query(value = "select count(*) from cvsubmit where post_id = :post_id", nativeQuery = true)
        Long countByPost(@Param("post_id") Long postId);

        @Query(value = "select count(*) from cvsubmit where user_id = :user_id", nativeQuery = true)
        Long countByUser(@Param("user_id") Long userId);

        @Modifying
        @Query(value = "delete from cvsubmit where user_id = :user_id and media_id = :media_id", nativeQuery = true)
        void deleteByProfile(@Param("user_id") Long userId, @Param("media_id") Long mediaId);

        @Modifying
        @Query(value = "delete from cvsubmit where post_id = :post_id", nativeQuery = true)
        void deleteByPost(@Param("post_id") Long postId);

        @Query(value = "select * from cvsubmit where user_id = :user_id order by match_percent desc limit :start,:limit", nativeQuery = true)
        List<CVSubmit> findByUser(@Param("user_id") Long userId, @Param("start") Integer start,
                        @Param("limit") Integer limit);

        @Query(value = "select post_id from cvsubmit where user_id = :user_id and media_id = :mediaId", nativeQuery = true)
        List<Long> findListPostSubmitted(@Param("user_id") Long userId, @Param("mediaId") Long mediaId);

        @Query(value = "Select new mypack.payload.statistic.StatisticForCount(Month(c.date) as month, count(c) as value)  from CVSubmit c where c.post in (:arr) and YEAR(c.date) = :year group by Month(c.date) order by Month(date) asc")
        List<StatisticForCount> getCountCVSubmit(@Param("arr") List<Post> lstId, @Param("year") Integer year);

        @Query(value = "Select new mypack.payload.statistic.StatisticForCount(Month(c.date) as month, count(c) as value)  from CVSubmit c where YEAR(c.date) = :year group by Month(c.date) order by Month(c.date) asc")
        List<StatisticForCount> getCountTotalCVSubmit(@Param("year") Integer year);
}
