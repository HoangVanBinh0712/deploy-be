package mypack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mypack.model.FollowUser;
import mypack.model.User;

@Repository
public interface FollowUserRepository extends JpaRepository<FollowUser, Long> {

    @Query(value = "Select * from follow_user where user_id = :id", nativeQuery = true)
    List<FollowUser> getAllFollowers(@Param("id") Long id);

    @Query(value = "Select follower_id from follow_user where user_id = :id", nativeQuery = true)
    List<Long> getIdFollowers(@Param("id") Long id);

    @Query(value = "Select * from follow_user where follower_id = :id", nativeQuery = true)
    List<FollowUser> getAllFollowedEmployer(@Param("id") Long id);

    @Query(value = "Select count(*) from follow_user where user_id = :id", nativeQuery = true)
    Long countFollowUserById(@Param("id") Long id);

    Optional<FollowUser> findByUserAndFollower(User user, User follower);

}
