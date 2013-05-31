package com.common.util.sound;

/**
 * El listado de los estado en los que puede estar un archivo de sonido dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum SoundState {

	/**
	 * El estado de un sonido reproduciendose una única vez.
	 */
	PLAY_1,
	/**
	 * El estado de un sonido reproduciendose de manera indefinida.
	 */
	PLAY_N,
	/**
	 * El estado de un sonido pausado.
	 */
	PAUSE,
	/**
	 * El estado de un sonido detenido.
	 */
	STOP;
}
