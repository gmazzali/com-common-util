package com.common.util.model.thread;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

/**
 * La clase que nos permite probar la clase {@link GenericTask}
 * 
 * @since 12/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GenericTaskUnitTest {

	/**
	 * Cuando se termina de ejecutar la prueba, dejamos un espacio en blanco.
	 */
	@After
	public void after() {
		System.out.println();
	}

	/**
	 * La función de prueba de arranque de los procesos.
	 */
	@Test
	public void testStart() {
		System.out.println("<<<<<<<<<<<<<<<<<<<< PRUEBA SOBRE EL ARRANQUE DE UNA TAREA >>>>>>>>>>>>>>>>>>>>");

		GenericTask<Integer> task = new GenericTask<Integer>() {

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

			task.start();
			task.join();

			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * La función de prueba de pausado de los procesos.
	 */
	@Test
	public void testPause() {
		System.out.println("<<<<<<<<<<<<<<<<<<<< PRUEBAS SOBRE EL PAUSADO DE UNA TAREA >>>>>>>>>>>>>>>>>>>>");

		GenericTask<Integer> task = new GenericTask<Integer>() {

			@Override
			protected void beforeExecute() {
				System.out.println("PRE PROCESO");
			}

			@Override
			protected void execute() {
				for (int i = 0; this.stop(i); i++) {
					try {
						synchronized (this.stateMutex) {
							if (this.taskState == TaskStatus.PAUSE) {
								this.stateMutex.wait();
							}
						}
						System.out.println("SALIDA: " + i);
						Thread.sleep(495);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			private synchronized boolean stop(int i) {
				return this.taskState != TaskStatus.STOP && i < 10;
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
			Thread.sleep(1000);
			task.pause();

			System.out.println("---- SUSPENDE ----");

			Thread.sleep(1000);
			task.resume();

			System.out.println("----- RESUME -----");

			Thread.sleep(1000);
			task.pause();

			System.out.println("---- SUSPENDE ----");

			Thread.sleep(1000);
			task.resume();

			System.out.println("----- RESUME -----");

			Thread.sleep(1000);
			task.pause();

			System.out.println("---- SUSPENDE ----");

			Thread.sleep(1000);
			task.resume();

			System.out.println("----- RESUME -----");

			task.join();

			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");

		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * La función de prueba de parada de los procesos.
	 */
	@Test
	public void testStop() {
		System.out.println("<<<<<<<<<<<<<<<<<<<<< PRUEBA SOBRE LA PARADA DE UNA TAREA >>>>>>>>>>>>>>>>>>>>>");

		GenericTask<Integer> task = new GenericTask<Integer>() {

			@Override
			protected void beforeExecute() {
				System.out.println("PRE PROCESO");
			}

			@Override
			protected void execute() {
				for (int i = 0; this.taskState != TaskStatus.STOP && i < 5; i++) {
					try {
						synchronized (this.stateMutex) {
							if (this.taskState == TaskStatus.PAUSE) {
								this.stateMutex.wait();
							}
						}
						System.out.println("SALIDA: " + i);
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
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
			Thread.sleep(1000);
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

		} catch (InterruptedException e) {
			Assert.fail();
		}
	}

	/**
	 * La función de prueba de reinicio de los procesos.
	 */
	@Test
	public void testReboot() {
		System.out.println("<<<<<<<<<<<<<<<<<<<< PRUEBA SOBRE EL REINICIO DE UNA TAREA >>>>>>>>>>>>>>>>>>>>");

		GenericTask<Integer> task = new GenericTask<Integer>() {

			@Override
			protected void beforeExecute() {
				System.out.println("PRE PROCESO");
			}

			@Override
			protected void execute() {
				for (int i = 0; this.taskState != TaskStatus.STOP && i < 5; i++) {
					try {
						synchronized (this.stateMutex) {
							if (this.taskState == TaskStatus.PAUSE) {
								this.stateMutex.wait();
							}
						}
						System.out.println("SALIDA: " + i);
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
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
			Thread.sleep(1000);
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

		} catch (InterruptedException e) {
			Assert.fail();
		}
	}
}