package com.api.log.controller.domain.service;

import com.api.log.controller.domain.model.Cliente;
import com.api.log.exception.NegocioException;
import com.api.log.repository.CLienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CatalogoClienteService {

    private CLienteRepository cLienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente){

        boolean emailEmUso = cLienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
        if (emailEmUso){
            throw new NegocioException("Já existe um cliente cadastrado com esse email");
        }

       return cLienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long id){
        cLienteRepository.deleteById(id);
    }

}
