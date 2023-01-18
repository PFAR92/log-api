package com.api.log.controller;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.api.log.controller.domain.model.Cliente;
import com.api.log.repository.CLienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/clientes")
public class CLienteController {
    private CLienteRepository cLienteRepository;

    @GetMapping
    public List<Cliente> listar(){
        return cLienteRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id){
        Optional<Cliente> cliente = cLienteRepository.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@RequestBody Cliente cliente){
        return cLienteRepository.save(cliente);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id,@RequestBody Cliente cliente){
        if (!cLienteRepository.existsById(id)) return ResponseEntity.notFound().build();
        else cliente.setId(id);

        cLienteRepository.save(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!cLienteRepository.existsById(id)) return ResponseEntity.notFound().build();
        else cLienteRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
