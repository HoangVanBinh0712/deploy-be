package mypack.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mypack.model.Notification;
import mypack.model.User;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUser(User user, Pageable pageable);

    Long countByUser(User user);

    @Transactional
    @Modifying
    @Query(value = "delete from notification where user_id = :userId", nativeQuery = true)
    void deleteALlUserNotice(@Param("userId") Long userId);

    @Query(value = "select * from Notification where date < :date", nativeQuery = true)
    List<Notification> getOldNotice(@Param("date") Date date);

    @Transactional
    @Modifying
    @Query(value = "delete from Notification where date < :date", nativeQuery = true)
    void deleteOldNotice(@Param("date") Date date);
}
