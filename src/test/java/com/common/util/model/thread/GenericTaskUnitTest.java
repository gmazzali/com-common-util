package com.common.util.model.thread;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * La clase que nos permite probar la clase GenericTask.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GenericTaskUnitTest {

	/**
	 * Cuando se termina de ejecutar la prueba, dejamos un espacio en blanco.
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println();
	}

	/**
	 * El elemento para probar.
	 */
	private GenericTask<Integer> task;

	/**
	 * La función de prueba de arranque de los procesos.
	 */
	@Test
	public void pruebaDeArranque() {
		System.out.println("<<<<<<<<<<<<<<<<<<<< PRUEBA SOBRE EL ARRANQUE DE UNA TAREA >>>>>>>>>>>>>>>>>>>>");

		this.task = new GenericTask<Integer>() {

			@Override
			protected void execute() {
				System.out.println("PROCESO PRINCIPAL");
			}

			@Override
			protected void beforeExecute() {
				System.out.println("PRE PROCESO");
			}

			@Override
			protected void afterExecute() {
				System.out.println("POS PROCESO");
			}
		};

		try {
			System.out.println("------------------");
			System.out.println("---- ARARNQUE ----");
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
	 * La función de prueba de pausado de los procesos.
	 */
	@Test
	public void pruebaDePausado() {
		System.out.println("<<<<<<<<<<<<<<<<<<<< PRUEBAS SOBRE EL PAUSADO DE UNA TAREA >>>>>>>>>>>>>>>>>>>>");

		this.task = new GenericTask<Integer>() {

			@Override
			protected void execute() {
				for (int i = 0; this.bol(i); i++) {
					System.out.println("SALIDA: " + i);
					try {
						Thread.sleep(500);
						synchronized (this.mutex) {
							if (this.taskState == TaskStatus.PAUSE) {
								this.mutex.wait();
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			private synchronized boolean bol(int i) {
				return this.taskState != TaskStatus.STOP && i < 10;
			}

			@Override
			protected void beforeExecute() {
				System.out.println("PRE PROCESO");
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

			Thread.sleep(1000);
			this.task.pause();
			System.out.println("---- SUSPENDE ----");

			Thread.sleep(1000);
			this.task.resume();
			System.out.println("----- RESUME -----");

			Thread.sleep(1000);
			this.task.pause();
			System.out.println("---- SUSPENDE ----");

			Thread.sleep(1000);
			this.task.resume();
			System.out.println("----- RESUME -----");

			Thread.sleep(1000);
			this.task.pause();
			System.out.println("---- SUSPENDE ----");

			Thread.sleep(1000);
			this.task.resume();
			System.out.println("----- RESUME -----");

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
	 * La función de prueba de parada de los procesos.
	 */
	@Test
	public void pruebaDeParada() {
		System.out.println("<<<<<<<<<<<<<<<<<<<<< PRUEBA SOBRE LA PARADA DE UNA TAREA >>>>>>>>>>>>>>>>>>>>>");

		this.task = new GenericTask<Integer>() {

			@Override
			protected void execute() {
				for (int i = 0; this.taskState != TaskStatus.STOP && i < 5; i++) {
					System.out.println("SALIDA: " + i);
					try {
						Thread.sleep(500);
						synchronized (this.mutex) {
							if (this.taskState == TaskStatus.PAUSE) {
								this.mutex.wait();
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			protected void beforeExecute() {
				System.out.println("PRE PROCESO");
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

			Thread.sleep(1000);
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
	 * La función de prueba de reinicio de los procesos.
	 */
	@Test
	public void pruebaDeReinicio() {
		System.out.println("<<<<<<<<<<<<<<<<<<<< PRUEBA SOBRE EL REINICIO DE UNA TAREA >>>>>>>>>>>>>>>>>>>>");

		this.task = new GenericTask<Integer>() {

			@Override
			protected void execute() {
				for (int i = 0; this.taskState != TaskStatus.STOP && i < 5; i++) {
					System.out.println("SALIDA: " + i);
					try {
						Thread.sleep(500);
						synchronized (this.mutex) {
							if (this.taskState == TaskStatus.PAUSE) {
								this.mutex.wait();
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			protected void beforeExecute() {
				System.out.println("PRE PROCESO");
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

			Thread.sleep(1000);
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

}
