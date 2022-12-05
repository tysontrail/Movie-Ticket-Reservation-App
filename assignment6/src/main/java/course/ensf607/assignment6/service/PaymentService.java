package course.ensf607.assignment6.service;

import course.ensf607.assignment6.controller.PaymentController;
import course.ensf607.assignment6.entity.Payment;
import course.ensf607.assignment6.entity.RegularUser;
import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserService userService;

    private final FinancialInstService financialService;

    public PaymentService(PaymentRepository paymentRepository, UserService userService, FinancialInstService financialService){
        this.paymentRepository = paymentRepository;
        this.userService = userService;
        this.financialService = financialService;
    }

    public boolean regularUserPayTicket(String firstName, String lastName, String email, long creditCard, int cvcNumber,
                                        int expiryDate){
        //This should be null
        RegularUser regularUser = RegularUser.getInstance();
        //Then we fill in the rest.
        regularUser.setFirstName(firstName);
        regularUser.setLastName(lastName);
        regularUser.setEmail(email);
        regularUser.setCreditCard(creditCard);
        regularUser.setCvcNumber(cvcNumber);
        regularUser.setExpiryDate(expiryDate);

        //Check if all the information here belongs in the financial Institution
        if(financialService.verify(regularUser) == true) {
            return true;
        }
        return false;

    }

    public boolean registeredUserPayTicket(String username){
        Optional<User> registeredUser = this.userService.searchUserByUserName(username);
        if(!registeredUser.isPresent()){
            return false;
        }
        if(financialService.verify(registeredUser.get()) == true) {
            return true;
        }
        else{
            return false;
        }
    }

    //Run whenever the user signs in.
    public boolean checkAnnualFeeStatus(String username){
        Optional<User> registeredUser = this.userService.searchUserByUserName(username);
        if(!registeredUser.isPresent()){
            return false;
        }
//        if(financialService.verify(registeredUser.get()) == true) {
//            registeredUser.get().setPaidAnnualFee(true);
//            registeredUser.get().setAnnualRenewalDate(LocalDate.now());
//            return true;
//        }

        if(registeredUser.get().getAnnualRenewalDate().compareTo(LocalDate.now()) < 0){
            registeredUser.get().setPaidAnnualFee(false);
            return false;
        }
        return true;
    }

    public boolean registeredUserPayAnnualFee(String username){
        Optional<User> registeredUser = this.userService.searchUserByUserName(username);

        if(!registeredUser.isPresent()){
            return false;
        }
        if(financialService.verify(registeredUser.get()) == true) {
            registeredUser.get().setPaidAnnualFee(true);
            registeredUser.get().setAnnualRenewalDate(LocalDate.now());
            return true;
        }
        return false;
    }
}
