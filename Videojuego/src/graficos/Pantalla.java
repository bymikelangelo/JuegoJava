package graficos;

import entes.criaturas.Jugador;
import mapa.cuadro.Cuadro;

public final class Pantalla {

	private final int ancho;
	private final int alto;
	
	private int diferenciaX;
	private int diferenciaY;
	
	public final int[] pixeles;
	
	// temporal
//	private final static int LADO_SPRITE = 32;
//	private final static int MASCARA_SPRITE = LADO_SPRITE - 1;
	// fin de temporal
	
	public Pantalla(final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;
		pixeles = new int[ancho * alto];
	}
	
	/*
	 * por cada frame pinta la pantalla de negro y redibuhja los graficos. 
	 * Esto es debido a que queremos evitar que los graficos se "arrastren" por la pantalla 
	 * consiguiendo un mejor efecto de animacion
	 */
	public void limpiar() {
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = 0;  //equivalente al color negro en codigo hex "000000"
		}
	}
	
	// temporal
//	public void mostrar(final int compensacionX, final int compensacionY) {
//		for (int y = 0; y < alto; y++) {  //entra en la primera linea de la pantalla
//			int posicionY = y + compensacionY;
//			if (posicionY < 0 || posicionY >= alto) {
//				continue;  //sale del bucle for cuando el grafico sale del tama�o de la pantalla
//			}
//			
//			for (int x = 0; x < ancho; x++) {  //dibuja cada pixel X de la linea Y del primer bucle
//				int posicionX = x + compensacionX;
//				if (posicionX < 0 || posicionX >= ancho) {
//					continue; 
//				}
//				
//				// temporal
//				pixeles[posicionX + posicionY * ancho] = Sprite.ASFALTO.pixeles
//						[(x & MASCARA_SPRITE) + (y & MASCARA_SPRITE) * LADO_SPRITE]; 
//			}
//		}
//	}
	//fin de temporal
	
	public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro) {
		compensacionX -= diferenciaX;  // compensacionX = compensacionX - diferenciaX
		compensacionY -= diferenciaY;
		
		for (int y = 0; y < cuadro.sprite.getLado(); y++) {
			int posicionY = y + compensacionY;
			
			for (int x = 0; x < cuadro.sprite.getLado(); x++) {
				int posicionX = x + compensacionX;
				// la sentencia permite que no se dibuje por pantalla los graficos que no pueden verse.
				if (posicionX < 0 - cuadro.sprite.getLado() || posicionX >= ancho
				|| posicionY < 0 - cuadro.sprite.getLado() || posicionY >= alto) {
					break;
				}
				if (posicionX < 0) {
					posicionX = 0;
				}
				if (posicionY < 0) {
					posicionY = 0;
				}
				
				pixeles[posicionX + posicionY * ancho] = cuadro.sprite.pixeles[x + y * cuadro.sprite.getLado()];
			}
		} 
	}
	
	public void mostrarJugador(int compensacionX, int compensacionY, Jugador jugador) {
		compensacionX -= diferenciaX;  // compensacionX = compensacionX - diferenciaX
		compensacionY -= diferenciaY;
		
		for (int y = 0; y < jugador.getSprite().getLado(); y++) {
			int posicionY = y + compensacionY;
			
			for (int x = 0; x < jugador.getSprite().getLado(); x++) {
				int posicionX = x + compensacionX;
				// la sentencia permite que no se dibuje por pantalla los graficos que no pueden verse.
				if (posicionX < 0 - jugador.getSprite().getLado() || posicionX >= ancho
				|| posicionY < 0 - jugador.getSprite().getLado() || posicionY >= alto) {
					break;
				}
				if (posicionX < 0) {
					posicionX = 0;
				}
				if (posicionY < 0) {
					posicionY = 0;
				}
				
				// a traves de esta sentencia excluimos el color del pixel que representa al croma o fondo de la hoja de Sprites.
				// conseguimos que no se copien los pixeles que no corresponden al sprite en la pantalla.
				if (jugador.getSprite().pixeles[x + y * jugador.getSprite().getLado()] != 0xffff00ff) {
					pixeles[posicionX + posicionY * ancho] = jugador.getSprite().pixeles[x + y * jugador.getSprite().getLado()];
				}
			}
		} 
	}
	
	public void setDiferencia(final int diferenciaX, final int diferenciaY) {
		this.diferenciaX = diferenciaX;
		this.diferenciaY = diferenciaY;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
		
}
