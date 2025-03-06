package com.grazielleanaia.sending_email2.controller;


import com.grazielleanaia.sending_email2.business.EmailService;
import com.grazielleanaia.sending_email2.business.dto.TasksDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor

public class EmailController {
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> sendEmail(@RequestBody TasksDTO tasksDTO) {
        emailService.sendEmail(tasksDTO);
        return ResponseEntity.ok().build();
    }
}
