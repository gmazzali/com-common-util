package com.common.util.model.thread;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Test;

import com.common.util.model.thread.GenericTask;
import com.common.util.model.thread.GenericTaskPool;

/**
 * La clase que nos permite probar el elemento del pool de tareas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GenericTaskPoolTestUnit {

	/**
	 * Cuando se termina de ejecutar la prueba, dejamos un espacio en blanco.
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println();
	}

	/**
	 * El pool de pruebas.
	 */
	private GenericTaskPool<GenericTask<Integer>> pool;

	/**
	 * La función de prueba de arranque de los procesos.
	 */
	@Test
	public void pruebaDeArranque() {
		System.out.println("<<<<<<<<<<<<<<<< PRUEBA SOBRE EL ARRANQUE DE UN POOL DE TAREAS >>>>>>>>>>>>>>>>");

		GenericTask<Integer> task1 = new GenericTask<Integer>() {

			@Override
			public void beforeExecute() {
				System.out.println("PRE PROCESO 1");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void execute() {
				System.out.println("PROCESO PRINCIPAL 1");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void afterExecute() {
				System.out.println("POS PROCESO 1");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		GenericTask<Integer> task2 = new GenericTask<Integer>() {

			@Override
			public void beforeExecute() {
				System.out.println("PRE PROCESO 2");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void execute() {
				System.out.println("PROCESO PRINCIPAL 2");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void afterExecute() {
				System.out.println("POS PROCESO 2");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		this.pool = new GenericTaskPool<GenericTask<Integer>>("POOL");
		this.pool.addTask(task1);
		this.pool.addTask(task2);

		try {
			System.out.println("------------------");
			System.out.println("---- ARARNQUE ----");
			this.pool.start();
			this.pool.join();
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
	public void pruebaDeAPausado() {
		System.out.println("<<<<<<<<<<<<<<<< PRUEBAS SOBRE EL PAUSADO DE UN POOL DE TAREAS >>>>>>>>>>>>>>>>");

		GenericTask<Integer> task1 = new GenericTask<Integer>() {

			@Override
			public void beforeExecute() {
				System.out.println("1 - PRE PROCESO");
			}

			@Override
			public void execute() {
				for (int i = 0; this.bol(i); i++) {
					System.out.println("1 - SALIDA: " + i);
					try {
						Thread.sleep(500);
						synchronized (this.stateMutex) {
							if (this.taskState == TaskStatus.PAUSE) {
								this.stateMutex.wait();
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
			public void afterExecute() {
				System.out.println("1 - POS PROCESO");
			}
		};

		GenericTask<Integer> task2 = new GenericTask<Integer>() {

			@Override
			public void beforeExecute() {
				System.out.println("2 - PRE PROCESO");
			}

			@Override
			public void execute() {
				for (int i = 0; this.bol(i); i++) {
					System.out.println("2 - SALIDA: " + i);
					try {
						Thread.sleep(500);
						synchronized (this.stateMutex) {
							if (this.taskState == TaskStatus.PAUSE) {
								this.stateMutex.wait();
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			private synchronized boolean bol(int i) {
				return this.taskState != TaskStatus.STOP && i < 5;
			}

			@Override
			public void afterExecute() {
				System.out.println("2 - POS PROCESO");
			}
		};

		this.pool = new GenericTaskPool<GenericTask<Integer>>("POOL");
		this.pool.addTask(task1);
		this.pool.addTask(task2);

		try {
			System.out.println("------------------");
			System.out.println("---- ARRANQUE ----");
			this.pool.start();

			Thread.sleep(1000);
			this.pool.pause();
			System.out.println("---- SUSPENDE ----");

			Thread.sleep(1000);
			this.pool.resume();
			System.out.println("----- RESUME -----");

			Thread.sleep(1000);
			this.pool.pause();
			System.out.println("---- SUSPENDE ----");

			Thread.sleep(1000);
			this.pool.resume();
			System.out.println("----- RESUME -----");

			Thread.sleep(1000);
			this.pool.pause();
			System.out.println("---- SUSPENDE ----");

			Thread.sleep(1000);
			this.pool.resume();
			System.out.println("----- RESUME -----");

			this.pool.join();
			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");
			Assert.assertTrue(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	/**
	 * La función de prueba de parada del pool de procesos.
	 */
	@Test
	public void pruebaDeParada() {
		System.out.println("<<<<<<<<<<<<<<<<< PRUEBA SOBRE LA PARADA DE UN POOL DE TAREAS >>>>>>>>>>>>>>>>>");

		GenericTask<Integer> task1 = new GenericTask<Integer>() {

			@Override
			public void beforeExecute() {
				System.out.println("1 - PRE PROCESO");
			}

			@Override
			public void execute() {
				for (int i = 0; this.bol(i); i++) {
					System.out.println("1 - SALIDA: " + i);
					try {
						Thread.sleep(500);
						synchronized (this.stateMutex) {
							if (this.taskState == TaskStatus.PAUSE) {
								this.stateMutex.wait();
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			private synchronized boolean bol(int i) {
				return this.taskState != TaskStatus.STOP && i < 5;
			}

			@Override
			public void afterExecute() {
				System.out.println("1 - POS PROCESO");
			}
		};

		GenericTask<Integer> task2 = new GenericTask<Integer>() {

			@Override
			public void beforeExecute() {
				System.out.println("2 - PRE PROCESO");
			}

			@Override
			public void execute() {
				for (int i = 0; this.bol(i); i++) {
					System.out.println("2 - SALIDA: " + i);
					try {
						Thread.sleep(500);
						synchronized (this.stateMutex) {
							if (this.taskState == TaskStatus.PAUSE) {
								this.stateMutex.wait();
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			private synchronized boolean bol(int i) {
				return this.taskState != TaskStatus.STOP && i < 5;
			}

			@Override
			public void afterExecute() {
				System.out.println("2 - POS PROCESO");
			}
		};

		this.pool = new GenericTaskPool<GenericTask<Integer>>("POOL");
		this.pool.addTask(task1);
		this.pool.addTask(task2);

		try {
			System.out.println("------------------");
			System.out.println("---- ARRANQUE ----");
			this.pool.start();

			Thread.sleep(2000);
			this.pool.stop();
			System.out.println("----- PARADA -----");

			this.pool.join();
			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");

			System.out.println("------------------");
			System.out.println("--- REARRANQUE ---");
			this.pool.start();
			this.pool.join();
			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");
			Assert.assertTrue(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	/**
	 * La función de prueba de reinicio del pool de procesos.
	 */
	@Test
	public void pruebaDeReinicio() {
		System.out.println("<<<<<<<<<<<<<<<< PRUEBA SOBRE EL REINICIO DE UN POOL DE TAREAS >>>>>>>>>>>>>>>>");

		GenericTask<Integer> task1 = new GenericTask<Integer>() {

			@Override
			public void beforeExecute() {
				System.out.println("1 - PRE PROCESO");
			}

			@Override
			public void execute() {
				for (int i = 0; this.bol(i); i++) {
					System.out.println("1 - SALIDA: " + i);
					try {
						Thread.sleep(500);
						synchronized (this.stateMutex) {
							if (this.taskState == TaskStatus.PAUSE) {
								this.stateMutex.wait();
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			private synchronized boolean bol(int i) {
				return this.taskState != TaskStatus.STOP && i < 5;
			}

			@Override
			public void afterExecute() {
				System.out.println("1 - POS PROCESO");
			}
		};

		GenericTask<Integer> task2 = new GenericTask<Integer>() {

			@Override
			public void beforeExecute() {
				System.out.println("2 - PRE PROCESO");
			}

			@Override
			public void execute() {
				for (int i = 0; this.bol(i); i++) {
					System.out.println("2 - SALIDA: " + i);
					try {
						Thread.sleep(500);
						synchronized (this.stateMutex) {
							if (this.taskState == TaskStatus.PAUSE) {
								this.stateMutex.wait();
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			private synchronized boolean bol(int i) {
				return this.taskState != TaskStatus.STOP && i < 5;
			}

			@Override
			public void afterExecute() {
				System.out.println("2 - POS PROCESO");
			}
		};

		this.pool = new GenericTaskPool<GenericTask<Integer>>("POOL");
		this.pool.addTask(task1);
		this.pool.addTask(task2);

		try {
			System.out.println("------------------");
			System.out.println("---- ARRANQUE ----");
			this.pool.start();

			Thread.sleep(1500);
			this.pool.stop();
			System.out.println("----- PARADA -----");

			this.pool.join();
			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");

			System.out.println("------------------");
			System.out.println("--- REARRANQUE ---");
			this.pool.start();
			this.pool.join();
			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");

			System.out.println("------------------");
			System.out.println("---- REINICIO ----");
			this.pool.reboot();

			System.out.println("---- ARRANQUE ----");
			this.pool.start();
			this.pool.join();
			System.out.println("-- FINALIZACIÓN --");
			System.out.println("------------------");
			Assert.assertTrue(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

}
