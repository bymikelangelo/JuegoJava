package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

public class Cuadro {
	public int x;
	public int y;
	public Sprite sprite;
	
	public static final int LADO = 32;

	// coleccion Cuadros
	public static final Cuadro VACIO = new Cuadro(Sprite.VACIO);
	
	// cuadros nivelPrueba
	public static final Cuadro ASFALTO = new Cuadro(Sprite.ASFALTO);
	public static final Cuadro PUERTA_SUP_IZQ = new Cuadro(Sprite.PUERTA_SUP_IZQ);
	public static final Cuadro PUERTA_SUP_DER  = new Cuadro(Sprite.PUERTA_SUP_DER);
	public static final Cuadro FUEGO = new Cuadro(Sprite.FUEGO);
	public static final Cuadro COLUMNA_SUPERIOR = new Cuadro(Sprite.COLUMNA_SUPERIOR);
	public static final Cuadro COLUMNA = new Cuadro(Sprite.COLUMNA);
	public static final Cuadro COLUMNA_INFERIOR = new Cuadro(Sprite.COLUMNA_INFERIOR);
	public static final Cuadro SUELO = new Cuadro(Sprite.SUELO);
	public static final Cuadro PUERTA_INF_IZQ = new Cuadro(Sprite.PUERTA_INF_IZQ);
	public static final Cuadro PUERTA_INF_DER = new Cuadro(Sprite.PUERTA_INF_DER);
	public static final Cuadro HIERBA = new Cuadro(Sprite.HIERBA);
	// fin nivelPrueba
	
	public static final Cuadro SAMUS = new Cuadro(Sprite.SAMUS);
	// fin de la coleccion
	
	public Cuadro(Sprite sprite) {
		this.sprite = sprite;
	}
	
	//cada objeto grafico se dibuja desde la propia clase, lo que ayuda a optimizar los recursos 
	public void mostrar(int x, int y, Pantalla pantalla) {
		pantalla.mostrarCuadro(x << 5, y << 5, this);
	}
	
	public boolean solido() {
		return false;
	}
}
