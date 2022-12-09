package com.uevitondev.springweb.services;

import com.uevitondev.springweb.domain.Cliente;
import com.uevitondev.springweb.exceptions.ObjectNotFoundException;
import com.uevitondev.springweb.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarClienteById(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Entidade não encontrada! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }


}
