package com.bliblioteca.biblioteca.Repository;

import com.bliblioteca.biblioteca.Model.DetallePrestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePrestamoRepository extends JpaRepository<DetallePrestamo, Long> {
}