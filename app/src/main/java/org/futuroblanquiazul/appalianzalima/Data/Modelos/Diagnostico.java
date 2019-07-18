package org.futuroblanquiazul.appalianzalima.Data.Modelos;

public class Diagnostico {
    int idDiagnostico;
    int Categoria;
    int Intercar; // DEpartamento
    int PosicionSugerida1;
    int PosicionSugerida2;
    int PiernaDerecha;
    int PiernaIzquierda;
    String FechaRegistro;
    Persona persona;
    CopaOro copaOro;
    CopaPlata copaPlata;
    DiagnosticoPersona diagnosticoPersona;
    Ubigeo ubigeo;
    Modulo modulo;
    int Sync;

    public Diagnostico(){

    }

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public int getCategoria() {
        return Categoria;
    }

    public void setCategoria(int categoria) {
        Categoria = categoria;
    }

    public int getIntercar() {
        return Intercar;
    }

    public void setIntercar(int intercar) {
        Intercar = intercar;
    }

    public int getPosicionSugerida1() {
        return PosicionSugerida1;
    }

    public void setPosicionSugerida1(int posicionSugerida1) {
        PosicionSugerida1 = posicionSugerida1;
    }

    public int getPosicionSugerida2() {
        return PosicionSugerida2;
    }

    public void setPosicionSugerida2(int posicionSugerida2) {
        PosicionSugerida2 = posicionSugerida2;
    }

    public int getPiernaDerecha() {
        return PiernaDerecha;
    }

    public void setPiernaDerecha(int piernaDerecha) {
        PiernaDerecha = piernaDerecha;
    }

    public int getPiernaIzquierda() {
        return PiernaIzquierda;
    }

    public void setPiernaIzquierda(int piernaIzquierda) {
        PiernaIzquierda = piernaIzquierda;
    }

    public String getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public CopaOro getCopaOro() {
        return copaOro;
    }

    public void setCopaOro(CopaOro copaOro) {
        this.copaOro = copaOro;
    }

    public CopaPlata getCopaPlata() {
        return copaPlata;
    }

    public void setCopaPlata(CopaPlata copaPlata) {
        this.copaPlata = copaPlata;
    }

    public DiagnosticoPersona getDiagnosticoPersona() {
        return diagnosticoPersona;
    }

    public void setDiagnosticoPersona(DiagnosticoPersona diagnosticoPersona) {
        this.diagnosticoPersona = diagnosticoPersona;
    }

    public Ubigeo getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(Ubigeo ubigeo) {
        this.ubigeo = ubigeo;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public int getSync() {
        return Sync;
    }

    public void setSync(int sync) {
        Sync = sync;
    }
}
