package com.uevitondev.springweb.services;

import com.uevitondev.springweb.domain.Categoria;
import com.uevitondev.springweb.exceptions.DataintegrityViolationException;
import com.uevitondev.springweb.exceptions.ObjectNotFoundException;
import com.uevitondev.springweb.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findCategorias() {
        return categoriaRepository.findAll();
    }


    public Categoria findCategoriaById(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insertCategoria(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(Categoria categoria) {
        findCategoriaById(categoria.getId());
        return categoriaRepository.save(categoria);
    }

    public void deleteCategoriaById(Integer id) {
        findCategoriaById(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataintegrityViolationException("Não é possível excluir uma Categoria que possui produtos!");
        }
    }
    public List<Categoria> findAllCategorias() {
        return categoriaRepository.findAll();
    }



}
