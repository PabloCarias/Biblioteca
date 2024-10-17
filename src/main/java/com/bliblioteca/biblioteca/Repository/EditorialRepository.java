package com.bliblioteca.biblioteca.Repository;

import com.bliblioteca.biblioteca.Model.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorialRepository extends JpaRepository<Editorial, Long> {
}