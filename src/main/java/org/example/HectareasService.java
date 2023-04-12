package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class HectareasService extends JFrame {

    public HectareasService(){
        this.setVisible(true);
        this.setSize(300,300);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.BLUE);
        this.setLayout(new GridLayout(3,3));
        TextField tf = new TextField();
        this.add(tf);

        TextField tf2 = new TextField();
        this.add(tf2);

        TextField tf3 = new TextField();
        this.add(tf3);

        JButton confirmar = new JButton("ok");
        confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (disponibilidadDeHectareas(tf.getText(),tf2.getText(),tf3.getText())) {

                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Has ingresado mas hectareas de las disponibles", "asd",  JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        this.add(confirmar);
    }
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
        if (HectareasDAO.verificacionHectareas(h)-Float.parseFloat(hectareas) >= 0){
            System.out.println("Se puede");
            HectareasDAO.actualizadorDeHectareas(h);
            return true;
        }
        else {
            System.out.println("No se puede UNUNUNU");
            return false;
        }
    }


}
