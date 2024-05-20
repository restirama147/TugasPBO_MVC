package Model;

//import Model.Mahasiswa.ModelMahasiswa;
//import java.util.ArrayList;
//import java.util.List;

import java.sql.*;
public class Connector {

    /* 
        Menyimpan informasi database ke dalam sebuah variabel.
        Pada contoh ini kita menggunakan database bernama "prak_pbo_if_f".
     */
    
    //PILAR ENCAPSULASI MENGAMANKAN DATA (PRIVATE)
    private static String nama_db = "prak_pbo_if_f";
    private static String username = "root";
    private static String password = "";
    private static String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    private static String url_db = "jdbc:mysql://localhost:3306/" + nama_db; //port mysql

    static Connection conn;

    public static Connection Koneksi() {
        try {
            // Register driver yang akan dipakai
            Class.forName(jdbc_driver);

            conn = DriverManager.getConnection(url_db, username, password);
            System.out.println("MySQL Connected");
        } catch (ClassNotFoundException | SQLException e) {
            // Menampilkan pesan error ketika MySQL gagal terhubung.
            System.out.println("Error " + e.getLocalizedMessage());
        }
        return conn;
    }
}
