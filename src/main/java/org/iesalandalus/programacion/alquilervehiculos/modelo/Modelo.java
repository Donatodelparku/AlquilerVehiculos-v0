package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Alquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Clientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Turismos;

public class Modelo {

	Clientes clientes;
	Turismos turismos;
	Alquileres alquileres;

	public Modelo() {

	}

	public void comenzar() {
		clientes = new Clientes();
		turismos = new Turismos();
		alquileres = new Alquileres();
	}

	public void terminar() {
		System.out.println("¡¡EL PROGRAMA HA FINALIZADO!!");
	}

	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		Cliente clienteCopia = new Cliente(cliente);
		clientes.insertar(clienteCopia);
	}

	public void insertar(Turismo turismo) throws OperationNotSupportedException {
		Turismo turismoCopia = new Turismo(turismo);
		turismos.insertar(turismoCopia);
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		if (clientes.buscar(alquiler.getCliente()) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		}
		if (turismos.buscar(alquiler.getTurismo()) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el turismo del alquiler.");
		}
		Alquiler alquilerCopia = new Alquiler(clientes.buscar(alquiler.getCliente()),
				turismos.buscar(alquiler.getTurismo()), alquiler.getFechaAlquiler());
		alquileres.insertar(alquilerCopia);

	}

	public Cliente buscar(Cliente cliente) {
		Cliente clienteNuevo;
		return clienteNuevo = new Cliente(clientes.buscar(cliente));
	}

	public Turismo buscar(Turismo turismo) {
		Turismo turismoNuevo;
		return turismoNuevo = new Turismo(turismos.buscar(turismo));
	}

	public Alquiler buscar(Alquiler alquiler) {
		Alquiler alquilerNuevo;
		return alquilerNuevo = new Alquiler(alquileres.buscar(alquiler));
	}

	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		clientes.modificar(cliente, nombre, telefono);
	}

	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		Alquiler alquilerABuscar = alquileres.buscar(alquiler);
		if (alquilerABuscar == null) {
			throw new OperationNotSupportedException("ERROR: No existe el alquiler a devolver.");
		}
		alquilerABuscar.devolver(fechaDevolucion);
		/*
		 * me hubiese gustado hacerlo sin crear la variable alquilerABuscar pero la
		 * unica forma que se me ocurria era llamando varias veces al metodo buscar por
		 * lo que me salta un error en los test, seguramente habría otra solución pero
		 * tampoco me quiero calentar la cabeza mucho que aún me queda proyecto
		 */
	}

	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		for (Alquiler alquilerBorrado : alquileres.get(cliente)) {
			borrar(alquilerBorrado);
		}
		clientes.borrar(cliente);
	}

	public void borrar(Turismo turismo) throws OperationNotSupportedException {
		for (Alquiler alquilerBorrado : alquileres.get(turismo)) {
			borrar(alquilerBorrado);
		}
		turismos.borrar(turismo);

	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {

		alquileres.borrar(alquiler);
	}

	public List<Cliente> getClientes() {
		List<Cliente> coleccionClientes = new ArrayList<>();
		for (Cliente cliente : clientes.get()) {
			coleccionClientes.add(new Cliente(cliente));
		}
		return coleccionClientes;
	}

	public List<Turismo> getTurismos() {
		List<Turismo> coleccionTurismos = new ArrayList<>();
		for (Turismo turismo : turismos.get()) {
			coleccionTurismos.add(new Turismo(turismo));
		}
		return coleccionTurismos;
	}

	public List<Alquiler> getAlquileres() {
		List<Alquiler> coleccionAlquileres = new ArrayList<>();
		for (Alquiler alquiler : alquileres.get()) {
			coleccionAlquileres.add(new Alquiler(alquiler));
		}
		return coleccionAlquileres;
	}

	public List<Alquiler> getAlquileres(Cliente cliente) {
		List<Alquiler> coleccionAlquileresClientes = new ArrayList<>();
		for (Alquiler coleccionALquileresAux : alquileres.get(cliente)) {
			coleccionAlquileresClientes.add(new Alquiler(coleccionALquileresAux));
		}

		return coleccionAlquileresClientes;
	}

	public List<Alquiler> getAlquileres(Turismo turismo) {
		List<Alquiler> coleccionAlquileresTurismos = new ArrayList<>();
		for (Alquiler coleccionAlquileresAux : alquileres.get(turismo)) {
			coleccionAlquileresTurismos.add(new Alquiler(coleccionAlquileresAux));
		}

		return coleccionAlquileresTurismos;
	}

}
