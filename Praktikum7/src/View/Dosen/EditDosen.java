/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Dosen;

import Controller.ControllerDosen;
import Model.Dosen.ModelDosen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author ASUS
 */
public class EditDosen extends JFrame {

    Controller.ControllerDosen controller;

    JLabel header = new JLabel("Edit Dosen");
    JLabel labelEditNama = new JLabel("Nama");
    JLabel labelEditNIDN = new JLabel("NIDN");
    JTextField EditNama = new JTextField();
    JTextField EditNidn = new JTextField();
    JButton tombolEdit = new JButton("Edit Data");
    JButton tombolKembali = new JButton("Kembali");

    public EditDosen(ModelDosen dosen) {
        setTitle("Daftar Dosen");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setSize(480, 240);

        add(header);
        add(labelEditNama);
        add(labelEditNIDN);
        add(EditNama);
        add(EditNidn);
        add(tombolEdit);
        add(tombolKembali);

        header.setBounds(20, 8, 440, 24);
        labelEditNama.setBounds(20, 32, 440, 24);
        EditNama.setBounds(18, 56, 440, 36);
        labelEditNIDN.setBounds(20, 96, 440, 24);
        EditNidn.setBounds(18, 120, 440, 36);
        tombolKembali.setBounds(20, 160, 215, 40);
        tombolEdit.setBounds(240, 160, 215, 40);

        EditNama.setText(dosen.getNama());
        EditNidn.setText(dosen.getNidn());

        controller = new ControllerDosen(this);

        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewDosen();
            }
        });
        tombolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.EditDosen(dosen.getId());
            }
        });
    }

    public String getEditNama() {
        return EditNama.getText();
    }

    public String getEditNidn() {
        return EditNidn.getText();
    }
}
