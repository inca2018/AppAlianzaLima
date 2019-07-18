package org.futuroblanquiazul.appalianzalima.Data.Modelos;

public class DiagnosticoPersona {
    int idDiagnosticoPersona;
    String Nombres;
    String Apellidos;
    String Alias;
    int Numero;
    int Telefono1;
    int Telefono2;
    String Domicilio;
    String FechaRegistro;
    String FechaMigracion;
    int Sync;

    public DiagnosticoPersona(){

    }

    public int getIdDiagnosticoPersona() {
        return idDiagnosticoPersona;
    }

    public void setIdDiagnosticoPersona(int idDiagnosticoPersona) {
        this.idDiagnosticoPersona = idDiagnosticoPersona;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int numero) {
        Numero = numero;
    }

    public int getTelefono1() {
        return Telefono1;
    }

    public void setTelefono1(int telefono1) {
        Telefono1 = telefono1;
    }

    public int getTelefono2() {
        return Telefono2;
    }

    public void setTelefono2(int telefono2) {
        Telefono2 = telefono2;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String domicilio) {
        Domicilio = domicilio;
    }

    public String getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }

    public String getFechaMigracion() {
        return FechaMigracion;
    }

    public void setFechaMigracion(String fechaMigracion) {
        FechaMigracion = fechaMigracion;
    }

    public int getSync() {
        return Sync;
    }

    public void setSync(int sync) {
        Sync = sync;
    }
}
