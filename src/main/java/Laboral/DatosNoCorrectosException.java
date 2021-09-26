package Laboral;


/**
 * @author jesus
 *
 *Excepción creada para cuando se introducen datos que no son correctos en la aplicación
 */
public class DatosNoCorrectosException extends Exception {
	public DatosNoCorrectosException(String errorMessage) {
		super(errorMessage);
	}
}
