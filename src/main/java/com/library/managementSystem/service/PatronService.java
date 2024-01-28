package com.library.managementSystem.service;

import com.library.managementSystem.entities.Patron;
import com.library.managementSystem.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {
    @Autowired
    private PatronRepository patronRepository;

    public boolean existsById(Long id){
        return patronRepository.existsById(id);
    }
    public List<Patron> findAll(){
        return patronRepository.findAll();
    }

    public Optional<Patron> findById(Long id){
        return patronRepository.findById(id);
    }

    public Patron save(Patron patron){
        return patronRepository.save(patron);
    }

    public void deleteById(Long id){
        patronRepository.deleteById(id);
    }
}
