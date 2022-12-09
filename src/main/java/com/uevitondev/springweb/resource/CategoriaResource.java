package com.uevitondev.springweb.resource;


import com.uevitondev.springweb.domain.Categoria;
import com.uevitondev.springweb.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> lista = categoriaService.listarCategorias();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> listarCategoriaById(@PathVariable Integer id) {
        Categoria categoria = categoriaService.buscarCategoriaById(id);
        return ResponseEntity.ok().body(categoria);
    }


}
