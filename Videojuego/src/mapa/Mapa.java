package mapa;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;

public abstract class Mapa {  // no es instanciable
	protected int ancho;
	protected int alto;
	
	protected int[] cuadros;  //tiles en ingles. 
	protected Cuadro[] cuadrosCatalogo;
	
	public Mapa(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;
		cuadros = new int[ancho * alto];
		generarMapa();
	}

	public Mapa(String ruta) {
		cargarMapa(ruta);
		generarMapa();
	}

	protected void generarMapa() {  // protected permite sobreescribir el metodo en clases herederas
		
	}
	
	protected void cargarMapa(String ruta) {
		
	}
	
	public void actualizar() {
		
	}
	
	public void mostrar(int compensacionX, int compensacionY, Pantalla pantalla) {
		pantalla.setDiferencia(compensacionX, compensacionY);
		
		int oeste = compensacionX >> 5;  // igual a dividir entre 32 (bit shifting)
		int este = (compensacionX + pantalla.getAncho() + Cuadro.LADO ) >> 5;
		int norte = compensacionY >> 5;
		int sur = (compensacionY + pantalla.getAlto() + Cuadro.LADO) >> 5; 
		
		for (int y = norte; y < sur; y++) {
			for (int x = oeste; x < este; x++) {
				// getCuadro(x, y).mostrar(x, y, pantalla);
				if (x < 0 || y < 0 || x >= ancho || y >= alto) {
					Cuadro.VACIO.mostrar(x, y, pantalla);
				}
				else {
					this.cuadrosCatalogo[x + y * ancho].mostrar(x, y, pantalla);
				}
			}
		}
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
	
//	public Cuadro getCuadro(final int x, final int y) {
//		if (x < 0 || y < 0 || x >= ancho || y >= alto) {  // impide salir de las afueras del array que contiene el mapa
//			return Cuadro.VACIO;
//		}
//		switch (cuadros[x + y * ancho]) {
//		case 0: 
//			return Cuadro.ASFALTO;
//		case 1: 
//			return Cuadro.PUERTA_SUP_IZQ;
//		default:
//			return Cuadro.VACIO;
//		}
//	}
} 
