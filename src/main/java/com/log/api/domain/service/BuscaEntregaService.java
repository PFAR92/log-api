package com.log.api.domain.service;

import com.log.api.exception.EntidadeNaoEncontradaExeption;
import com.log.api.domain.model.Entrega;
import com.log.api.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

    private EntregaRepository entregaRepository;

    public Entrega buscar(Long id) {
        return entregaRepository.findById(id).orElseThrow(()-> new EntidadeNaoEncontradaExeption("Entrega não encontrada"));
    }
}
