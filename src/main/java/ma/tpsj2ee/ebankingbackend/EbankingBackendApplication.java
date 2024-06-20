package ma.tpsj2ee.ebankingbackend;

import ma.tpsj2ee.ebankingbackend.dtos.BankAccountDTO;
import ma.tpsj2ee.ebankingbackend.dtos.CurrentBankAccountDTO;
import ma.tpsj2ee.ebankingbackend.dtos.CustomerDTO;
import ma.tpsj2ee.ebankingbackend.dtos.SavingBankAccountDTO;
import ma.tpsj2ee.ebankingbackend.entities.*;
import ma.tpsj2ee.ebankingbackend.enums.AccountStatus;
import ma.tpsj2ee.ebankingbackend.enums.OperationType;
import ma.tpsj2ee.ebankingbackend.exception.BalanceNotSufficentException;
import ma.tpsj2ee.ebankingbackend.exception.BankAccountNotFoundException;
import ma.tpsj2ee.ebankingbackend.exception.CustomerNotFoundException;
import ma.tpsj2ee.ebankingbackend.repositories.AccountOperationRepository;
import ma.tpsj2ee.ebankingbackend.repositories.BankAccountRepository;
import ma.tpsj2ee.ebankingbackend.repositories.CustomerRepository;
import ma.tpsj2ee.ebankingbackend.services.BankAccountService;
import ma.tpsj2ee.ebankingbackend.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
        return args -> {
            Stream.of("Hassan","Imane","Mohamed").forEach(name->{
               CustomerDTO customer=new CustomerDTO();
               customer.setName(name);
               customer.setEmail(name+"@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCutomers().forEach(customer ->{
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*9000,customer.getId(),9000);
                    bankAccountService.saveSavingBankAccount(Math.random()*120000,customer.getId(),5.5 );

                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }

            }
            );
            List<BankAccountDTO> bankAccounts= bankAccountService.bankAccountList();
            for(BankAccountDTO bankAccount:bankAccounts){
                for (int i=0;i<10;i++){
                    String accountId;
                    if(bankAccount instanceof SavingBankAccountDTO){
                        accountId=((SavingBankAccountDTO) bankAccount).getId();
                    }
                    else{
                        accountId=((CurrentBankAccountDTO) bankAccount).getId();
                    }
                    bankAccountService.credit(accountId, 10000+Math.random()*1200000,"Credit");
                    bankAccountService.debit(accountId,10000+Math.random()*9000,"Debit");
                }}

        };
    }
//@Bean
    CommandLineRunner start(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository, AccountOperationRepository accountOperationRepository){
        return args->{
            Stream.of("Hassan","Yassine","Aicha").forEach(name->{
                Customer customer=new Customer();
                customer.setName(name);
                customer.setEmail(name+"gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(cust->{
                CurrentAccount currentAccount= new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*900000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount= new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random()*900000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);
            });
            bankAccountRepository.findAll().forEach(acc->{
                for(int i=0;i<5; i++){
                    AccountOperation accountOperation=new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random()*12000);
                    accountOperation.setType(Math.random()>0.5? OperationType.DEBIT: OperationType.CREDIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);

                }


            });
        };

    }

}
