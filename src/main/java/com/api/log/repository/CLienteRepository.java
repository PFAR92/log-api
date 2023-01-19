package com.api.log.repository;

import com.api.log.controller.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CLienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByEmail(String email);
    boolean existsByEmail(String email);

    List<Cliente> findByNome(String nome);
    List<Cliente> findByNomeContaining(String nome);
}
