package mapa;

import java.util.Random;

public class MapaRandom extends Mapa {
	
	private static final Random random = new Random();

	public MapaRandom(int ancho, int alto) {
		super(ancho, alto);
	}

	protected void generarMapa() {
		for (int y = 0; y < alto; y++) {
			for (int x = 0; x < ancho; x++) {
				cuadros[x + y * ancho] = random.nextInt(3);
			}
		}
	}
}
