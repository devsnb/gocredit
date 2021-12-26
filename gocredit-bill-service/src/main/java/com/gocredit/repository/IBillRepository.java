package com.gocredit.repository;

import com.gocredit.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IBillRepository extends JpaRepository<Bill, Integer> {

    @Query("from Bill b inner join b.creditCard c inner join c.user u where u.userId=?1")
    List<Bill> findByUser(int userId);

    @Query("from Bill b inner join b.creditCard c inner join c.user u where b.date=?2 and u.userId=?1")
    List<Bill> findByBillingDate(int userId, LocalDate date);

    @Query("from Bill b inner join b.creditCard c inner join c.user u where u.userId=?1 and b.amount<=?2")
    List<Bill> findByLessAmount(int userId, double amount);

    @Query("from Bill b inner join b.creditCard c inner join c.user u where b.amount>?2 and u.userId=?1")
    List<Bill> findByGreaterAmount(int userId, double amount);

    @Query("from Bill b inner join b.creditCard c where c.cardNumber=?1")
    List<Bill> findByCardNumber(String cardNumber);

    @Query("from Bill b inner join b.creditCard c inner join c.user u where b.isPaid=?2 and u.userId=?1")
    List<Bill> findByIsPaid(int userId, boolean isPaid);

    @Query("from Bill b inner join b.creditCard c inner join c.user u where b.billerName =?2 and u.userId =?1")
    List<Bill> findByBillerName(int userId, String billerName);

    @Query("from Bill b inner join b.creditCard c where c.cardNumber=?1 and b.isPaid=?2")
    List<Bill> findByCardAndIsPaid(String cardNumber, boolean isPaid);

    @Query("from Bill b inner join b.creditCard c where c.cardNumber=?1 and b.amount<=?2")
    List<Bill> findByCardAndLessAmount(String cardNumber, double amount);

    @Query("from Bill b inner join b.creditCard c where c.cardNumber=?1 and b.amount>=?2")
    List<Bill> findByCardAndGreaterAmount(String cardNumber, double amount);


}
