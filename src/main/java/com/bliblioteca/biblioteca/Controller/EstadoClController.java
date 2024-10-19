package com.bliblioteca.biblioteca.Controller;


import com.bliblioteca.biblioteca.Exeption.ResourceNotFoundException;
import com.bliblioteca.biblioteca.Model.EstadoCl;
import com.bliblioteca.biblioteca.Repository.EstadoClRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/estadoCI")
public class EstadoClController {


    @Autowired
    private EstadoClRepository estadoClRepository;



    @GetMapping("/all")
    private List<EstadoCl> ListEstadoCl() {
        return estadoClRepository.findAll();
    }


    @PostMapping("/save")
    public EstadoCl guardarCliente(@RequestBody EstadoCl estadoCl) {
        return estadoClRepository.save(estadoCl);
    }

    @GetMapping("{id}")
    public ResponseEntity<EstadoCl> ListarEstadoClPorId(@PathVariable long id) {
        EstadoCl estadoCl = estadoClRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstadoCle no encontrado : " + id));
        return ResponseEntity.ok(estadoCl);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<EstadoCl> editarEstadoCl(@PathVariable long id, @RequestBody EstadoCl estadoClRequest) {
        EstadoCl estadoCl = estadoClRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("EstadoCl no encontrado : " + id));

        estadoCl.setEstado(estadoClRequest.getEstado());
        EstadoCl estadoClActualizado = estadoClRepository.save(estadoCl);
        return ResponseEntity.ok(estadoClActualizado);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEstadoCl(@PathVariable long id) {
        EstadoCl estadoCl= estadoClRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("EstadoCl no encontrado : " + id));
        estadoClRepository.delete(estadoCl);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }






}
