package com.common.util.model.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import com.common.util.model.query.filter.BetweenFilter;
import com.common.util.model.query.filter.CompareFilter;
import com.common.util.model.query.filter.Filter;
import com.common.util.model.query.filter.InFilter;
import com.common.util.model.query.filter.LikeFilter;
import com.common.util.model.query.filter.NullFilter;
import com.common.util.model.query.filter.LikeFilter.LikeType;

/**
 * La clase que permite probar la clase que representa los filtros de una consulta SQL.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class FilterUnitTest {

	/**
	 * Cuando se termina de ejecutar la prueba, dejamos un espacio en blanco.
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println();
	}

	/**
	 * Pruebas sobre el filtro de comparación.
	 */
	@Test
	public void pruebasSobreElFiltroDeComparación() {
		System.out.println("<<<<<<<<<<<<<<<<<<<< PRUEBA SOBRE UN FILTRO DE COMPARACIÓN >>>>>>>>>>>>>>>>>>>>");

		CompareFilter<Serializable> filter = null;

		filter = Filter.eq("attr", 1);
		System.out.println("     IGUALDAD: " + filter);

		filter = Filter.neq("attr", 1);
		System.out.println("  NO IGUALDAD: " + filter);

		filter = Filter.gt("attr", 1);
		System.out.println("        MAYOR: " + filter);

		filter = Filter.ge("attr", 1);
		System.out.println("MAYOR O IGUAL: " + filter);

		filter = Filter.lt("attr", 1);
		System.out.println("        MENOR: " + filter);

		filter = Filter.le("attr", 1);
		System.out.println("MENOR O IGUAL: " + filter);
	}

	/**
	 * Pruebas sobre el filtro between.
	 */
	@Test
	public void pruebasSobreElFiltroBetween() {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< PRUEBA SOBRE UN FILTRO DE BETWEEN >>>>>>>>>>>>>>>>>>>>>>");

		BetweenFilter<Serializable> filter = null;

		filter = Filter.between("attr", 1, 3);
		System.out.println("      BETWEEN: " + filter);
	}

	/**
	 * Pruebas sobre el filtro in.
	 */
	@Test
	public void pruebasSobreElFiltroIn() {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<< PRUEBAS SOBRE UN FILTRO DE IN >>>>>>>>>>>>>>>>>>>>>>>>");

		InFilter<Serializable> filter = null;
		List<String> listado = new ArrayList<String>();
		listado.add("A");
		listado.add("B");
		listado.add("C");
		listado.add("AB");
		listado.add("BC");
		listado.add("AC");
		listado.add("ABC");

		filter = Filter.in("attr", listado);
		System.out.println("    INCLUSIÓN: " + filter);
	}

	/**
	 * Pruebas sobre el filtro Like.
	 */
	@Test
	public void pruebasSobreElFiltroLike() {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<< PRUEBAS SOBRE UN FILTRO DE LIKE >>>>>>>>>>>>>>>>>>>>>>>");

		LikeFilter filter = null;

		filter = Filter.like("attr", "pepe", LikeType.EQUAL);
		System.out.println("   EQUAL LIKE: " + filter);

		filter = Filter.like("attr", "pepe", LikeType.START);
		System.out.println("   START LIKE: " + filter);

		filter = Filter.like("attr", "pepe", LikeType.END);
		System.out.println("     END LIKE: " + filter);

		filter = Filter.like("attr", "pepe", LikeType.ANY);
		System.out.println("     ANY LIKE: " + filter);

		filter = Filter.like("attr", "pepe");
		System.out.println("   EQUAL LIKE: " + filter);
	}

	/**
	 * Pruebas sobre el filtro null.
	 */
	@Test
	public void pruebasSobreElFiltroNull() {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<< PRUEBAS SOBRE UN FILTRO DE NULL >>>>>>>>>>>>>>>>>>>>>>>");

		NullFilter filter = null;

		filter = Filter.isNull("attr");
		System.out.println("      IS NULL: " + filter);
	}
}
