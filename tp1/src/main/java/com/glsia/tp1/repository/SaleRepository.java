package com.glsia.tp1.repository;

import com.glsia.tp1.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer > {
    @Transactional
    @Query(value = "select s from Sale s where s.billId = :id")
    List<Sale> findByBillId(@Param("id") Integer billId);
}
