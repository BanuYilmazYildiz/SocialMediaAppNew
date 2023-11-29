package com.banu.manager;

import com.banu.dto.request.ActivateStatusRequestDto;
import com.banu.dto.request.UserCreateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.banu.constants.RestApi.*;


@FeignClient(url = "http://localhost:7071/api/v1/user", name = "auth-userprofile")
public interface UserManager {

    @PostMapping("/create")
    public ResponseEntity<Boolean> createUser(@RequestBody UserCreateRequestDto dto);

//    @GetMapping(ACTIVATESTATUS+"/{authId}")
//    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authId);

    @PostMapping(ACTIVATESTATUS)
    public ResponseEntity<Boolean> activateStatus(@RequestHeader(value = "Authorization") String token);

    @PostMapping(ACTIVATESTATUS+"2")
    public ResponseEntity<Boolean> activateStatus2(@RequestBody ActivateStatusRequestDto dto);

    @DeleteMapping(DELETEBYID)
    public ResponseEntity<Boolean> delete(@RequestParam Long authId);

}
