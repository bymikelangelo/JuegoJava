package entes.criaturas;

import controles.Teclado;
import graficos.Pantalla;
import graficos.Sprite;

public class Jugador extends Criatura {

	private Teclado teclado;
	private int animacion;
	
	public Jugador (Teclado teclado, Sprite sprite) {
		this.teclado = teclado;
		this.sprite = sprite;
	}
	
	public Jugador (Teclado teclado, Sprite sprite, int posicionX, int posicionY) {
		this.teclado = teclado;
		this.sprite = sprite;
		this.x = posicionX;
		this.y = posicionY;
	}
	
	public void actualizar() {
		int desplazamientoX = 0;
		int desplazamientoY = 0;
		
		int velocidad = 1;
		
		if (animacion < 32767) {
			animacion++;
		}
		else {
			animacion = 0;
		}
		
		if (teclado.correr) {
			velocidad = 2;
		}
		
		if (teclado.arriba) {
			desplazamientoY -= velocidad;
			//this.sprite = Sprite.MAGO_ARRIBA_PARADO_1;
		}
		
		if (teclado.abajo) {
			desplazamientoY += velocidad;
			//this.sprite = Sprite.MAGO_ABAJO_PARADO_1;
		}
		
		if (teclado.izquierda) {
			desplazamientoX -= velocidad;
			//this.sprite = Sprite.MAGO_IZQUIERDA_PARADO;
		}
		
		if (teclado.derecha) {
			desplazamientoX += velocidad;
			//this.sprite = Sprite.MAGO_DERECHA_PARADO;
		}
		
		if (desplazamientoX != 0 || desplazamientoY != 0) {
			mover(desplazamientoX, desplazamientoY);
			this.enMovimiento = true;
		}
		else {
			this.enMovimiento = false;
		}
		
		// asociamos el sprite segun la direccion del jugador
//		if (this.direccion == 'n' ) {
//			this.sprite = Sprite.MAGO_ARRIBA_PARADO_1;
//			if (enMovimiento) {
//				if (animacion % 60 > 20 && animacion % 60 <= 40) {
//					this.sprite = Sprite.MAGO_ARRIBA_ANDANDO_1;
//				}
//				else if (animacion % 60 > 40){
//					this.sprite = Sprite.MAGO_ARRIBA_PARADO_1;
//				}
//				else {
//					this.sprite = Sprite.MAGO_ARRIBA_ANDANDO_2;
//				}
//			}
//		}
		
//		if (this.direccion == 'n' ) {
//			this.sprite = Sprite.MAGO_ARRIBA_PARADO_1;
//			if (enMovimiento) {
//				if (animacion % 60 < 30){
//					this.sprite = Sprite.MAGO_ARRIBA_ANDANDO_1;
//				}
//				else {
//					this.sprite = Sprite.MAGO_ARRIBA_ANDANDO_2;
//				}
//			}
//		}
		
		if (this.direccion == 'n' ) {
			this.sprite = Sprite.MAGO_ARRIBA_PARADO_1;
			if (enMovimiento) {
				if (animacion % 80 > 20 && animacion % 80 <= 40) {
					this.sprite = Sprite.MAGO_ARRIBA_ANDANDO_1;
				}
				else if (animacion % 80 > 40 && animacion % 80 <= 60) {
					this.sprite = Sprite.MAGO_ARRIBA_ANDANDO_2;
				}
				else if (animacion % 80 > 60) {
					this.sprite = Sprite.MAGO_ARRIBA_ANDANDO_3;
				}
				else {
					this.sprite = Sprite.MAGO_ARRIBA_ANDANDO_4;
				}
			}
		}
		
//		if (this.direccion == 's' ) {
//			this.sprite = Sprite.MAGO_ABAJO_PARADO_1;
//			if (enMovimiento) {
//				if (animacion % 60 < 30){
//					this.sprite = Sprite.MAGO_ABAJO_ANDANDO_1;
//				}
//				else {
//					this.sprite = Sprite.MAGO_ABAJO_ANDANDO_2;
//				}
//			}
//		}
		
		if (this.direccion == 's' ) {
			this.sprite = Sprite.MAGO_ABAJO_PARADO_1;
			if (enMovimiento) {
				if (animacion % 80 > 20 && animacion % 80 <= 40) {
					this.sprite = Sprite.MAGO_ABAJO_ANDANDO_1;
				}
				else if (animacion % 80 > 40 && animacion % 80 <= 60) {
					this.sprite = Sprite.MAGO_ABAJO_ANDANDO_2;
				}
				else if (animacion % 80 > 60) {
					this.sprite = Sprite.MAGO_ABAJO_ANDANDO_3;
				}
				else {
					this.sprite = Sprite.MAGO_ABAJO_ANDANDO_4;
				}
			}
		}
		
//		if (this.direccion == 'e' ) {
//			this.sprite = Sprite.MAGO_DERECHA_PARADO;
//			if (enMovimiento) {
//				if (animacion % 60 > 30) {
//					this.sprite = Sprite.MAGO_DERECHA_ANDANDO;
//				}
//			}
//		}
		
		if (this.direccion == 'e' ) {
		this.sprite = Sprite.MAGO_DERECHA_PARADO;
		if (enMovimiento) {
			if (animacion % 80 > 20 && animacion % 80 <= 40) {
				this.sprite = Sprite.MAGO_DERECHA_ANDANDO_1;
			}
			else if (animacion % 80 > 40 && animacion % 80 <= 60) {
				this.sprite = Sprite.MAGO_DERECHA_PARADO;
			}
			else if (animacion % 80 > 60) {
				this.sprite = Sprite.MAGO_DERECHA_ANDANDO_2;
			}
			else {
				this.sprite = Sprite.MAGO_DERECHA_PARADO;
			}
		}
	}
		
//		if (this.direccion == 'o' ) {
//			this.sprite = Sprite.MAGO_IZQUIERDA_PARADO;
//			if (enMovimiento) {
//				if (animacion % 60 > 30) {
//					this.sprite = Sprite.MAGO_IZQUIERDA_ANDANDO;
//				}
//			}
//		}
		
		if (this.direccion == 'o' ) {
			this.sprite = Sprite.MAGO_IZQUIERDA_PARADO;
			if (enMovimiento) {
				if (animacion % 80 > 20 && animacion % 80 <= 40) {
					this.sprite = Sprite.MAGO_IZQUIERDA_ANDANDO_1;
				}
				else if (animacion % 80 > 40 && animacion % 80 <= 60) {
					this.sprite = Sprite.MAGO_IZQUIERDA_PARADO;
				}
				else if (animacion % 80 > 60) {
					this.sprite = Sprite.MAGO_IZQUIERDA_ANDANDO_2;
				}
				else {
					this.sprite = Sprite.MAGO_IZQUIERDA_PARADO;
				}
			}
		}
	}
	
	public void mostrar(Pantalla pantalla) {
		pantalla.mostrarJugador(x, y, this);
	}
	
	
}
