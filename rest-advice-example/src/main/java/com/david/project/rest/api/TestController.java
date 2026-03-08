package com.david.project.rest.api;

import com.david.project.constant.ErrorCode;
import com.david.project.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/exception")
    public ResponseEntity<?> testException(@RequestParam String errorCode,
                                           @RequestParam boolean isThrow) {
        testService.throwErrorCodedException(isThrow, ErrorCode.valueOf(errorCode));
        return ResponseEntity.status(HttpStatus.OK).body("No exception thrown");
    }
}
