package com.uce.edu.api.service;

import java.util.List;

import com.uce.edu.api.repository.modelo.Vehiculo;

public interface IVehiculoService {
	
	public void guardar (Vehiculo vehiculo);
	
	public Vehiculo buscarPorPlaca(String placa);

    public void borrar(String placa);

    public List<Vehiculo> buscarTodos();

	
	
}
