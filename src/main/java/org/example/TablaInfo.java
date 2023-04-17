package org.example;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class TablaInfo extends AbstractTableModel {

    static String[] columnas;

    static String[] hectareasCol;
    static String[] plantasCol;
    static String[] fechasCol;
    static String[] productoresCol;

    static String[] nombresColumnas = {"Hectareas", "Planta", "Fecha", "Productor"};

    static Object[][] datos;
    static DefaultTableModel dtm;

    public static DefaultTableModel tablaActualizar() throws SQLException {
        columnas = HectareasService.datosTabla();

        hectareasCol = columnas[0].split(",");
        plantasCol = columnas[1].split(",");
        fechasCol = columnas[2].split(",");
        productoresCol = columnas[3].split(",");

        cortarCorchetes(hectareasCol);
        cortarCorchetes(plantasCol);
        cortarCorchetes(fechasCol);
        cortarCorchetes(productoresCol);

        datos = new Object[hectareasCol.length][nombresColumnas.length];
        for (int i = 0; i<nombresColumnas.length; i++){
            for (int j = 0; j<hectareasCol.length; j++){
                datos[j][0] = hectareasCol[j];
                datos[j][1] = plantasCol[j];
                datos[j][2] = fechasCol[j];
                datos[j][3] = productoresCol[j];
            }
        }
        dtm = new DefaultTableModel(datos,nombresColumnas);

        return dtm;
    }
    public static String[] cortarCorchetes(String textoCortar []){
        textoCortar[0] = textoCortar[0].substring(1, textoCortar[0].length());
        textoCortar[textoCortar.length-1] = textoCortar[textoCortar.length-1].substring(1, textoCortar[textoCortar.length-1].length()-1);
        return textoCortar;
    }

    public static void tablaUpdate(Object obj[]){
        dtm.addRow(obj);
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}


