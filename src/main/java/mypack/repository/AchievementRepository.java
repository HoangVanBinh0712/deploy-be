package mypack.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mypack.model.Achievement;
import mypack.model.User;
import mypack.utility.datatype.EAchievementType;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    List<Achievement> findByUser(User user);

    List<Achievement> findByUserAndType(User user, EAchievementType type);

    @Modifying
    @Transactional
    @Query(value = "update achievement set image_id = null where id = :id", nativeQuery = true)
    void updateBeforeDeleteImage(@Param("id") Long id);
}
