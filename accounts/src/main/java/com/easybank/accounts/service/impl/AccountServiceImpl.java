package com.easybank.accounts.service.impl;

import com.easybank.accounts.constants.AccountsConstants;
import com.easybank.accounts.dto.CustomerDto;
import com.easybank.accounts.entity.Accounts;
import com.easybank.accounts.entity.Customer;
import com.easybank.accounts.exception.CustomerAlreadyExistsException;
import com.easybank.accounts.mapper.CustomerMapper;
import com.easybank.accounts.repository.AccountRepository;
import com.easybank.accounts.repository.CustomerRepository;
import com.easybank.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> byMobileNumber = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(byMobileNumber.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }
}
