package Laboral;
/**
 * @author jesus
 *
 * Clase Empleado. Toda persona contratada se considera empleado.
 */
public class Empleado extends Persona {
	private int categoria;
	int anyos;

	/**
	 * Constructor para empleado al que se le asignan los valores de los atributos de la clase Persona deseados por el usuario y los valores por defecto de los atributos específicos de la clase Empleado
	 * @param nombre	Nombre completo de la persona.
	 * @param dni	Código del Documento Nacional de Identidad de la persona.	
	 * @param sexo	Sexo asignado a la persona.
	 */
	public Empleado(String nombre, String dni, String sexo) {
		super(nombre, dni, sexo);
		try {
			setCategoria(1);
		} catch (DatosNoCorrectosException dnce) {
			System.out.println(dnce.getMessage());
			System.exit(1);
		}
		this.anyos = 0;
	}

	/**
	 * Constructor de la clase Empleado que incluye los atributos de la clase Persona y los atributos específicos de la clase Empleado
	 * @param nombre	Nombre completo de la persona.
	 * @param dni	Código del Documento Nacional de Identidad de la persona.	
	 * @param sexo	Sexo asignado a la persona.
	 * @param categoria Número de la categoría del empleo que desarrolla el empleado. La categoría no puede ser menor que 1 ni mayor que 10
	 * @param anyos Número de años trabajados que tiene un empleado en concreto. Los años trabajados no pueden ser menor que 0.
	 * @throws DatosNoCorrectosException En caso de que algún dato no sea correcto, el programa lanza DatosNoCorrectosException
	 */
	public Empleado(String nombre, String dni, String sexo, int categoria, int anyos) throws DatosNoCorrectosException{
		super(nombre, dni, sexo);
		try {
			setCategoria(categoria);
		} catch (DatosNoCorrectosException dnce) {
			System.out.println(dnce.getMessage());
			System.exit(1);
		}
		if (anyos >= 0) {
			this.anyos = anyos;
		} else {
			throw new DatosNoCorrectosException("Datos no correctos");
		}
	}

	/**
	 * Asignación de una categoría nueva al empleado en concreto.
	 * @param categoria Número de la categoría del empleo que desarrolla el empleado.
	 * @throws DatosNoCorrectosException En caso de que algún dato no sea correcto, el programa lanza DatosNoCorrectosException
	 */
	public void setCategoria(int categoria) throws DatosNoCorrectosException {
		if (categoria >= 1 && categoria <= 10) {
			this.categoria = categoria;
		} else {
			throw new DatosNoCorrectosException("Datos no correctos");
		}
	}

	/**
	 * 
	 * @return Devuelve la categoría del puesto laboral del Empleado.
	 */
	public int getCategoria() {
		return this.categoria;
	}

	/**
	 * Aumenta en 1 los años de experiencia que tiene el Empleado.
	 */
	public void incrAnyo() {
		this.anyos++;
	}

	/**
	 * Imprime por consola todos los datos del Empleado.
	 */
	public void imprime() {
		System.out.println("Nombre: " + this.nombre + ", DNI: " + this.dni + ", Categoria: " + this.categoria
				+ ", Años trabajados: " + this.anyos);
	}
}
