package com.bliblioteca.biblioteca.Repository;

import com.bliblioteca.biblioteca.Model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}