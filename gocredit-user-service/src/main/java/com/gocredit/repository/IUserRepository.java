package com.gocredit.repository;

import com.gocredit.exceptions.BillNotFoundException;
import com.gocredit.exceptions.UserNotFoundException;
import com.gocredit.model.Role;
import com.gocredit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    List<User> findByRole(Role role);

    List<User> findByName(String name);

    User findByEmail(String email);

    User findByContactNumber(long contactNumber);

    //custom queries

    @Query("from User u inner join u.creditCards c where c.cardNumber=?1")
    List<User> findByCardNumber(String cardNumber);

    @Query("from User u inner join u.creditCards c inner join c.bills b where b.billId=?1 ")
    List<User> findByBillId(int billId);

    @Query("from User u inner join u.creditCards c inner join c.bills b where b.isPaid=?1")
    List<User> findByIsBillPaid(boolean isPaid);
}
