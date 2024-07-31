/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import java.sql.*;
import Model.DAO.DataBaseManager;
import java.time.LocalDateTime;

/**
 *
 * @author karen
 */
public class PersonaControlador {
    DataBaseManager db;
    
    public PersonaControlador (){
        db = new DataBaseManager();
    }
    
    public boolean agregarPersona(String cedula, String nombre, String apellido, String fecha, String email, String genero){
        int codigoGenero = db.getCodigoGenero(genero);
        boolean respuesta=db.agregarPaciente(cedula, nombre, apellido, fecha, email, codigoGenero);
        if (respuesta)
            return true;
        else
            return false;
    }
    
    public ResultSet buscarPaciente(String cedula){
        return db.buscarPaciente(cedula);
    }
    
    public String devolverGenero(int codigoGenero){
        return db.buscarGenero(codigoGenero);
    }
    
    public boolean editarPersona(String cedula, String nombre, String apellido, String fecha, String email, String genero, int codigoPaciente){
        int codigoGenero = db.getCodigoGenero(genero);
        boolean respuesta = db.editarPersona(cedula, nombre, apellido, fecha, email, codigoGenero, codigoPaciente);
        
        if(respuesta)
            return true;
        else
            return false;
    }

    public boolean guardarRegistro(int aInt, LocalDateTime now, double parseDouble, int parseInt) {
        if(db.agregarRegistro(aInt,now,parseDouble,parseInt)){
            return true;
        }else{
            return false;
        }
    }
}
