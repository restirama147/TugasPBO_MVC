package Model.Mahasiswa;
//Data Akses Object
//Berhubungan dengan query

import Model.Connector;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class DAO implements InterfaceDAO {

    @Override
    public void insert(ModelMahasiswa mahasiswa) {
        try {
            String query = "INSERT INTO Mahasiswa (nama, nim) VALUES (?,?);";

            PreparedStatement statement;
            statement = Connector.Koneksi().prepareStatement(query);
            statement.setString(1, mahasiswa.getNama());
            statement.setString(2, mahasiswa.getNim());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error" + e.getLocalizedMessage());

        }
    }

    @Override
    public void update(ModelMahasiswa mahasiswa) {
        try {
            // Perintah query disimpan ke dalam variabel "query"
            String query = "UPDATE mahasiswa SET nama=?, nim=? WHERE id=?;";

            PreparedStatement statement;
            statement = Connector.Koneksi().prepareStatement(query);
            statement.setString(1, mahasiswa.getNama());
            statement.setString(2, mahasiswa.getNim());
            statement.setInt(3, mahasiswa.getId());

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
            String query = "DELETE FROM mahasiswa WHERE id=?;";

            /* 
              Memasukkan id berdasarkan data yang mau dihapus ke dalam query 
              untuk mengisi bagian "?".
             */
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
    public List<ModelMahasiswa> getAll() {
        List<ModelMahasiswa> listMahasiswa = null;
        try {
            /* 
              Membuat sebuah variabel bernama "listMahasiswa".
              Variabel ini memiliki tipe data List karena berfungsi untuk menyimpan banyak data
              Variabel ini nantinya akan digunakan untuk menyimpan daftar mahasiswa
              hasil query dari database.
             */
            listMahasiswa = new ArrayList<>();

            // Membuat objek statement yang digunakan untuk melakukan query.
            Statement statement = Connector.Koneksi().createStatement();

            /* 
                Menyimpan query database ke dalam varibel "query".
                Dalam hal ini, kita akan mengambil seluruh data mahasiswa pada tabel "mahasiswa".
             */
            String query = "SELECT * FROM mahasiswa;";

            // Mengeksekusi query dan menyimpannya ke dalam variabel "resultSet".
            ResultSet resultSet = statement.executeQuery(query);

            /* 
                Karena hasil query memiliki tipe data List, supaya dapat mencetak semua data mahasiswa,
                Kita perlu melakukan looping (perulangan) untuk mencetak tiap-tiap elemen.
             */
            while (resultSet.next()) {
                // Membuat sebuah objek "Mahasiswa" untuk menyimpan data tiap-tiap mahasiswa
                ModelMahasiswa mhs = new ModelMahasiswa();

                // Memasukkan hasil query ke objek mahasiswa
                mhs.setId(resultSet.getInt("id"));
                mhs.setNama(resultSet.getString("nama"));
                mhs.setNim(resultSet.getString("nim"));

                /* 
                  Menambahkan mahasiswa ke dalam daftar mahasiswa.
                  Daftar mahasiswa disimpan ke dalam variabel "listMahasiswa"
                  yang memiliki tipe data List.
                 */
                listMahasiswa.add(mhs);
            }

            // Menutup koneksi untuk menghemat penggunaan memory.
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error!" + e.getLocalizedMessage());

        }
        return listMahasiswa;
    }

}
