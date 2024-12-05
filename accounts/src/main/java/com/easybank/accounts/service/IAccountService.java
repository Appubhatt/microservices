package com.easybank.accounts.service;

import com.easybank.accounts.dto.CustomerDto;

public interface IAccountService {

    void createAccount(CustomerDto customerDto);
}
