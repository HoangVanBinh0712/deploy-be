package mypack.repository.custom;

import java.util.Date;
import java.util.List;

import mypack.model.Appointment;

public interface AppointmentCustomRepository {
	public List<Appointment> getAppointments(Long userId, Long employerId, Date startTime, Boolean deny,
			Boolean complete);
}
