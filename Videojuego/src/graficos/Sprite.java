package graficos;

public final class Sprite {
	private final int lado;  // lado del cuadrado que conforma el Sprite
	private int x;  // posicion X de la hoja de Sprite
	private int y;  // posicion Y de la hoja de Sprite
	public int[] pixeles;
	private HojaSprites hoja;  // hoja donde se situa el Sprite
	
	// coleccion de Sprites
	public static final Sprite VACIO = new Sprite(32, 0x000000);
	
	//coleccion jugador Mago
	public static final Sprite MAGO_ABAJO_PARADO_1 = new Sprite(32, 0, 0, HojaSprites.mago);
	public static final Sprite MAGO_ABAJO_PARADO_2 = new Sprite(32, 1, 0, HojaSprites.mago);
	public static final Sprite MAGO_ABAJO_ANDANDO_1 = new Sprite(32, 2, 0, HojaSprites.mago);
	public static final Sprite MAGO_ABAJO_ANDANDO_2 = new Sprite(32, 3, 0, HojaSprites.mago);
	public static final Sprite MAGO_ABAJO_ANDANDO_3 = new Sprite(32, 4, 0, HojaSprites.mago);
	public static final Sprite MAGO_ABAJO_ANDANDO_4 = new Sprite(32, 5, 0, HojaSprites.mago);
	public static final Sprite MAGO_ARRIBA_PARADO_1 = new Sprite(32, 0, 1, HojaSprites.mago);
	public static final Sprite MAGO_ARRIBA_PARADO_2 = new Sprite(32, 1, 1, HojaSprites.mago);
	public static final Sprite MAGO_ARRIBA_ANDANDO_1 = new Sprite(32, 2, 1, HojaSprites.mago);
	public static final Sprite MAGO_ARRIBA_ANDANDO_2 = new Sprite(32, 3, 1, HojaSprites.mago);
	public static final Sprite MAGO_ARRIBA_ANDANDO_3 = new Sprite(32, 4, 1, HojaSprites.mago);
	public static final Sprite MAGO_ARRIBA_ANDANDO_4 = new Sprite(32, 5, 1, HojaSprites.mago);
	public static final Sprite MAGO_DERECHA_PARADO = new Sprite(32, 0, 2, HojaSprites.mago);
	public static final Sprite MAGO_DERECHA_ANDANDO_1 = new Sprite(32, 2, 2, HojaSprites.mago);
	public static final Sprite MAGO_DERECHA_ANDANDO_2 = new Sprite(32, 4, 2, HojaSprites.mago);
	public static final Sprite MAGO_IZQUIERDA_PARADO = new Sprite(32, 0, 3, HojaSprites.mago);
	public static final Sprite MAGO_IZQUIERDA_ANDANDO_1 = new Sprite(32, 2, 3, HojaSprites.mago);
	public static final Sprite MAGO_IZQUIERDA_ANDANDO_2 = new Sprite(32, 4, 3, HojaSprites.mago);
	//fin coleccion jugador Mago
	
	// sprites nivelPrueba
	public static final Sprite ASFALTO = new Sprite(32, 0, 0, HojaSprites.nivelPrueba);
	public static final Sprite PUERTA_SUP_IZQ = new Sprite (32, 1, 0, HojaSprites.nivelPrueba);
	public static final Sprite PUERTA_SUP_DER = new Sprite (32, 2, 0, HojaSprites.nivelPrueba);
	public static final Sprite FUEGO = new Sprite (32, 3, 0, HojaSprites.nivelPrueba);
	public static final Sprite COLUMNA_SUPERIOR = new Sprite (32, 4, 0, HojaSprites.nivelPrueba);
	public static final Sprite COLUMNA = new Sprite (32, 5, 0, HojaSprites.nivelPrueba);
	public static final Sprite COLUMNA_INFERIOR = new Sprite (32, 6, 0, HojaSprites.nivelPrueba);
	public static final Sprite SUELO = new Sprite (32, 0, 1, HojaSprites.nivelPrueba);
	public static final Sprite PUERTA_INF_IZQ = new Sprite (32, 1, 1, HojaSprites.nivelPrueba);
	public static final Sprite PUERTA_INF_DER = new Sprite (32, 2, 1, HojaSprites.nivelPrueba);
	public static final Sprite HIERBA = new Sprite (32, 0, 2, HojaSprites.nivelPrueba);
	// fin nivelPrueba

	public static final Sprite SAMUS = new Sprite(256, 0, 0, HojaSprites.samus);
	// fin coleccion de Sprites
	
