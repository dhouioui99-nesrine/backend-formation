package com.bank.security.inscrit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.security.Formation.Formation;
import com.bank.security.Formation.FormationRepository;


@Service
public class InscritService {
    
    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private InscritRepository inscritRepository;

    
    // Méthode qui transfère les données de "Formation" à "Inscrit"
    public void transferDataFromFormationToInscrit() {
        // Récupère toutes les données de la table Formation
        List<Formation> formations = formationRepository.findAll();

        // Parcourt la liste des formations et transfère les données dans la table Inscrit
        for (Formation formation : formations) {
            inscrit inscrit = new inscrit();
            inscrit.setNom(formation.getNom()); 
            inscrit.setDomaine(formation.getDomaine()); 
            inscrit.setPeriode(formation.getPeriode());
            inscrit.setPrix(formation.getPrix());
            inscrit.setEmail(inscrit.getEmail());
            // Enregistrement dans la table "Inscrit"
            inscritRepository.save(inscrit);
        }
    }
}