package ma.tpsj2ee.ebankingbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.tpsj2ee.ebankingbackend.dtos.CustomerDTO;
import ma.tpsj2ee.ebankingbackend.entities.Customer;
import ma.tpsj2ee.ebankingbackend.exception.CustomerNotFoundException;
import ma.tpsj2ee.ebankingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {
    private BankAccountService bankAccountService;
    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return bankAccountService.listCutomers();
    }
    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name="id") Long customerId) throws CustomerNotFoundException {
    return  bankAccountService.getCustomer(customerId);
    }
    //norme restful: tt ce qui concerne le customer sera mappe avec /customer
    @PostMapping("/customers")
    //rrecuperer les donnees de customerDTO a partir du corps de la requete en format JSON
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return bankAccountService.saveCustomer(customerDTO);
    }
    @PutMapping("/customers/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId,@RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }
    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        bankAccountService.deleteCustomer(id);

    }
}
