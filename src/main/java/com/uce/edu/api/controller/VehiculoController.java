package com.uce.edu.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uce.edu.api.repository.modelo.Vehiculo;
import com.uce.edu.api.repository.modelo.VehiculoDTO;
import com.uce.edu.api.service.IVehiculoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(path = "/vehiculos")
public class VehiculoController {

    @Autowired
    private IVehiculoService iVehiculoService;

    // http://localhost:8081/API/v1.0/Concesionario/vehiculos
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Vehiculo> guardar(@RequestBody Vehiculo vehiculo) {
        HttpHeaders cabezeras = new HttpHeaders();
        cabezeras.add("mensaje 201", "Vehiculo ingresado con exito");
        this.iVehiculoService.guardar(vehiculo);
        return ResponseEntity.status(201).body(vehiculo);
    }

    // http://localhost:8081/API/v1.0/Consecionario/vehiculos/consulta
    @GetMapping(path = "/consulta", produces = "application/json")
    public ResponseEntity<List<VehiculoDTO>> consultarTodos() {
        List<Vehiculo> vehiculos = iVehiculoService.buscarTodos();
        List<VehiculoDTO> vehiculosDTO = vehiculos.stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(vehiculosDTO);
    }

    private VehiculoDTO convertToDto(Vehiculo vehiculo) {
        VehiculoDTO dto = new VehiculoDTO();
        dto.setPlaca(vehiculo.getPlaca());
        dto.setMarca(vehiculo.getMarca());
        dto.setModelo(vehiculo.getModelo());
        return dto;
    }

    // http://localhost:8081/API/v1.0/Consecionario/vehiculos/{placa}
    @GetMapping(path = "/{placa}", produces = "application/json")
    public ResponseEntity<Vehiculo> buscarPorPlaca(@PathVariable("placa") String placa) {
        Vehiculo vehiculo = iVehiculoService.buscarPorPlaca(placa);
        if (vehiculo != null) {
            return ResponseEntity.ok(vehiculo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // http://localhost:8081/API/v1.0/Consecionario/vehiculos/borrar/{placa}
    @DeleteMapping(path = "/borrar/{placa}")
    public ResponseEntity<Void> borrar(@PathVariable("placa") String placa) {
        this.iVehiculoService.borrar(placa);
        HttpHeaders cabezeras = new HttpHeaders();
        cabezeras.add("mensaje 200", "Vehiculo borrado con exito");
        return ResponseEntity.status(201).build();
    }
}

