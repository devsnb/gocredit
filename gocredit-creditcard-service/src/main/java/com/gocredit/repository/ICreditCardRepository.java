package com.gocredit.repository;

import com.gocredit.exceptions.CardNotFoundException;
import com.gocredit.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICreditCardRepository extends JpaRepository<CreditCard,Integer> {


    @Query("from CreditCard c inner join c. where ")
    List<CreditCard> findByUserAndNameOnCard(int userId, String nameOnCard) throws CardNotFoundException;
    List<CreditCard>  findByUserAndType(int userId,String cardType) throws CardNotFoundException;
    CreditCard findByCardNumber(String number) throws CardNotFoundException;
    List<CreditCard> findByUserId(int userId) throws CardNotFoundException;

}
