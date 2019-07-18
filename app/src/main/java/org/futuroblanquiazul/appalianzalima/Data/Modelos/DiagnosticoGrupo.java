package org.futuroblanquiazul.appalianzalima.Data.Modelos;

public class DiagnosticoGrupo {
    int idDiagnosticoGrupo;
    String Grupo;
    String FechaRegistro;
    String FechaUpdate;
    int Sync;

    public DiagnosticoGrupo(){

    }

    public int getIdDiagnosticoGrupo() {
        return idDiagnosticoGrupo;
    }

    public void setIdDiagnosticoGrupo(int idDiagnosticoGrupo) {
        this.idDiagnosticoGrupo = idDiagnosticoGrupo;
    }

    public String getGrupo() {
        return Grupo;
    }

    public void setGrupo(String grupo) {
        Grupo = grupo;
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

    public int getSync() {
        return Sync;
    }

    public void setSync(int sync) {
        Sync = sync;
    }
}
