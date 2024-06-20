package ma.tpsj2ee.ebankingbackend.services;

import ma.tpsj2ee.ebankingbackend.dtos.*;
import ma.tpsj2ee.ebankingbackend.entities.BankAccount;
import ma.tpsj2ee.ebankingbackend.entities.CurrentAccount;
import ma.tpsj2ee.ebankingbackend.entities.Customer;
import ma.tpsj2ee.ebankingbackend.entities.SavingAccount;
import ma.tpsj2ee.ebankingbackend.exception.BalanceNotSufficentException;
import ma.tpsj2ee.ebankingbackend.exception.BankAccountNotFoundException;
import ma.tpsj2ee.ebankingbackend.exception.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customer);
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, Long customerId, double overDraft) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, Long customerId, double interestRate) throws CustomerNotFoundException;
    List<CustomerDTO> listCutomers();
    BankAccountDTO getBankAccount (String accountId) throws BankAccountNotFoundException;
    void debit(String accountId, double amount,String description) throws BankAccountNotFoundException, BalanceNotSufficentException;
    void credit(String accountId, double amount,String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountDestination,double amount) throws BankAccountNotFoundException, BalanceNotSufficentException;

    List<BankAccountDTO> bankAccountList();

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    List<AccountOperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
}
