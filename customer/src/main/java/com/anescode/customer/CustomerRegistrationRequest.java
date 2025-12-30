package com.anescode.customer;

public record CustomerRegistrationRequest(
        String firstname,
        String lastname,
        String email) {

}
