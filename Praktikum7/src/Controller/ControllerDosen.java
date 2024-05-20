package Controller;

import Model.Dosen.ModelDosen;
import Model.Dosen.ModelTabelDosen;
import View.Dosen.EditDosen;
import View.Dosen.InsertDosen;
import View.Dosen.ViewDosen;
import java.util.List;
import javax.swing.JOptionPane;

public class ControllerDosen {

    ViewDosen view;
    InsertDosen insert;
    EditDosen edit;

    Model.Dosen.InterfaceDAODosen daoDosen;

    List<Model.Dosen.ModelDosen> daftarDosen;

    public ControllerDosen(ViewDosen view) {
        this.view = view;
        this.daoDosen = new Model.Dosen.DAODosen();
    }

    public ControllerDosen(InsertDosen insert) {
        this.insert = insert;
        this.daoDosen = new Model.Dosen.DAODosen();
    }

    public ControllerDosen(EditDosen edit) {
        this.edit = edit;
        this.daoDosen = new Model.Dosen.DAODosen();
    }

    public void getAllDosen() {
        daftarDosen = daoDosen.getAll();
        Model.Dosen.ModelTabelDosen table = new ModelTabelDosen(daftarDosen);

        //Mengirim data ke view
        view.getTableDosen().setModel(table);
    }

    public void insertDosen() {
        try {
            Model.Dosen.ModelDosen inputDosen = new ModelDosen();
            String nama = insert.getInputNama();
            String nidn = insert.getInputNIDN();

            if ("".equals(nama) || "".equals(nidn)) {
                throw new Exception("Isi Data Nama dan Nim");
            }

            inputDosen.setNama(nama);
            inputDosen.setNidn(nidn);

            daoDosen.insert(inputDosen);
            insert.dispose();
            new ViewDosen();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getLocalizedMessage());
        }
    }

    public void EditDosen(int id) {
        try {
            Model.Dosen.ModelDosen editDosen = new ModelDosen();
            String nama = edit.getEditNama();
            String nidn = edit.getEditNidn();

            if ("".equals(nama) || "".equals(nidn)) {
                throw new Exception("Isi Data Nama dan Nim");
            }

            editDosen.setId(id);
            editDosen.setNama(nama);
            editDosen.setNidn(nidn);

            daoDosen.update(editDosen);
            JOptionPane.showMessageDialog(null, "Data telah di edit");

            edit.dispose();

            new ViewDosen();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getLocalizedMessage());

        }
    }

    public void deleteDosen(int baris) {
        Integer id = (int) view.getTableDosen().getValueAt(baris, 0);
        String nama = view.getTableDosen().getValueAt(baris, 1).toString();

        int input = JOptionPane.showConfirmDialog(null, "Hapus" + nama + "?", "Hapus Dosen", JOptionPane.YES_NO_OPTION);

        if (input == 0) {
            daoDosen.delete(id);

            JOptionPane.showMessageDialog(null, "Berhasil Menghapus Data");
            getAllDosen();
        }
    }

}
