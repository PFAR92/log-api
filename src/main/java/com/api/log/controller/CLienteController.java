package com.api.log.controller;

import com.api.log.domain.model.Cliente;
import com.api.log.domain.service.CatalogoClienteService;
import com.api.log.domain.repository.CLienteRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    private CatalogoClienteService catalogoClienteService;

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
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){
        return catalogoClienteService.salvar(cliente);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id,@RequestBody Cliente cliente){
        if (!cLienteRepository.existsById(id)) return ResponseEntity.notFound().build();
        else cliente.setId(id);

        catalogoClienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!cLienteRepository.existsById(id)) return ResponseEntity.notFound().build();
        else catalogoClienteService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
