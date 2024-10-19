package com.bliblioteca.biblioteca.Controller;


import com.bliblioteca.biblioteca.Exeption.ResourceNotFoundException;
import com.bliblioteca.biblioteca.Model.Idioma;
import com.bliblioteca.biblioteca.Repository.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/idioma")
public class IdiomaController {


    @Autowired
    private IdiomaRepository idiomaRepository;

    @GetMapping("/all")
    private List<Idioma> ListarIdioma() {
        return idiomaRepository.findAll();
    }

    @PostMapping("/save")
    public Idioma guardarIdioma(@RequestBody Idioma idioma){
        return idiomaRepository.save(idioma);
    }

    @GetMapping("{id}")
    public ResponseEntity<Idioma> ListarIdiomaPorId(@PathVariable long id) {
        Idioma idioma = idiomaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Idioma no encontrado : " + id));
        return ResponseEntity.ok(idioma);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Idioma> editarIdioma(@PathVariable long id, @RequestBody Idioma idiomaRequest) {
        Idioma idioma = idiomaRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Idioma no encontrado : " + id));

        idioma.setNombre(idiomaRequest.getNombre());
        Idioma idiomaActualizado = idiomaRepository.save(idioma);
        return ResponseEntity.ok(idiomaActualizado);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminaridioma(@PathVariable long id) {
        Idioma idioma = idiomaRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("cliente no encontrado : " + id));
        idiomaRepository.delete(idioma);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }



}
