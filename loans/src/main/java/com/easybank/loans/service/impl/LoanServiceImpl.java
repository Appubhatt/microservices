package com.easybank.loans.service.impl;

import com.easybank.loans.constants.LoansConstants;
import com.easybank.loans.dto.LoansDto;
import com.easybank.loans.entity.Loans;
import com.easybank.loans.exception.LoanAlreadyExistsException;
import com.easybank.loans.exception.ResourceNotAlreadyExistsException;
import com.easybank.loans.mapper.LoansMapper;
import com.easybank.loans.repository.LoansRepository;
import com.easybank.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private LoansRepository loansRepository;
    @Override
    public void createLoan(String mobileNumber) {

        Optional<Loans> optional = loansRepository.findByMobileNumber(mobileNumber);

        if (optional.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans optional = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotAlreadyExistsException("Loan","mobileNumber",mobileNumber)
        );

        return LoansMapper.mapToLoansDto(optional,new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans optional = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                ()-> new ResourceNotAlreadyExistsException("Loan","LoanNumber", loansDto.getLoanNumber())
        );

        LoansMapper.mapToLoans(loansDto,optional);
        loansRepository.save(optional);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotAlreadyExistsException("Loan","MobilNumber",mobileNumber)
        );

        loansRepository.deleteById(loans.getLoanId());
        return true;
    }

    private Loans createNewLoan(String mobileNumber){
        Loans loans = new Loans();

        loans.setMobileNumber(mobileNumber);
        long loanNumber = 100000000000L + new Random().nextInt(900000000);
        loans.setLoanNumber(String.valueOf(loanNumber));
        loans.setLoanType(LoansConstants.HOME_LOAN);
        loans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loans.setAmountPaid(0);
        loans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return loans;
    }



}
