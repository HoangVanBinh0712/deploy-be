package mypack.controller.public_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mypack.service.UserService;

@RestController
@RequestMapping("api/public")
public class UserInformationAPI {
    @Autowired
    private UserService userService;

    @GetMapping("user/{id}")
    public ResponseEntity<?> getUserInfor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getJSKProfile(id));
    }

}
