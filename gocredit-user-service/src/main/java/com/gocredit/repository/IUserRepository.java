package com.gocredit.repository;

import com.gocredit.exceptions.BillNotFoundException;
import com.gocredit.exceptions.UserNotFoundException;
import com.gocredit.model.Role;
import com.gocredit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    List<User> findByRole(Role role) throws UserNotFoundException;

    List<User> findByName(String name) throws UserNotFoundException;

    List<User> findByEmail(String email) throws UserNotFoundException;

    List<User> findByContactNumber(long contactNumber) throws UserNotFoundException;

    //custom queries

    @Query("from User u inner join u.creditCards c where c.cardNumber=?1")
    List<User> findByCardNumber(String cardNumber) throws UserNotFoundException;

    @Query("from User u inner join u.creditCards c inner join c.bills b where b.billId=?1 ")
    List<User> findByBillId(int billId) throws BillNotFoundException;

    @Query("from User u inner join u.creditCards c inner join c.bills b where b.isPaid=?1")
    List<User> findByIsBillPaid(boolean isPaid) throws UserNotFoundException;
}
