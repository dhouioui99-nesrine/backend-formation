package com.bank.security.Formation;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName.Form;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/formation")
public class formationController {

  @Autowired
  private FormationRepository formationRepository;

  @GetMapping
  public List<Formation> getAllFormations() {
    return formationRepository.findAll();
  }

  @GetMapping("/{id}")
  public Formation getFormationById(@PathVariable Long id) {
    Optional<Formation> formation = formationRepository.findById(id);
    return formation.orElse(null);
  }

 
  @PostMapping
  public ResponseEntity<Formation> createFormation(@RequestBody Formation formation) {
    try {
        // Optionnel : validation de l'objet formation avant sauvegarde
        if (formation.getNom() == null || formation.getNom().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        // Sauvegarde de la formation
        Formation savedFormation = formationRepository.save(formation);
        // Retourne une réponse avec le statut 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFormation);
    } catch (Exception e) {
        e.printStackTrace(); // Log l'exception pour le débogage
        // Retourne une réponse avec le statut 500 Internal Server Error
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
    }
}

  @DeleteMapping("/{id}")
  public void deleteFormation(@PathVariable Long id) {
    formationRepository.deleteById(id);
  }

  @PutMapping("/{id}")
  public Formation updateFormation(@PathVariable Long id, @RequestBody Formation formation) {
    Formation formation1 = formationRepository.findById(id).orElse(null);
    if (formation1 != null) {
      formation1.setNom(formation.getNom());
      formation1.setDomaine(formation.getDomaine());
      formation1.setPeriode(formation.getPeriode());
      formation1.setPrix(formation.getPrix());
      
      formationRepository.save(formation1);

    }
    return formation1;
  }
 
}