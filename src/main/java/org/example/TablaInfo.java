package org.example;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Arrays;

public class TablaInfo {
    public static JTable tablaActualizar() throws SQLException {
        JTable tabla = new JTable();
        String columnas[];
        columnas = HectareasService.datosTabla();
        System.out.println("nyana");
        String guardadoresInfo[] = {new String(), new String(), new String(), new String()};
        guardadoresInfo[0] = columnas[0];
        System.out.println(guardadoresInfo);
        String hectareasCol[], plantasCol[], fechasCol[], productoresCol[];
        hectareasCol = columnas[0].split(",");
        System.out.println(hectareasCol[0].substring(1,hectareasCol[0].length()));
        return tabla;
    }
}
