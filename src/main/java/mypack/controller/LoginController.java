package mypack.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mypack.dto.LoginRequest;
import mypack.payload.auth.EmployerRegisterRequest;
import mypack.payload.auth.JobseekerRegisterRequest;
import mypack.service.UserService;

@RestController
public class LoginController {

	@Autowired
	UserService userService;

	@PostMapping("api/register-jobseeker")
	public ResponseEntity<?> jobseekerRegister(@RequestBody @Valid JobseekerRegisterRequest request) {
		return ResponseEntity.ok(userService.jobseekerRegister(request));
	}

	@PostMapping("api/register-employer")
	public ResponseEntity<?> employerRegister(@RequestBody @Valid EmployerRegisterRequest request) {
		return ResponseEntity.ok(userService.employerRegister(request));
	}

	@PostMapping("api/login")
	public ResponseEntity<?> register(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(userService.login(request));
	}

}
