package Model.Mahasiswa;

//PILAR INHERITANCE
import java.util.List;

public interface InterfaceDAO {
    public void insert(ModelMahasiswa mahasiswa);
    
    public void update(ModelMahasiswa mahasiswa);
    
    public void delete(int id);
    
    public List<ModelMahasiswa>getAll();
}
