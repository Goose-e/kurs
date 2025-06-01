package com.example.kurs.repo;

import com.example.kurs.dto.deposites.get_all_for_user.GetAllUsersDepoResponseDTO;
import com.example.kurs.dto.deposites.report.ReportResponseDTO;
import com.example.kurs.models.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DepositRepo extends JpaRepository<Deposit, Long> {

    @Query("SELECT new com.example.kurs.dto.deposites.get_all_for_user.GetAllUsersDepoResponseDTO(" +
            "u.username,d.depoCode, d.openDate, d.closeDate, dt.name, d.balance) " +
            "FROM Deposit d " +
            "JOIN d.client c " +
            "JOIN c.user u " +
            "JOIN d.type dt " +
            "WHERE u.userCode = :userCode")
    List<GetAllUsersDepoResponseDTO> getAllDepositByUserCode(@Param("userCode") String userCode);

    Deposit getDepositByDepoCode(String depoCode);

    @Query("""
                SELECT new com.example.kurs.dto.deposites.report.ReportResponseDTO(
                    d.depoCode,
                    cl.passportNumber,
                    dt.name,
                    d.openDate,
                    d.closeDate,
                    d.balance,
                    COALESCE(SUM(CASE WHEN ia.accrualDate BETWEEN :start AND :end THEN ia.amount ELSE 0 END), 0),
                    (SELECT COALESCE(SUM(t.amount), 0)
                     FROM Transaction t
                     WHERE t.deposit = d AND t.operationDate BETWEEN :start AND :end)
                )
                FROM Deposit d
                JOIN d.client cl
                JOIN d.type dt
                LEFT JOIN InterestAccruals ia ON ia.deposit = d
                 GROUP BY d.depoCode, cl.passportNumber, d.balance, dt.name, d.openDate, d.closeDate, d.depositId
            """)
    List<ReportResponseDTO> getAdminDepositReport(@Param("start") LocalDateTime start,
                                                  @Param("end") LocalDateTime end);

    @Query("SELECT d FROM Deposit d WHERE (d.closeDate IS NULL OR d.closeDate > :now) AND (d.lastAccrual IS NULL OR d.lastAccrual <= :lastMonth)")
    List<Deposit> findActiveDepositsForAccrual(@Param("now") LocalDateTime now, @Param("lastMonth") LocalDateTime lastMonth);

}
