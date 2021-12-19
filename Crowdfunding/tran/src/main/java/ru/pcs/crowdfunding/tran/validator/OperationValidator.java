package ru.pcs.crowdfunding.tran.validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.pcs.crowdfunding.tran.domain.Account;
import ru.pcs.crowdfunding.tran.domain.OperationType;
import ru.pcs.crowdfunding.tran.dto.OperationDto;
import ru.pcs.crowdfunding.tran.repositories.AccountsRepository;
import ru.pcs.crowdfunding.tran.repositories.PaymentsRepository;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
@Slf4j
public class OperationValidator {

    private final AccountsRepository accountsRepository;
    private final PaymentsRepository paymentsRepository;

    public void isValid(OperationDto operationDto) {
        log.info("Start method 'isValid' with parameters: {}", operationDto.toString());

        this.isOperationDtoNotNull(operationDto);
        this.isOperationTypeExist(operationDto);
        this.isSumGreaterThanZero(operationDto);

        if (operationDto.getOperationType().equals(OperationType.Type.PAYMENT.toString()) ||
            operationDto.getOperationType().equals(OperationType.Type.REFUND.toString())
        ) {
            this.isDebitAccountIdExist(operationDto);
            this.isCreditAccountIdExist(operationDto);
            this.isBalanceEnoughForOperation(operationDto);
            this.isCreditAccountEqualsDebitAccount(operationDto);
        }

        if (operationDto.getOperationType().equals(OperationType.Type.TOP_UP.toString())) {
            this.isDebitAccountIdExist(operationDto);
        }

        if (operationDto.getOperationType().equals(OperationType.Type.WITHDRAW.toString())) {
            this.isCreditAccountIdExist(operationDto);
            this.isBalanceEnoughForOperation(operationDto);
        }
    }

    private void isOperationDtoNotNull(OperationDto operationDto) {
        if (operationDto == null) {
            log.error("Validation error: ", new IllegalArgumentException("Операция не передана/получен null"));
            throw new IllegalArgumentException("Операция не передана / получен null");
        }
    }

    private void isOperationTypeExist(OperationDto operationDto) {
        if (!operationDto.getOperationType().equals(OperationType.Type.PAYMENT.toString()) &&
            !operationDto.getOperationType().equals(OperationType.Type.REFUND.toString()) &&
            !operationDto.getOperationType().equals(OperationType.Type.TOP_UP.toString()) &&
            !operationDto.getOperationType().equals(OperationType.Type.WITHDRAW.toString())
        ) {
            log.error("Validation error: ", new IllegalArgumentException(
                "Типа операции " +  operationDto.getOperationType() + " не существует"));
            throw new IllegalArgumentException("Типа операции " +  operationDto.getOperationType() + " не существует");
        }
    }

    private void isCreditAccountIdExist(OperationDto operationDto) {
        if (!accountsRepository.findById(operationDto.getCreditAccountId()).isPresent()) {
            log.error("Validation error: ", new IllegalArgumentException(
                "Счета credit_account_id = " + operationDto.getCreditAccountId() + " не существует"));
            throw new IllegalArgumentException("Счета credit_account_id = " + operationDto.getCreditAccountId() + " не существует");
        }
    }

    private void isDebitAccountIdExist(OperationDto operationDto) {
        if (!accountsRepository.findById(operationDto.getDebitAccountId()).isPresent()){
            log.error("Validation error: ", new IllegalArgumentException(
                "Счета debit_account_id = " + operationDto.getDebitAccountId() + " не существует"));
            throw new IllegalArgumentException("Счета debit_account_id = " + operationDto.getDebitAccountId() + " не существует");
        }
    }

    private void isSumGreaterThanZero(OperationDto operationDto) {
        if (operationDto.getSum().compareTo(BigDecimal.ZERO) < 1) {
            log.error("Validation error: ", new IllegalArgumentException("Сумма = " + operationDto.getSum() + " операции меньше или равна нулю"));
            throw new IllegalArgumentException("Сумма = " + operationDto.getSum() + " операции меньше или равна нулю");
        }
    }

    private void isBalanceEnoughForOperation(OperationDto operationDto) {
        Account account = accountsRepository.getById(operationDto.getCreditAccountId());
        BigDecimal balance = paymentsRepository.findBalanceByAccountAndDatetime(account, operationDto.getDatetime());
        if (balance.compareTo(operationDto.getSum()) < 0 || balance.compareTo(BigDecimal.ZERO) < 1) {
            log.error("Validation error: ", new IllegalArgumentException(
                "Для совершения операции на сумму " + operationDto.getSum() + " на счете недостаточно средств"));
            throw new IllegalArgumentException("Для совершения операции на сумму " + operationDto.getSum() + " на счете недостаточно средств");
        }
    }

    public void isCreditAccountEqualsDebitAccount(OperationDto operationDto) {
        Account creditAccount = accountsRepository.getById(operationDto.getCreditAccountId());
        Account debitAccount = accountsRepository.getById(operationDto.getDebitAccountId());
        if (creditAccount.equals(debitAccount)) {
            log.error("Validation error: ", new IllegalArgumentException(
                "Осуществлять перевод с с кошелька на этот же кошелек запрещено"));
            throw new IllegalArgumentException("Осуществлять перевод с с кошелька на этот же кошелек запрещено");
        }
    }
}