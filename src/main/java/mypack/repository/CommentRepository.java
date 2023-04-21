//package mypack.repository;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import mypack.model.Comment;
//import mypack.model.Post;
//import mypack.payload.statistic.StatisticForCount;
//
//@Repository
//public interface CommentRepository extends JpaRepository<Comment, Long> {
//
//    @Query(value = "select * from comment where post_id = :postId and reply_id is null", nativeQuery = true)
//    List<Comment> findCommentByPost(@Param("postId") Long postId);
//
//    @Query(value = "select new mypack.payload.statistic.StatisticForCount(Month(c.date) as month, count(c) as value)  from Comment c where c.post in (:arr) and YEAR(c.date) = :year group by Month(c.date) order by Month(c.date) asc")
//    List<StatisticForCount> getCountComments(@Param("arr") List<Post> lstId, @Param("year") Integer year);
//
//    @Query(value = "Select new mypack.payload.statistic.StatisticForCount(Month(c.date) as month, count(c) as value)  from Comment c where YEAR(c.date) = :year group by Month(c.date) order by Month(c.date) asc")
//    List<StatisticForCount> getCountAllComments(@Param("year") Integer year);
//
// 
//}
