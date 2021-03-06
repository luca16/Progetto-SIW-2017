package it.uniroma3.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.spring.model.Utente;


@Repository
public interface UtenteRepository extends JpaRepository<Utente, String> {
    Utente findByUsername(String username);
}
