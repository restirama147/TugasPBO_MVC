package View.Mahasiswa;

import Controller.ControllerMahasiswa;
import Model.Mahasiswa.ModelMahasiswa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import praktikum7.HalamanPilih;

public class ViewData extends JFrame {
    
    Controller.ControllerMahasiswa controller;
    
    Integer baris;

    // Menginisiasi komponen
    JLabel header = new JLabel("Selamat Datang!");
    JButton tombolTambah = new JButton("Tambah Mahasiswa");
    JButton tombolEdit = new JButton("Edit Mahasiswa");
    JButton tombolHapus = new JButton("Hapus Mahasiswa");
    JButton tombolKembali = new JButton("Kembali");

    /*
      Untuk membuat Tabel, kita memerlukan 3 komponen, yaitu:
      1. JTable sebagai komponen tabelnya
      2. DefaultTableModel untuk model atau isinya
      3. JScrollPane supaya tabel dapat di-scroll saat datanya melebihi ukuran layar.
     */
    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;

    /*
      Nama kolom tabelnya disimpan ke dalam variabel "namaKolom" yang memiliki 
      tipe data Array String.
     */
    String namaKolom[] = {"ID", "Nama", "NIM"};
    
    public ViewData() {
        tableModel = new DefaultTableModel(namaKolom, 0); //nama kolom array, 0 karena belum ada data
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table); //bisa discroll

        setTitle("Daftar Mahasiswa"); // judul
        setVisible(true); //biar layar kelihatan
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //program ditengah
        setLayout(null);
        setSize(552, 580);
        
        add(header);
        header.setBounds(20, 8, 440, 24);
        
        add(scrollPane);
        scrollPane.setBounds(20, 36, 512, 320);
        
        add(tombolTambah);
        tombolTambah.setBounds(20, 370, 512, 40);
        
        add(tombolEdit);
        tombolEdit.setBounds(20, 414, 512, 40);
        
        add(tombolHapus);
        tombolHapus.setBounds(20, 456, 512, 40);
        
        add(tombolKembali);
        tombolKembali.setBounds(20, 498, 512, 40);
        
        table.setCellSelectionEnabled(false); //memilih secara baris bukan satu kolom saja
        table.setRowSelectionAllowed(true);
        
        controller = new ControllerMahasiswa(this);
        controller.getAllMahasiswa();

        //agar dapat memilih item yang akan
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                baris = table.getSelectedRow();
            }
        });
        
        tombolTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new InsertData();
            }
        });
        
        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HalamanPilih();
            }
        });
        
        tombolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {
                    ModelMahasiswa pilih = new ModelMahasiswa();
                    
                    Integer id = (int) table.getValueAt(baris, 0);
                    String nama = table.getValueAt(baris, 1).toString();
                    String nim = table.getValueAt(baris, 2).toString();
                    
                    pilih.setId(id);
                    pilih.setNama(nama);
                    pilih.setNim(nim);
                    
                    dispose();
                    new EditData(pilih);
                } else {
                    JOptionPane.showMessageDialog(null, "Data Belum dipilih");
                }
            }
        });
        
        tombolHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {
                    controller.deleteMahasiswa(baris);
                    baris = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih Data");
                }
                
            }
        });
    }
    
    public JTable getTableMahasiswa() {
        return table;
    }
}
