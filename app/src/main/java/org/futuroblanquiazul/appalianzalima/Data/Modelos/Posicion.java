package org.futuroblanquiazul.appalianzalima.Data.Modelos;

public class Posicion {
    int idPosicion;
    String Codigo;
    String Posicion;
    String FechaRegistro;
    String FechaUpdate;
    Estado estado;
    int Sync;

    public Posicion(){

    }

    public int getIdPosicion() {
        return idPosicion;
    }

    public void setIdPosicion(int idPosicion) {
        this.idPosicion = idPosicion;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getPosicion() {
        return Posicion;
    }

    public void setPosicion(String posicion) {
        Posicion = posicion;
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
