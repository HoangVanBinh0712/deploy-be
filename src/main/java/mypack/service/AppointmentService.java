package mypack.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypack.controller.exception.CommonRuntimeException;
import mypack.dto.AppointmentDTO;
import mypack.model.Appointment;
import mypack.model.User;
import mypack.payload.BaseResponse;
import mypack.payload.DataResponse;
import mypack.payload.appoint.AppointRequest;
import mypack.payload.appoint.AppointUpdateRequest;
import mypack.repository.AppointmentRepository;
import mypack.repository.UserRepository;
import mypack.utility.datatype.ERole;

@Service
public class AppointmentService {

	@Autowired
	AppointmentRepository appointRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	ModelMapper mapper;

	@Autowired
	SendEmailService sendMail;

	@Autowired
	NotificationService notiService;

	public BaseResponse denyAppointment(Long userId, Long appointId) {
		Appointment ap = appointRepo.findById(appointId)
				.orElseThrow(() -> new CommonRuntimeException("Appointment not found !"));
		if (ap.getUser().getId() != userId)
			throw new CommonRuntimeException("Appointment not found !");
		ap.setDeny(true);

		appointRepo.save(ap);
		// Add notification (both) and send mail for employer ?
		notiService.addNotification(ap.getEmployer().getId(), ap.getUser().getName() + " has denied your appointment !",
				null);
		if (ap.getEmployer().getEmailConfirm())
			sendMail.sendMailForNotification(new String[] { ap.getEmployer().getEmail() },
					ap.getUser().getName() + " has denied your appointment !");
		return new BaseResponse(true, "Deny appointment success !");
	}

	public DataResponse<AppointmentDTO> createAppointment(AppointRequest request) {
		if (request.getStartTime().before(new Date()))
			throw new CommonRuntimeException("Start time must be in future !");
		if (!isAvailableTime(request.getStartTime(), request.getEmpId()))
			throw new CommonRuntimeException("Already have a meeting at " + request.getStartTime().toString());

		User emp = userRepo.findByIdAndRole(request.getEmpId(), ERole.ROLE_EMPLOYER)
				.orElseThrow(() -> new CommonRuntimeException("Employer not found !"));
		User user = userRepo.findByIdAndRole(request.getUserId(), ERole.ROLE_USER)
				.orElseThrow(() -> new CommonRuntimeException("User not found !"));
		Appointment ap = new Appointment();
		ap.setEmployer(emp);
		ap.setUser(user);
		ap.setNote(request.getNote());
		ap.setStartTime(request.getStartTime());
		ap.setDeny(false);
		ap.setComplete(false);
		ap = appointRepo.save(ap);
		// Add notification (both) and send mail for user ?
		// user
		notiService.addNotification(ap.getUser().getId(),
				ap.getEmployer().getName() + " has created an appointment with you !", null);
		// emp
		notiService.addNotification(ap.getEmployer().getId(),
				"You have created an appointment with " + ap.getUser().getName() + " !", null);
		if (ap.getUser().getEmailConfirm())
			sendMail.sendMailForNotification(new String[] { ap.getUser().getEmail() },
					ap.getEmployer().getName() + " has created an appointment with you !");
		return new DataResponse<AppointmentDTO>(true, "Create appointment success !",
				mapper.map(ap, AppointmentDTO.class));
	}

	public Boolean isAvailableTime(Date date, Long empId) {
		if (appointRepo.checkAvailableTime(empId, date) != null)
			return false;
		return true;
	}

	public DataResponse<AppointmentDTO> updateAppointment(Long empId, Long appointId, AppointUpdateRequest request) {
		if (request.getStartTime().before(new Date()))
			throw new CommonRuntimeException("Start time must be in future !");
		Appointment ap = appointRepo.findById(appointId)
				.orElseThrow(() -> new CommonRuntimeException("Appointment not found !"));
		if (ap.getEmployer().getId() != empId)
			throw new CommonRuntimeException("Appointment not found !");

		ap.setNote(request.getNote());
		if (ap.getStartTime() != request.getStartTime()) {
			if (!isAvailableTime(request.getStartTime(), empId))
				throw new CommonRuntimeException("Already have a meeting at " + request.getStartTime().toString());
			ap.setStartTime(request.getStartTime());
		}
		ap = appointRepo.save(ap);
		// Add notification (both) and send mail for user ?
		// user
		notiService.addNotification(ap.getUser().getId(),
				ap.getEmployer().getName() + " has updated an appointment with you !", null);
		// emp
		notiService.addNotification(ap.getEmployer().getId(),
				"You have updated an appointment with " + ap.getUser().getName() + " !", null);

		return new DataResponse<AppointmentDTO>(true, "Update appointment success !",
				mapper.map(ap, AppointmentDTO.class));

	}

	public BaseResponse deleteAppointment(Long empId, Long appointId) {
		Appointment ap = appointRepo.findById(appointId)
				.orElseThrow(() -> new CommonRuntimeException("Appointment not found !"));
		if (ap.getEmployer().getId() != empId)
			throw new CommonRuntimeException("Appointment not found !");

		appointRepo.delete(ap);
		// Add notification (both) and send mail for user if appoint day has not came
		// yet?
		if (ap.getStartTime().after(new Date())) {
			// user
			notiService.addNotification(ap.getUser().getId(),
					ap.getEmployer().getName() + " has cancelled an appointment with you !", null);
			// emp
			notiService.addNotification(ap.getEmployer().getId(),
					"You have cancelled an appointment with " + ap.getUser().getName() + " !", null);
			if (ap.getUser().getEmailConfirm())
				sendMail.sendMailForNotification(new String[] { ap.getUser().getEmail() },
						ap.getEmployer().getName() + " has cancelled an appointment with you !");
		}

		return new BaseResponse(true, "Delete appointment success !");

	}

	public List<AppointmentDTO> getListAppointment(Long userId, Long employerId, Date startTime, Boolean deny,
			Boolean complete) {
		List<Appointment> data = appointRepo.getAppointments(userId, employerId, startTime, deny, complete);
		return data.stream().map(x -> mapper.map(x, AppointmentDTO.class)).toList();

	}
}
