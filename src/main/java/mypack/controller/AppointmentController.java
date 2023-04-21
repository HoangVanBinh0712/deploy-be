package mypack.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mypack.payload.appoint.AppointRequest;
import mypack.payload.appoint.AppointUpdateRequest;
import mypack.service.AppointmentService;
import mypack.service.UserDetailsCustom;

@RestController
@RequestMapping("api")
public class AppointmentController {

	@Autowired
	AppointmentService serivce;

	@GetMapping("appointment")
	public ResponseEntity<?> getPost(@RequestParam(name = "userId", required = false) Long userId,
			@RequestParam(name = "employerId", required = false) Long employerId,
			@RequestParam(name = "startTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm") Date startDate,
			@RequestParam(name = "deny", required = false) Boolean deny,
			@RequestParam(name = "complete", required = false) Boolean complete) {
		return ResponseEntity.ok(serivce.getListAppointment(userId, employerId, startDate, deny, complete));
	}

	@PutMapping("employer/appointment/{id}")
	public ResponseEntity<?> updateAppointment(@AuthenticationPrincipal UserDetailsCustom emp,
			@PathVariable("id") Long appointId, @RequestBody @Valid AppointUpdateRequest request) {
		return ResponseEntity.ok(serivce.updateAppointment(emp.getId(), appointId, request));
	}

	@PostMapping("employer/appointment")
	public ResponseEntity<?> updateAppointment(@RequestBody @Valid AppointRequest request) {
		return ResponseEntity.ok(serivce.createAppointment(request));
	}

	@DeleteMapping("employer/appointment/{id}")
	public ResponseEntity<?> updateAppointment(@AuthenticationPrincipal UserDetailsCustom emp,
			@PathVariable("id") Long appointId) {
		return ResponseEntity.ok(serivce.deleteAppointment(emp.getId(), appointId));
	}

	@PutMapping("user/appointment/{id}")
	public ResponseEntity<?> denyAppointment(@AuthenticationPrincipal UserDetailsCustom user,
			@PathVariable("id") Long appointId) {
		return ResponseEntity.ok(serivce.denyAppointment(user.getId(), appointId));
	}
}
