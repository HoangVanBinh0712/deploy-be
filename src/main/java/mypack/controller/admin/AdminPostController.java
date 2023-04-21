package mypack.controller.admin;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mypack.service.AdminPostService;
import mypack.service.PostSearchService;
import mypack.service.UserDetailsCustom;
import mypack.utility.ModelSorting;
import mypack.utility.Page;
import mypack.utility.datatype.ECurrency;
import mypack.utility.datatype.EExperience;
import mypack.utility.datatype.EGender;
import mypack.utility.datatype.EMethod;
import mypack.utility.datatype.EPosition;
import mypack.utility.datatype.EStatus;

@RestController
@RequestMapping("api/admin/post")
public class AdminPostController {

	@Autowired
	AdminPostService adminPostService;

	@Autowired
	PostSearchService postSearchService;

	@GetMapping("{postId}")
	public ResponseEntity<?> getDetail(@PathVariable("postId") Long postId) {
		return ResponseEntity.ok(adminPostService.getPostDetail(postId));
	}

	@GetMapping
	public ResponseEntity<?> getPost(@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "recruit", required = false) Long recruit,
			@RequestParam(name = "salary", required = false) Long salary,
			@RequestParam(name = "method", required = false) EMethod method,
			@RequestParam(name = "position", required = false) EPosition position,
			@RequestParam(name = "experience", required = false) EExperience experience,
			@RequestParam(name = "gender", required = false) EGender gender,
			@RequestParam(name = "currency", required = false) ECurrency currency,
			@RequestParam(name = "authorId", required = false) Long authorId,
			@RequestParam(name = "industryId", required = false) Long industryId,
			@RequestParam(name = "cityId", required = false) Long cityId,
			@RequestParam(name = "expirationDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date expirationDate,
			@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(name = "serviceId", required = false) Long serviceId,
			@RequestParam(name = "status", required = false) EStatus status,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "limit", required = false) Integer limit,
			@RequestParam(required = false) Integer sortBy,
			@RequestParam(required = false) Boolean sortDescending)
			throws ParseException {

		Long count = postSearchService.getCountBeforSearch(keyword, recruit, salary, method, position, experience,
				gender, currency, authorId, industryId, cityId, status, expirationDate, startDate,
				serviceId);

		return ResponseEntity.ok(postSearchService.search(keyword, recruit, salary, method, position, experience,
				gender, currency, authorId, industryId, cityId, status, expirationDate, startDate,
				serviceId, new Page(page, limit, count.intValue(), ModelSorting.getPostSort(sortBy, sortDescending))));
	}

	@PutMapping("/accept/{postId}")
	public ResponseEntity<?> accept(@AuthenticationPrincipal UserDetailsCustom admin,
			@PathVariable("postId") Long postId) {
		return ResponseEntity.ok(adminPostService.acceptPost(admin.getEmail(),
				postId));
	}

	@PutMapping("/unaccept/{postId}")
	public ResponseEntity<?> unAccept(@AuthenticationPrincipal UserDetailsCustom admin,
			@PathVariable("postId") Long postId) {
		return ResponseEntity.ok(adminPostService.unAcceptPost(admin.getEmail(), postId));
	}

	// @GetMapping("statistic")
	// public ResponseEntity<?> statistc() {
	// return ResponseEntity.ok(adminPostService.getStatistic());
	// }
}
