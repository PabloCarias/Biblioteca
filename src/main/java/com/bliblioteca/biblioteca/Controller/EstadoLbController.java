package com.bliblioteca.biblioteca.Controller;


import com.bliblioteca.biblioteca.Exeption.ResourceNotFoundException;
import com.bliblioteca.biblioteca.Model.EstadoLb;
import com.bliblioteca.biblioteca.Repository.EstadoLbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/estadolb")
public class EstadoLbController {

    @Autowired
    private EstadoLbRepository estadoLbRepository;



    @GetMapping("/all")
    private List<EstadoLb> Listarlb() {
        return estadoLbRepository.findAll();
    }


    @PostMapping("/save")
    public EstadoLb guardarlb(@RequestBody EstadoLb estadoLb){
        return estadoLbRepository.save(estadoLb);
    }

    @GetMapping("{id}")
    public ResponseEntity<EstadoLb> ListarestadoLBpId(@PathVariable long id) {
        EstadoLb estadoLb = estadoLbRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstadoLB no encontrado : " + id));
        return ResponseEntity.ok(estadoLb);
    }




    @PutMapping("/edit/{id}")
    public ResponseEntity<EstadoLb> editarEstadoLB(@PathVariable long id, @RequestBody EstadoLb estadoLbRequest) {
        EstadoLb estadoLb = estadoLbRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("EstadoLB no encontrado : " + id));

        estadoLb.setEstado(estadoLbRequest.getEstado());
        EstadoLb estadoLBActualizado = estadoLbRepository.save(estadoLb);

        return ResponseEntity.ok(estadoLBActualizado);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEstadolb(@PathVariable long id) {
        EstadoLb estadoLb = estadoLbRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("EstadoLB no encontrado : " + id));
        estadoLbRepository.delete(estadoLb);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }








}
