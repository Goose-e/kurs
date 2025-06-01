package com.example.kurs.services.servicesImpl;

import com.example.kurs.config.jwt.CustomAuthenticationToken;
import com.example.kurs.dao.*;
import com.example.kurs.dto.deposites.close.CloseDepoRequestDTO;
import com.example.kurs.dto.deposites.close.CloseDepoResponse;
import com.example.kurs.dto.deposites.close.CloseDepoResponseDTO;
import com.example.kurs.dto.deposites.create.CreateDepoRequestDTO;
import com.example.kurs.dto.deposites.create.CreateDepoResponse;
import com.example.kurs.dto.deposites.create.CreateDepoResponseDTO;
import com.example.kurs.dto.deposites.get_all_for_user.GetAllDepoResponse;
import com.example.kurs.dto.deposites.get_all_for_user.GetAllDepositsForUserListDTO;
import com.example.kurs.dto.deposites.get_all_for_user.GetAllUsersDepoResponseDTO;
import com.example.kurs.dto.deposites.get_trans_history.GetTransHistoryForUserResponse;
import com.example.kurs.dto.deposites.get_trans_history.GetTransHistoryForUserResponseDTO;
import com.example.kurs.dto.deposites.get_trans_history.GetTransHistoryForUserResponseListDTO;
import com.example.kurs.dto.deposites.report.ReportResponse;
import com.example.kurs.dto.deposites.report.ReportResponseDTO;
import com.example.kurs.dto.deposites.report.ReportResponseListDTO;
import com.example.kurs.dto.deposites.top_up.TopUpDepoResponseDTO;
import com.example.kurs.dto.deposites.top_up.TopUpRequestDTO;
import com.example.kurs.dto.deposites.top_up.TopUpResponse;
import com.example.kurs.dto.deposites.withdraw.WithDrawRequestDTO;
import com.example.kurs.dto.deposites.withdraw.WithDrawResponse;
import com.example.kurs.dto.deposites.withdraw.WithDrawResponseDTO;
import com.example.kurs.mapper.DepoMapper;
import com.example.kurs.models.*;
import com.example.kurs.services.IDeposits;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DepoService implements IDeposits {
    private final ClientDao clientDao;
    private final DepoMapper depoMapper;
    private final DepositDao depositDao;
    private final InterestAccrualsDao interestAccrualsDao;
    private final TransactionDao transactionDao;
    private final DepositTypeDao depositTypeDao;

    @Override
    public ResponseEntity<CreateDepoResponseDTO> createDeposit(CreateDepoRequestDTO depoRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CreateDepoResponseDTO createDepoResponseDTO = new CreateDepoResponseDTO();
        HttpStatusCode code;
        if (authentication instanceof CustomAuthenticationToken customToken) {
            String userCode = customToken.getCode();
            Client client = clientDao.findClientByCode(userCode);
            if (client != null) {
                DepositTypes depositTypes = depositTypeDao.findTypeById((long) depoRequestDTO.getDepoTypeId());
                Deposit deposit = depoMapper.createDeposit(depoRequestDTO, client, depositTypes);
                Transaction transaction = depoMapper.createTransaction(deposit, depoRequestDTO.getDepoSum(), "Create deposit");
                save(deposit, transaction);
                BigDecimal result = deposit.getType().getTypeId() == 1 ? depoResultB(deposit) : depoResultA(deposit);
                createDepoResponseDTO = depoMapper.createDepoResponseDTO(deposit, result);
                code = HttpStatus.OK;
            } else {
                createDepoResponseDTO.setMessage("Server error");
                code = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            createDepoResponseDTO.setMessage("You need to login first");
            code = HttpStatus.UNAUTHORIZED;
        }
        return new CreateDepoResponse(createDepoResponseDTO, code);
    }

    private BigDecimal depoResultB(Deposit deposit) {
        BigDecimal principal = deposit.getBalance();
        BigDecimal annualRate = BigDecimal.valueOf(deposit.getType().getInterest_rate());
        int termMonths = deposit.getType().getTerm_months() * 30;
        int n = 12;
        BigDecimal term = BigDecimal.valueOf(termMonths);
        BigDecimal daysInYear = BigDecimal.valueOf(365);
        BigDecimal hundred = BigDecimal.valueOf(100);

        BigDecimal rate = annualRate.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);
        BigDecimal monthlyRate = rate.divide(BigDecimal.valueOf(n), 10, RoundingMode.HALF_UP);
        BigDecimal exponent = BigDecimal.valueOf(n * termMonths / 12.0);
        BigDecimal base = BigDecimal.ONE.add(monthlyRate);

// (1 + r/n) ^ (n*t)
        BigDecimal power = base.pow(exponent.intValue());

// A = P * (1 + r/n) ^ (n*t)
        BigDecimal amount = principal.multiply(power).setScale(2, RoundingMode.HALF_UP);

        return amount.subtract(principal);
    }

    private BigDecimal depoResultA(Deposit deposit) {
        BigDecimal balance = deposit.getBalance();
        BigDecimal rate = BigDecimal.valueOf(deposit.getType().getInterest_rate());
        int termMonths = deposit.getType().getTerm_months() * 30;

        BigDecimal term = BigDecimal.valueOf(termMonths);
        BigDecimal daysInYear = BigDecimal.valueOf(365);
        BigDecimal hundred = BigDecimal.valueOf(100);

        BigDecimal result = balance.multiply(rate).multiply(term);
        result = result.divide(daysInYear, 10, RoundingMode.HALF_UP);
        result = result.divide(hundred, 10, RoundingMode.HALF_UP);

        return result;
    }

    private void save(Deposit deposit, InterestAccruals accruals, Transaction transaction) {
        depositDao.save(deposit);
        interestAccrualsDao.save(accruals);
        transactionDao.save(transaction);
    }

    private void save(Deposit deposit, Transaction transaction) {
        depositDao.save(deposit);
        transactionDao.save(transaction);
    }

    @Override
    public ResponseEntity<GetAllDepositsForUserListDTO> getAllDepositsForUser(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GetAllDepositsForUserListDTO responseDTO = new GetAllDepositsForUserListDTO();
        HttpStatusCode code;
        if (username == null) {
            if (authentication instanceof CustomAuthenticationToken customToken) {
                String userCode = customToken.getCode();
                List<GetAllUsersDepoResponseDTO> deposits = depositDao.findAllForUserByUserCode(userCode);
                if (deposits.isEmpty()) {
                    responseDTO.setMessage("Deposit not found");
                } else {
                    responseDTO.setMessage("Deposit found");
                    responseDTO.setAllDeposits(deposits);
                }
                code = HttpStatus.OK;
            } else {
                code = HttpStatus.UNAUTHORIZED;
                responseDTO.setMessage("User unauthorized");
            }
        } else {
            Client client = clientDao.findClientsByUserName(username);
            if (client != null) {
                List<GetAllUsersDepoResponseDTO> deposits = depositDao.findAllForUserByUserCode(client.getUser().getUserCode());
                if (deposits.isEmpty()) {
                    responseDTO.setMessage("Deposit not found");
                } else {
                    responseDTO.setMessage("Deposits found");
                    responseDTO.setAllDeposits(deposits);
                }
            } else {
                responseDTO.setMessage("Client not found");
            }
            code = HttpStatus.OK;
        }

        return new GetAllDepoResponse(responseDTO, code);
    }

    @Override
    public ResponseEntity<TopUpDepoResponseDTO> topUpDepo(TopUpRequestDTO depoRequestDTO) {
        TopUpDepoResponseDTO responseDTO = new TopUpDepoResponseDTO();
        HttpStatusCode code;
        Deposit deposit = depositDao.getDepositByDepositCode(depoRequestDTO.getDepoCode());
        if (deposit == null) {
            responseDTO.setMessage("Deposit not found");
        } else {
            if (deposit.getType().getCan_add_funds()) {
                deposit.setBalance(deposit.getBalance().add(depoRequestDTO.getAmount()));
                Transaction trans = depoMapper.createTransaction(deposit, depoRequestDTO.getAmount(), "top up");
                save(deposit, trans);
                responseDTO.setMessage("Deposit top upped");
                responseDTO.setNewBalance(deposit.getBalance());
            } else {
                responseDTO.setMessage("Deposit cant be top up");

            }
        }
        code = HttpStatus.OK;
        return new TopUpResponse(responseDTO, code);
    }

    @Override
    public ResponseEntity<ReportResponseListDTO> report(String period) {
        ReportResponseListDTO responseListDTO = new ReportResponseListDTO();
        HttpStatusCode code;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start;
        switch (period.toLowerCase()) {
            case "year" -> start = now.minusYears(1);
            case "month" -> start = now.minusMonths(1);
            default -> throw new IllegalArgumentException("Период должен быть 'month' или 'year'");
        }
        List<ReportResponseDTO> report = depositDao.report(start, now);
        if (report.isEmpty()) {
            responseListDTO.setMessage("Deposits not found");
        } else {
            responseListDTO.setMessage("Deposits found");
            responseListDTO.setReportResponseDTOList(report);


        }
        code = HttpStatus.OK;
        return new ReportResponse(responseListDTO, code);
    }

    @Override
    public ResponseEntity<CloseDepoResponseDTO> closeDepo(CloseDepoRequestDTO closeDepoRequestDTO) {
        CloseDepoResponseDTO responseDTO = new CloseDepoResponseDTO();
        HttpStatusCode code;
        Deposit deposit = depositDao.getDepositByDepositCode(closeDepoRequestDTO.getDepoCode());
        if (deposit == null) {
            responseDTO.setMessage("Deposit not found");
        } else {
            if (deposit.getType().getCan_withdraw()) {
                responseDTO.setMessage("Deposit cant be withdrawal");
            } else {
                if (!deposit.getCloseDate().isBefore(LocalDateTime.now())) {
                    responseDTO.setMessage("Deposit already closed");
                } else {
                    deposit.setCloseDate(LocalDateTime.now());
                    depositDao.save(deposit);
                    responseDTO.setMessage("Deposit closed");
                    responseDTO.setBalance(deposit.getBalance());
                    responseDTO.setCloseDate(LocalDateTime.now());
                }
            }
        }
        code = HttpStatus.OK;
        return new CloseDepoResponse(responseDTO, code);
    }

    @Override
    public ResponseEntity<GetTransHistoryForUserResponseListDTO> getTransHistoryForUser(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GetTransHistoryForUserResponseListDTO responseDTO = new GetTransHistoryForUserResponseListDTO();
        HttpStatusCode code;
        if (username == null) {
            if (authentication instanceof CustomAuthenticationToken customToken) {
                String userCode = customToken.getCode();
                Client client = clientDao.findClientByCode(userCode);
                code = getTransHistoryForClient(responseDTO, client);
            } else {
                code = HttpStatus.UNAUTHORIZED;
            }
        } else {
            Client client = clientDao.findClientsByUserName(username);
            code = getTransHistoryForClient(responseDTO, client);
        }

        return new GetTransHistoryForUserResponse(responseDTO, code);
    }

    @Override
    public ResponseEntity<WithDrawResponseDTO> withDraw(WithDrawRequestDTO depositRequestDTO) {
        WithDrawResponseDTO responseDTO = new WithDrawResponseDTO();
        HttpStatusCode code;
        Deposit deposit = depositDao.getDepositByDepositCode(depositRequestDTO.getDepoCode());
        if (deposit == null) {
            responseDTO.setMessage("Deposit not found.");
        } else {
            if (deposit.getType().getCan_withdraw()) {
                if (deposit.getCloseDate().isBefore(LocalDateTime.now())) {
                    responseDTO.setMessage("Deposit already closed.");
                } else {
                    if (deposit.getBalance().compareTo(depositRequestDTO.getAmount()) < 0) {
                        responseDTO.setMessage("Request balance is lower than deposit balance.");
                    } else {
                        deposit.setBalance(deposit.getBalance().subtract(depositRequestDTO.getAmount()));
                        Transaction transaction = depoMapper.createTransaction(deposit, depositRequestDTO.getAmount(), "withdraw");
                        save(deposit, transaction);
                        responseDTO.setMessage("Deposit withdrawal.");
                        if (deposit.getBalance().compareTo(BigDecimal.ZERO) == 0) {
                            deposit.setCloseDate(LocalDateTime.now());
                            responseDTO.setMessage(responseDTO.getMessage().concat(" Deposit closed"));
                        }
                        depositDao.save(deposit);
                        responseDTO.setReceivedAmount(depositRequestDTO.getAmount());
                        responseDTO.setAmountRemainder(deposit.getBalance());
                    }
                }
            } else {
                responseDTO.setMessage("Deposit cant be withdrawal");
            }
        }
        code = HttpStatus.OK;
        return new WithDrawResponse(responseDTO, code);
    }

    private HttpStatusCode getTransHistoryForClient(GetTransHistoryForUserResponseListDTO responseDTO, Client client) {
        HttpStatusCode code;
        if (client != null) {
            List<GetTransHistoryForUserResponseDTO> transListDB = transactionDao.getTransHistoryForUser(client.getClientId());
            if (transListDB.isEmpty()) {
                responseDTO.setMessage("transactions not found");

            } else {
                responseDTO.setMessage("transactions found");
                responseDTO.setTransHistoryForUserResponseDTOList(transListDB);
            }
        } else {
            responseDTO.setMessage("Client not found");
        }
        code = HttpStatus.OK;
        return code;
    }

    @Transactional
    public void accrueMonthlyInterest() {
        LocalDateTime now = LocalDateTime.now();
        List<Deposit> deposits = depositDao.getAllActiveDeposit();
        List<InterestAccruals> interestAccrualsList = new ArrayList<>();
        for (Deposit deposit : deposits) {
            BigDecimal rate = BigDecimal.valueOf(deposit.getType().getInterest_rate()).divide(BigDecimal.valueOf(100));
            BigDecimal interest = deposit.getBalance().multiply(rate);
            deposit.setBalance(deposit.getBalance().add(interest));
            deposit.setLastAccrual(now);
            interestAccrualsList.add(depoMapper.createInterestAccural(deposit, interest));
        }


        depositDao.saveAll(deposits);
        interestAccrualsDao.saveAll(interestAccrualsList);
    }
}
