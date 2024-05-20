/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package praktikum7;

import View.Dosen.ViewDosen;
import View.Mahasiswa.ViewData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class HalamanPilih extends JFrame implements ActionListener {

    JLabel header = new JLabel("CRUD Dosen dan Mahasiswa!");
    JLabel subheader = new JLabel("Pilih Menu");

    JButton tombolDosen = new JButton("Dosen");
    JButton tombolMahasiswa = new JButton("Mahasiswa");

    public HalamanPilih() {
        setVisible(true);
        setSize(480, 240);
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null); //hasil running berada di tengah

        add(header);
        header.setFont(header.getFont().deriveFont(20.0f));
        header.setBounds(20, 20, 440, 24);

        add(subheader);
        subheader.setFont(subheader.getFont().deriveFont(14.0f));
        subheader.setBounds(20, 55, 440, 12);

        add(tombolDosen);
        tombolDosen.setBounds(20, 80, 200, 40);
        tombolDosen.addActionListener(this);

        add(tombolMahasiswa);
        tombolMahasiswa.setBounds(240, 80, 200, 40);
        tombolMahasiswa.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == tombolMahasiswa) {
                ViewData viewMahasiswa = new ViewData();
                viewMahasiswa.setVisible(true);
                this.dispose();
            }else if(e.getSource() == tombolDosen){
                ViewDosen halamanDosen = new ViewDosen();
                halamanDosen.setVisible(true);
                this.dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }
    }

}
