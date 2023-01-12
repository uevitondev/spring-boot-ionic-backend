package com.uevitondev.springweb.services;

import com.uevitondev.springweb.domain.Cidade;
import com.uevitondev.springweb.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> findCidadesByEstadoId(Integer estadoId) {
        return cidadeRepository.findCidades(estadoId);
    }

}
