package entes.criaturas;

import entes.Ente;
import graficos.Sprite;

public abstract class Criatura extends Ente {

	protected Sprite sprite;
	protected char direccion = 'n';
	protected boolean enMovimiento = false;
	
	public void actualizar() {
		
	}
	
	public void mostrar() {
		
	}
	
	public void mover(int desplazamientoX, int desplazamientoY) {
		if (desplazamientoX > 0) {
			direccion = 'e';  // este
		}
		if (desplazamientoX < 0) {
			direccion = 'o';  // oeste
		}
		if (desplazamientoY > 0) {
			direccion = 's';  // sur
		}
		if (desplazamientoY < 0) {
			direccion = 'n';  //norte
		}
		
		if (isEliminado() == false) {
			this.setX(desplazamientoX);
			this.setY(desplazamientoY);
		}
	}
	
	private boolean enColision() {
		return false;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
}
