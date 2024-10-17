package com.bliblioteca.biblioteca.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "genero_persona")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GeneroPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

}