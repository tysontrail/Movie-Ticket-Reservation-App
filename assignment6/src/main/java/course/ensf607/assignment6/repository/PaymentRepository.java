package course.ensf607.assignment6.repository;

import course.ensf607.assignment6.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**Interface for the payment repository to allow for storage abd access by the services (e.g controllers.
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
