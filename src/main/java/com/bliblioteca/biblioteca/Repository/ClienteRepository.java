package com.bliblioteca.biblioteca.Repository;

import com.bliblioteca.biblioteca.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}