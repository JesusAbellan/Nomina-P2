package Laboral;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Jesus
 * 
 * Clase FicheroManager. Esta clase sirve para toda la gestión de archivos, tanto leer como escribir en los distintos documentos que requiere la aplicación.
 */
public class FicheroManager {
	private String rutaEmpleados = "";
	private String rutaSalarios = "";
	private String rutaEmpleadosNuevos = "";

	private File empleadosArchivo = null;
	private File salariosArchivo = null;
	private File empleadosNuevosArchivo = null;

	private BufferedWriter bwEmpleados = null;
	private BufferedWriter bwSalarios = null;
	private BufferedWriter bwEmpleadosNuevos = null;

	private BufferedReader brEmpleados = null;
	private BufferedReader brSalarios = null;
	private BufferedReader brEmpleadosNuevos = null;

	private Nomina nom = new Nomina();

	public FicheroManager(String rutaEmpleados, String rutaSalarios, String rutaEmpleadosNuevos) throws IOException {
		this.rutaEmpleados = rutaEmpleados;
		this.empleadosArchivo = new File(rutaEmpleados);

		this.rutaSalarios = rutaSalarios;
		this.salariosArchivo = new File(this.rutaSalarios);
		
		this.rutaEmpleadosNuevos = rutaEmpleadosNuevos;
		this.empleadosNuevosArchivo = new File(this.rutaEmpleadosNuevos);

		this.bwEmpleados = new BufferedWriter(new FileWriter(this.empleadosArchivo));
		this.bwSalarios = new BufferedWriter(new FileWriter(this.salariosArchivo));
		this.bwEmpleadosNuevos = new BufferedWriter(new FileWriter(this.empleadosNuevosArchivo,true));
		
		this.brEmpleados = new BufferedReader(new FileReader(this.empleadosArchivo));
		this.brEmpleados.mark(10000000);
		
		this.brSalarios = new BufferedReader(new FileReader(this.salariosArchivo));
		this.brSalarios.mark(100000000);
		
		this.brEmpleadosNuevos = new BufferedReader(new FileReader(this.empleadosNuevosArchivo));
		this.brEmpleadosNuevos.mark(10000000);
	}

	public void anadirEmpleado(List<Empleado> emps) throws IOException, NumberFormatException, DatosNoCorrectosException {
		String lineaEmpleado;
		String[] empleadoSeparado;
		Empleado empleadoTemporal;

		this.brEmpleados.reset();
		while ((lineaEmpleado = this.brEmpleados.readLine()) != null) {
			empleadoSeparado = lineaEmpleado.split("-");
			empleadoTemporal = new Empleado(empleadoSeparado[0], empleadoSeparado[1], empleadoSeparado[2],
					Integer.parseInt(empleadoSeparado[3]), Integer.parseInt(empleadoSeparado[4]));
			emps.add(empleadoTemporal);
		}
	}
	
	public List<Empleado> empleadosNuevos() throws IOException, NumberFormatException, DatosNoCorrectosException {
		String lineaEmpleado;
		String[] empleadoSeparado;
		Empleado empleadoTemporal;
		List<Empleado> emps = new ArrayList();

		this.brEmpleadosNuevos.reset();
		while ((lineaEmpleado = this.brEmpleadosNuevos.readLine()) != null) {
			empleadoSeparado = lineaEmpleado.split("-");
			empleadoTemporal = new Empleado(empleadoSeparado[0], empleadoSeparado[1], empleadoSeparado[2],
					Integer.parseInt(empleadoSeparado[3]), Integer.parseInt(empleadoSeparado[4]));
			emps.add(empleadoTemporal);
		}
		
		return emps;
	}
	
	public void copiaDeSeguridad(List<Empleado> emps) throws IOException {
		for (Empleado emp : emps) {
			String emptxt = emp.nombre+"-"+emp.sexo+"-"+emp.dni+"-"+emp.anyos+"-"+emp.getCategoria();
			this.bwEmpleados.write(emptxt);
			this.bwEmpleados.newLine();
			this.bwEmpleados.flush();
		}
		anadirSalarios(emps);	
		this.bwEmpleados.close();
		this.bwSalarios.close();
	}

	public void anadirSalarios(List<Empleado> emps) throws IOException {
		String lineaEmpleado;
		String lineaSalario;
		String DNItmp;
		Empleado emp;

		this.brEmpleados.reset();
		
		while ((lineaEmpleado = this.brEmpleados.readLine()) != null) {
			DNItmp = lineaEmpleado.split("-")[2];
			for (Empleado empleado : emps) {
				if (empleado.dni.equalsIgnoreCase(DNItmp)) {
					emp = empleado;
					lineaSalario = empleado.dni + "-" + nom.sueldo(emp);
					this.bwSalarios.write(lineaSalario);
					this.bwSalarios.newLine();
					this.bwSalarios.flush();
					System.out.println("entra");
				}
			}
		}
	}
}
