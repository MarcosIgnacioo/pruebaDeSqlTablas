package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class Ventana extends JFrame{
    public Ventana() throws SQLException {
        this.setVisible(true);
        this.setSize(900,900);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.BLUE);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel camposPanel = new JPanel();
            camposPanel.setVisible(true);
            camposPanel.setBackground(Color.CYAN);
            camposPanel.setPreferredSize(new Dimension(600,600));
            camposPanel.setLayout(new GridBagLayout());
            this.add(camposPanel, BorderLayout.CENTER);
        JPanel tablaPanel = new JPanel();
        tablaPanel.setVisible(true);
        tablaPanel.setBackground(Color.pink);
        tablaPanel.setPreferredSize(new Dimension(300,300));
        tablaPanel.setLayout(new BorderLayout());
        this.add(tablaPanel, BorderLayout.WEST);

        JLabel hectareasLbl = new JLabel("Ingresa las hectareas que se hayan sembrado");
        hectareasLbl.setFont(new Font("Arial", Font.BOLD, 24));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,50,0);
        camposPanel.add(hectareasLbl,gbc);
        TextField tf = new TextField();
        tf.setPreferredSize(new Dimension(200,50));
        gbc.gridx = 1;
        gbc.gridy = 1;
        camposPanel.add(tf,gbc);

        JLabel hectareasLbl2 = new JLabel("Ingresa la planta que se haya sembrado");
        hectareasLbl2.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 2;
        camposPanel.add(hectareasLbl2,gbc);

        TextField tf2 = new TextField();
        tf2.setPreferredSize(new Dimension(200,50));
        gbc.gridx = 1;
        gbc.gridy = 3;
        camposPanel.add(tf2,gbc);

        JLabel hectareasLbl3 = new JLabel("Ingresa el nombre del productor");
        hectareasLbl3.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 4;
        camposPanel.add(hectareasLbl3,gbc);

        TextField tf3 = new TextField();
        tf3.setPreferredSize(new Dimension(200,50));
        gbc.gridx = 1;
        gbc.gridy = 5;
        camposPanel.add(tf3,gbc);

        JTable tabla = new JTable(0,0);
        tabla.setVisible(true);
        TablaInfo t = new TablaInfo();
        TablaInfo.tablaActualizar();

        String[] nombresColumnas = {"Hectareas", "Planta", "Fecha", "Productor"};

        ArrayList<Object[][]> datos2 = new ArrayList<Object[][]>();
        Object[][] datos = {
                {1,"Zumaya",    "Abraham",   ""},
                {2,"Arias",     "Cruz",      ""},
                {3,"Alejandro", "Francisco", ""},
                {4,"Con h",     "Cris",      ""},
                {5,"Mendoz",    "Piter",     ""}
        };
        DefaultTableModel dtm = new DefaultTableModel(datos,nombresColumnas);
        tabla.setModel(dtm);
        tabla.setLocation(250,550);
        tabla.setSize(250,100);

        ScrollPane sp = new ScrollPane();
        sp.setVisible(true);
        sp.setPreferredSize(new Dimension(tablaPanel.getWidth(),tablaPanel.getHeight()));
        sp.add(tabla);
        tablaPanel.add(sp, BorderLayout.WEST);

        JButton confirmar = new JButton("ok");
        confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (HectareasService.disponibilidadDeHectareas(tf.getText(),tf2.getText(),tf3.getText())) {
                        HectareasService.meterDatosConfirmado(tf.getText(),tf2.getText(),tf3.getText());
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Has ingresado mas hectareas de las disponibles", "asd",  JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 6;
        camposPanel.add(confirmar,gbc);

        repaint();
        revalidate();
    }
}
