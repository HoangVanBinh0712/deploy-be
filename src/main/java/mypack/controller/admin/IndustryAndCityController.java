package mypack.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mypack.dto.CityDTO;
import mypack.dto.IndustryDTO;
import mypack.service.CityService;
import mypack.service.IndustryService;

@RestController
@RequestMapping("api")
public class IndustryAndCityController {

	@Autowired
	IndustryService industryService;

	@Autowired
	CityService cityService;

	@GetMapping("city")
	public ResponseEntity<?> getCity() {
		return ResponseEntity.ok(cityService.getAll());
	}

	@PostMapping("admin/city")
	public ResponseEntity<?> createCity(@RequestBody @Valid CityDTO request) {
		return ResponseEntity.ok(cityService.create(request));
	}

	@PutMapping("admin/city")
	public ResponseEntity<?> updateCity(@RequestBody @Valid CityDTO request) {
		return ResponseEntity.ok(cityService.update(request));
	}

	@DeleteMapping("admin/city/{id}")
	public ResponseEntity<?> deleteCity(@PathVariable(name = "id", required = true) Long id) {
		return ResponseEntity.ok(cityService.delete(id));
	}

	// Industry
	@PostMapping("admin/industry")
	public ResponseEntity<?> createIndustry(@RequestBody @Valid IndustryDTO request) {
		return ResponseEntity.ok(industryService.create(request));
	}

	@PutMapping("admin/industry")
	public ResponseEntity<?> updateIndustry(@RequestBody @Valid IndustryDTO request) {
		return ResponseEntity.ok(industryService.update(request));
	}

	@DeleteMapping("admin/industry/{id}")
	public ResponseEntity<?> deleteIndustry(@PathVariable(name = "id", required = true) Long id) {
		return ResponseEntity.ok(industryService.delete(id));
	}

	@GetMapping("industry")
	public ResponseEntity<?> getIndustry() {
		return ResponseEntity.ok(industryService.getAll());
	}
}
