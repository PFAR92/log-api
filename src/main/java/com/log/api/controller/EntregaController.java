package com.log.api.controller;

import com.log.api.domain.model.Entrega;
import com.log.api.domain.repository.EntregaRepository;
import com.log.api.domain.service.SolicitacaoEntregaService;
import com.log.api.model.DestinatarioModel;
import com.log.api.model.EntregaModel;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/entregas")
public class EntregaController {

    private SolicitacaoEntregaService solicitacaoEntregaService;
    private EntregaRepository entregaRepository;


    @GetMapping
    public List<Entrega> listar(){
        return entregaRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EntregaModel> buscar (@PathVariable Long id){
        Optional<Entrega> entrega = entregaRepository.findById(id);
        return entrega.map(entrega1 -> {
            EntregaModel entregaModel = new EntregaModel();
            entregaModel.setId(entrega.get().getId());
            entregaModel.setNomeCliente(entrega.get().getCliente().getNome());
            entregaModel.setDestinatario(new DestinatarioModel());
            entregaModel.getDestinatario().setNome(entrega.get().getDestinatario().getNome());
            entregaModel.getDestinatario().setLogradouro(entrega.get().getDestinatario().getLogradouro());
            entregaModel.getDestinatario().setNumero(entrega.get().getDestinatario().getNumero());
            entregaModel.getDestinatario().setComplemento(entrega.get().getDestinatario().getComplemento());
            entregaModel.getDestinatario().setBairro(entrega.get().getDestinatario().getBairro());
            entregaModel.setTaxa(entrega.get().getTaxa());
            entregaModel.setStatus(entrega.get().getStatus());
            entregaModel.setDataPedido(entrega.get().getDataPedido());
            entregaModel.setDataFinalizacao(entrega.get().getDataFinalizacao());
            return ResponseEntity.ok(entregaModel);
        }).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@Valid @RequestBody Entrega entrega){
        return solicitacaoEntregaService.solicitar(entrega);
    }
}
