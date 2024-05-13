package com.rphmota.transportadoradesafio.application.service;

import com.rphmota.transportadoradesafio.domain.entity.Cliente;
import com.rphmota.transportadoradesafio.domain.repository.ClienteRepository;
import com.rphmota.transportadoradesafio.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    //Injetando o repositorio no construtor da classe de serviço
    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente updatedCliente) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNome(updatedCliente.getNome());
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));
    }

    public void deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));
        clienteRepository.delete(cliente);
    }


}

