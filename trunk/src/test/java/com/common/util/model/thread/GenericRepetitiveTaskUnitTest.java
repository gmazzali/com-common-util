package com.common.util.model.thread;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Test;

import com.common.util.exception.CheckedException;

/**
 * La clase que nos permite probar el manejo de un proceso repetitivo.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GenericRepetitiveTaskUnitTest {

	/**
	 * Cuando se termina de ejecutar la prueba, dejamos un espacio en blanco.
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println();
	}

	/**
	 * La tarea repetitiva que vamos a realizar.
	 */
	private GenericRepetitiveTask<Integer> task;

	/**
	 * Prueba sobre el arranque de una tarea repetitiva.
	 */
	@Test
	public void pruebaDeArranque() {
		System.out.println("<<<<<<<<<<<<<< PRUEBAS SOBRE EL ARRANQUE DE UNA TAREA REPETITIVA >>>>>>>>>>>>>>");

		this.task = new GenericRepetitiveTask<Integer>() {

			private int counter = 0;

			@Override
			protected void beforeExecute() {
				this.counter = 0;
				System.out.println("PRE PROCESO");
			}

			@Override
			protected Boolean dontStopCondition() {
				return this.counter++ < 5;
			}

			@Override
			protected void singleExecution() throws CheckedException {
				System.out.println("SALIDA: " + this.counter);
			}

			@Override
			protected Long getDelayExecution() {
				return 100l;
			}

			@Override
			protected void afterExecute() {
				System.out.println("POS PROCESO");
			}
		};

		try {
			System.out.println("------------------");
			System.out.println("---- ARRANQUE ----");
			this.task.start();
			this.task.join();
			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");
			Assert.assertTrue(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	/**
	 * Prueba sobre el pausado de una tarea repetitiva.
	 */
	@Test
	public void pruebaDePausado() {
		System.out.println("<<<<<<<<<<<<<<< PRUEBA SOBRE EL PAUSADO DE UNA TAREA REPETITIVA >>>>>>>>>>>>>>>");

		this.task = new GenericRepetitiveTask<Integer>() {

			private int counter = 0;

			@Override
			protected void beforeExecute() {
				this.counter = 0;
				System.out.println("PRE PROCESO");
			}

			@Override
			protected synchronized Boolean dontStopCondition() {
				return this.counter++ < 10;
			}

			@Override
			protected void singleExecution() throws CheckedException {
				System.out.println("SALIDA: " + this.counter);
			}

			@Override
			protected Long getDelayExecution() {
				return 500l;
			}

			@Override
			protected void afterExecute() {
				System.out.println("POS PROCESO");
			}
		};

		try {
			System.out.println("------------------");
			System.out.println("---- ARRANQUE ----");
			this.task.start();

			Thread.sleep(1050);
			this.task.pause();
			Thread.sleep(5);
			System.out.println("---- SUSPENDE ----");

			Thread.sleep(1000);
			System.out.println("----- RESUME -----");
			this.task.resume();

			Thread.sleep(1050);
			this.task.pause();
			System.out.println("---- SUSPENDE ----");

			Thread.sleep(1000);
			System.out.println("----- RESUME -----");
			this.task.resume();

			Thread.sleep(1050);
			this.task.pause();
			System.out.println("---- SUSPENDE ----");

			Thread.sleep(1000);
			System.out.println("----- RESUME -----");
			this.task.resume();

			this.task.join();
			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");
			Assert.assertTrue(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	/**
	 * La función de prueba de parada de una tarea repetitiva.
	 */
	@Test
	public void pruebaDeParada() {
		System.out.println("<<<<<<<<<<<<<<< PRUEBAS SOBRE LA PARADA DE UNA TAREA REPETITIVA >>>>>>>>>>>>>>>");

		this.task = new GenericRepetitiveTask<Integer>() {

			private int counter = 0;

			@Override
			protected void beforeExecute() {
				this.counter = 0;
				System.out.println("PRE PROCESO");
			}

			@Override
			protected Boolean dontStopCondition() {
				return this.counter++ < 10;
			}

			@Override
			protected void singleExecution() throws CheckedException {
				System.out.println("SALIDA: " + this.counter);
			}

			@Override
			protected Long getDelayExecution() {
				return 500l;
			}

			@Override
			protected void afterExecute() {
				System.out.println("POS PROCESO");
			}
		};

		try {
			System.out.println("------------------");
			System.out.println("---- ARRANQUE ----");
			this.task.start();

			Thread.sleep(1050);
			this.task.stop();
			System.out.println("----- PARADA -----");

			this.task.join();
			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");
			System.out.println("--- REARRANQUE ---");
			this.task.start();
			this.task.join();
			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");
			Assert.assertTrue(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	/**
	 * La función de prueba de reinicio de una tarea repetitiva.
	 */
	@Test
	public void pruebaDeReinicio() {
		System.out.println("<<<<<<<<<<<<<< PRUEBAS SOBRE EL REINICIO DE UNA TAREA REPETITIVA >>>>>>>>>>>>>>");

		this.task = new GenericRepetitiveTask<Integer>() {

			private int counter = 0;

			@Override
			protected void beforeExecute() {
				this.counter = 0;
				System.out.println("PRE PROCESO");
			}

			@Override
			protected Boolean dontStopCondition() {
				return this.counter++ < 5;
			}

			@Override
			protected void singleExecution() throws CheckedException {
				System.out.println("SALIDA: " + this.counter);
			}

			@Override
			protected Long getDelayExecution() {
				return 500l;
			}

			@Override
			protected void afterExecute() {
				System.out.println("POS PROCESO");
			}
		};

		try {
			System.out.println("------------------");
			System.out.println("---- ARRANQUE ----");
			this.task.start();

			Thread.sleep(1050);
			this.task.stop();
			System.out.println("----- PARADA -----");

			this.task.join();
			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");
			System.out.println("--- REARRANQUE ---");
			this.task.start();
			this.task.join();
			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");
			System.out.println("---- REINICIO ----");
			this.task.reboot();
			System.out.println("--- REARRANQUE ---");
			this.task.start();
			this.task.join();
			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");
			Assert.assertTrue(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	/**
	 * La función de prueba de reinicio de una tarea repetitiva.
	 */
	@Test
	public void pruebaDePausadoReinicio() {
		System.out.println("<<<<<<<<< PRUEBAS SOBRE EL PAUSADO Y REINICIO DE UNA TAREA REPETITIVA >>>>>>>>>");

		this.task = new GenericRepetitiveTask<Integer>() {

			private int counter = 0;

			@Override
			protected void beforeExecute() {
				this.counter = 0;
				System.out.println("PRE PROCESO");
			}

			@Override
			protected Boolean dontStopCondition() {
				return this.counter++ < 5;
			}

			@Override
			protected void singleExecution() throws CheckedException {
				System.out.println("SALIDA: " + this.counter);
			}

			@Override
			protected Long getDelayExecution() {
				return 500l;
			}

			@Override
			protected void afterExecute() {
				System.out.println("POS PROCESO");
			}
		};

		try {
			System.out.println("------------------");
			System.out.println("---- ARRANQUE ----");
			this.task.start();

			Thread.sleep(1050);
			this.task.pause();
			System.out.println("---- SUSPENDE ----");

			Thread.sleep(1000);
			this.task.stop();
			System.out.println("----- PARADA -----");

			this.task.join();
			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");
			System.out.println("---- REINICIO ----");
			this.task.reboot();
			System.out.println("--- REARRANQUE ---");
			this.task.start();
			this.task.join();
			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");
			Assert.assertTrue(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}
