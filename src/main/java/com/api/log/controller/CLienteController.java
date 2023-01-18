package com.api.log.controller;

import com.api.log.controller.domain.model.Cliente;
import com.api.log.repository.CLienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class CLienteController {
    private CLienteRepository cLienteRepository;

    @GetMapping
    public List<Cliente> listar(){
        return cLienteRepository.findAll();
    }

}
