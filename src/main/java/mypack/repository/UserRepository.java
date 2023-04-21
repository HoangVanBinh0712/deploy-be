package mypack.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mypack.model.User;
import mypack.payload.statistic.StatisticForCount;
import mypack.repository.custom.UserSerachCustomRepository;
import mypack.utility.datatype.ERole;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserSerachCustomRepository {
	Optional<User> findByIdAndRole(Long Id, ERole role);

	Optional<User> findByEmail(String email);

	Optional<User> findByEmailAndRole(String email, ERole role);

	Boolean existsByPhone(String phone);

	Boolean existsByEmail(String email);

	@Modifying
	@Transactional
	@Query(value = "update user set avatar_id = null where id = :id", nativeQuery = true)
	void updateBeforeDeleteImage(@Param("id") Long id);
	
	@Modifying
	@Transactional
	@Query(value = "update user set cover_id = null where id = :id", nativeQuery = true)
	void updateBeforeDeleteCover(@Param("id") Long id);

	@Query(value = "SELECT * FROM user WHERE role = 'ROLE_EMPLOYER' ORDER BY RAND() LIMIT 10", nativeQuery = true)
	List<User> getListCompany();

	@Query(value = "SELECT us.* FROM user as us, (select user_id, count(*) as view_count from view_page group by user_id) as vc WHERE us.role = 'ROLE_EMPLOYER' and vc.user_id = us.id order by vc.view_count desc limit 9", nativeQuery = true)
	List<User> getHighLightCompany();
	
	@Query(value = "SELECT email FROM user WHERE id in(:listId) and email_confirm = true", nativeQuery = true)
	String[] getListEmailUser(@Param("listId") List<Long> lstId);

	@Query(value = "Select new mypack.payload.statistic.StatisticForCount(Month(c.createDate) as month, count(c) as value)  from User c where YEAR(c.createDate) = :year group by Month(c.createDate) order by Month(c.createDate) asc")
	List<StatisticForCount> getCountNewUsers(@Param("year") Integer year);

	@Query(value = "select * from user where user.service_expiration_date >= :start and user.service_expiration_date <= :end and role = 1", nativeQuery = true)
	List<User> getEmployerNearToEndService(@Param("start") Date start, @Param("end") Date end);
}
