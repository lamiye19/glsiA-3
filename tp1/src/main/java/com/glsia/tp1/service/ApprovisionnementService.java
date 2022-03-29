package com.glsia.tp1.service;

import com.glsia.tp1.models.Approvisionnement;
import com.glsia.tp1.repository.ApprovisionnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovisionnementService {

    @Autowired
    private ApprovisionnementRepository approvisionnementRepository;

    public void save(Approvisionnement approvisionnement)
    {
        approvisionnementRepository.save(approvisionnement);
    }

    public List<Approvisionnement> showAllApprovisionnement()
    {
        return approvisionnementRepository.findAll();
    }

    public Approvisionnement selectOneApprovisionnement(int id)
    {
        return approvisionnementRepository.findById(id).get();
    }

    public  void deleteApprovisionnement(int id)
    {
        approvisionnementRepository.deleteById(id);
    }

}
