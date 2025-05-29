package com.example.kurs.services.servicesImpl;

import com.example.kurs.config.jwt.CustomAuthenticationToken;
import com.example.kurs.dao.ClientDao;
import com.example.kurs.dao.DepositDao;
import com.example.kurs.dao.InterestAccrualsDao;
import com.example.kurs.dao.TransactionDao;
import com.example.kurs.dto.deposites.change_type.ChangeDepoTypeRequestDTO;
import com.example.kurs.dto.deposites.change_type.ChangeDepoTypeResponseDTO;
import com.example.kurs.dto.deposites.close.CloseDepoRequestDTO;
import com.example.kurs.dto.deposites.close.CloseDepoResponseDTO;
import com.example.kurs.dto.deposites.create.CreateDepoRequestDTO;
import com.example.kurs.dto.deposites.create.CreateDepoResponse;
import com.example.kurs.dto.deposites.create.CreateDepoResponseDTO;
import com.example.kurs.dto.deposites.get_all.GetAllDepoResponseDTO;
import com.example.kurs.dto.deposites.report.ReportRequestDTO;
import com.example.kurs.dto.deposites.top_up.TopUpDepoRequestDTO;
import com.example.kurs.mapper.DepoMapper;
import com.example.kurs.models.Client;
import com.example.kurs.models.Deposit;
import com.example.kurs.models.InterestAccruals;
import com.example.kurs.models.Transaction;
import com.example.kurs.services.IDeposits;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@AllArgsConstructor
public class DepoService implements IDeposits {
    private final ClientDao clientDao;
    private final DepoMapper depoMapper;
    private final DepositDao depositDao;
    private final InterestAccrualsDao interestAccrualsDao;
    private final TransactionDao transactionDao;

    @Override
    public ResponseEntity<CreateDepoResponseDTO> createDeposit(CreateDepoRequestDTO depoRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CreateDepoResponseDTO createDepoResponseDTO = new CreateDepoResponseDTO();
        HttpStatusCode code;
        if (authentication instanceof CustomAuthenticationToken customToken) {
            String userCode = customToken.getCode();
            Client client = clientDao.findClientByCode(userCode);
            if (client != null) {
                Deposit deposit = depoMapper.createDeposit(depoRequestDTO, client);
                InterestAccruals accruals = depoMapper.createInterestAccural(deposit, depoRequestDTO.getDepoSum());
                Transaction transaction = depoMapper.createTransaction(deposit, depoRequestDTO.getDepoSum(), "Create deposit");
                save(deposit, accruals, transaction);
                createDepoResponseDTO = depoMapper.createDepoResponseDTO(deposit, depoResultB(deposit));
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
        BigDecimal annualRate = BigDecimal.valueOf(deposit.getDepositType().getInterestRate());
        int termMonths = deposit.getDepositType().getTermMonths() * 30;
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
        BigDecimal rate = BigDecimal.valueOf(deposit.getDepositType().getInterestRate());
        int termMonths = deposit.getDepositType().getTermMonths() * 30;

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

    @Override
    public ResponseEntity<GetAllDepoResponseDTO> getAllDeposits() {
        return null;
    }

    @Override
    public ResponseEntity<TopUpDepoRequestDTO> topUpDepo(TopUpDepoRequestDTO depoRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ReportRequestDTO> report(ReportRequestDTO reportRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<CloseDepoResponseDTO> closeDepo(CloseDepoRequestDTO closeDepoRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ChangeDepoTypeResponseDTO> changeDepoType(ChangeDepoTypeRequestDTO
                                                                            changeDepoTypeRequestDTO) {
        return null;
    }
}
