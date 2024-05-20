/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Dosen;

import Model.Connector;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ASUS
 */
public class DAODosen implements InterfaceDAODosen{

    @Override
    public void insert(ModelDosen dosen) {
        try {
            String query = "INSERT INTO dosen (nama, nidn) VALUES (?,?);";

            PreparedStatement statement;
            statement = Connector.Koneksi().prepareStatement(query);
            statement.setString(1, dosen.getNama());
            statement.setString(2, dosen.getNidn());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
             System.out.println("Error" + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(ModelDosen dosen) {
        try {
            // Perintah query disimpan ke dalam variabel "query"
            String query = "UPDATE dosen SET nama=?, nidn=? WHERE id=?;";

            PreparedStatement statement;
            statement = Connector.Koneksi().prepareStatement(query);
            statement.setString(1, dosen.getNama());
            statement.setString(2, dosen.getNidn());
            statement.setInt(3, dosen.getId());

            // Menjalankan query untuk menghapus data mahasiswa yang dipilih
            statement.executeUpdate();

            // Menutup koneksi untuk menghemat penggunaan memory.
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error! Update Gagal" + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
             // Perintah query disimpan ke dalam variabel "query"
            String query = "DELETE FROM dosen WHERE id=?;";

          
            PreparedStatement statement;
            statement = Connector.Koneksi().prepareStatement(query);
            statement.setInt(1, id);

            // Menjalankan query untuk menghapus data mahasiswa yang dipilih
            statement.executeUpdate();

            // Menutup koneksi untuk menghemat penggunaan memory.
            statement.close();

        } catch (SQLException e) {
            System.out.println("Error! Penghapusan Gagal" + e.getLocalizedMessage());

        }
    }

    @Override
    public List<ModelDosen> getAll() {
          List<ModelDosen> listDosen = null;
        try {
            listDosen = new ArrayList<>();

            // Membuat objek statement yang digunakan untuk melakukan query.
            Statement statement = Connector.Koneksi().createStatement();

            /* 
                Menyimpan query database ke dalam varibel "query".
                Dalam hal ini, kita akan mengambil seluruh data mahasiswa pada tabel "mahasiswa".
             */
            String query = "SELECT * FROM dosen;";

            // Mengeksekusi query dan menyimpannya ke dalam variabel "resultSet".
            ResultSet resultSet = statement.executeQuery(query);

            /* 
                Karena hasil query memiliki tipe data List, supaya dapat mencetak semua data mahasiswa,
                Kita perlu melakukan looping (perulangan) untuk mencetak tiap-tiap elemen.
             */
            while (resultSet.next()) {
                // Membuat sebuah objek "Mahasiswa" untuk menyimpan data tiap-tiap mahasiswa
                ModelDosen dsn = new ModelDosen();

                // Memasukkan hasil query ke objek mahasiswa
                dsn.setId(resultSet.getInt("id"));
                dsn.setNama(resultSet.getString("nama"));
                dsn.setNidn(resultSet.getString("nidn"));

                /* 
                  Menambahkan mahasiswa ke dalam daftar mahasiswa.
                  Daftar mahasiswa disimpan ke dalam variabel "listMahasiswa"
                  yang memiliki tipe data List.
                 */
                listDosen.add(dsn);
            }

            // Menutup koneksi untuk menghemat penggunaan memory.
            statement.close();
        } catch (SQLException e) {
             System.out.println("Error!" + e.getLocalizedMessage());
        }
        return listDosen;
    }
    
}
