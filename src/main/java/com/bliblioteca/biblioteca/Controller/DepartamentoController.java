package com.bliblioteca.biblioteca.Controller;


import com.bliblioteca.biblioteca.Exeption.ResourceNotFoundException;
import com.bliblioteca.biblioteca.Model.Departamento;
import com.bliblioteca.biblioteca.Repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository departamentoRepository;


    @GetMapping("/all")
    private List<Departamento> listaDepartamento() {
        return departamentoRepository.findAll();
    }


    @PostMapping("/save")
    public Departamento guardarDepartamento(@RequestBody Departamento departamento) {
        return departamentoRepository.save(departamento);
    }


    @GetMapping({"/id"})
    public ResponseEntity<Departamento> busacarDepartamentoporId(@RequestParam long id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado" +  id));
        return ResponseEntity.ok().body(departamento);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<Departamento> editarDepartamento(@PathVariable long id, @RequestBody Departamento departamentoRequets) {
        Departamento departamento = departamentoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("cliente no encontrado : " + id));

        departamento.setNombre(departamentoRequets.getNombre());
        Departamento departamentoActualizado= departamentoRepository.save(departamento);
        return ResponseEntity.ok(departamentoActualizado);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarDepartamento(@PathVariable Long id) {
        Departamento departamento = departamentoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("departemento no encontrado : " + id));
        departamentoRepository.delete(departamento);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }






}
