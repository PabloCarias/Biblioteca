package com.bliblioteca.biblioteca.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "nivel_escolar")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NivelEscolar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

}