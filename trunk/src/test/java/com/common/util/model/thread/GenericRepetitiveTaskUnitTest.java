package com.common.util.model.thread;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

/**
 * La clase que nos permite probar la clase {@link GenericRepetitiveTask}.
 * 
 * @since 12/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GenericRepetitiveTaskUnitTest {

	/**
	 * Cuando se termina de ejecutar la prueba, dejamos un espacio en blanco.
	 */
	@After
	public void after() {
		System.out.println();
	}

	/**
	 * Prueba sobre el arranque de una tarea repetitiva.
	 */
	@Test
	public void testStart() {
		System.out.println("<<<<<<<<<<<<<< PRUEBAS SOBRE EL ARRANQUE DE UNA TAREA REPETITIVA >>>>>>>>>>>>>>");

		GenericRepetitiveTask<Integer> task = new GenericRepetitiveTask<Integer>() {

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
			protected void singleExecution() {
				System.out.println("SALIDA: " + this.counter);
			}

			@Override
			protected Long getDelayExecution() {
				return 50l;
			}

			@Override
			protected void afterExecute() {
				System.out.println("POS PROCESO");
			}
		};

		try {
			System.out.println("------------------");
			System.out.println("---- ARRANQUE ----");

			task.start();
			task.join();

			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");

		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * Prueba sobre el pausado de una tarea repetitiva.
	 */
	@Test
	public void testPause() {
		System.out.println("<<<<<<<<<<<<<<< PRUEBA SOBRE EL PAUSADO DE UNA TAREA REPETITIVA >>>>>>>>>>>>>>>");

		GenericRepetitiveTask<Integer> task = new GenericRepetitiveTask<Integer>() {

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
			protected void singleExecution() {
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

			task.start();
			Thread.sleep(1050);
			task.pause();
			Thread.sleep(5);

			System.out.println("---- SUSPENDE ----");

			Thread.sleep(1000);

			System.out.println("----- RESUME -----");

			task.resume();
			Thread.sleep(1050);
			task.pause();

			System.out.println("---- SUSPENDE ----");

			Thread.sleep(1000);

			System.out.println("----- RESUME -----");

			task.resume();
			Thread.sleep(1050);
			task.pause();

			System.out.println("---- SUSPENDE ----");

			Thread.sleep(1000);

			System.out.println("----- RESUME -----");

			task.resume();
			task.join();

			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");

		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * La función de prueba de parada de una tarea repetitiva.
	 */
	@Test
	public void testStop() {
		System.out.println("<<<<<<<<<<<<<<< PRUEBAS SOBRE LA PARADA DE UNA TAREA REPETITIVA >>>>>>>>>>>>>>>");

		GenericRepetitiveTask<Integer> task = new GenericRepetitiveTask<Integer>() {

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
			protected void singleExecution() {
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

			task.start();
			Thread.sleep(1050);
			task.stop();

			System.out.println("----- PARADA -----");

			task.join();

			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");
			System.out.println("--- REARRANQUE ---");

			task.start();
			task.join();

			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");

		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * La función de prueba de reinicio de una tarea repetitiva.
	 */
	@Test
	public void testReboot() {
		System.out.println("<<<<<<<<<<<<<< PRUEBAS SOBRE EL REINICIO DE UNA TAREA REPETITIVA >>>>>>>>>>>>>>");

		GenericRepetitiveTask<Integer> task = new GenericRepetitiveTask<Integer>() {

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
			protected void singleExecution() {
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

			task.start();
			Thread.sleep(1050);
			task.stop();

			System.out.println("----- PARADA -----");

			task.join();

			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");
			System.out.println("--- REARRANQUE ---");

			task.start();
			task.join();

			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");
			System.out.println("---- REINICIO ----");

			task.reboot();

			System.out.println("--- REARRANQUE ---");

			task.start();
			task.join();

			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");

		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * La función de prueba de reinicio de una tarea repetitiva.
	 */
	@Test
	public void testRebootPause() {
		System.out.println("<<<<<<<<< PRUEBAS SOBRE EL PAUSADO Y REINICIO DE UNA TAREA REPETITIVA >>>>>>>>>");

		GenericRepetitiveTask<Integer> task = new GenericRepetitiveTask<Integer>() {

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
			protected void singleExecution() {
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

			task.start();
			Thread.sleep(1050);
			task.pause();

			System.out.println("---- SUSPENDE ----");

			Thread.sleep(1000);
			task.stop();

			System.out.println("----- PARADA -----");

			task.join();

			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");
			System.out.println("---- REINICIO ----");

			task.reboot();

			System.out.println("--- REARRANQUE ---");

			task.start();
			task.join();

			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");

		} catch (Exception e) {
			Assert.fail();
		}
	}
}