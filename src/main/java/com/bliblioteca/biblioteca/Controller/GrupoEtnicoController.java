package com.bliblioteca.biblioteca.Controller;


import com.bliblioteca.biblioteca.Exeption.ResourceNotFoundException;
import com.bliblioteca.biblioteca.Model.GrupoEtnico;
import com.bliblioteca.biblioteca.Repository.GrupoEtnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grupoEtnico")
public class GrupoEtnicoController {


    @Autowired
    private GrupoEtnicoRepository grupoEtnicoRepository;

    @GetMapping("/all")
    private List<GrupoEtnico> ListarGrupoEtnico() {
        return grupoEtnicoRepository.findAll();
    }

    @PostMapping("/save")
    public GrupoEtnico guardarGrupoEtnico(@RequestBody GrupoEtnico grupoEtnico) {
        return grupoEtnicoRepository.save(grupoEtnico);
    }

    @GetMapping("{id}")
    public ResponseEntity<GrupoEtnico> ListarGrupoEtnicoId(@PathVariable long id) {
        GrupoEtnico  grupoEtnico = grupoEtnicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grupo Etnico no encontrado : " + id));
        return ResponseEntity.ok(grupoEtnico);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<GrupoEtnico> editarGrupoEtnico(@PathVariable long id, @RequestBody GrupoEtnico grupoEtnicoRequest) {
        GrupoEtnico grupoEtnico = grupoEtnicoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Grupo Etnico no encontrado : " + id));

        grupoEtnico.setNombre(grupoEtnicoRequest.getNombre());
        GrupoEtnico grupoEtnicoActualizado = grupoEtnicoRepository.save(grupoEtnico);
        return ResponseEntity.ok(grupoEtnicoActualizado);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarGrupoEtnico(@PathVariable long id) {
        GrupoEtnico grupoEtnico = grupoEtnicoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(" Grupo Etnico no encontrado : " + id));
        grupoEtnicoRepository.delete(grupoEtnico);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }





}
