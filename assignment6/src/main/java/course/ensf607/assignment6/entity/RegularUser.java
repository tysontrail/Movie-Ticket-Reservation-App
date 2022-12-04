package course.ensf607.assignment6.entity;

/**RegularUser class that extends User but keeps out the registered user stuff.
 */
public class RegularUser extends User{
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
