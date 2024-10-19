package com.bliblioteca.biblioteca.Controller;




import com.bliblioteca.biblioteca.Exeption.ResourceNotFoundException;
import com.bliblioteca.biblioteca.Model.Penalizacion;
import com.bliblioteca.biblioteca.Repository.PenalizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/penalizacion")
public class PenalizacionController {


    @Autowired
    private PenalizacionRepository penalizacionRepository;


    @GetMapping("/all")
    private List<Penalizacion> ListarAutores(){
        return penalizacionRepository.findAll();
    }

    @PostMapping("/save")
    public Penalizacion guardarpenalizacion(@RequestBody Penalizacion penalizacion){
        return penalizacionRepository.save(penalizacion);
    }

    @GetMapping("{id}")
    public ResponseEntity<Penalizacion> buscarporID(@PathVariable long id){
        Penalizacion penalizacion = penalizacionRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Penalizacion no encontrado: "+id));
        return ResponseEntity.ok(penalizacion);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<Penalizacion> editarpenalizacion(@PathVariable long id, @RequestBody Penalizacion penalizacionRequest){

        Penalizacion penalizacion = penalizacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Penalizacion id :"+ id));

        penalizacion.setCliente(penalizacionRequest.getCliente());
        penalizacion.setPrestamos(penalizacionRequest.getPrestamos());
        penalizacion.setTiempoPenalizacion(penalizacionRequest.getTiempoPenalizacion());
        penalizacion.setFechaPenalizacion(penalizacion.getFechaPenalizacion());
        Penalizacion penalizacionActualizado = penalizacionRepository.save(penalizacion);
        return ResponseEntity.ok(penalizacionActualizado);

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> BorrarPenalizacion(@PathVariable long id){
        Penalizacion penalizacion = penalizacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Penalizacion no encontrado: "+id));
        penalizacionRepository.delete(penalizacion);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }









}
