package com.bliblioteca.biblioteca.Controller;


import com.bliblioteca.biblioteca.Exeption.ResourceNotFoundException;
import com.bliblioteca.biblioteca.Model.Cliente;
import com.bliblioteca.biblioteca.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

  @Autowired
   private ClienteRepository clienteRepository;

  @GetMapping("/all")
 private List<Cliente> ListarClientes(){
      return clienteRepository.findAll();
  }

  @PostMapping("/save")
    public Cliente SalvarCliente(@RequestBody Cliente cliente){
      return clienteRepository.save(cliente);
  }

  @GetMapping("{id}")
  public ResponseEntity<Cliente> BuscarClienteporID(@PathVariable long id){
      Cliente cliente = clienteRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado ." + id));
              return ResponseEntity.ok(cliente);
  }



  @PutMapping("/edit/{id}")
    public ResponseEntity<Cliente> ActualizarCliente(@PathVariable long id, @RequestBody Cliente clienteRequest){
      Cliente cliente =  clienteRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado ." + id));
      cliente.setDpi(clienteRequest.getDpi());
      cliente.setNombres(clienteRequest.getNombres());
      cliente.setApellidos(clienteRequest.getApellidos());
      cliente.setGeneroPersona(clienteRequest.getGeneroPersona());
      cliente.setFechaNacimiento(clienteRequest.getFechaNacimiento());
      cliente.setIdioma(clienteRequest.getIdioma());
      cliente.setGrupoEtnico(clienteRequest.getGrupoEtnico());
      cliente.setNivelEscolar(clienteRequest.getNivelEscolar());
      cliente.setTelefono(clienteRequest.getTelefono());
      cliente.setEmail(clienteRequest.getEmail());
      cliente.setMunicipio(clienteRequest.getMunicipio());
      cliente.setEstadoCl(clienteRequest.getEstadoCl());
      Cliente clienteActualizado = clienteRepository.save(cliente);
      return ResponseEntity.ok(clienteActualizado);
  }


  @DeleteMapping("/delete/{id}")
    public ResponseEntity<Cliente> EliminarCliente(@PathVariable long id){
     Cliente cliente = clienteRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado :" + id));
     clienteRepository.delete(cliente);
      Map<String, Boolean> response = new HashMap<>();
     response.put("eliminado", Boolean.TRUE);
     return ResponseEntity.ok(cliente);
  }

}
