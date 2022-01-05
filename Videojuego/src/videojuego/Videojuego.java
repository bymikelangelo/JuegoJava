package videojuego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controles.Teclado;
import entes.criaturas.Jugador;
import graficos.Pantalla;
import graficos.Sprite;
import mapa.Mapa;
import mapa.MapaCargado;

// Canva es la clase encargada de mostrar los graficos del juego
// Runnable es la interfaz encargada de ejecutar los hilos de ejecucion (o threads)
public class Videojuego extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	// definimos las dimensiones de la resolucion del Canvas del juego
	private static final int ANCHO = 640; // define el ancho de la ventana
	private static final int ALTO = 480; // define el alto de la ventana
	private static final String NOMBRE = "Videojuego";
	
	private static String contador_aps = "";
	private static String contador_fps = "";
	
	// volatile impide que dos bucles en ejecucion que utilicen la misma variable de forma simultanea
	private static volatile boolean enFuncionamiento = false;  // define si el juego esta en ejecucion
	
	private static int aps = 0;
	private static int fps = 0;
	
	private static JFrame ventana;  // define el objeto que contendra el Canvas que muestra el juego
	private static Thread thread;  // define el thread o hilo de ejecucion
	private static Teclado teclado;
	private static Pantalla pantalla;
	
	private static Mapa mapa;
	private static Jugador jugador;
	
	private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
	private static int[] pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();
	
	private static final ImageIcon icono = new ImageIcon(Videojuego.class.getResource("/icono/icono.png"));

	private Videojuego() {
		super.setPreferredSize(new Dimension(ANCHO, ALTO)); // metodo que define el tama�o del Canvas
		
		pantalla = new Pantalla(ANCHO, ALTO);
		
		// instanciamos el teclado y a�adimos el metodo que espera las pulsaciones del teclado
		teclado = new Teclado();
		addKeyListener(teclado); 
		
		mapa = new MapaCargado("/mapas/nivelPrueba.png");
		jugador = new Jugador(teclado, Sprite.MAGO_ABAJO_PARADO_1);
		
		//definimos la configuracion del JFrame ventana que contiene el Canvas Juego
		ventana = new JFrame(NOMBRE); // instancia del objeto ventana que contiene el Canvas
		// metodo que define el comportamiento de cierre de la aplicacion
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false); // define si la ventana es redimensionable por el usuario
		// define la organizacion interna de los elementos de la ventana
		ventana.setLayout(new BorderLayout()); // new BorderLayout indica el gestor de organizacion
		// añadimos el objeto Juego a la ventana asi como el BorderLayout en el centro de la ventana;
		ventana.add(this, BorderLayout.CENTER);
		// permite mostrar la ventana sin bordes
		ventana.setUndecorated(true);
		ventana.pack();  // hace que la ventana se adapte al tama�o del Canvas
		ventana.setLocationRelativeTo(null);  // fija la ventana en el centro del escritorio
		ventana.setVisible(true);  // define que al crear la ventana sea visualizable
		ventana.setIconImage(icono.getImage());
	}
	
	public static void main(String [] args) {
		Videojuego videojuego = new Videojuego();
		videojuego.iniciar();
	}

	// synchronized actua de forma similar a volatile impidiendo que un metodo modifique una variable si esta siendo leida por otro
	private synchronized void iniciar() {
		enFuncionamiento = true;
		thread = new Thread(this, "Graficos");  // recibe la clase desde la que se ejecuta el Thread y el nombre del thread
		thread.start();  // inicia el thread en cuestion. Para ello, llama al metodo run()
	}
	
	private synchronized void detener() {
		enFuncionamiento = false;
		
		try {
			thread.join();  // join finaliza el thread cuando termine su ejecucion.
			// no es recomendable utilizar thread.stop(); ya que finaliza de forma tajante el thread pudiendo corromper la variable de ejecucion
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void actualizar() {
		teclado.actualizar();
		
		jugador.actualizar();

		if (teclado.salir) {
			System.exit(0);  // permite cerrar la ejecucion del programa
		}
		
		aps++;
	}
	
	private void mostrar() {
		/*
		 *  creamos un buffer para facilitar el visionado de las imagenes en la pantalla.
		 *  Permite obtener primero la imagen, guardarla en la memoria y luego mostrarla en pantalla.
		 */
		BufferStrategy estrategia = getBufferStrategy();
		
		if (estrategia == null) {
			createBufferStrategy(3);  // triple buffer
			return;
		}
		
//		pantalla.limpiar();  // dibuja un fotograma negro por cada frame
//		pantalla.mostrar(x, y);
		mapa.mostrar(jugador.getX() - (pantalla.getAncho() / 2) + (jugador.getSprite().getLado() / 2), 
				jugador.getY() - (pantalla.getAlto() / 2) + (jugador.getSprite().getLado() / 2), pantalla);
		jugador.mostrar(pantalla);
		
		/*
		 *  el siguiente metodo puede ralentizar PC de bajos recursos o con versiones de Java antiguas.
		 *  Copia el array de pixeles de Pantalla al array de pixeles de Videojuego
		*/
		
//		for (int i = 0; i < pixeles.length; i++) {
//			pixeles[i] = pantalla.pixeles[i];
//		}
		
		// el siguiente metodo hace lo mismo que el anterior pero utilizando menos recursos del PC
		System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);
		
		// instanciamos para dibujar los graficos en la pantalla
		Graphics graficos = estrategia.getDrawGraphics();  // dibuja los graficos 
		graficos.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
		graficos.setColor(Color.white);
		// graficos.fillRect(ANCHO / 2, ALTO / 2, 32, 32);  // muestra un recuadro en el centro de la pantalla
		
		if (ventana.isUndecorated()) {
			graficos.drawString(contador_aps, 10, 20);
			graficos.drawString(contador_fps, 10, 35);
		}
		graficos.drawString("X = " + jugador.getX(), 10, 50);
		graficos.drawString("Y = " + jugador.getY(), 10, 65);
		
		graficos.dispose();  // borra los datos de "graficos" de la memoria
		
		estrategia.show();
		
		fps++;
	}
	
	@Override
	public void run() {
		final int NANO_POR_SEGUNDO = 1000000000;  // define la equivalencia entre nano a segundo
		final byte APS = 120;  // defina la cantidad de actualizaciones por segundo
		final double NANO_POR_ACTUALIZACION = NANO_POR_SEGUNDO / APS;  // nanosegundos por actualizacion
		
		// tomamos la primera referencia de tiempo antes de iniciar el bucle 
		long referenciaActualizacion = System.nanoTime();  // nanoTime() mide el tiempo actual en nanosegundos. 
		long referenciaContador = System.nanoTime();
		double tiempoTranscurrido;  // define el tiempo que pasa entre el momento de iniciar el bucle y al final
		double delta = 0;  // define el tiempo entre cada actualizacion del juego
		
		super.requestFocus();  // permite obtener el foco en la ventana de la aplicacion sin necesidad de hacer clic antes
		
		// inicia el bucle en ejecucion el juego
		while (enFuncionamiento) {
			final long inicioBucle = System.nanoTime();  // tomamos una primera referencia del tiempo al iniciar el bucle
			tiempoTranscurrido = inicioBucle - referenciaActualizacion;  // se guarda el valor que pasa entre el momento temporal al iniciar y al finalizar el bucle
			referenciaActualizacion = inicioBucle;  // vuelve a tomar otra referencia del tiempo
			
			delta += tiempoTranscurrido / NANO_POR_ACTUALIZACION;  // conseguimos que delta tome el valor de 1 un aprox de 60 veces cada segundo
			
			while (delta >= 1) {
				this.actualizar();
				delta--;
			}
			
			mostrar();
			
			if (System.nanoTime() - referenciaContador > NANO_POR_SEGUNDO) {
				ventana.setTitle(NOMBRE + " || APS: " + aps + " || FPS: " + fps);
				contador_aps = "APS = " + aps;
				contador_fps = "FPS = " + fps;
				aps = 0;
				fps = 0;
				referenciaContador = System.nanoTime();
			}
		}
	}
}
