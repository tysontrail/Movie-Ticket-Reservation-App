package course.ensf607.assignment6.repository;

import course.ensf607.assignment6.entity.FinancialInst;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**Interface for the financial institution that we're mimicking for the payment.
 */
public interface FinancialInstRepository extends JpaRepository<FinancialInst, Long> {

    Optional<FinancialInst> findByCreditCard(Long creditCard);

}
