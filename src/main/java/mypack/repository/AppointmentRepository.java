package mypack.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mypack.model.Appointment;
import mypack.model.User;
import mypack.repository.custom.AppointmentCustomRepository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>, AppointmentCustomRepository {

	List<Appointment> findByUser(User user);

	List<Appointment> findByEmployer(User employer);
	
	@Query(value = "select * from appointment where employer=:empId and start_time= :date limit 1", nativeQuery= true)
	Appointment checkAvailableTime(@Param("empId")Long empId,@Param("date")Date date);
	

}
