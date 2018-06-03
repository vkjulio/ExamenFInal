package Umg;

public class Nodo {
	
	int codigo;
	String nombre;
	Nodo siguiente;
	private byte[] bytesNombre;

	public byte[] getBytesNombre() {
		return bytesNombre;
	}

	public void setBytesNombre(byte[] bytesNombre) {
		this.bytesNombre = bytesNombre;
		nombre = new String(bytesNombre);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		bytesNombre = new byte[20]; 
		for (int i = 0; i < nombre.length(); i++) {
			bytesNombre[i] = (byte) nombre.charAt(i);
		}
	}

	public Nodo getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Nodo siguiente) {
		this.siguiente = null;
	}


}
