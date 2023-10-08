import java.sql.*;
import java.util.*;
import java.io.*;

public class Principal {
	public static void main(String[] args) {
		try {
			Scanner entrada = new Scanner(System.in);
			System.out.println("Seleccione una opcion ");
			System.out.println("\n 1. Insertar \n 2. Actualizar \n 3. Eliminar \n 4. Consultar_todo");

			String opcion = entrada.nextLine();
			switch (opcion) {
			case "1":
				Insertar();
				break;
			case "2":
				Actualizar();
				break;
			case "3":
				Eliminar();
				break;
			case "4":
				Mostrar_Todo();
				break;
			default:
				System.out.println("Seleccione una opcion adecuada!");
				break;
			}

		} catch (SQLException e) {
			System.out.println("Error al conectar: " + e.getMessage());
		}
	}

	public static void Insertar() throws SQLException {
		Scanner entrada = new Scanner(System.in);

		Connection conexion = null;
		PreparedStatement statement = null;

		try {
			// Cargar el driver de MariaDB
			Class.forName("org.mariadb.jdbc.Driver");

			// Establecer la conexión con la base de datos
			String url = "jdbc:mariadb://127.0.0.1:3304/fabrica_de_sillas";
			String user = "root";
			String password = "Alfredo+123";
			conexion = DriverManager.getConnection(url, user, password);

			// Crear una nueva silla en la base de datos
			// materiales?
			String insertQuery = "INSERT INTO material (nombre, costo, cantidad) VALUES ( ?, ?, ?)";
			statement = conexion.prepareStatement(insertQuery);
			System.out.println("Ingrese un nombre para el material");
			String nombre_material = entrada.nextLine();
			statement.setString(1, nombre_material);
			System.out.println("Ingrese un costo para el material");
			Double costo = entrada.nextDouble();
			statement.setDouble(2, costo);
			System.out.println("Ingrese una cantidad del material");
			Integer cantidad = entrada.nextInt();
			statement.setInt(3, cantidad);
			int rowsInsertedMaterial = statement.executeUpdate();
//silla
			insertQuery = "INSERT INTO silla (nombre, costo, precio_venta, id_material) VALUES (?, ?, ?, ?)";
			entrada.nextLine();
			statement = conexion.prepareStatement(insertQuery);
			statement.setString(1, "silla");
			System.out.println("Ingrese un costo para la silla");
			Double costo_silla = entrada.nextDouble();
			statement.setDouble(2, costo_silla);
			System.out.println("Ingrese un precio de venta para la silla");
			Double precio_venta = entrada.nextDouble();
			statement.setDouble(3, precio_venta);
//corregir id material

			statement.setInt(4, 2);
			int rowsInsertedSilla = statement.executeUpdate();

			// Mostrar las sillas en la consola
			Mostrar_Todo();
		} catch (ClassNotFoundException | SQLException e) {

			System.out.println("Error: " + e.getMessage());
			e.printStackTrace(); // Imprimir la traza de la excepción

		} finally {
			// Cerrar recursos (statement y conexión)
			if (statement != null) {
				statement.close();
			}
			if (conexion != null) {
				conexion.close();
			}
		}
	}

	public static void Actualizar() throws SQLException {
		Scanner entrada = new Scanner(System.in);
		Connection conexion = null;
		PreparedStatement statement = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Establecer la conexión con la base de datos
			String url = "jdbc:mariadb://127.0.0.1:3304/fabrica_de_sillas";
			String user = "root";
			String password = "Alfredo+123";
			conexion = DriverManager.getConnection(url, user, password);

			// Crear una nueva silla en la base de datos
			// materiales?
			String updateQuery = "UPDATE silla SET costo = ?, precio_venta = ? WHERE id_silla = ?";
			System.out.println("Seleccione una silla para modificar por su id");
			Mostrar_Todo();
			Integer Id_Silla = entrada.nextInt();
			statement = conexion.prepareStatement(updateQuery);
			// aqui
			System.out.println("Ingrese un nuevo costo para la silla");
			Double nuevo_costo = entrada.nextDouble();
			statement.setDouble(1, nuevo_costo);
			System.out.println("Ingrese un nuevo precio de venta para la silla");
			Double nuevo_precio_venta = entrada.nextDouble();
			statement.setDouble(2, nuevo_precio_venta);
			// agergagr
			statement.setInt(3, Id_Silla);
			int rowsUpdatedSilla = statement.executeUpdate();
			Mostrar_Todo();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Cerrar recursos (statement y conexión)
			if (statement != null) {
				statement.close();
			}
			if (conexion != null) {
				conexion.close();
			}
		}
	}

	public static void Eliminar() throws SQLException {
		Scanner entrada = new Scanner(System.in);
		Connection conexion = null;
		PreparedStatement statement = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// Establecer la conexión con la base de datos
			String url = "jdbc:mariadb://127.0.0.1:3304/fabrica_de_sillas";
			String user = "root";
			String password = "Alfredo+123";
			conexion = DriverManager.getConnection(url, user, password);

			// Crear una nueva silla en la base de datos
			// materiales?
			String deleteQuery = "DELETE from silla WHERE id_silla =? ";
			System.out.println("Seleccione una silla para eliminar por su id");
			Mostrar_Todo();
			Integer Id_Silla = entrada.nextInt();
			statement = conexion.prepareStatement(deleteQuery);
			statement.setInt(1, Id_Silla);
			int rowsDeletedSilla = statement.executeUpdate();
			Mostrar_Todo();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Cerrar recursos (statement y conexión)
			if (statement != null) {
				statement.close();
			}
			if (conexion != null) {
				conexion.close();
			}
		}
	}

	public static void Mostrar_Todo() throws SQLException {
		Connection conexion = null;
		PreparedStatement statement = null;
		try {

			// Establecer la conexión con la base de datos
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3304/fabrica_de_sillas";
			String user = "root";
			String password = "Alfredo+123";
			conexion = DriverManager.getConnection(url, user, password);
			String selectQuery = "SELECT * FROM silla";
			statement = conexion.prepareStatement(selectQuery);
			ResultSet resultados = statement.executeQuery();
			System.out.println("+------------------------------------------------------------------------+");
			while (resultados.next()) {
				int idSilla = resultados.getInt("id_silla");
				String nombre = resultados.getString("nombre");
				double res_costo = resultados.getDouble("costo");
				double precioVenta = resultados.getDouble("precio_venta");
				int idMaterial = resultados.getInt("id_material");

				System.out.println("|ID silla: " + idSilla + " Nombre: " + nombre + " Costo: " + res_costo
						+ " Precio de venta: " + precioVenta + " ID material: " + idMaterial + "|");

			}
			System.out.println("+------------------------------------------------------------------------+");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// Cerrar recursos (statement y conexión)
			if (statement != null) {
				statement.close();
			}
			if (conexion != null) {
				conexion.close();
			}
		}
	}
}
