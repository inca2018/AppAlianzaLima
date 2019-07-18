package org.futuroblanquiazul.appalianzalima.Data.Modelos;

public class Area {
    int idArea;
    String Area;
    String FechaRegistro;
    String FechaUpdate;
    Estado Estado;
    int Sync;

    public Area() {
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
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

    public org.futuroblanquiazul.appalianzalima.Data.Modelos.Estado getEstado() {
        return Estado;
    }

    public void setEstado(org.futuroblanquiazul.appalianzalima.Data.Modelos.Estado estado) {
        Estado = estado;
    }

    public int getSync() {
        return Sync;
    }

    public void setSync(int sync) {
        Sync = sync;
    }
}
