package mypack.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mypack.payload.report.ReportRequest;
import mypack.service.ReportService;

@RestController
@RequestMapping("api")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@PostMapping("report")
	public ResponseEntity<?> submit(@RequestBody @Valid ReportRequest request) {
		return ResponseEntity.ok(reportService.create(request));
	}

	@GetMapping("admin/report")
	public ResponseEntity<?> get(@RequestParam(name = "postId", required = false) Long postId,
			@RequestParam(name = "handle", required = false) Boolean handle,
			@RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam(name = "handleDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date handleDate,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "limit", required = false) Integer limit,
			@RequestParam(required = false) Integer sortBy, @RequestParam(required = false) Boolean sortDescending) {
		return ResponseEntity
				.ok(reportService.searchReport(postId, handle, date, handleDate, page, limit, sortBy, sortDescending));
	}

	@PutMapping("admin/report")
	public ResponseEntity<?> get(@RequestParam(name = "reportId", required = true) Long reportId,
			@RequestParam(name = "handle", required = true) Boolean handle) {
		return ResponseEntity.ok(reportService.updateStatus(reportId, handle));
	}

	@DeleteMapping("admin/report/{reportId}")
	public ResponseEntity<?> get(@PathVariable("reportId") Long reportId) {
		return ResponseEntity.ok(reportService.deleteReport(reportId));
	}
}
