package mypack.controller;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mypack.payload.RefreshTokenResponse;
import mypack.utility.jwt.JwtUtils;

@RequestMapping("api/refreshtoken")
@RestController
public class RefreshTokenController {

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping()
    public ResponseEntity<?> getAccessToken(HttpServletRequest req) {

        try {
            String accessToken = req.getHeader("Authorization");
            String refreshToken = req.getHeader("refresh-token");
            if (accessToken == null || accessToken.length() == 0 || refreshToken == null || refreshToken.length() == 0)
                return ResponseEntity.status(400).body("No refresh token found !");
            accessToken = jwtUtils.parseJwt(accessToken);
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String[] parts = accessToken.split("\\."); // Splitting header, payload and signature
            JSONObject json = new JSONObject(new String(decoder.decode(parts[1])));

            String userEmailFromRefreshToken = jwtUtils.getUsernameFromJwtRefreshToken(refreshToken);
            if (!json.get("sub").toString().equals(userEmailFromRefreshToken))
                return ResponseEntity.status(400).body("Invalid refresh token!");

            String newAccessToken = jwtUtils.generateJwtToken(userEmailFromRefreshToken);

            String newRefreshToken = jwtUtils.generateJwtRefreshToken(userEmailFromRefreshToken);
            // send both new access token and refresh token
            return ResponseEntity.ok(new RefreshTokenResponse(newAccessToken, newRefreshToken));

        } catch (Exception ex) {
            // jwt expired or some thing
            return ResponseEntity.status(400).body("Please login again !");
        }

    }

}