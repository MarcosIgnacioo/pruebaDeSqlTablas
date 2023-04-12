package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class HectareasService extends JFrame {


    public static void meterDatosConfirmado(String hectareas,String productor,String planta) throws SQLException {
        Hectareas h = new Hectareas();
        h.setHectareas(Float.parseFloat(hectareas));
        h.setProductor(productor);
        h.setPlanta(planta);
        HectareasDAO.insertarDatos(h);
    }
    public static boolean disponibilidadDeHectareas(String hectareas,String productor,String planta) throws SQLException {
        Hectareas h = new Hectareas();
        h.setHectareas(Float.parseFloat(hectareas));
        h.setProductor(productor);
        h.setPlanta(planta);
        return HectareasDAO.actualizadorDeHectareas(h);
    }
    public static String[] datosTabla() throws SQLException {
        String datos [] = new String[4];
        datos = HectareasDAO.mostrarTabla();
        return datos;
    }
}
