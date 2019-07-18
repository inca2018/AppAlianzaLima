package org.futuroblanquiazul.appalianzalima.Data.Modelos;

public class DiagnosticoResultado {
    int idDiagnosticoResultado;
    int Resultado;
    String FechaRegistro;
    Diagnostico diagnostico;
    DiagnosticoGrupo diagnosticoGrupo;
    DiagnosticoCampo diagnosticoCampo;
    int Sync;

    public DiagnosticoResultado(){

    }

    public int getIdDiagnosticoResultado() {
        return idDiagnosticoResultado;
    }

    public void setIdDiagnosticoResultado(int idDiagnosticoResultado) {
        this.idDiagnosticoResultado = idDiagnosticoResultado;
    }

    public int getResultado() {
        return Resultado;
    }

    public void setResultado(int resultado) {
        Resultado = resultado;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public DiagnosticoGrupo getDiagnosticoGrupo() {
        return diagnosticoGrupo;
    }

    public void setDiagnosticoGrupo(DiagnosticoGrupo diagnosticoGrupo) {
        this.diagnosticoGrupo = diagnosticoGrupo;
    }

    public DiagnosticoCampo getDiagnosticoCampo() {
        return diagnosticoCampo;
    }

    public void setDiagnosticoCampo(DiagnosticoCampo diagnosticoCampo) {
        this.diagnosticoCampo = diagnosticoCampo;
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
