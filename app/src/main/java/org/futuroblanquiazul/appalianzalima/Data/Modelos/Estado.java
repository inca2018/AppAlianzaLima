package org.futuroblanquiazul.appalianzalima.Data.Modelos;

public class Estado {
    int idEstado;
    int Grupo;
    String Estado;
    String FechaRegistro;
    int Sync;

    public Estado() {
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getGrupo() {
        return Grupo;
    }

    public void setGrupo(int grupo) {
        Grupo = grupo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
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
