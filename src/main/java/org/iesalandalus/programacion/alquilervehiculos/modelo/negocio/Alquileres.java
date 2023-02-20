package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public class Alquileres {

	private List<Alquiler> coleccionAlquileres;

	public Alquileres() {
		coleccionAlquileres = new ArrayList<>();
	}

	public List<Alquiler> get() {
		return new ArrayList<>(coleccionAlquileres);
	}

	public List<Alquiler> get(Cliente cliente) {

		if (cliente == null) {
			throw new NullPointerException("ERROR: El cliente no puede ser nulo.");
		}

		List<Alquiler> listaAlquilerCliente = new ArrayList<>();
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente)) {
				listaAlquilerCliente.add(alquiler);
			}
		}
		return listaAlquilerCliente;
	}

	public List<Alquiler> get(Turismo turismo) {
		if (turismo == null) {
			throw new NullPointerException("ERROR: El cliente no puede ser nulo.");
		}

		List<Alquiler> listaAlquilerTurismo = new ArrayList<>();
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getTurismo().equals(turismo)) {
				listaAlquilerTurismo.add(alquiler);
			}
		}
		return listaAlquilerTurismo;
	}

	public int getCantidad() {
		return coleccionAlquileres.size();
	}

	private void comprobarAlquiler(Cliente cliente, Turismo turismo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {
		for (Alquiler alquiler : coleccionAlquileres) {

			if (alquiler.getFechaDevolucion() == null) {

				if (cliente.equals(alquiler.getCliente())) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
				}
				if (turismo.equals(alquiler.getTurismo())) {
					throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");

				}
			} else {
				if (cliente.equals(alquiler.getCliente()) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
						|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler))) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
				}
				if (turismo.equals(alquiler.getTurismo()) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
						|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler))) {
					throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");

				}
			}
		}
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		comprobarAlquiler(alquiler.getCliente(), alquiler.getTurismo(), alquiler.getFechaAlquiler());
		coleccionAlquileres.add(alquiler);
	}

	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		}

		if (buscar(alquiler) != null) {
			alquiler.devolver(fechaDevolucion);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}

	}

	public Alquiler buscar(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
		int indice = coleccionAlquileres.indexOf(alquiler);
		if (indice != -1) {
			return coleccionAlquileres.get(indice);
		} else {
			return null;
		}
	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}
		if (!coleccionAlquileres.contains(alquiler)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		coleccionAlquileres.remove(alquiler);
	}

}
