package com.bank.security.inscrit;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import lombok.AllArgsConstructor;



@RestController
@AllArgsConstructor
@RequestMapping("/inscrit")
public class InscritController {
    
    @Autowired
    private InscritRepository inscritrepository;

    private InscritService inscritservice;
  
    @GetMapping
    public List<inscrit> getAllInscrit() {
      return inscritrepository.findAll();
    }
  
    @GetMapping("/{id}")
    public inscrit getinscritById(@PathVariable Long id) {
      Optional<inscrit> inscrit = inscritrepository.findById(id);
      return inscrit.orElse(null);
    }
  
    @PostMapping("/save")
    public ResponseEntity<?> saveInscription(@RequestBody inscrit inscrit) {
        // Sauvegarde l'unique donnée inscrite
        return ResponseEntity.ok("Donnée transférée avec succès");
    }
    
    @PostMapping
    public ResponseEntity<inscrit> createinscrit(@RequestBody inscrit inscrit) {
      try {
          // Optionnel : validation de l'objet formation avant sauvegarde
          if (inscrit.getNom() == null || inscrit.getNom().isEmpty()) {
              return ResponseEntity.badRequest().body(null);
          }
          // Sauvegarde de la inscrit
          inscrit savedinscrit = inscritrepository.save(inscrit);
          // Retourne une réponse avec le statut 201 Created
          return ResponseEntity.status(HttpStatus.CREATED).body(savedinscrit);
      } catch (Exception e) {
          e.printStackTrace(); // Log l'exception pour le débogage
          // Retourne une réponse avec le statut 500 Internal Server Error
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body(null);
      }
  }
  
    @DeleteMapping("/{id}")
    public void deleteinscrit(@PathVariable Long id) {
        inscritrepository.deleteById(id);
    }
  
    @PutMapping("/{id}")
    public inscrit updateinscrit(@PathVariable Long id, @RequestBody inscrit inscrit) {
        inscrit inscrit1 = inscritrepository.findById(id).orElse(null);
      if (inscrit1 != null) {
        inscrit1.setNom(inscrit.getNom());
        inscrit1.setDomaine(inscrit.getDomaine());
        inscrit1.setPeriode(inscrit.getPeriode());
        inscrit1.setPrix(inscrit.getPrix());
        inscrit1.setEmail(inscrit.getEmail());
      

        
        inscritrepository.save(inscrit1);
  
      }
      return inscrit1;
    }
   
  }
