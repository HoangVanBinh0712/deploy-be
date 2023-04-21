package mypack.controller.employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mypack.payload.BaseResponse;
import mypack.repository.UserRepository;
import mypack.service.CVSearchAndViewedService;
import mypack.service.UserDetailsCustom;
import mypack.utility.datatype.EExperience;
import mypack.utility.datatype.EMethod;
import mypack.utility.datatype.EPosition;

@RestController
@RequestMapping("api/employer/profile-search")
public class ProfileSearchController {

	@Autowired
	CVSearchAndViewedService cvSearchService;

	@Autowired
	UserRepository userRepository;

	@GetMapping
	public ResponseEntity<?> searchProfile(@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "method", required = false) EMethod method,
			@RequestParam(name = "position", required = false) EPosition position,
			@RequestParam(name = "experience", required = false) EExperience experience,
			@RequestParam(name = "industryId", required = false) Long industryId,
			@RequestParam(name = "cityId", required = false) Long cityId,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "limit", required = false) Integer limit,
			@RequestParam(required = false) Integer sortBy, @RequestParam(required = false) Boolean sortDescending,
			@AuthenticationPrincipal UserDetailsCustom emp) {
		if (cvSearchService.isEligible(emp.getId())) {

			return ResponseEntity.ok(cvSearchService.searchProfile(keyword, method, position, experience, industryId,
					cityId, page, limit, sortBy, sortDescending));
		}
		return ResponseEntity
				.ok(new BaseResponse(false, "Your current package is not posible to access this feature !"));

	}

	@PostMapping
	public ResponseEntity<?> addViewed(@AuthenticationPrincipal UserDetailsCustom emp,
			@RequestParam("userId") Long userId, @RequestParam("mediaId") Long mediaId) {
		return ResponseEntity.ok(cvSearchService.addViewed(emp.getEmail(), mediaId, userId));

	}
}
