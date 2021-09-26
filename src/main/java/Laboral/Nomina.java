package Laboral;
/**
 * 
 * @author jesus
 *
 * Clase Nómina. Clase utilizada para calcular el salario de un empleado según sus atributos.
 */
public class Nomina {
	private static final int SUELDO_BASE[] = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000,
			230000 };

	/**
	 *
	 * @param emp Empleado que recibe el método para calcular su sueldo.
	 * @return  Devuelve el sueldo de un Empleado teniendo encuenta su categoría y su experiencia laboral. 
	 */
	
	public int sueldo(Empleado emp) {
		int sueldo = SUELDO_BASE[emp.getCategoria() - 1] + 5000 * emp.anyos;
		return sueldo;
	}
}
