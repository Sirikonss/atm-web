package th.ac.ku.atm.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.atm.model.BankAccount;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class BankAccountService {

    private RestTemplate restTemplate;

    public BankAccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BankAccount> getCustomerBankAccount(int customerId) {
        String url = "http://localhost:8091/api/bankaccount/customer/" +
                customerId;
        ResponseEntity<BankAccount[]> response =
                restTemplate.getForEntity(url, BankAccount[].class);

        BankAccount[] accounts = response.getBody();

        return Arrays.asList(accounts);
    }

    private ArrayList<BankAccount> BankAccountList;

    @PostConstruct
    public void postConstruct() {
        this.BankAccountList = new ArrayList<>();
    }

    public void createBankAccount(BankAccount bankAccount) {
        BankAccountList.add(bankAccount);
    }

    public List<BankAccount> getBankAccount() {
        return new ArrayList<>(this.BankAccountList);
    }

    public BankAccount findBankAccount(int id) {
        for (BankAccount bankAccount: BankAccountList) {
            if (bankAccount.getId() == id)
                return bankAccount;
        }
        return null;
    }


}
