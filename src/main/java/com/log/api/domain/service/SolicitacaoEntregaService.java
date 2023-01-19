package com.log.api.domain.service;

import com.log.api.domain.model.Cliente;
import com.log.api.domain.model.Entrega;
import com.log.api.domain.model.StatusEntrega;
import com.log.api.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

    private CatalogoClienteService catalogoClienteService;
    private EntregaRepository entregaRepository;

    @Transactional
    public Entrega solicitar (Entrega entrega){
        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
        entrega.setCliente(cliente);

        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }
}
