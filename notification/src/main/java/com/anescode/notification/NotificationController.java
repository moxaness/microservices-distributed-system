package com.anescode.notification;

import com.anescode.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {
    private final NotificationService notificationService;
    @RequestMapping
    public void sendNotification(@RequestBody NotificationRequest notificationRequest){

        log.info("new notification... {}", notificationRequest);
        notificationService.send(notificationRequest);
    }
}
