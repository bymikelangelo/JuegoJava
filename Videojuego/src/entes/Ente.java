package entes;

import mapa.Mapa;

public abstract class Ente {

	protected int x;
	protected int y;
	private boolean eliminado = false;
	protected Mapa mapa;
	
	public void actualizar() {
		
	}
	
	public void mostrar() {
		
	}
	
	public void eliminar() {
		eliminado = true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setX(int desplazamientoX) {
		x += desplazamientoX;
	}

	public void setY(int desplazamientoY) {
		y += desplazamientoY;
	}	

	
}
