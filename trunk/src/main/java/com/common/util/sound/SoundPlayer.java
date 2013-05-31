package com.common.util.sound;

import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import org.apache.log4j.Logger;

/**
 * La clase que define un reproductor de sonido que va a ejecutarse en segundo plano.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class SoundPlayer {

	/**
	 * El Logger que vamos a ocupar dentro de la clase.
	 */
	private static final Logger log = Logger.getLogger(SoundPlayer.class);

	/**
	 * La enumeración que define los estados en los que podemos definir el reproductor, puede estar encendido o apagado.
	 * 
	 * @author Guillermo Mazzali
	 * @version 1.0
	 */
	public enum PlayerStatus {
		ON, OFF;
	}

	/**
	 * El listado de las acciones que podemos establecer sobre el archivo de audio.
	 * 
	 * @author Guillermo Mazzali
	 * @version 1.0
	 */
	public enum PlayerActions {
		INIT, PLAY_1, PLAY_N, PAUSE;
	}

	/**
	 * El clip de audio.
	 */
	private Clip audioClip;
	/**
	 * El proceso de reproducción en segundo plano.
	 */
	private Thread player;

	/**
	 * El estado en el que se encuentra el reproductor.
	 */
	private PlayerStatus status;
	/**
	 * La accion que definimos para el archivo.
	 */
	private PlayerActions action;

	/**
	 * El constructor que recibe un archivo de sonido para el reproductor.
	 * 
	 * @param soundFile
	 *            El archivo de sonido para el reproductor.
	 */
	public SoundPlayer(File soundFile) {
		SoundPlayer.log.trace("SoundPlayer create");

		try {
			AudioFileFormat audioFileFormat = AudioSystem.getAudioFileFormat(soundFile);
			AudioInputStream audioImputStream = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat af = audioFileFormat.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, audioImputStream.getFormat(),
					((int) audioImputStream.getFrameLength() * af.getFrameSize()));

			this.audioClip = (Clip) AudioSystem.getLine(info);
			this.audioClip.open(audioImputStream);

		} catch (Exception e) {
			SoundPlayer.log.error("SoundPlayer create failed", e);
		}

		this.initProccess();
	}

	/**
	 * Función que permite inicializar el proceso de reproducción del archivo de audio.
	 */
	private void initProccess() {
		SoundPlayer.log.trace("SoundPlayer initProccess");

		this.status = PlayerStatus.ON;
		this.action = PlayerActions.INIT;

		this.player = new Thread() {
			@Override
			public void run() {
				while (SoundPlayer.this.status == PlayerStatus.ON) {

					SoundPlayer.log.trace(SoundPlayer.this.action);

					switch (SoundPlayer.this.action) {

						case PLAY_1:
							SoundPlayer.this.audioClip.start();
							break;

						case PLAY_N:
							SoundPlayer.this.audioClip.loop(Clip.LOOP_CONTINUOUSLY);
							break;

						case PAUSE:
							SoundPlayer.this.audioClip.flush();
							SoundPlayer.this.audioClip.stop();
							break;

						default:
							break;
					}
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						SoundPlayer.log.error("SoundPlayer initProccess failed", e);
					}
				}

				// Cuando se apague, detenemos el proceso del audio.
				while (SoundPlayer.this.audioClip.isActive()) {
					SoundPlayer.this.audioClip.flush();
					SoundPlayer.this.audioClip.stop();
				}
			}
		};
		this.player.start();
	}

	/**
	 * La función que permite reproducir por una única vez el archivo de audio.
	 */
	public synchronized void play() {
		SoundPlayer.log.trace("SoundPlayer play");

		this.action = PlayerActions.PLAY_1;
	}

	/**
	 * La función que permite reproducir de manera continua el archivo de audio.
	 */
	public synchronized void loop() {
		SoundPlayer.log.trace("SoundPlayer loop");

		this.action = PlayerActions.PLAY_N;
	}

	/**
	 * La función que permite pausar la reproducción del archivo de audio.
	 */
	public synchronized void pause() {
		SoundPlayer.log.trace("SoundPlayer pause");

		this.action = PlayerActions.PAUSE;
	}

	/**
	 * La función que permite detener la reproducción del archivo de audio.
	 */
	public synchronized void stop() {
		SoundPlayer.log.trace("SoundPlayer stop");

		this.status = PlayerStatus.OFF;
	}

	/**
	 * La función encargada de reiniciar el proceso de reproducción de sonido.
	 */
	public synchronized void reboot() {
		this.stop();
		this.initProccess();
	}
}