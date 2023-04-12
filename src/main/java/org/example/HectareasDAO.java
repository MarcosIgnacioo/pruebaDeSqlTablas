package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HectareasDAO {
    static Conexion dbConnect = null;
    String tablas[] = {"pichilingue", "pescadero"};
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

    public static boolean actualizadorDeHectareas(Hectareas hectarea) throws SQLException {

        if (dbConnect == null){
            dbConnect = new Conexion();
        }
        try (Connection conexion = dbConnect.getConnection()){
            PreparedStatement ps = null;
            try{
                if (HectareasDAO.verificacionHectareas(hectarea)-hectarea.getHectareas() >= 0){
                String query = "UPDATE terreno_informacion_pichilingue SET hectareas_usadas = (hectareas_usadas + ?) WHERE terreno_informacion_pichilingue . id = 1;";
                ps = conexion.prepareStatement(query);
                ps.setString(1, String.valueOf(hectarea.getHectareas()));
                ps.executeUpdate();

                query = "UPDATE terreno_informacion_pichilingue SET hectareas_libres = (hectareas_totales - hectareas_usadas)";
                ps = conexion.prepareStatement(query);
                ps.executeUpdate();
                return true;
                }
                else {
                    return false;
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return false;
    }

    public  static String[] mostrarTabla()throws SQLException {
        String columnasInfo [] = new String[4];
        if (dbConnect == null) {
            dbConnect = new Conexion();
        }
        float hectareasTotales = 0;
        try (Connection conexion = dbConnect.getConnection()) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            int i = 0;
            ArrayList<String> hectareas = new ArrayList<String>();
            ArrayList<String> plantas = new ArrayList<String>();
            ArrayList<String> fechas = new ArrayList<String>();
            ArrayList<String> productores = new ArrayList<String>();
            try {
                String query = "SELECT * from `pichilingue`";
                ps = conexion.prepareStatement(query);
                rs = ps.executeQuery(); // ejectua la consulta sin transacciones
                while (rs.next()) {
                    hectareas.add(String.valueOf(rs.getFloat("hectareas")));
                    plantas.add(rs.getString("planta"));
                    fechas.add(rs.getString("fecha"));
                    productores.add(rs.getString("productor"));
                    System.out.println("Hectareas: " + hectareas.get(i));;
                    System.out.println("Plantas: " + plantas.get(i));;
                    System.out.println("fechas: " + fechas.get(i));;
                    System.out.println("Productores: " + productores.get(i));;
                    i++;
                }
                columnasInfo[0] = String.valueOf(hectareas);
                columnasInfo[1] = String.valueOf(plantas);
                columnasInfo[2] = String.valueOf(fechas);
                columnasInfo[3] = String.valueOf(productores);
                return columnasInfo;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        return columnasInfo;
    }

}
