package com.log.api.controller;

import com.log.api.assembler.OcorrenciaAssembler;
import com.log.api.domain.model.Entrega;
import com.log.api.domain.model.Ocorrencia;
import com.log.api.domain.service.BuscaEntregaService;
import com.log.api.domain.service.RegistroOcorrenciaService;
import com.log.api.model.OcorrenciaModel;
import com.log.api.model.input.OcorrenciaInput;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "entregas/{id}/ocorrencias")
public class OcorrenciaController {

    private RegistroOcorrenciaService registroOcorrenciaService;
    private OcorrenciaAssembler ocorrenciaAssembler;
    private BuscaEntregaService buscaEntregaService;


    @GetMapping
    public List<OcorrenciaModel> listar(@PathVariable Long id){

        Entrega entrega = buscaEntregaService.buscar(id);
        return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar(@PathVariable Long id, @Valid @RequestBody OcorrenciaInput ocorrenciaInput){

        Ocorrencia OcorrenciaRegistrada = registroOcorrenciaService.registrar(id, ocorrenciaInput.getDescricao());

        return ocorrenciaAssembler.toModel(OcorrenciaRegistrada);
    }
}
