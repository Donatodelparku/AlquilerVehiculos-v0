package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;


public class Clientes {

	private List<Cliente> coleccionClientes;
	
	public Clientes() {
		coleccionClientes = new ArrayList<>();
	}

	//mal, no hace lo que deberia
	public List<Cliente> get() {
		return new ArrayList<>(coleccionClientes);
	}
	
	public int getCantidad() {
		return coleccionClientes.size();
	}

	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}
		if (coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
		}
		coleccionClientes.add(cliente);
	}
	
	public Cliente buscar(Cliente cliente) { 
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		}
		int indice = coleccionClientes.indexOf(cliente);
		if (indice != -1) {
			return coleccionClientes.get(indice);
		} else {
			return null;
		}
	}
	
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}
		if (!coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
		coleccionClientes.remove(cliente);
	}
	
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
		}
		
		if (coleccionClientes.indexOf(cliente) == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
	
		if (nombre == null && telefono == null) {
			
		}else {
			if (nombre == null) {
				buscar(cliente).setTelefono(telefono);
			} else if (nombre.isBlank()) {
				buscar(cliente).setTelefono(telefono);
			}
			if (telefono == null) {
				buscar(cliente).setNombre(nombre);
			} else if (telefono.isBlank()) {
				buscar(cliente).setNombre(nombre);
			}
			if (nombre != null && telefono != null) {
				if (!nombre.isBlank() && !telefono.isBlank()) {
					buscar(cliente).setTelefono(telefono);
					buscar(cliente).setNombre(nombre);
				}
			}			
		}
	}
}
