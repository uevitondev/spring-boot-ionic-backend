package com.uevitondev.springweb.resource;

import com.uevitondev.springweb.domain.Cidade;
import com.uevitondev.springweb.domain.Estado;
import com.uevitondev.springweb.dto.CidadeDTO;
import com.uevitondev.springweb.dto.EstadoDTO;
import com.uevitondev.springweb.services.CidadeService;
import com.uevitondev.springweb.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeService cidadeService;


    @GetMapping
    public ResponseEntity<List<EstadoDTO>> findAll() {
        List<Estado> listEstados = estadoService.findAll();
        List<EstadoDTO> listaEstadosDto = listEstados.stream().map(estado -> new EstadoDTO(estado)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaEstadosDto);
    }

    @GetMapping(value = "/{estadoId}/cidades")
    public ResponseEntity<List<CidadeDTO>> findAllCidadesEstadoId(@PathVariable Integer estadoId) {
        List<Cidade> listCidades = cidadeService.findCidadesByEstadoId(estadoId);
        List<CidadeDTO> listCidadesDto = listCidades.stream().map(cidade -> new CidadeDTO(cidade)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listCidadesDto);
    }


}
