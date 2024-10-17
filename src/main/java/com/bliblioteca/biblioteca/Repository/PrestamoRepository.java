package com.bliblioteca.biblioteca.Repository;

import com.bliblioteca.biblioteca.Model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}