package course.ensf607.assignment6.controller;

import course.ensf607.assignment6.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**Payment controller class used mostly for testing on postman.
 */
@RestController
@CrossOrigin({"*"})
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping({"/api/v1/regularuserpayticket"})
    public boolean regularUserPayTicket(@RequestParam String firstName, @RequestParam String lastName,
                                        @RequestParam String email, @RequestParam long creditCard,
                                        @RequestParam int cvcNumber, @RequestParam int expiryDate){
        return this.paymentService.regularUserPayTicket(firstName, lastName, email, creditCard, cvcNumber, expiryDate);
    }

    @GetMapping({"/api/v1/registereduserpayticket"})
    public boolean registeredUserPayTicket(@RequestParam String username){
        return this.paymentService.registeredUserPayTicket(username);
    }

    @GetMapping({"/api/v1/checkannualfeestatus"})
    public boolean checkAnnualFeeStatus(@RequestParam String username){
        return this.paymentService.checkAnnualFeeStatus(username);
    }

    @GetMapping({"/api/v1/registeduserpayannualfee"})
    public boolean registeredUserPayAnnualFee(String username){
        return this.paymentService.registeredUserPayAnnualFee(username);
    }

}
