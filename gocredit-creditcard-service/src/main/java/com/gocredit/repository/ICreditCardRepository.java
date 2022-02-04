package com.gocredit.repository;

import com.gocredit.exceptions.CreditCardNotFoundException;
import com.gocredit.model.CardType;
import com.gocredit.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICreditCardRepository extends JpaRepository<CreditCard,Integer> {

    @Query("from CreditCard c inner join c.user u where u.userId=?1 and c.nameOnCard=?2")
    List<CreditCard> findByUserAndNameOnCard(int userId, String nameOnCard) throws CreditCardNotFoundException;

    @Query("from CreditCard c inner join c.user u where u.userId=?1 and c.cardType=?2")
    List<CreditCard> findByUserAndType(int userId, CardType cardType) throws CreditCardNotFoundException;

    @Query("from CreditCard where cardNumber=?1")
    CreditCard findByCardNumber(String number) throws CreditCardNotFoundException;

    @Query("from CreditCard c inner join c.user u where u.userId=?1")
    List<CreditCard> findByUserId(int userId) throws CreditCardNotFoundException;

    @Query(value = "delete from creditcards where cardid=?1", nativeQuery = true)
    Void deleteById(int cardId);

}
