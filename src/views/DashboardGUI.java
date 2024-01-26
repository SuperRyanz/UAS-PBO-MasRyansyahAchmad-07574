package views;

import controllers.KambingController;
import entity.KambingEntity;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

public class DashboardGUI {
    private final KambingController controller;
    private JFrame frame;
    private JTextField namaField, panjangField, beratField, tinggiField, statusField, tanggalField;
    private DefaultTableModel tableModel;
    private JTable table;

    public DashboardGUI() {
        controller = new KambingController();
        initialize();
        updateTable();
    }

    private void initialize() {
        frame = new JFrame("Dashboard Kambing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"Nama", "Panjang", "Berat", "Tinggi", "Status", "Tanggal"}, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(8, 2));
        inputPanel.add(new JLabel("Nama:"));
        namaField = new JTextField();
        inputPanel.add(namaField);

        inputPanel.add(new JLabel("Panjang:"));
        panjangField = new JTextField();
        inputPanel.add(panjangField);

        inputPanel.add(new JLabel("Berat:"));
        beratField = new JTextField();
        inputPanel.add(beratField);

        inputPanel.add(new JLabel("Tinggi:"));
        tinggiField = new JTextField();
        inputPanel.add(tinggiField);

        inputPanel.add(new JLabel("Status:"));
        statusField = new JTextField();
        inputPanel.add(statusField);

        inputPanel.add(new JLabel("Tanggal (dd/MM/yyyy):"));
        tanggalField = new JTextField();
        inputPanel.add(tanggalField);

        JButton addButton = new JButton("Tambah");
        addButton.addActionListener(this::addGoat);
        inputPanel.add(addButton);

        JButton deleteButton = new JButton("Hapus");
        deleteButton.addActionListener(this::deleteGoat);
        inputPanel.add(deleteButton);

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(this::editGoat);
        inputPanel.add(editButton);

        frame.add(inputPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    private void addGoat(ActionEvent event) {
        try {
            String nama = namaField.getText();
            double panjang = Double.parseDouble(panjangField.getText());
            double berat = Double.parseDouble(beratField.getText());
            double tinggi = Double.parseDouble(tinggiField.getText());
            String status = statusField.getText();
            Date tanggal = parseDate(tanggalField.getText());

            KambingEntity kambing = new KambingEntity(nama, panjang, berat, tinggi, status, tanggal);
            controller.addGoat(kambing);
            updateTable();
        } catch (NumberFormatException | ParseException e) {
            JOptionPane.showMessageDialog(frame, "Error in input format: " + e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteGoat(ActionEvent event) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String nama = (String) tableModel.getValueAt(row, 0);
            controller.deleteGoat(nama);
            updateTable();
        } else {
            JOptionPane.showMessageDialog(frame, "Silakan pilih baris yang akan dihapus", "Tidak ada seleksi", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void editGoat(ActionEvent event) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            namaField.setText((String) tableModel.getValueAt(row, 0));
            panjangField.setText(tableModel.getValueAt(row, 1).toString());
            beratField.setText(tableModel.getValueAt(row, 2).toString());
            tinggiField.setText(tableModel.getValueAt(row, 3).toString());
            statusField.setText((String) tableModel.getValueAt(row, 4));
            tanggalField.setText(formatDate((Date) tableModel.getValueAt(row, 5)));

            JButton okButton = new JButton("Ok");
            okButton.addActionListener(e -> updateGoat(row));
            frame.add(okButton, BorderLayout.NORTH);
            frame.revalidate();
        } else {
            JOptionPane.showMessageDialog(frame, "Silakan pilih baris yang akan diedit", "Tidak ada seleksi", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateGoat(int row) {
        try {
            String originalName = (String) tableModel.getValueAt(row, 0);

            String nama = namaField.getText();
            double panjang = Double.parseDouble(panjangField.getText());
            double berat = Double.parseDouble(beratField.getText());
            double tinggi = Double.parseDouble(tinggiField.getText());
            String status = statusField.getText();
            Date tanggal = parseDate(tanggalField.getText());

            KambingEntity updatedKambing = new KambingEntity(nama, panjang, berat, tinggi, status, tanggal);
            controller.updateGoat(updatedKambing, originalName);
            updateTable();
        } catch (NumberFormatException | ParseException e) {
            JOptionPane.showMessageDialog(frame, "Error in input format: " + e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        List<KambingEntity> goats = controller.getGoats();
        for (KambingEntity kambing : goats) {
            tableModel.addRow(new Object[]{kambing.getNama(), kambing.getPanjang(), kambing.getBerat(), kambing.getTinggi(), kambing.getStatus(), kambing.getTanggal()});
        }
    }

    private Date parseDate(String dateString) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}
