/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Mahasiswa;

import Controller.ControllerMahasiswa;
import Model.Mahasiswa.ModelMahasiswa;
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
public class EditData extends JFrame {

    Controller.ControllerMahasiswa controller;
    
    JLabel header = new JLabel("Edit Mahasiswa");
    JLabel labelEditNama = new JLabel("Nama");
    JLabel labelEditNIM = new JLabel("NIM");
    JTextField EditNama = new JTextField();
    JTextField EditNIM = new JTextField();
    JButton tombolEdit = new JButton("Edit Data");
    JButton tombolKembali = new JButton("Kembali");

    public EditData(ModelMahasiswa mahasiswa) {
        setTitle("Daftar Mahasiswa");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setSize(480, 240);

        add(header);
        add(labelEditNama);
        add(labelEditNIM);
        add(EditNama);
        add(EditNIM);
        add(tombolEdit);
        add(tombolKembali);

        header.setBounds(20, 8, 440, 24);
        labelEditNama.setBounds(20, 32, 440, 24);
        EditNama.setBounds(18, 56, 440, 36);
        labelEditNIM.setBounds(20, 96, 440, 24);
        EditNIM.setBounds(18, 120, 440, 36);
        tombolKembali.setBounds(20, 160, 215, 40);
        tombolEdit.setBounds(240, 160, 215, 40);

        EditNama.setText(mahasiswa.getNama());
        EditNIM.setText(mahasiswa.getNim());
        
        controller = new ControllerMahasiswa(this);

        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewData();
            }
        });

        tombolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.EditMahasiswa(mahasiswa.getId());
            }
        });
    }

    public String getEditNama() {
        return EditNama.getText();
    }

    public String getEditNIM() {
        return EditNIM.getText();
    }
   
}
