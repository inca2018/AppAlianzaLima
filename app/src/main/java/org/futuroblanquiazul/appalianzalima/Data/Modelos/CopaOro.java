package org.futuroblanquiazul.appalianzalima.Data.Modelos;

public class CopaOro {
    int idCopaOro;
    String DescripcionCopaOro;
    String FechaRegistro;
    String FechaUpdate;
    Estado estado;
    int Sync;
    public CopaOro(){

    }

    public int getIdCopaOro() {
        return idCopaOro;
    }

    public void setIdCopaOro(int idCopaOro) {
        this.idCopaOro = idCopaOro;
    }

    public String getDescripcionCopaOro() {
        return DescripcionCopaOro;
    }

    public void setDescripcionCopaOro(String descripcionCopaOro) {
        DescripcionCopaOro = descripcionCopaOro;
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