	// metodo constructor para rellenar sprites de un colo determinado por codigo
	public Sprite(final int lado, final int color) {
		this.lado = lado;
		pixeles = new int[lado * lado];
		
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = color;
		}
	}
	
	public Sprite(final int lado, final int columna, final int fila, final HojaSprites hoja) {
		this.lado = lado;
		this.x = columna * lado;
		this.y = fila * lado;
		this.hoja = hoja;
		pixeles = cargarSprite();
	}
	
	public Sprite(final int lado, final int columna, final int fila, final int version, final HojaSprites hoja) {
		this.lado = lado;
		this.x = columna * lado;
		this.y = fila * lado;
		this.hoja = hoja;
		pixeles = cargarVersion(version);
	}
	
	public int getLado() {
		return lado;
	}
	
	/*
	 * lee los pixeles correspondientes a la posicion de la hoja de sprites cargada y los guarda como variable.
	 * Dependiendo del orden de los bucles de lectura conseguimos obtener el sprite de forma invertida o rotada.
	 */
	private int[] cargarSprite() {
		// actua como sistema de coordenadas para acceder a los pixeles de la hoja de sprites
		int[] sprite  = new int[lado * lado];
		
		for (int y = 0; y < lado; y++) {  // recorre la coordenada y de la imagen
			for (int x = 0; x < lado; x++) {  // recorre la coordenada x de la imagen
				sprite[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y) * hoja.getAncho()];
			}
		}
		
		return sprite;
	}
	
	private int[] cargarVersion(int version) {
		
		int[] sprite = cargarSprite();
		
		switch(version) {
		case 0:
			return sprite;
		case 1:
			return invertirX(sprite);
		case 2:
			return invertirY(sprite);
		case 3:
			return invertirXY(sprite);
		case 4:
			return rotar90Izquierda(sprite);
		case 5:
			return rotar90Derecha(sprite);
		case 6:
			return rotar90IzquierdaInvertirX(sprite);
		case 7:
			return rotar90DerechaInvertirX(sprite);
		default:
			return sprite;
		}
	}
	
	// version = 1
	private int[] invertirX (int[] sprite) {
		int[] spriteModificado = new int[lado * lado];
		int i = 0;
		for (int y = lado -1; y >= 0; y--) {
			for (int x = 0; x < lado; x++) {
				spriteModificado[i] = sprite[x + y * lado];
				i++;
			}
		}
		return spriteModificado;
	}
	
	// version = 2
	private int[] invertirY (int[] sprite) {  
		int[] spriteModificado = new int[lado * lado];
		int i = 0;
		for (int y = 0; y < lado; y++) {
			for (int x = lado - 1; x >= 0; x--) {
				spriteModificado[i] = sprite[x + y * lado];
				i++;
			}
		}
		return spriteModificado;
	}
	
	// version = 3
	private int[] invertirXY (int[] sprite) {  // o rotar 180º
		int[] spriteModificado = new int[lado * lado];
		for (int i = 0; i < spriteModificado.length; i++) {
			spriteModificado[i] = sprite[(sprite.length -1) - i];
		}
		return spriteModificado;
	}
	
	// version = 4
	private int[] rotar90Izquierda(int[] sprite) {  // o rotar 270º
		int[] spriteModificado = new int[lado * lado];
		int i = 0;
		for (int x = lado -1 ; x >= 0 ; x--) {
			for (int y = 0; y < lado; y++) {
				spriteModificado[i] = sprite[x + y * lado];
				i++;
			}
		}
		return spriteModificado;
	}
	
	// version = 5
	private int[] rotar90Derecha(int[] sprite) {
		int[] spriteModificado = new int[lado * lado];
		int i = 0;
		for (int x = 0; x < lado; x++) {
			for (int y = lado - 1; y >= 0; y--) {
				spriteModificado[i] = sprite[x + y * lado];
				i++;
			}
		}
		return spriteModificado;
	}
	
	// version = 6
	private int[] rotar90IzquierdaInvertirX(int[] sprite) {
		int[] spriteModificado = new int[lado * lado];
		int i = 0;
		for (int x = 0 ; x < lado ; x++) {
			for (int y = 0; y < lado; y++) {
				spriteModificado[i] = sprite[x + y * lado];
				i++;
			}
		}
		return spriteModificado;
	}
	
	// version = 7
	private int[] rotar90DerechaInvertirX(int[] sprite) {
		int[] spriteModificado = new int[lado * lado];
		int i = 0;
		for (int x = lado - 1; x >= 0; x--) {
			for (int y = lado - 1; y >= 0; y--) {
				spriteModificado[i] = sprite[x + y * lado];
				i++;
			}
		}
		return spriteModificado;
	}
}
