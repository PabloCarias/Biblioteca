package com.bliblioteca.biblioteca.Controller;


import com.bliblioteca.biblioteca.Exeption.ResourceNotFoundException;
import com.bliblioteca.biblioteca.Model.Editorial;
import com.bliblioteca.biblioteca.Repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Editorial")
public class EditorialController {

    @Autowired
    private EditorialRepository editorialRepository;


    @GetMapping("/all")
    private List<Editorial> EditorialClientes() {
        return editorialRepository.findAll();
    }


    @PostMapping("/save")
    public Editorial guardarEditorial(@RequestBody Editorial editorial) {
        return editorialRepository.save(editorial);
    }


    @GetMapping("{id}")
    public ResponseEntity<Editorial> ListarEditorialPorId(@PathVariable long id) {
        Editorial editorial = editorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("editorial no encontrado : " + id));
        return ResponseEntity.ok(editorial);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Editorial> editarEditorial(@PathVariable long id, @RequestBody Editorial editorialRequest) {
        Editorial editorial = editorialRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("editorial no encontrado : " + id));

        editorial.setNombre(editorialRequest.getNombre());
        Editorial editorialActualizado = editorialRepository.save(editorial);
        return ResponseEntity.ok(editorialActualizado);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEditorial(@PathVariable long id) {
        Editorial editorial = editorialRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("editorial no encontrado : " + id));
        editorialRepository.delete(editorial);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }



}
