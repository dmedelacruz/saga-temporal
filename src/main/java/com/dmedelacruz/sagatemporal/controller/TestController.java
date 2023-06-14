package com.dmedelacruz.sagatemporal.controller;

import com.dmedelacruz.sagatemporal.activities.payment.UpdatePaymentVerificationRequest;
import com.dmedelacruz.sagatemporal.saga.BookingSaga;
import com.dmedelacruz.sagatemporal.workflow.booking.BookingWorkflowRequest;
import io.temporal.workflow.Workflow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/temporal")
@RequiredArgsConstructor
public class TestController {

    private final BookingSaga bookingSaga;

    @PostMapping
    public String createNamespace(@RequestParam("namespace") String namespace, @RequestBody BookingWorkflowRequest request) {
        return bookingSaga.processBooking(namespace, request);
    }

    @PostMapping("/update-payment")
    public void updatePayment(@RequestParam("namespace") String namespace, @RequestBody UpdatePaymentVerificationRequest request) {
        bookingSaga.updatePayment(namespace, request);
    }

}
