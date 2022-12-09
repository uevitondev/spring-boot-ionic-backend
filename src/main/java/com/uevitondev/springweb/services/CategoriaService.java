package com.uevitondev.springweb.services;

import com.uevitondev.springweb.domain.Categoria;
import com.uevitondev.springweb.exceptions.ObjectNotFoundException;
import com.uevitondev.springweb.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }


    public Categoria buscarCategoriaById(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }


}
