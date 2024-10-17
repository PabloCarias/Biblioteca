package com.bliblioteca.biblioteca.Repository;

import com.bliblioteca.biblioteca.Model.Penalizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenalizacionRepository extends JpaRepository<Penalizacion, Long> {
}