package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HectareasDAO {
    static Conexion dbConnect = null;
    public  static void insertarDatos(Hectareas hectarea)throws SQLException {
        if (dbConnect == null){
            dbConnect = new Conexion();
        }
        try (Connection conexion = dbConnect.getConnection()){
            PreparedStatement ps = null;
            try{
                String query = "INSERT INTO `pichilingue` (`hectareas`, `planta`, `fecha`, `productor`) VALUES (?, ?, current_timestamp(), ?);";
                ps = conexion.prepareStatement(query);
                ps.setString(1, String.valueOf(hectarea.getHectareas()));
                ps.setString(2, hectarea.getPlanta());
                ps.setString(3, hectarea.getProductor());
                ps.executeUpdate();
                System.out.println("Se hizo la insertada");
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public  static float verificacionHectareas(Hectareas hectarea)throws SQLException {
        if (dbConnect == null) {
            dbConnect = new Conexion();
        }
        float hectareasTotales = 0;
        try (Connection conexion = dbConnect.getConnection()) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                String query = "SELECT hectareas_totales from terreno_informacion_pichilingue";
                ps = conexion.prepareStatement(query);
                rs = ps.executeQuery(); // ejectua la consulta sin transacciones
                while (rs.next()) {
                    hectareasTotales = rs.getFloat("hectareas_totales");
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        return hectareasTotales;
    }

    public static void actualizadorDeHectareas(Hectareas hectarea) throws SQLException {

        if (dbConnect == null){
            dbConnect = new Conexion();
        }
        try (Connection conexion = dbConnect.getConnection()){
            PreparedStatement ps = null;
            try{
                String query = "UPDATE terreno_informacion_pichilingue SET hectareas_usadas = (hectareas_usadas + ?) WHERE terreno_informacion_pichilingue . id = 1;";
                ps = conexion.prepareStatement(query);
                ps.setString(1, String.valueOf(hectarea.getHectareas()));
                ps.executeUpdate();

                query = "UPDATE terreno_informacion_pichilingue SET hectareas_libres = (hectareas_totales - hectareas_usadas)";
                ps = conexion.prepareStatement(query);
                ps.executeUpdate();

                System.out.println("Se hizo la actualizada");
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
}
