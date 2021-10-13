package Laboral;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

	private Connection con;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;

	public DatabaseManager() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Nominas", "root", "");
		st = con.createStatement();
	}

	public void altaEmpleado(Empleado emp, Nomina nom) throws SQLException {
		st.executeUpdate("INSERT INTO Nominas.empleados VALUES ('" + emp.nombre + "','" + emp.dni + "','" + emp.sexo
				+ "'," + emp.getCategoria() + "," + emp.anyos + ");");
		st.execute("INSERT INTO Nominas.nominas (DNI,Sueldo) VALUES('" + emp.dni + "'," + nom.sueldo(emp) + ");");
	}

	public void altaEmpleado(List<Empleado> emps, Nomina nom) throws SQLException {
		for (Empleado empleado : emps) {
			altaEmpleado(empleado, nom);
		}
	}

	public void actualizarEmpleado(String dni, String valor, int aux) throws SQLException {
		switch (aux) {
		case 1:
			ps = con.prepareStatement("UPDATE Nominas.empleados SET Nombre_Completo = '" + valor + "' WHERE DNI = '" + dni + "';");
			break;
		case 2:
			ps = con.prepareStatement("UPDATE Nominas.empleados SET DNI = '" + valor + "' WHERE DNI = '" + dni + "';");
			break;
		case 3:
			ps = con.prepareStatement("UPDATE Nominas.empleados SET Sexo = '" + valor + "' WHERE DNI = '" + dni + "';");
			break;
		case 4:
			ps = con.prepareStatement("UPDATE Nominas.empleados SET Categoria = '" + valor + "' WHERE DNI = '" + dni + "';");
			break;
		case 5:
			ps = con.prepareStatement("UPDATE Nominas.empleados SET Anyos = '" + valor + "' WHERE DNI = '" + dni + "';");
			break;
		}
		ps.executeUpdate();
	}

	public void actualizarSalario(String dni, String valor) throws SQLException {
		st.executeUpdate("UPDATE Nominas.nominas SET Sueldo = " + valor + " WHERE DNI = '" + dni + "';");
	}

	public void selectEmpleados() throws SQLException {
		ps = con.prepareStatement("select * from Nominas.empleados;");
		rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		while(rs.next()) {
			for (int i = 1; i <=5 ; i++) {
		        if (i > 1) System.out.print(",  ");
		        String columnValue = rs.getString(i);
		        System.out.print(columnValue + " " + rsmd.getColumnName(i));
		    }
			System.out.println();
			System.out.println("--------------------------------");
		}
	}

	public Empleado selectEmpleado(String dni) throws SQLException, DatosNoCorrectosException {
		ps = con.prepareStatement("SELECT * FROM Nominas.empleados WHERE DNI = '" + dni + "';");
		rs = ps.executeQuery();
		rs.next();
		return new Empleado(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
	}
	
	public List<String> selectDNIs() throws SQLException{
		List<String> dnis = new ArrayList();
		ps = con.prepareStatement("SELECT DNI FROM Nominas.empleados");
		rs = ps.executeQuery();
		while (rs.next()) {
			dnis.add(rs.getString("DNI"));
		}
		return dnis;
	}

	public void selectSalario(String dni) throws SQLException {
		ps = con.prepareStatement("select sueldo from Nominas.nominas WHERE DNI = '" + dni + "';");
		rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt(1));
		}
	}
}
