package com.common.util.sound;

import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 * La clase que define un reproductor de sonido que va a ejecutarse en segundo plano.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Player {

	/**
	 * El clip de audio.
	 */
	private Clip audioClip;
	/**
	 * El proceso de reproducción en segundo plano.
	 */
	private Thread player;
	/**
	 * El estado en el que se encuentra el sonido.
	 */
	private SoundState soundState;
	/**
	 * El objeto que permite parar la reproducción del sonido.
	 */
	@SuppressWarnings("unused")
	private Object mutex = new Object();

	/**
	 * El constructor que recibe un archivo de sonido para el reproductor.
	 * 
	 * @param soundFile
	 *            El archivo de sonido para el reproductor.
	 */
	public Player(File soundFile) {
		try {
			AudioFileFormat audioFileFormat = AudioSystem.getAudioFileFormat(soundFile);
			AudioInputStream audioImputStream = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat af = audioFileFormat.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, audioImputStream.getFormat(),
					((int) audioImputStream.getFrameLength() * af.getFrameSize()));

			this.audioClip = (Clip) AudioSystem.getLine(info);
			this.audioClip.open(audioImputStream);
		} catch (Exception e) {
		}
		this.initProccess();
	}

	/**
	 * Función que permite inicializar el proceso de reproducción del archivo de audio.
	 */
	private void initProccess() {
		this.soundState = SoundState.STOP;
		this.player = new Thread() {
			@Override
			public void run() {
				try {
					while (true) {
						switch (Player.this.soundState) {
							case PLAY_1:
								Player.this.audioClip.start();
								break;

							case PLAY_N:
								Player.this.audioClip.loop(Clip.LOOP_CONTINUOUSLY);
								break;

							case PAUSE:
							case STOP:
							default:
								Player.this.audioClip.stop();
								break;
						}
						Thread.sleep(100);
					}
				} catch (Exception ex) {
				}
			}
		};
		this.player.start();
	}

	/**
	 * La función que permite reproducir por una única vez el archivo de audio.
	 */
	public synchronized void play() {
		this.soundState = SoundState.PLAY_1;
	}

	/**
	 * La función que permite reproducir de manera continua el archivo de audio.
	 */
	public synchronized void loop() {
		this.soundState = SoundState.PLAY_N;
	}

	/**
	 * La función que permite pausar la reproducción del archivo de audio.
	 */
	public synchronized void pause() {
		this.soundState = SoundState.PAUSE;
	}

	/**
	 * La función que permite detener la reproducción del archivo de audio.
	 */
	public synchronized void stop() {
		this.soundState = SoundState.STOP;
	}
}
