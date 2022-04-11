package com.example.ivan.app.services;

import com.example.ivan.app.models.Approvisionnement;
import com.example.ivan.app.repositories.ApprovisionnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApprovisionnementService {

    @Autowired
    private ApprovisionnementRepository approvisionnementRepository;


    public void Add(Approvisionnement approvisionnement){
        approvisionnementRepository.save(approvisionnement);
    }

    public List<Approvisionnement> getList(){
        return approvisionnementRepository.findAll();
    }

    public Approvisionnement selectOne(Integer id){
        Optional<Approvisionnement> approvisionnementOptional = approvisionnementRepository.findById(id);
        Approvisionnement approvisionnement = null;
        if(approvisionnementOptional.isPresent()){
            approvisionnement = approvisionnementOptional.get();
        } else{
            throw new RuntimeException("Aucun approvisionnement trouvé ayant id = "+ id);
        }
        return approvisionnement;
    }

    public void delete(Integer id){
        if(selectOne(id) != null){
            approvisionnementRepository.deleteById(id);
        }
    }
}
