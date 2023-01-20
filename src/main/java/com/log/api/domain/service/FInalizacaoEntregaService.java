package com.log.api.domain.service;

import com.log.api.domain.model.Entrega;
import com.log.api.domain.model.StatusEntrega;
import com.log.api.domain.repository.EntregaRepository;
import com.log.api.exception.NegocioException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class FInalizacaoEntregaService {

    private BuscaEntregaService buscaEntregaService;
    private EntregaRepository entregaRepository;

    @Transactional
    public void finalizar(Long id){
        Entrega entrega = buscaEntregaService.buscar(id);

        entrega.finalizar();

        entregaRepository.save(entrega);
    }
}
