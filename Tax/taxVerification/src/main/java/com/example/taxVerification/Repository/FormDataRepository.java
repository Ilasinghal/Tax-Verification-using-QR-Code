package com.example.taxVerification.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.taxVerification.Entity.Formdata;
import com.example.taxVerification.Entity.User;

@Repository
public interface FormDataRepository extends CrudRepository<Formdata,Long> {
	
    Formdata findByUsername(String username);
    
    List<Formdata> findAll();
    
    Formdata findByAccnumberAndFnameAndTaxnoAndUsername(String accnumber, String fname, String taxno, String username);
    
    @Modifying
    @Transactional
    @Query("UPDATE Formdata v SET v.transactionid = :transactionid WHERE v.username = :username AND v.fname = :fname AND v.taxno = :taxno")
    int updateTransactionidByUsernameAndFnameAndTaxno(String transactionid, String username, String fname, String taxno);
    
    @Modifying
    @Transactional
    @Query("UPDATE Formdata v SET v.qrpath = :qrpath WHERE v.username = :username AND v.fname = :fname AND v.taxno = :taxno")
    int updateQrpathByUsernameAndFnameAndTaxno(String qrpath, String username, String fname, String taxno);    
    
    @Modifying
    @Transactional
    @Query("UPDATE Formdata v SET v.status = :status, v.taxamount = :taxamount, v.duedate = :duedate WHERE v.username = :username AND v.fname = :fname AND v.taxno = :taxno")
    int updateData(String status, String taxamount, String duedate, String username, String fname, String taxno);
}
