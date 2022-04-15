package com.ssaffron.business.api.controller;

import com.ssaffron.business.api.dto.LoginRequestDto;
import com.ssaffron.business.api.dto.UserDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class UserController {

    @PostMapping("/signup")
    public ResponseEntity registerUser(@RequestBody UserDto userDto){
        log.info("registerUser in "+userDto.getUserEmail());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto){
        //로그인 할 때, JWT를 헤더에 넣어서 반환
        log.info("login in "+loginRequestDto.getUserEmail());
        UserDto userDto = new UserDto();
        HttpHeaders headers = new HttpHeaders();
//        headers.add(JWT에 관한 내용)
        return new ResponseEntity<>(userDto, headers, HttpStatus.OK);
    }

    @GetMapping("/{useremail}")
    public ResponseEntity<UserDto> getUser(@PathVariable("useremail") String userEmail){
        UserDto userDto = new UserDto();
        userDto.setUserEmail(userEmail);
        log.info("getUser in "+userEmail);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PutMapping("{useremail}")
    public ResponseEntity updateUser(@PathVariable("useremail") String userEmail, @RequestBody UserDto userDto){
        UserDto responseDto = new UserDto();
        responseDto.setUserEmail(userEmail);
        log.info("updateUser in "+userEmail);
        log.info("updateUser in "+userDto.toString());
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{useremail}")
    public ResponseEntity deleteUser(@PathVariable("useremail") String userEmail){
        log.info("deleteUser in "+userEmail);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/refresh/{useremail}")
    public ResponseEntity refreshToken(@PathVariable("useremail") String userEmail){
        HttpHeaders headers = new HttpHeaders();
        log.info("refreshToken in "+userEmail);
        return new ResponseEntity(headers, HttpStatus.OK);
    }


}
