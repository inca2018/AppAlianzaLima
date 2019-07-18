package org.futuroblanquiazul.appalianzalima.Data.Modelos;

public class DiagnosticoCampo {
    int idDiagnosticoCampo;
    String Campo;
    String FechaRegistro;
    String FechaUpdate;
    Estado estado;
    DiagnosticoGrupo diagnosticoGrupo;
    int Sync;

    public DiagnosticoCampo(){

    }

    public int getIdDiagnosticoCampo() {
        return idDiagnosticoCampo;
    }

    public void setIdDiagnosticoCampo(int idDiagnosticoCampo) {
        this.idDiagnosticoCampo = idDiagnosticoCampo;
    }

    public String getCampo() {
        return Campo;
    }

    public void setCampo(String campo) {
        Campo = campo;
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

    public DiagnosticoGrupo getDiagnosticoGrupo() {
        return diagnosticoGrupo;
    }

    public void setDiagnosticoGrupo(DiagnosticoGrupo diagnosticoGrupo) {
        this.diagnosticoGrupo = diagnosticoGrupo;
    }

    public int getSync() {
        return Sync;
    }

    public void setSync(int sync) {
        Sync = sync;
    }
}
