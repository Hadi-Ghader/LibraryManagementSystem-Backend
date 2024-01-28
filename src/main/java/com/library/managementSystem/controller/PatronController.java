package com.library.managementSystem.controller;

import com.library.managementSystem.entities.Patron;
import com.library.managementSystem.service.PatronService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/patrons")
public class PatronController {
    @Autowired
    private PatronService patronService;

    @GetMapping
    public ResponseEntity<List<Patron>> getAllPatrons() {
        List<Patron> patrons = patronService.findAll();
        return new ResponseEntity<>(patrons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        Optional<Patron> optionalPatron = patronService.findById(id);
        return optionalPatron.map(patron -> new ResponseEntity<>(patron, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Patron> addPatron(@Valid @RequestBody Patron patron) {
        Patron savedPatron = patronService.save(patron);
        return new ResponseEntity<>(savedPatron, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable Long id, @RequestBody Patron updatedPatron) {
        Optional<Patron> optionalPatron = patronService.findById(id);

        if (optionalPatron.isPresent()) {
            Patron existingPatron = optionalPatron.get();
            existingPatron.setName(updatedPatron.getName());
            existingPatron.setContactInformation(updatedPatron.getContactInformation());
            Patron savedPatron = patronService.save(existingPatron);
            return new ResponseEntity<>(savedPatron, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        if (patronService.existsById(id)) {
            patronService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
