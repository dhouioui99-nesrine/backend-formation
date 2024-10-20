package com.bank.security.Formation;
import jakarta.persistence.*;




@Entity
@Table(name = "formation")
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
  private String nom;
  private String domaine;
  private String periode;
  private String prix ;
  
public Formation() {
}
public Formation(Long id, String nom, String domaine, String periode, String prix) {
    this.id = id;
    this.nom = nom;
    this.domaine = domaine;
    this.periode = periode;
    this.prix = prix;
}
public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
public String getNom() {
    return nom;
}
public void setNom(String nom) {
    this.nom = nom;
}
public String getDomaine() {
    return domaine;
}
public void setDomaine(String domaine) {
    this.domaine = domaine;
}
public String getPeriode() {
    return periode;
}
public void setPeriode(String periode) {
    this.periode = periode;
}
public String getPrix() {
    return prix;
}
public void setPrix(String prix) {
    this.prix = prix;
}
    
}
