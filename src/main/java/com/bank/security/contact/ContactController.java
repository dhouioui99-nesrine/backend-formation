package com.bank.security.contact;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.security.Formation.Formation;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/contact")
public class ContactController {
    @Autowired
  private ContactRepository contactRepository;

  @GetMapping
  public List<contact> getAllContacts() {
    return contactRepository.findAll();
  }

  @GetMapping("/{id}")
  public contact getContactById(@PathVariable Long id) {
    Optional<contact> contact = contactRepository.findById(id);
    return contact.orElse(null);
  }

  @PostMapping
  public ResponseEntity<contact> createContact(@RequestBody contact contact) {
    try {
        // Optionnel : validation de l'objet contact avant sauvegarde
        if (contact.getNom() == null || contact.getNom().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        // Sauvegarde de la contact
        contact savedcontact= contactRepository.save(contact);
        // Retourne une réponse avec le statut 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(savedcontact);
    } catch (Exception e) {
        e.printStackTrace(); // Log l'exception pour le débogage
        // Retourne une réponse avec le statut 500 Internal Server Error
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
    }
}

  @DeleteMapping("/{id}")
  public void deleteContact(@PathVariable Long id) {
    contactRepository.deleteById(id);
  }

  @PutMapping("/{id}")
  public contact updateContact(@PathVariable Long id, @RequestBody contact contact) {
    contact contact1 = contactRepository.findById(id).orElse(null);
    if (contact1 != null) {
        contact1.setNom(contact.getNom());
        contact1.setEmail(contact.getEmail());
        contact1.setSujet(contact.getSujet());
        contact1.setMessage(contact.getMessage());
      
      contactRepository.save(contact1);

    }
    return contact1;
  }
 
}


