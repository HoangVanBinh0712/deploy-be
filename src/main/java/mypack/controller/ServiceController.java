package mypack.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mypack.payload.service.ServiceCreateRequest;
import mypack.payload.service.ServiceUpdateRequest;
import mypack.service.PackageService;
import mypack.service.UserDetailsCustom;

@RestController
@RequestMapping("api")
public class ServiceController {

	@Autowired
	PackageService packageService;

	@GetMapping("service")
	public ResponseEntity<?> getListService() {
		return ResponseEntity.ok(packageService.getListService());
	}

	@GetMapping("/admin/service")
	public ResponseEntity<?> getListService(@RequestParam(name = "active", required = false) Boolean active) {
		if (active == null)
			return ResponseEntity.ok(packageService.getListService());
		return ResponseEntity.ok(packageService.getListService(active));
	}

	@PostMapping("/admin/service")
	public ResponseEntity<?> createService(@RequestBody @Valid ServiceCreateRequest request) {
		return ResponseEntity.ok(packageService.create(request));
	}

	@PutMapping("/admin/service")
	public ResponseEntity<?> updateService(@RequestBody @Valid ServiceUpdateRequest request) {
		return ResponseEntity.ok(packageService.update(request));
	}

	@DeleteMapping("employer/service")
	public ResponseEntity<?> deleteService(@AuthenticationPrincipal UserDetailsCustom emp) {
		return ResponseEntity.ok(packageService.deleteUserService(emp.getEmail()));
	}

}
