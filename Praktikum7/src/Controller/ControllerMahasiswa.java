package Controller;

import Model.Mahasiswa.ModelMahasiswa;
import Model.Mahasiswa.ModelTabel;
import View.Mahasiswa.EditData;
import View.Mahasiswa.InsertData;
import View.Mahasiswa.ViewData;
import java.util.List;
import javax.swing.JOptionPane;

public class ControllerMahasiswa {

    ViewData view;
    InsertData insert;
    EditData edit;

    Model.Mahasiswa.InterfaceDAO daoMahasiswa;

    List<Model.Mahasiswa.ModelMahasiswa> daftarMahasiswa;

    public ControllerMahasiswa(ViewData view) {
        this.view = view;
        this.daoMahasiswa = new Model.Mahasiswa.DAO();
    }

    public ControllerMahasiswa(InsertData insert) {
        this.insert = insert;
        this.daoMahasiswa = new Model.Mahasiswa.DAO();
    }

    public ControllerMahasiswa(EditData edit) {
        this.edit = edit;
        this.daoMahasiswa = new Model.Mahasiswa.DAO();
    }

    public void getAllMahasiswa() {
        daftarMahasiswa = daoMahasiswa.getAll();
        Model.Mahasiswa.ModelTabel table = new ModelTabel(daftarMahasiswa);

        //Mengirim data ke view
        view.getTableMahasiswa().setModel(table);
    }

    public void insertMahasiswa() {
        try {
            Model.Mahasiswa.ModelMahasiswa inputMahasiswa = new ModelMahasiswa();
            String nama = insert.getInputNama();
            String nim = insert.getInputNIM();

            if ("".equals(nama) || "".equals(nim)) {
                throw new Exception("Isi Data Nama dan Nim");
            }

            inputMahasiswa.setNama(nama);
            inputMahasiswa.setNim(nim);

            daoMahasiswa.insert(inputMahasiswa);
            insert.dispose();
            new ViewData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getLocalizedMessage());
        }
    }

    public void EditMahasiswa(int id) {
        try {
            Model.Mahasiswa.ModelMahasiswa editMahasiswa = new ModelMahasiswa();
            String nama = edit.getEditNama();
            String nim = edit.getEditNIM();

            if ("".equals(nama) || "".equals(nim)) {
                throw new Exception("Isi Data Nama dan Nim");
            }

            editMahasiswa.setId(id);
            editMahasiswa.setNama(nama);
            editMahasiswa.setNim(nim);

            daoMahasiswa.update(editMahasiswa);
            JOptionPane.showMessageDialog(null, "Data telah di edit");

            edit.dispose();

            new ViewData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getLocalizedMessage());

        }
    }

    public void deleteMahasiswa(int baris) {
        Integer id = (int) view.getTableMahasiswa().getValueAt(baris, 0);
        String nama = view.getTableMahasiswa().getValueAt(baris, 1).toString();

        int input = JOptionPane.showConfirmDialog(null, "Hapus" + nama + "?", "Hapus Mahasiswa", JOptionPane.YES_NO_OPTION);

        if (input == 0) {
            daoMahasiswa.delete(id);

            JOptionPane.showMessageDialog(null, "Berhasil Menghapus Data");
           getAllMahasiswa();
        }
    }

}
