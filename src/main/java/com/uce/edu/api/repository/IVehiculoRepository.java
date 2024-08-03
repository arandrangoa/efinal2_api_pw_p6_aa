package com.uce.edu.api.repository;

import java.util.List;

import com.uce.edu.api.repository.modelo.Vehiculo;
import com.uce.edu.api.repository.modelo.VehiculoDTO;

public interface IVehiculoRepository {
	
    public void insertar(Vehiculo vehiculo);

    public Vehiculo seleccionarPorPlaca(String placa);

    public void eliminar(String placa);

    public List<Vehiculo> buscarTodos();
	
	
}
