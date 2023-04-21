package mypack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mypack.service.BannerService;
import mypack.service.UserSearchService;
import mypack.service.UserService;
import mypack.utility.Page;
import mypack.utility.datatype.ERole;

@RestController
@RequestMapping("/api/admin/manage")
public class AdminManageController {
    @Autowired
    UserService userService;

    @Autowired
    UserSearchService userSearchService;

    @Autowired
    BannerService bannerService;

    @PutMapping("account")
    public ResponseEntity<?> manageAccount(@RequestParam("userId") Long userId,
            @RequestParam("active") Boolean status) {
        return ResponseEntity.ok(userService.accountManage(userId, status));
    }

    @GetMapping("account")
    public ResponseEntity<?> searchUser(@RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "industryId", required = false) Long industryId,
            @RequestParam(name = "cityId", required = false) Long cityId,
            @RequestParam(name = "role", required = false) ERole role,
            @RequestParam(name = "status", required = false) Boolean status,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "limit", required = false) Integer limit) {
        int count = userSearchService.getCountBeforSearch(email, name, phone, role, status, industryId, cityId)
                .intValue();
        return ResponseEntity.ok(userSearchService.search(email, name, phone, role, status, industryId, cityId,
                new Page(page, limit, count)));
    }

    @PutMapping(value = "banner/{bannerId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateBanner(@PathVariable("bannerId") Long bannerId,
            @RequestPart("image") MultipartFile avatar) {
        return ResponseEntity.ok(bannerService.updateBanner(bannerId, avatar));
    }

    @PostMapping(value = "banner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addBanner(@RequestPart("image") MultipartFile avatar) {
        return ResponseEntity.ok(bannerService.addBanner(avatar));
    }

    @DeleteMapping("banner/{bannerId}")
    public ResponseEntity<?> deleteBanner(@PathVariable("bannerId") Long bannerId) {
        return ResponseEntity.ok(bannerService.deleteBanner(bannerId));
    }

    @GetMapping("banner")
    public ResponseEntity<?> getBanner() {
        return ResponseEntity.ok(bannerService.getBanners());
    }
}
