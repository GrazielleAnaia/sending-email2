package com.grazielleanaia.sending_email2.business.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.grazielleanaia.sending_email2.infrastructure.enums.NotificationStatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor



@Builder

public class TasksDTO {

    private String id;

    private String customerEmail;

    private String taskName;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime eventDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime changeDate;

    private NotificationStatusEnum notificationStatusEnum;
}
