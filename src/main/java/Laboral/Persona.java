package Laboral;

/**
 * @author jesus
 *
 *
 * Clase Persona. La unidad mínima para generar un individuo.
 */


public class Persona {
	public String nombre;
	public String dni;
	public String sexo;

	/**
	 * Constructor de la clase Persona con asignación de todos sus atributos.
	 * 
	 * @param nombre	Nombre completo de la persona.
	 * @param dni	Código del Documento Nacional de Identidad de la persona.	
	 * @param sexo	Sexo asignado a la persona.
	 */
	public Persona(String nombre, String dni, String sexo) {
		this.nombre = nombre;
		this.dni = dni;
		this.sexo = sexo;
	}

	/**
	 * Constructor de la clase Persona con asignación de su nombre y sexo.
	 * 
	 * @param nombre Nombre completo de la persona.
	 * @param sexo	Sexo asignado a la persona.
	 */
	public Persona(String nombre, String sexo) {
		super();
		this.nombre = nombre;
		this.sexo = sexo;
	}

	/**
	 * Asignación de un dni a la persona en concreto.
	 * 
	 * @param dni Código del dni que se va a asignar a la persona.
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Imprime por consola todos los atributos de la Persona.
	 * 
	 */
	public void Imprime() {
		System.out.println("Nombre: " + this.nombre + " DNI: " + this.dni);
	}
}
