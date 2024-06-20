package ma.tpsj2ee.ebankingbackend.repositories;

import ma.tpsj2ee.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
