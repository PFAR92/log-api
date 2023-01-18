package com.api.log.controller;

import com.api.log.controller.domain.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class CLienteController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping
    public List<Cliente> listar(){
        return manager.createQuery("from Cliente", Cliente.class)
                .getResultList();
    }

}
