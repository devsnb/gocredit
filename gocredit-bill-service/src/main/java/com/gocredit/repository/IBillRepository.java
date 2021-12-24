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
    List<Bill> getByUser(int userId);

    @Query("from Bill b inner join b.creditCard c where c.date=?")
    List<Bill> getByBillingDate(LocalDate date);

    @Query("from Bill b inner join b.creditCard c where c.amount<=?1")
    List<Bill> getByLessAmount(double amount);

    @Query("from Bill b inner join b.creditCard c where c.amount>?1")
    List<Bill> getByGreaterAmount(double amount);

    @Query("from Bill b inner join b.creditCard c where c.cardNumber=?1")
    List<Bill> getByCardNumber(String cardNumber);

    @Query("from Bill b inner join b.creditCard c inner join c")
    List<Bill> getByIsPaid(boolean isPaid);

    @Query("from Bill b inner join b.creditCard c inner join c.user u where b.billerName =?2 and u.userId =?1")
    List<Bill> getByBillerName(String userId, String billerName);

}
