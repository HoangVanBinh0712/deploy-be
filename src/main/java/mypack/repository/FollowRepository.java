package mypack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mypack.model.Follow;
import mypack.model.pk.FollowPK;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowPK> {

    @Query(value = "select post_id from follow where user_id = :userId", nativeQuery = true)
    List<Long> getListPostIdFollow(@Param("userId")Long userId);
}
