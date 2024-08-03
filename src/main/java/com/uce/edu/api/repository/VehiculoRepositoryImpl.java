package com.uce.edu.api.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.uce.edu.api.repository.modelo.Vehiculo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class VehiculoRepositoryImpl implements IVehiculoRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Vehiculo vehiculo) {
		this.entityManager.persist(vehiculo);
	}

	@Override
	public Vehiculo seleccionarPorPlaca(String placa) {
		TypedQuery<Vehiculo> query = this.entityManager.createQuery("select v from Vehiculo v where v.placa = :datoPlaca", Vehiculo.class);
		query.setParameter("datoPlaca", placa);
		return query.getSingleResult();
	}

	@Override
	public void eliminar(String placa) {
		Vehiculo vehiculo = this.seleccionarPorPlaca(placa);
		this.entityManager.remove(vehiculo);
	}
	
	@Override
	public List<Vehiculo> buscarTodos() {
		TypedQuery<Vehiculo> query = this.entityManager.createQuery("SELECT v FROM Vehiculo v", Vehiculo.class);
		return query.getResultList();
	}
}





