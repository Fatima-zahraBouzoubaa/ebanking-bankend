package ma.tpsj2ee.ebankingbackend.web;

import lombok.AllArgsConstructor;
import ma.tpsj2ee.ebankingbackend.dtos.AccountHistoryDTO;
import ma.tpsj2ee.ebankingbackend.dtos.AccountOperationDTO;
import ma.tpsj2ee.ebankingbackend.dtos.BankAccountDTO;
import ma.tpsj2ee.ebankingbackend.exception.BankAccountNotFoundException;
import ma.tpsj2ee.ebankingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class BankAccountRestAPI {
    private BankAccountService bankAccountService;

    @GetMapping("/accounts/{id}")
    public BankAccountDTO getBankAccount(@PathVariable String id) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(id);
    }
    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts(){
        return bankAccountService.bankAccountList();
    }
    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
        return bankAccountService.accountHistory(accountId);

    }
    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId, @RequestParam(name="page",defaultValue="0") int page,
                                               @RequestParam(name="size",defaultValue="5")int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId,page,size);

    }
}
