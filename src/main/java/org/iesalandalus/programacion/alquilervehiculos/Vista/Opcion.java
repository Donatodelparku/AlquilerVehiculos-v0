package org.iesalandalus.programacion.alquilervehiculos.Vista;

public enum Opcion {

	SALIR("Salir"), 
    INSERTAR_CLIENTE("Insertar cliente"), 
    INSERTAR_TURISMO("Insrtar turismo"), 
    INSERTAR_ALQUILER("Insrtar alquiler"),
    BUSCAR_CLIENTE("Buscar cliente"),
    BUSCAR_TURISMO("Buscar turismo"),
    BUSCAR_ALQUILER("Buscar alquiler"),
    MODIFICAR_CLIENTE("Modificar cliente"),
    DEVOLVER_ALQUILER("Devolver alquiler"),
    BORRAR_CLIENTE("Borrar cliente"),
    BORRAR_TURISMO("Borrar turismo"),
    BORRAR_ALQUILER("Borrar alquiler"),
    LISTAR_CLIENTES("Listar clientes"),
    LISTAR_TURISMOS("Listar turismos"),
    LISTAR_ALQUILERES("Listar alquileres"),
    LISTAR_ALQUILERES_CLIENTE("Listar alquileres de cliente"),
    LISTAR_ALQUILERES_TURISMO("Listar alquileres de turismo");
	
	private String opcionAMostrar;
	
	private Opcion(String opcionAMostrar) {
		this.opcionAMostrar = opcionAMostrar;
	}
	
	public boolean esOrdinalValido(int ordinal) {
		return (ordinal >= 0 && ordinal <= values().length - 1);
	}
	
	public Opcion get(int ordinal) {
		if (esOrdinalValido(ordinal))
			return values()[ordinal];
		else
			throw new IllegalArgumentException("ERROR: Ordinal no válido");
	}
	
	@Override
	public String toString() {
		return String.format("%d.- %s", ordinal(), opcionAMostrar);
	}
}
