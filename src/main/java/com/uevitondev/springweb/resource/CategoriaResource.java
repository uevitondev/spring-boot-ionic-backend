package com.uevitondev.springweb.resource;


import com.uevitondev.springweb.domain.Categoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {


    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {

        Categoria cat1 = new Categoria(1, "Informática");
        Categoria cat2 = new Categoria(2, "Escritório");

        List<Categoria> listaCategorias = Arrays.asList(cat1, cat2);

        return ResponseEntity.ok().body(listaCategorias);
    }

}
