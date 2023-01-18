package com.api.log.controller;

import com.api.log.controller.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class CLienteController {

    @GetMapping
    public List<Cliente> listar(){
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Paulo");
        cliente.setTelefone("34991406563");
        cliente.setEmail("paulofelipetj@hotmail.com");
        return Arrays.asList(cliente);
    }

}
