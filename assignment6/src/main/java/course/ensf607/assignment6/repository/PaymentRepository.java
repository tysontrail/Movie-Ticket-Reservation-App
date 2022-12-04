package course.ensf607.assignment6.repository;

import course.ensf607.assignment6.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
