package com.anescode.clients.notification;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerEmail,
        String toCustomerName,
        String message
) {
}