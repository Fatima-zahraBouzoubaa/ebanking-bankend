package ma.tpsj2ee.ebankingbackend.repositories;

import ma.tpsj2ee.ebankingbackend.entities.BankAccount;
import ma.tpsj2ee.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
