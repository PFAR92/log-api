package com.log.api.controller;

import com.log.api.assembler.EntregaAssembler;
import com.log.api.domain.model.Entrega;
import com.log.api.domain.repository.EntregaRepository;
import com.log.api.domain.service.FInalizacaoEntregaService;
import com.log.api.domain.service.SolicitacaoEntregaService;
import com.log.api.model.EntregaModel;
import com.log.api.model.input.EntregaInput;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/entregas")
public class EntregaController {

    private SolicitacaoEntregaService solicitacaoEntregaService;
    private EntregaRepository entregaRepository;
    private EntregaAssembler entregaAssembler;
    private FInalizacaoEntregaService fInalizacaoEntregaService;


    @GetMapping
    public List<EntregaModel> listar(){
        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EntregaModel> buscar (@PathVariable Long id){
        return entregaRepository.findById(id)
                .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput){
        Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
        Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
        return entregaAssembler.toModel(entregaSolicitada);
    }
    @PutMapping(value = "/{id}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long id){
        fInalizacaoEntregaService.finalizar(id);
    }
}
