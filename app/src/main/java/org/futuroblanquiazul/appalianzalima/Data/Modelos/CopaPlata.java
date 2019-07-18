package org.futuroblanquiazul.appalianzalima.Data.Modelos;

public class CopaPlata {
    int idCopaPlata;
    String DescripcionCopaPlata;
    String FechaRegistro;
    String FechaUpdate;
    Estado estado;
    int Sync;
    public CopaPlata(){

    }

    public int getIdCopaPlata() {
        return idCopaPlata;
    }

    public void setIdCopaPlata(int idCopaPlata) {
        this.idCopaPlata = idCopaPlata;
    }

    public String getDescripcionCopaPlata() {
        return DescripcionCopaPlata;
    }

    public void setDescripcionCopaPlata(String descripcionCopaPlata) {
        DescripcionCopaPlata = descripcionCopaPlata;
    }

    public String getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }

    public String getFechaUpdate() {
        return FechaUpdate;
    }

    public void setFechaUpdate(String fechaUpdate) {
        FechaUpdate = fechaUpdate;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getSync() {
        return Sync;
    }

    public void setSync(int sync) {
        Sync = sync;
    }
}
