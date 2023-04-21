package mypack.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import mypack.payload.BaseResponse;
import mypack.service.OrderService;
import mypack.service.PaypalService;
import mypack.service.UserDetailsCustom;

@RestController
public class PaypalController {

    @Autowired
    PaypalService service;

    @Autowired
    OrderService orderService;

    public static final String SUCCESS_URL = "api/pay/success";
    public static final String CANCEL_URL = "api/pay/cancel";

    @PostMapping("api/pay")
    public ResponseEntity<?> payment(@AuthenticationPrincipal UserDetailsCustom emp,
            @RequestParam(value = "serviceId", required = true) Long serviceId,
            @RequestParam(value = "duration", required = true) Long duration) {

        if (emp == null)
            return ResponseEntity.ok(new BaseResponse(false, "Login and try again !"));

        try {
            Long orderId = orderService.createOrder(emp.getEmail(), serviceId, duration);

            try {
                Payment payment = service.createPayment(orderId, "http://localhost:8081/" + CANCEL_URL,
                        "http://localhost:8081/" + SUCCESS_URL);
                for (Links link : payment.getLinks()) {
                    if (link.getRel().equals("approval_url")) {
                        service.saveLink(orderId, link.getHref());
                        return ResponseEntity.ok(new BaseResponse(true, link.getHref()));
                    }
                }
                return ResponseEntity.ok(new BaseResponse(false, "Create url success ! "));

            } catch (PayPalRESTException e) {
                e.printStackTrace();
                return ResponseEntity.ok(new BaseResponse(false, "There is some thing wrong !"));

            }

        } catch (Exception ex) {
            return ResponseEntity.ok(new BaseResponse(false, ex.getMessage()));

        }

    }

    @GetMapping(CANCEL_URL)
    public ResponseEntity<?> cancelPay() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("http://localhost:3000/employer/orders"))
                .build();
    }

    @GetMapping(SUCCESS_URL)
    public ResponseEntity<?> successPay(@RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId) {
        String message = "Payment ID: " + paymentId + ", Payer ID: " + payerId;

        try {
            Payment payment = service.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                // Do update here
                service.handleSuccess(payment);
                return ResponseEntity.status(HttpStatus.FOUND)
                        .location(URI.create("http://localhost:3000/employer/orders"))
                        .build();

            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(new BaseResponse(false,
                "We are so sory about for the inconvenience but error occur when verify success payment ! Please keep the information below and send it to the admin email: "
                        + message));
    }
}
