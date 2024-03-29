package controles;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

	private final static int numeroTeclas = 120;  // refiere al codigo ASCII al que pertenecen las teclas principales
	private final boolean[] teclas = new boolean[numeroTeclas];
	
	public boolean arriba;
	public boolean abajo;
	public boolean izquierda;
	public boolean derecha;
	
	public boolean correr;
	
	public boolean salir;
	
	public void actualizar() {
		arriba = teclas[KeyEvent.VK_W];
		abajo = teclas[KeyEvent.VK_S];
		izquierda = teclas[KeyEvent.VK_A];
		derecha = teclas[KeyEvent.VK_D];
		correr = teclas[KeyEvent.VK_SHIFT];
		salir = teclas[KeyEvent.VK_ESCAPE];
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		teclas[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		teclas[e.getKeyCode()] = false;
	}

}
