package com.banu.controller;

import com.banu.dto.request.ActivateStatusRequestDto;
import com.banu.dto.request.UserCreateRequestDto;
import com.banu.dto.request.UserProfileUpdateRequestDto;
import com.banu.repository.entity.UserProfile;
import com.banu.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.banu.constants.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createUser(@RequestBody UserCreateRequestDto dto) {
        return ResponseEntity.ok(userProfileService.createUser(dto));
    }

    //    @GetMapping(ACTIVATESTATUS+"/{authId}")
//    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authId){
//        return ResponseEntity.ok(userProfileService.activateStatus(authId));
//    }
    @PostMapping(ACTIVATESTATUS)
    public ResponseEntity<Boolean> activateStatus(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(userProfileService.activateStatus(token));
    }

    @PostMapping(ACTIVATESTATUS2)
    public ResponseEntity<Boolean> activateStatus2(@RequestBody ActivateStatusRequestDto dto) {
        return ResponseEntity.ok(userProfileService.activateStatus2(dto));
    }

    @PostMapping(value = UPDATE)
    public ResponseEntity<Boolean> update(@RequestBody UserProfileUpdateRequestDto dto) {
        return ResponseEntity.ok(userProfileService.update(dto));
    }

    @DeleteMapping(DELETEBYID)
    public ResponseEntity<Boolean> delete(@RequestParam Long authId) {
        return ResponseEntity.ok(userProfileService.delete(authId));
    }

    @GetMapping(FINDALL)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<UserProfile>> findAll() {
        return ResponseEntity.ok(userProfileService.findAll());
    }


    @GetMapping("/find_by_username")
    public ResponseEntity<UserProfile> findByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userProfileService.findByUsername(username));
    }

   // @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(FINDBYROLE)
    public ResponseEntity<List<UserProfile>> findByRole(@RequestHeader(value = "Authorization") String token, @RequestParam String role) {
        return ResponseEntity.ok(userProfileService.findByRole(token,role));
    }
}
