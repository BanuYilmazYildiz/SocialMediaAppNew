package com.banu.controller;


import com.banu.dto.request.ActivationRequestDto;
import com.banu.dto.request.LoginRequestDto;
import com.banu.dto.request.RegisterRequestDto;
import com.banu.dto.request.UpdateEmailOrUsernameRequestDto;
import com.banu.dto.response.RegisterResponseDto;
import com.banu.repository.entity.Auth;
import com.banu.service.AuthService;
import com.banu.utility.JwtTokenManager;
import com.banu.utility.enums.ERole;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.banu.constants.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {

    private final AuthService authService;
    private final JwtTokenManager tokenManager;
    private final CacheManager cacheManager;


    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping(REGISTER + "2")
    public ResponseEntity<RegisterResponseDto> registerWithRabbitMq(@RequestBody @Valid RegisterRequestDto dto) {
        return ResponseEntity.ok(authService.registerWithRabbitMQ(dto));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping(ACTIVATESTATUS)
    public ResponseEntity<Boolean> activateStatus(ActivationRequestDto dto) {
        return ResponseEntity.ok(authService.activateStatus(dto));
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping(FINDALL)
    public ResponseEntity<List<Auth>> findAll() {
        return ResponseEntity.ok(authService.findAll());
    }


    @GetMapping("/create_token")
    public ResponseEntity<String> createToken(Long id, ERole role) {
        return ResponseEntity.ok(tokenManager.createToken(id, role).get());
    }

    @GetMapping("/create_token2")
    public ResponseEntity<String> createToken2(Long id) {
        return ResponseEntity.ok(tokenManager.createToken(id).get());
    }

    @GetMapping("/get_id_from_token")
    public ResponseEntity<Long> getIdFromToken(String token) {
        return ResponseEntity.ok(tokenManager.getIdFromToken(token).get());
    }

    @GetMapping("/get_role_from_token")
    public ResponseEntity<String> getRoleFromToken(String token) {
        return ResponseEntity.ok(tokenManager.getRoleFromToken(token).get());
    }


    @PutMapping("/update_email_or_username")
    public ResponseEntity<Boolean> updateEmailOrUsername(@RequestBody UpdateEmailOrUsernameRequestDto dto) {
        return ResponseEntity.ok(authService.updateEmailOrUsername(dto));
    }

    @DeleteMapping(DELETEBYID)
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(authService.delete(id));
    }

    @DeleteMapping(DELETEBYTOKEN)
    public ResponseEntity<Boolean> deleteByToken(String token) {
        return ResponseEntity.ok(authService.deleteByToken(token));
    }


    @GetMapping("/redis")
    @Cacheable(value = "redisexample")
    public String redisExample(@RequestParam String value) {
        try {
            Thread.sleep(2000);
            return value;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @GetMapping("/redisdelete")
    @CacheEvict(cacheNames = "redisexample", allEntries = true)
    public void redisDelete() {

    }

    @GetMapping("/redisdelete2")
    public Boolean redisDelete2() {
        try {
//            cacheManager.getCache("redisexample").clear(); // "redisexample" etiketli butun cache'leri temizler.
            cacheManager.getCache("redisexample").evict("alperen");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

   // @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(FINDBYROLE)
    public ResponseEntity<List<Long>> findByRole(@RequestHeader(value = "Authorization") String token, @RequestParam String role) {
        return ResponseEntity.ok(authService.findByRole(role));
    }


}
