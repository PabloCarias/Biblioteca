package com.bliblioteca.biblioteca.Controller;


import com.bliblioteca.biblioteca.Exeption.ResourceNotFoundException;
import com.bliblioteca.biblioteca.Model.GeneroLiterario;
import com.bliblioteca.biblioteca.Repository.GeneroLiterarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/generloLiterario")
public class GeneroLiterarioController {

    @Autowired
    private GeneroLiterarioRepository generoLiterarioRepository;


    @PostMapping("/save")
    public GeneroLiterario guardarCliente(@RequestBody GeneroLiterario generoLiterario) {
        return generoLiterarioRepository.save(generoLiterario);
    }


    @GetMapping("{id}")
    public ResponseEntity<GeneroLiterario> ListarGeneroLiterarioPorId(@PathVariable long id) {
        GeneroLiterario generoLiterario = generoLiterarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genero Literario no encontrado : " + id));
        return ResponseEntity.ok(generoLiterario);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<GeneroLiterario> editarCliente(@PathVariable long id, @RequestBody GeneroLiterario generoLiterarioRequest) {
        GeneroLiterario generoLiterario = generoLiterarioRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("cliente no encontrado : " + id));

        generoLiterario.setNombre(generoLiterarioRequest.getNombre());
        GeneroLiterario generoLiterarioActualizado = generoLiterarioRepository.save(generoLiterario);
        return ResponseEntity.ok(generoLiterarioActualizado);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCliente(@PathVariable long id) {
        GeneroLiterario generoLiterario = generoLiterarioRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Genero Literariono encontrado : " + id));
        generoLiterarioRepository.delete(generoLiterario);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }





}
