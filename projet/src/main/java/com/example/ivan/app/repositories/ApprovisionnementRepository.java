package com.example.ivan.app.repositories;

import com.example.ivan.app.models.Approvisionnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovisionnementRepository extends JpaRepository<Approvisionnement, Integer>  {

}
