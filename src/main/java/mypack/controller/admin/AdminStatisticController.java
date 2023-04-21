package mypack.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mypack.service.AdminStatisticService;
import mypack.service.ReportService;
import mypack.utility.datatype.EROrderStatus;

@RestController
@RequestMapping("/api/admin/statistic")
public class AdminStatisticController {
    @Autowired
    AdminStatisticService service;

    @Autowired
    ReportService reportService;
    
    @GetMapping("cv-submit")
    public ResponseEntity<?> getCVSubmitStatistic(@RequestParam(name = "year", required = true) Integer year) {

        return ResponseEntity.ok(service.getCVSubmitCountStatistc(year));

    }

    @GetMapping("post")
    public ResponseEntity<?> getViewPostStatistic(@RequestParam(name = "year", required = true) Integer year) {

        return ResponseEntity.ok(service.getPostCountStatistc(year));

    }

//    @GetMapping("comments")
//    public ResponseEntity<?> getCommentStatistic(@RequestParam(name = "year", required = true) Integer year) {
//
//        return ResponseEntity.ok(service.getCountComments(year));
//
//    }

    @GetMapping("user")
    public ResponseEntity<?> getUserStatistic(@RequestParam(name = "year", required = true) Integer year) {

        return ResponseEntity.ok(service.getNewUserRegistered(year));

    }

    @GetMapping("revenue")
    public ResponseEntity<?> getTotalRevenue(@RequestParam(name = "year", required = true) Integer year,
            @RequestParam(name = "status", required = true) EROrderStatus status) {

        return ResponseEntity.ok(service.getTotalOrder(year, status));
    }
    
    @GetMapping("report")
    public ResponseEntity<?> getReport(@RequestParam(name = "year", required = true) Integer year,
            @RequestParam(name = "handle", required = false) Boolean handle) {

        return ResponseEntity.ok(reportService.getStatisticReportByStatus(year,handle));
    }
}
