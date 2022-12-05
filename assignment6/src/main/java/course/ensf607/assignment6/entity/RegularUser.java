package course.ensf607.assignment6.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**RegularUser class that extends User but keeps out the registered user stuff.
 */
public class RegularUser extends User{

    private static RegularUser onlyInstance = null;
    public static RegularUser getInstance() {
        if(onlyInstance == null){
            onlyInstance = new RegularUser();
        }
        return onlyInstance;
    }

    public RegularUser(){

    }

    public RegularUser(
            Long id,
            String firstName,
            String lastName,
            String email,
            long creditCard,
            int cvcNumber,
            int expiryDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.creditCard = creditCard;
        this.cvcNumber = cvcNumber;
        this.expiryDate = expiryDate;
    }

    public RegularUser(
            String firstName,
            String lastName,
            String email,
            long creditCard,
            int cvcNumber,
            int expiryDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.creditCard = creditCard;
        this.cvcNumber = cvcNumber;
        this.expiryDate = expiryDate;
    }


}
