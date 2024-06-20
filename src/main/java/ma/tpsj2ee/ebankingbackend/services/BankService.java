package ma.tpsj2ee.ebankingbackend.services;

import jakarta.transaction.Transactional;
import ma.tpsj2ee.ebankingbackend.entities.BankAccount;
import ma.tpsj2ee.ebankingbackend.entities.CurrentAccount;
import ma.tpsj2ee.ebankingbackend.entities.SavingAccount;
import ma.tpsj2ee.ebankingbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {
    @Autowired
    BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount=bankAccountRepository.findById("3591771a-8141-459d-beee-b7ca50529ebb").orElse(null);
        if(bankAccount!=null){
            System.out.println("**************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            if(bankAccount instanceof CurrentAccount){
                System.out.println("Over Draft=>"+((CurrentAccount)bankAccount).getOverDraft());;
            }else if(bankAccount instanceof SavingAccount){
                System.out.println("Rate=>"+((SavingAccount)bankAccount).getInterestRate());
            }
            bankAccount.getAccountOperation().forEach(op->{
                System.out.println(op.getType()+"\t"+op.getOperationDate()+"\t"+op.getAmount());
            });}
    }
}
