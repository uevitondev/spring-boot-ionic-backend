package com.uevitondev.springweb.repositories;

import com.uevitondev.springweb.domain.Cliente;
import com.uevitondev.springweb.domain.Pedido;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    @Transactional
    Page<Pedido> findByCliente(Cliente cliente, Pageable pageable);
}
