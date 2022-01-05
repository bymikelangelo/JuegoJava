package mapa;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mapa.cuadro.Cuadro;

public class MapaCargado extends Mapa{
	
	private int[] pixeles;  // corresponde a cada pixel que contiene la imagen cargada
	
	public MapaCargado(String ruta) {  // recibe el nivel traducido como mapa de pixeles de colores 
		super(ruta);
	}

	protected void cargarMapa(String ruta) {
		try {
			BufferedImage mapaPixel = ImageIO.read(this.getClass().getResource(ruta));
			
			this.alto = mapaPixel.getHeight();
			this.ancho = mapaPixel.getWidth();
			
			this.cuadrosCatalogo = new Cuadro[ancho * alto];
			this.pixeles = new int[ancho * alto];
			
			mapaPixel.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void generarMapa() {
		for (int i = 0; i < pixeles.length; i++) {
			switch(pixeles[i]) {
			case 0xff000000:
				cuadrosCatalogo[i] = Cuadro.ASFALTO;
				continue;
			case 0xffeeff00:
				cuadrosCatalogo[i] = Cuadro.COLUMNA;
				continue;
			case 0xffb6be4b:
				cuadrosCatalogo[i] = Cuadro.COLUMNA_INFERIOR;
				continue;
			case 0xff6a6f2a:
				cuadrosCatalogo[i] = Cuadro.COLUMNA_SUPERIOR;
				continue;
			case 0xffff0600:
				cuadrosCatalogo[i] = Cuadro.FUEGO;
				continue;
			case 0xff4dbb00:
				cuadrosCatalogo[i] = Cuadro.HIERBA;
				continue;
			case 0xffff8500:
				cuadrosCatalogo[i] = Cuadro.PUERTA_INF_DER;
				continue;
			case 0xffcc6b00:
				cuadrosCatalogo[i] = Cuadro.PUERTA_INF_IZQ;
				continue;
			case 0xff773e00:
				cuadrosCatalogo[i] = Cuadro.PUERTA_SUP_DER;
				continue;
			case 0xff472500:
				cuadrosCatalogo[i] = Cuadro.PUERTA_SUP_IZQ;
				continue;
			case 0xffffb400:
				cuadrosCatalogo[i] = Cuadro.SUELO;
				continue;
			default:
				cuadrosCatalogo[i] = Cuadro.VACIO;
			}
		}
	}
}
