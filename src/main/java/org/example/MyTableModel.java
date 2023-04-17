package org.example;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;

public class MyTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nombre", "Edad", "Dirección"};
    private Object[][] data = {{"Juan", 30, "Calle 123"},
            {"María", 25, "Avenida 456"}};

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }

    public void addRow(Object[] rowData) {
        data = Arrays.copyOf(data, data.length + 1);
        data[data.length - 1] = rowData;
        fireTableDataChanged();
    }
}