package mypack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mypack.model.Profile;
import mypack.model.pk.ProfilePK;
import mypack.repository.custom.ProfileSearchCustomRepository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, ProfilePK>, ProfileSearchCustomRepository {
	@Query(value = "select * from profile where user_id = :user_id", nativeQuery = true)
	List<Profile> findByUserId(@Param("user_id") Long userId);
}
