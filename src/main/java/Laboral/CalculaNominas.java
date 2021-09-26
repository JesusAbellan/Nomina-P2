package Laboral;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalculaNominas {
	public static void main(String[] args) {
		List<Empleado> emps = new ArrayList();
		DatabaseManager dm = null;
		FicheroManager fm = null;
		Scanner sc = new Scanner(System.in);
		Nomina nom = new Nomina();

		int aux;

		do {
			System.out.println("1.- Mostrar la información de todos los empleados");
			System.out.println("2.- Mostar el salario de un empleado");
			System.out.println("3.- Modificar un dato de un empleado");
			System.out.println("4.- Recalcular y actualizar el sueldo de un empleado");
			System.out.println("5.- Recalcular y actualizar el sueldo de todos los empleados");
			System.out.println("6.- Realizar una copia de seguridad");
			System.out.println("0.- Salir");

			System.out.println("Introduzca un número para elegir una acción");

			aux = sc.nextInt();
			sc.nextLine();

			try {
				dm = new DatabaseManager();
				switch (aux) {
				case 1:
					dm.selectEmpleados();
					break;
				case 2:
					System.out.println("Introduzca el dni del empleado");
					dm.selectSalario(sc.nextLine());
					break;
				case 3:
					System.out.println("Seleccione el dato que quiere modificar");
					System.out.println("1.- Modificar el Nombre Completo ");
					System.out.println("2.- Modificar el DNI");
					System.out.println("3.- Modificar el Sexo");
					System.out.println("4.- Modificar la Categoría");
					System.out.println("5.- Modificar los anyos trabajados");
					
					int aux2 = sc.nextInt();
					sc.nextLine();
					
					System.out.println("Escribe el dni del empleado que quiere actualizar");
					String dni = sc.nextLine();
					
					System.out.println("Escribe el nuevo valor");
					String valor = sc.nextLine();
					
					dm.actualizarEmpleado(dni, valor, aux2);
					break;
				case 4:
					System.out.println("Introduce el dni del empleado");
					dni = sc.nextLine();
					
					dm.actualizarSalario(dni, String.valueOf(nom.sueldo(dm.selectEmpleado(dni))));
					
					break;
				case 5:
					
					List<String> dnis = dm.selectDNIs();
					for (String elem : dnis) {
						dm.actualizarSalario(elem, String.valueOf(nom.sueldo(dm.selectEmpleado(elem))));
					}

					break;
				case 6:

					fm = new FicheroManager("E:/Users/Jesus/eclipse-workspace/gestiondenominas/doc/empleados.txt",
							"E:/Users/Jesus/eclipse-workspace/gestiondenominas/doc/salarios.dat",
							"E:/Users/Jesus/eclipse-workspace/gestiondenominas/doc/empleadosNuevos.txt");
					System.out.println("Se ha creado el Fichero Manager");
					
					dnis = dm.selectDNIs();
					for (String elem : dnis) {
						emps.add(dm.selectEmpleado(elem));
					}
					fm.copiaDeSeguridad(emps);

					break;

				case 0:
					System.out.println("Adiós");
					break;

				default:
					System.out.println("Número incorrecto, introduce un número válido.");
					break;
				}
			} catch (SQLException | DatosNoCorrectosException | IOException e) {
				e.printStackTrace();
			}

		} while (aux != 0);
	}
}
