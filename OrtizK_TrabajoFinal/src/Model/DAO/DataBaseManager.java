/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author karen
 */
public class DataBaseManager {
    Statement statement;
    Connection con;
    
    public void conectar(){
       try 
        {
            if(con!=null)
            {
                System.out.println ("ya existe la conexion");
            }
            else
            {
                con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pacientes_db","root","Km/op2004");
                statement= con.createStatement();
                System.out.println ("conexion exitosa");
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean agregarPaciente(String cedula, String nombre, String apellido, String fecha, String email, int codigoGenero)
    {
        conectar();
        try 
        {
            //creo el query
            PreparedStatement sentencia=con.prepareStatement("INSERT INTO Paciente (cedula, nombre, apellido, fechaNacimiento, email, codigoGenero)  VALUES (?,?,?,?,?,?)");
       
            //seteo los parámetros
            sentencia.setString(1, cedula);
            sentencia.setString(2, nombre);
            sentencia.setString(3, apellido);
            sentencia.setString(4, fecha);
            sentencia.setString(5, email);
            sentencia.setInt(6, codigoGenero);
            
            //ejecuto el query
            sentencia.executeUpdate();
            
            return true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ResultSet listarPacientes()
    {
        conectar();
        
        try 
        {
            ResultSet listado=statement.executeQuery("SELECT * FROM Jugador");
            return listado;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int eliminarPaciente(int codJugador)
    {
        conectar();
        try 
        {
            //creo el query
            PreparedStatement sentencia=con.prepareStatement("DELETE FROM Jugador WHERE codigoJugador= ?");
            //seteo el parámetro
            sentencia.setInt(1, codJugador);
            //ejecuto el query
            return sentencia.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public boolean modificarPaciente(String cedula, String nombre, String apellido, String fecha, String email, int codigoGenero)
    {
        conectar();
        try {
            //creo el query
            PreparedStatement sentencia=con.prepareStatement("UPDATE Jugador SET nombre=?, fechaNacimiento=? WHERE codigoJugador=?");
            //seteo el parámetro
            
            //ejecuto el query
            sentencia.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
            
    }
    
    public int getCodigoGenero(String generoNombre) {
        conectar();
        System.out.println("nombre genero: " +generoNombre);
        
        conectar();
        int codigoGenero = -1;
        String query = "SELECT codigoGenero FROM Genero WHERE genero = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, generoNombre);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                codigoGenero = resultSet.getInt("codigoGenero");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("codigo:"+codigoGenero);

        return codigoGenero;
    }

    public ResultSet buscarPaciente(String cedula) {
        conectar();
        String query = "SELECT * FROM Paciente WHERE cedula = ?";
        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, cedula);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public String buscarGenero(int codigoGenero) {
        conectar();
        String query = "SELECT * FROM Genero WHERE codigoGenero = ?";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, codigoGenero);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Retorna el valor de "genero" si hay resultados
                return resultSet.getString("genero");
            } else {
                // No se encontró el género
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean editarPersona(String cedula, String nombre, String apellido, String fecha, String email, int codigoGenero, int codigoPaciente) {
        conectar();
        String query = "UPDATE Paciente SET cedula = ?, nombre = ?, apellido = ?, email = ?, fechaNacimiento = ?, codigoGenero = ? WHERE codigoPaciente = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, cedula);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, apellido);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, fecha);
            preparedStatement.setInt(6, codigoGenero);
            preparedStatement.setInt(7, codigoPaciente);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean agregarRegistro(int aInt, LocalDateTime now, double parseDouble, int parseInt) {
        conectar();
        Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
        
        Date date = (Date) Date.from(instant);
        
        try 
        {
            //creo el query
            PreparedStatement sentencia=con.prepareStatement("INSERT INTO RegistroVital (codigoPaciente, fechayHora, pulso, saturacion_oxigeno)  VALUES (?,?,?,?)");
       
            //seteo los parámetros
            sentencia.setInt(1, aInt);
            sentencia.setDate(2, date);
            sentencia.setDouble(3, parseDouble);
            sentencia.setInt(4, parseInt);
            
            //ejecuto el query
            sentencia.executeUpdate();
            
            return true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
}
