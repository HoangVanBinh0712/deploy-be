package mypack.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mypack.model.CVViewed;
import mypack.model.pk.CVViewedPK;

@Repository
public interface CVViewedRepository extends JpaRepository<CVViewed, CVViewedPK> {

    @Transactional
    @Modifying
    @Query(value = "delete from cvviewed where user_id = :userId", nativeQuery = true)
    void deleteBeforeDeleteProfile(@Param("userId") Long userId);

    @Query(value = "select count(*) from cvviewed where media_id = :mediaId and user_id = :userId", nativeQuery = true)
    Long countViewedForCV(@Param("mediaId") Long mediaId, @Param("userId") Long userId);

    @Query(value = "select * from cvviewed where media_id = :mediaId and user_id = :userId", nativeQuery = true)
    List<CVViewed> getEmployerViewedCV(@Param("mediaId") Long mediaId, @Param("userId") Long userId);
}
