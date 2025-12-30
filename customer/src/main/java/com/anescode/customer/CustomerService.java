package com.anescode.customer;

import com.anescode.amqp.RabbitMQMessageProducer;
import com.anescode.clients.fraud.FraudCheckResponse;
import com.anescode.clients.fraud.FraudClient;
import com.anescode.clients.notification.NotificationClient;
import com.anescode.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.management.MBeanServerDelegate;

@Service
@AllArgsConstructor
public class CustomerService {
    public final CustomerRepository customerRepository;
    //private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCostumer(CustomerRegistrationRequest request) throws IllegalAccessException {
        Customer customer = Customer.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .build();

        // todo : check if email valid
        // todo : check if email no taken
        customerRepository.saveAndFlush(customer);
        // todo : check is fraudster
        /*
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );
        */
        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalAccessException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                customer.getFirstname(),
                String.format("Hi %s, welcome to Anescode...",
                        customer.getFirstname())
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

    }
}