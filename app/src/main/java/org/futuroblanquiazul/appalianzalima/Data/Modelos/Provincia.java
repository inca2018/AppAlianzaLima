package org.futuroblanquiazul.appalianzalima.Data.Modelos;

public class Provincia {
    int idProvincia;
    String provincia;
    Departamento departamento;
    String FechaRegistro;
    int Sync;
    public Provincia(){

    }
    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public int getSync() {
        return Sync;
    }

    public void setSync(int sync) {
        Sync = sync;
    }

    public String getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }
}
