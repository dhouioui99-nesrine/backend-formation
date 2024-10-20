package com.bank.security.contact;

import jakarta.persistence.*;

@Entity
@Table(name = "contact")
public class contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
  private String nom;
  private String email;
  private String sujet;
  private String message ;
public contact() {
}
public contact(Long id, String nom, String email, String sujet, String message) {
    this.id = id;
    this.nom = nom;
    this.email = email;
    this.sujet = sujet;
    this.message = message;
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
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}
public String getSujet() {
    return sujet;
}
public void setSujet(String sujet) {
    this.sujet = sujet;
}
public String getMessage() {
    return message;
}
public void setMessage(String message) {
    this.message = message;
} 
 
}
