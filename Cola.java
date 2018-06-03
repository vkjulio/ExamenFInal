package Umg;

import java.io.RandomAccessFile;
import java.util.Scanner;

public class Cola {
	Scanner sc = new Scanner(System.in);
	RandomAccessFile fichero = null;
	private final int totalBytes = 25;
	private Nodo raiz, cima;
	//int contador = 1;

	public Cola() {
		raiz = null;
		cima = null;

	}

	public boolean colaVacia() {
		if (raiz == null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean insertar() {
		boolean resultado = false;
		//int cant = contador;
		try {
			Nodo nuevo = new Nodo();
			System.out.println("Ingrese el codigo de la muestra");
			nuevo.setCodigo(sc.nextInt());
			System.out.println("Ingrese el nombre de la muestra");
			String strNombre = "";
			int longitud = 0;
			do {
				strNombre = sc.nextLine();
				longitud = strNombre.length();
				if (longitud <= 0 || longitud > 20) {
					System.out.println("la longitud del nombre es un maximo de 20 caracteres");
				}
			} while (longitud <= 0 || longitud > 20);
			nuevo.setNombre(strNombre);
			fichero.seek(fichero.length());
			//fichero.writeInt(cant);
			fichero.writeInt(nuevo.getCodigo());
			fichero.write(nuevo.getBytesNombre());
			fichero.write("\n".getBytes()); 
			nuevo.siguiente = null;
			
			if (colaVacia()) {
				raiz = nuevo;
				cima = nuevo;
			} else {
				cima.siguiente = nuevo;
				cima = nuevo;
			}
			resultado = true;

		} catch (Exception e) {
			resultado = false;
			System.out.println("Error al agregar el registro " + e.getMessage());
		}
		return resultado;
	}

	public int quitar() {
		if (colaVacia()) {
			System.out.println("Cola Vacia");
			return -1;
		}
		int aux = raiz.getCodigo();
		String aud = raiz.getNombre();

		if (raiz == cima) {
			raiz = null;
			cima = null;
		} else {
			raiz = raiz.siguiente;
		}
		System.out.println("Eliminado" + aux);
		System.out.println("Eliminado" + aud);
		return aux;
	}

	public void CargarDatos() {
		try {
			long longitud = fichero.length();
			if (longitud <= 0) {
				System.out.println("No hay registros");
				return; 
			}
			fichero.seek(0);
			Nodo a;
			while (longitud >= totalBytes) {
				a = new Nodo();
				a.setCodigo(fichero.readInt());
				byte[] bNombre = new byte[20]; 
				fichero.read(bNombre);
				a.setBytesNombre(bNombre);
				fichero.readByte();
				//System.out.println("Muestra No." + contador);
				System.out.println("Codigo: " + a.getCodigo());
				System.out.println("Nombre: " + a.getNombre());
				
				longitud -= totalBytes;
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());

		}
	}
	
	public void listar(){
		Nodo t = raiz;
		while (t != null) {
			System.out.println(t.codigo);
			System.out.println(t.nombre);
			t = t.siguiente;
		}
	}
}
