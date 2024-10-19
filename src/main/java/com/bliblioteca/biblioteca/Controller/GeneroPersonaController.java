package com.bliblioteca.biblioteca.Controller;


import com.bliblioteca.biblioteca.Exeption.ResourceNotFoundException;
import com.bliblioteca.biblioteca.Model.GeneroLiterario;
import com.bliblioteca.biblioteca.Model.GeneroPersona;
import com.bliblioteca.biblioteca.Repository.GeneroPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/generopersona")
public class GeneroPersonaController {

    @Autowired
    private GeneroPersonaRepository generoPersonaRepository;

    @GetMapping("/all")
    private List<GeneroPersona> ListarGeneroPersona() {
        return generoPersonaRepository.findAll();
    }

    @PostMapping("/save")
    public GeneroPersona guardarPersona(@RequestBody GeneroPersona generoPersona) {
        return generoPersonaRepository.save(generoPersona);
    }

    @GetMapping("{id}")
    public ResponseEntity<GeneroPersona> ListarGeneroPersonaPorId(@PathVariable long id) {
        GeneroPersona generoPersona = generoPersonaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genero persona no encontrado : " + id));
        return ResponseEntity.ok(generoPersona);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<GeneroPersona> editarPersona(@PathVariable long id, @RequestBody GeneroLiterario GeneroPersonaRequest) {
        GeneroPersona generoPersona = generoPersonaRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("cliente no encontrado : " + id));

        generoPersona.setNombre(GeneroPersonaRequest.getNombre());
        GeneroPersona generoPersonaActualizado =generoPersonaRepository.save(generoPersona);
        return ResponseEntity.ok(generoPersonaActualizado);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCliente(@PathVariable long id) {
        GeneroPersona generoPersona = generoPersonaRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Genero Literariono encontrado : " + id));
        generoPersonaRepository.delete(generoPersona);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }






}
