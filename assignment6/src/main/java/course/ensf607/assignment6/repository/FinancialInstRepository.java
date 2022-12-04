package course.ensf607.assignment6.repository;

import course.ensf607.assignment6.entity.FinancialInst;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FinancialInstRepository extends JpaRepository<FinancialInst, Long> {

    Optional<FinancialInst> findByCreditCard(Long creditCard);

}
