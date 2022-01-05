package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HojaSprites {
	public final int[] pixeles;
	private final int ancho;
	private final int alto;
	
	// coleccion hojas de Sprites
	public static HojaSprites nivelPrueba = new HojaSprites("/texturas/hojaNivelPrueba.png", 256, 256);
	public static HojaSprites mago= new HojaSprites("/texturas/hojaMago2.png", 256, 256);
	public static HojaSprites samus = new HojaSprites("/texturas/samus.png", 256, 256);
	// fin de la correcion
	
	public HojaSprites(final String ruta, final int ancho, final int alto) {  // final, en este caso, permite ahorrar recursos de calculo al ordenador
		this.ancho = ancho;
		this.alto = alto;
		
		pixeles = new int[ancho * alto];
		
		try {
			BufferedImage imagen = ImageIO.read(this.getClass().getResource(ruta));
			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, alto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public int getAncho() {
		return ancho;
	}
	
	public int getAlto() {
		return alto;
	}
	
	
}
