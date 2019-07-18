package org.futuroblanquiazul.appalianzalima.Data.Conexion.Local;

public class ConstantesDb {

    /*** VALORES TABLA USUARIO ***/
    public static final String USUARIO_TBL="usuario";
    public static final String USUARIO_CAMPO_ID="idUsuario";
    public static final String USUARIO_CAMPO_USUARIO="usuario";
    public static final String USUARIO_CAMPO_PASSWORD="password";
    public static final String USUARIO_CAMPO_NOMBRES="NombreUsuario";
    public static final String USUARIO_CAMPO_APELLIDOS="ApellidosUsuario";
    public static final String USUARIO_CAMPO_DNI="Dni";
    public static final String USUARIO_CAMPO_CARGO="Cargo";
    public static final String USUARIO_CAMPO_CORREO="Correo";
    public static final String USUARIO_CAMPO_FECHA_REGISTRO="fechaRegistro";
    public static final String USUARIO_CAMPO_FECHA_UPDATE="fechaUpdate";
    public static final String USUARIO_CAMPO_IMAGEN="imagen";
    public static final String USUARIO_CAMPO_PERFIL_FK="`perfil_idPerfil`";
    public static final String USUARIO_CAMPO_AREA_FK="`area_idArea`";
    public static final String USUARIO_CAMPO_ESTADO_FK="`estado_idEstado`";
    public static final String USUARIO_CAMPO_SYNC="sync";
    /*** CREATE TABLA USUARIO ***/
    public static final String CREATE_TBL_USUARIO="CREATE TABLE " + USUARIO_TBL
            + "("+USUARIO_CAMPO_ID+" INTEGER, " +
            USUARIO_CAMPO_USUARIO + " VARCHAR(50), " +
            USUARIO_CAMPO_PASSWORD + " VARCHAR(45),"+
            USUARIO_CAMPO_NOMBRES+ " VARCHAR(50),"+
            USUARIO_CAMPO_APELLIDOS+ " VARCHAR(100),"+
            USUARIO_CAMPO_DNI+ " CHAR(11),"+
            USUARIO_CAMPO_CARGO+ " VARCHAR(150),"+
            USUARIO_CAMPO_CORREO+ " VARCHAR(150),"+
            USUARIO_CAMPO_FECHA_REGISTRO+ " DATETIME,"+
            USUARIO_CAMPO_FECHA_UPDATE+ " DATETIME,"+
            USUARIO_CAMPO_IMAGEN+ " TEXT,"+
            USUARIO_CAMPO_PERFIL_FK+ " INTEGER,"+
            USUARIO_CAMPO_AREA_FK+ " INTEGER,"+
            USUARIO_CAMPO_ESTADO_FK+ " INTEGER,"+
            USUARIO_CAMPO_SYNC+ " INTEGER);";
    /*** DELETE TABLA USUARIO ***/
    public static final String DELETE_TBL_USUARIO="DROP TABLE IF EXISTS "+USUARIO_TBL;


    /*** VALORES TABLA ESTADO ***/
    public static final String ESTADO_TBL="estado";
    public static final String ESTADO_CAMPO_ID="idEstado";
    public static final String ESTADO_CAMPO_GRUPO="grupo";
    public static final String ESTADO_CAMPO_DESCRIPCION="DescripcionEstado";
    public static final String ESTADO_CAMPO_FECHA_REGISTRO="fechaRegistro";
    public static final String ESTADO_CAMPO_SYNC="sync";
    /*** CREATE TABLA ESTADO ***/
    public static final String CREATE_TBL_ESTADO="CREATE TABLE " + ESTADO_TBL
            + "("+ESTADO_CAMPO_ID+" INTEGER, " +
            ESTADO_CAMPO_GRUPO + " CHAR(4), " +
            ESTADO_CAMPO_DESCRIPCION + " VARCHAR(150),"+
            ESTADO_CAMPO_FECHA_REGISTRO + " DATETIME,"+
            ESTADO_CAMPO_SYNC+ " INTEGER);";
    /*** DELETE TABLA ESTADO ***/
    public static final String DELETE_TBL_ESTADO="DROP TABLE IF EXISTS "+ESTADO_TBL;


    /*** VALORES TABLA PERFIL ***/
    public static final String PERFIL_TBL="perfil";
    public static final String PERFIL_CAMPO_ID="idPerfil";
    public static final String PERFIL_CAMPO_DESCRIPCION="DescripcionPerfil";
    public static final String PERFIL_CAMPO_FECHA_REGISTRO="fechaRegistro";
    public static final String PERFIL_CAMPO_FECHA_UPDATE="fechaUpdate";
    public static final String PERFIL_CAMPO_ESTADO_FK="estado_idEstado";
    public static final String PERFIL_CAMPO_SYNC="sync";
    /*** CREATE TABLA PERFIL ***/
    public static final String CREATE_TBL_PERFIL="CREATE TABLE " + PERFIL_TBL
            + "("+PERFIL_CAMPO_ID+" INTEGER, " +
            PERFIL_CAMPO_DESCRIPCION + " VARCHAR(150), " +
            PERFIL_CAMPO_FECHA_REGISTRO + " DATETIME,"+
            PERFIL_CAMPO_FECHA_UPDATE + " DATETIME,"+
            PERFIL_CAMPO_ESTADO_FK + " INTEGER,"+
            PERFIL_CAMPO_SYNC+ " INTEGER);";
    /*** DELETE TABLA PERFIL ***/
    public static final String DELETE_TBL_PERFIL="DROP TABLE IF EXISTS "+PERFIL_TBL;


    /*** VALORES TABLA AREA ***/
    public static final String AREA_TBL="area";
    public static final String AREA_CAMPO_ID="idArea";
    public static final String AREA_CAMPO_DESCRIPCION="DescripcionArea";
    public static final String AREA_CAMPO_FECHA_REGISTRO="fechaRegistro";
    public static final String AREA_CAMPO_FECHA_UPDATE="fechaUpdate";
    public static final String AREA_CAMPO_ESTADO_FK="estado_idEstado";
    public static final String AREA_CAMPO_SYNC="sync";
    /*** CREATE TABLA AREA ***/
    public static final String CREATE_TBL_AREA="CREATE TABLE " + AREA_TBL
            + "("+AREA_CAMPO_ID+" INTEGER, " +
            AREA_CAMPO_DESCRIPCION + " VARCHAR(150), " +
            AREA_CAMPO_FECHA_REGISTRO + " DATETIME,"+
            AREA_CAMPO_FECHA_UPDATE + " DATETIME,"+
            AREA_CAMPO_ESTADO_FK + " INTEGER,"+
            AREA_CAMPO_SYNC+ " INTEGER);";
    /*** DELETE TABLA AREA ***/
    public static final String DELETE_TBL_AREA="DROP TABLE IF EXISTS "+AREA_TBL;


    /*** VALORES TABLA DEPARTAMENTO ***/
    public static final String DEPARTAMENTO_TBL="departamento";
    public static final String DEPARTAMENTO_CAMPO_ID="idDepartamento";
    public static final String DEPARTAMENTO_CAMPO_DEPARTAMENTO="departamento";
    public static final String DEPARTAMENTO_CAMPO_FECHA_REGISTRO="fechaRegistro";
    public static final String DEPARTAMENTO_CAMPO_SYNC="sync";
    /*** CREATE TABLA DEPARTAMENTO ***/
    public static final String CREATE_TBL_DEPARTAMENTO="CREATE TABLE " + DEPARTAMENTO_TBL
            + "("+DEPARTAMENTO_CAMPO_ID+" INTEGER, " +
            DEPARTAMENTO_CAMPO_DEPARTAMENTO + " VARCHAR(150), " +
            DEPARTAMENTO_CAMPO_FECHA_REGISTRO + " DATETIME, " +
            DEPARTAMENTO_CAMPO_SYNC+ " INTEGER);";
    /*** DELETE TABLA DEPARTAMENTO ***/
    public static final String DELETE_TBL_DEPARTAMENTO="DROP TABLE IF EXISTS "+DEPARTAMENTO_TBL;



    /*** VALORES TABLA PROVINCIA ***/
    public static final String PROVINCIA_TBL="provincia";
    public static final String PROVINCIA_CAMPO_ID="idProvincia";
    public static final String PROVINCIA_CAMPO_PROVINCIA="provincia";
    public static final String PROVINCIA_CAMPO_DEPARTAMENTO_FK="departamento_idDepartamento";
    public static final String PROVINCIA_CAMPO_FECHA_REGISTRO="fechaRegistro";
    public static final String PROVINCIA_CAMPO_SYNC="sync";
    /*** CREATE TABLA PROVINCIA ***/
    public static final String CREATE_TBL_PROVINCIA="CREATE TABLE " + PROVINCIA_TBL
            + "("+PROVINCIA_CAMPO_ID+" INTEGER, " +
            PROVINCIA_CAMPO_PROVINCIA + " VARCHAR(150), " +
            PROVINCIA_CAMPO_DEPARTAMENTO_FK + " INTEGER, " +
            PROVINCIA_CAMPO_FECHA_REGISTRO + " DATETIME, " +
            PROVINCIA_CAMPO_SYNC+ " INTEGER);";
    /*** DELETE TABLA PROVINCIA ***/
    public static final String DELETE_TBL_PROVINCIA="DROP TABLE IF EXISTS "+PROVINCIA_TBL;


    /*** VALORES TABLA DISTRITO ***/
    public static final String DISTRITO_TBL="distrito";
    public static final String DISTRITO_CAMPO_ID="idDistrito";
    public static final String DISTRITO_CAMPO_DISTRITO="distrito";
    public static final String DISTRITO_CAMPO_PROVINCIA_FK="provincia_idProvincia";
    public static final String DISTRITO_CAMPO_FECHA_REGISTRO="fechaRegistro";
    public static final String DISTRITO_CAMPO_SYNC="sync";
    /*** CREATE TABLA DISTRITO ***/
    public static final String CREATE_TBL_DISTRITO="CREATE TABLE " + DISTRITO_TBL
            + "("+DISTRITO_CAMPO_ID+" INTEGER, " +
            DISTRITO_CAMPO_DISTRITO + " VARCHAR(150), " +
            DISTRITO_CAMPO_PROVINCIA_FK + " INTEGER, " +
            DISTRITO_CAMPO_FECHA_REGISTRO + " DATETIME, " +
            DISTRITO_CAMPO_SYNC+ " INTEGER);";
    /*** DELETE TABLA DISTRITO ***/
    public static final String DELETE_TBL_DISTRITO="DROP TABLE IF EXISTS "+DISTRITO_TBL;



    /*** VALORES TABLA COPAPLATA ***/
    public static final String COPAPLATA_TBL="copaplata";
    public static final String COPAPLATA_CAMPO_ID="idCopaPlata";
    public static final String COPAPLATA_CAMPO_DESCRIPCION="DescripcionCopaPlata";
    public static final String COPAPLATA_CAMPO_FECHA_REGISTRO="fechaRegistro";
    public static final String COPAPLATA_CAMPO_FECHA_UPDATE="fechaUpdate";
    public static final String COPAPLATA_CAMPO_ESTADO_FK="estado_idEstado";
    public static final String COPAPLATA_CAMPO_SYNC="sync";
    /*** CREATE TABLA COPAPLATA ***/
    public static final String CREATE_TBL_COPAPLATA="CREATE TABLE " + COPAPLATA_TBL
            + "("+COPAPLATA_CAMPO_ID+" INTEGER, " +
            COPAPLATA_CAMPO_DESCRIPCION + " VARCHAR(150), " +
            COPAPLATA_CAMPO_FECHA_REGISTRO + " DATETIME,"+
            COPAPLATA_CAMPO_FECHA_UPDATE + " DATETIME,"+
            COPAPLATA_CAMPO_ESTADO_FK + " INTEGER,"+
            COPAPLATA_CAMPO_SYNC+ " INTEGER);";
    /*** DELETE TABLA COPAPLATA ***/
    public static final String DELETE_TBL_COPAPLATA="DROP TABLE IF EXISTS "+COPAPLATA_TBL;



    /*** VALORES TABLA COPAORO ***/
    public static final String COPAORO_TBL="copaoro";
    public static final String COPAORO_CAMPO_ID="idCopaOro";
    public static final String COPAORO_CAMPO_DESCRIPCION="DescripcionCopaOro";
    public static final String COPAORO_CAMPO_FECHA_REGISTRO="fechaRegistro";
    public static final String COPAORO_CAMPO_FECHA_UPDATE="fechaUpdate";
    public static final String COPAORO_CAMPO_ESTADO_FK="estado_idEstado";
    public static final String COPAORO_CAMPO_SYNC="sync";
    /*** CREATE TABLA COPAORO ***/
    public static final String CREATE_TBL_COPAORO="CREATE TABLE " + COPAORO_TBL
            + "("+COPAORO_CAMPO_ID+" INTEGER, " +
            COPAORO_CAMPO_DESCRIPCION + " VARCHAR(150), " +
            COPAORO_CAMPO_FECHA_REGISTRO + " DATETIME,"+
            COPAORO_CAMPO_FECHA_UPDATE + " DATETIME,"+
            COPAORO_CAMPO_ESTADO_FK + " INTEGER,"+
            COPAORO_CAMPO_SYNC+ " INTEGER);";
    /*** DELETE TABLA COPAORO ***/
    public static final String DELETE_TBL_COPAORO="DROP TABLE IF EXISTS "+COPAORO_TBL;



    /*** VALORES TABLA DIAGNOSTICO ***/
    public static final String DIAGNOSTICO_TBL="diagnostico";
    public static final String DIAGNOSTICO_CAMPO_ID="idDiagnostico";
    public static final String DIAGNOSTICO_CAMPO_CATEGORIA="Categoria";
    public static final String DIAGNOSTICO_CAMPO_INTERCAR="Intercar";
    public static final String DIAGNOSTICO_CAMPO_POSICIONSUGERIDA1="PosicionSugerida1";
    public static final String DIAGNOSTICO_CAMPO_POSICIONSUGERIDA2="PosicionSugerida2";
    public static final String DIAGNOSTICO_CAMPO_PIERNA_DERECHA="PiernaDerecha";
    public static final String DIAGNOSTICO_CAMPO_PIERNA_IZQUIERDA="PiernaIzquierda";
    public static final String DIAGNOSTICO_CAMPO_FECHA_REGISTRO="fechaRegistro";
    public static final String DIAGNOSTICO_CAMPO_PERSONA_FK="persona_idPersona";
    public static final String DIAGNOSTICO_CAMPO_COPAORO_FK="copaoro_idCopaOro";
    public static final String DIAGNOSTICO_CAMPO_COPAPLATA_FK="copaplata_idCopaPlata";
    public static final String DIAGNOSTICO_CAMPO_DIAGNOSTICO_PERSONA_FK="diagnosticopersona_idDiagnosticoPersona";
    public static final String DIAGNOSTICO_CAMPO_UBIGEO_FK="ubigeo_idUbigeo";
    public static final String DIAGNOSTICO_CAMPO_MODULO_FK="modulos_idModulos";
    public static final String DIAGNOSTICO_CAMPO_SYNC="sync";
    /*** CREATE TABLA DIAGNOSTICO ***/
    public static final String CREATE_TBL_DIAGNOSTICO="CREATE TABLE " + DIAGNOSTICO_TBL
            + "("+DIAGNOSTICO_CAMPO_ID+" INTEGER, " +
            DIAGNOSTICO_CAMPO_CATEGORIA + " INTEGER, " +
            DIAGNOSTICO_CAMPO_INTERCAR + " INTEGER,"+
            DIAGNOSTICO_CAMPO_POSICIONSUGERIDA1 + " INTEGER,"+
            DIAGNOSTICO_CAMPO_POSICIONSUGERIDA2 + " INTEGER,"+
            DIAGNOSTICO_CAMPO_PIERNA_DERECHA + " INTEGER,"+
            DIAGNOSTICO_CAMPO_PIERNA_IZQUIERDA + " INTEGER,"+
            DIAGNOSTICO_CAMPO_FECHA_REGISTRO + " DATETIME,"+
            DIAGNOSTICO_CAMPO_PERSONA_FK + " INTEGER,"+
            DIAGNOSTICO_CAMPO_COPAORO_FK + " INTEGER,"+
            DIAGNOSTICO_CAMPO_COPAPLATA_FK + " INTEGER,"+
            DIAGNOSTICO_CAMPO_DIAGNOSTICO_PERSONA_FK + " INTEGER,"+
            DIAGNOSTICO_CAMPO_UBIGEO_FK + " INTEGER,"+
            DIAGNOSTICO_CAMPO_MODULO_FK + " INTEGER,"+
            DIAGNOSTICO_CAMPO_SYNC+ " INTEGER);";
    /*** DELETE TABLA DIAGNOSTICO ***/
    public static final String DELETE_TBL_DIAGNOSTICO="DROP TABLE IF EXISTS "+DIAGNOSTICO_TBL;



    /*** VALORES TABLA DIAGNOSTICOCAMPO ***/
    public static final String DIAGNOSTICOCAMPO_TBL="diagnosticocampo";
    public static final String DIAGNOSTICOCAMPO_CAMPO_ID="idDiagnosticoCampo";
    public static final String DIAGNOSTICOCAMPO_CAMPO_DESCRIPCION="DescripcionCampo";
    public static final String DIAGNOSTICOCAMPO_CAMPO_FECHA_REGISTRO="fechaRegistro";
    public static final String DIAGNOSTICOCAMPO_CAMPO_FECHA_UPDATE="fechaUpdate";
    public static final String DIAGNOSTICOCAMPO_CAMPO_ESTADO_FK="estado_idEstado";
    public static final String DIAGNOSTICOCAMPO_CAMPO_DIAGNOSTICO_GRUPO_FK="diagnosticogrupo_iddiagnosticogrupo";
    public static final String DIAGNOSTICOCAMPO_CAMPO_SYNC="sync";
    /*** CREATE TABLA DIAGNOSTICOCAMPO ***/
    public static final String CREATE_TBL_DIAGNOSTICOCAMPO="CREATE TABLE " + DIAGNOSTICOCAMPO_TBL
            + "("+DIAGNOSTICOCAMPO_CAMPO_ID+" INTEGER, " +
            DIAGNOSTICOCAMPO_CAMPO_DESCRIPCION + " VARCHAR(150), " +
            DIAGNOSTICOCAMPO_CAMPO_FECHA_REGISTRO + " DATETIME,"+
            DIAGNOSTICOCAMPO_CAMPO_FECHA_UPDATE + " DATETIME,"+
            DIAGNOSTICOCAMPO_CAMPO_ESTADO_FK + " INTEGER,"+
            DIAGNOSTICOCAMPO_CAMPO_DIAGNOSTICO_GRUPO_FK + " INTEGER,"+
            DIAGNOSTICOCAMPO_CAMPO_SYNC+ " INTEGER);";
    /*** DELETE TABLA DIAGNOSTICOCAMPO ***/
    public static final String DELETE_TBL_DIAGNOSTICOCAMPO="DROP TABLE IF EXISTS "+DIAGNOSTICOCAMPO_TBL;


    /*** VALORES TABLA DIAGNOSTRICOGRUPO ***/
    public static final String DIAGNOSTICOGRUPO_TBL="diagnosticogrupo";
    public static final String DIAGNOSTICOGRUPO_CAMPO_ID="idDiagnosticoGrupo";
    public static final String DIAGNOSTICOGRUPO_CAMPO_DESCRIPCION="DescripcionGrupo";
    public static final String DIAGNOSTICOGRUPO_CAMPO_FECHA_REGISTRO="fechaRegistro";
    public static final String DIAGNOSTICOGRUPO_CAMPO_FECHA_UPDATE="fechaUpdate";
    public static final String DIAGNOSTICOGRUPO_CAMPO_SYNC="sync";
    /*** CREATE TABLA DIAGNOSTRICOGRUPO ***/
    public static final String CREATE_TBL_DIAGNOSTICOGRUPO="CREATE TABLE " + DIAGNOSTICOGRUPO_TBL
            + "("+DIAGNOSTICOGRUPO_CAMPO_ID+" INTEGER, " +
            DIAGNOSTICOGRUPO_CAMPO_DESCRIPCION + " VARCHAR(150), " +
            DIAGNOSTICOGRUPO_CAMPO_FECHA_REGISTRO + " DATETIME, " +
            DIAGNOSTICOGRUPO_CAMPO_FECHA_UPDATE + " DATETIME, " +
            DIAGNOSTICOGRUPO_CAMPO_SYNC+ " INTEGER);";
    /*** DELETE TABLA DIAGNOSTRICOGRUPO ***/
    public static final String DELETE_TBL_DIAGNOSTICOGRUPO="DROP TABLE IF EXISTS "+DIAGNOSTICOGRUPO_TBL;




    /*** VALORES TABLA DIAGNOSTICOPERSONA ***/
    public static final String DIAGNOSTICOPERSONA_TBL="diagnosticopersona";
    public static final String DIAGNOSTICOPERSONA_CAMPO_ID="idDiagnosticoPersona";
    public static final String DIAGNOSTICOPERSONA_CAMPO_NOMBRES="Nombres";
    public static final String DIAGNOSTICOPERSONA_CAMPO_APELLIDOS="Apellidos";
    public static final String DIAGNOSTICOPERSONA_CAMPO_ALIAS="Alias";
    public static final String DIAGNOSTICOPERSONA_CAMPO_NUMERO="Numero";
    public static final String DIAGNOSTICOPERSONA_CAMPO_TELEFONO1="telefono1";
    public static final String DIAGNOSTICOPERSONA_CAMPO_TELEFONO2="telefono2";
    public static final String DIAGNOSTICOPERSONA_CAMPO_DOMICILIO="Domicilio";
    public static final String DIAGNOSTICOPERSONA_CAMPO_FECHA_REGISTRO="fechaRegistro";
    public static final String DIAGNOSTICOPERSONA_CAMPO_FECHA_MIGRACION="fechaMigracion";
    public static final String DIAGNOSTICOPERSONA_CAMPO_SYNC="sync";
    /*** CREATE TABLA DIAGNOSTICOPERSONA ***/
    public static final String CREATE_TBL_DIAGNOSTICOPERSONA="CREATE TABLE " + DIAGNOSTICOPERSONA_TBL
            + "("+DIAGNOSTICOPERSONA_CAMPO_ID+" INTEGER, " +
            DIAGNOSTICOPERSONA_CAMPO_NOMBRES + " VARCHAR(100), " +
            DIAGNOSTICOPERSONA_CAMPO_APELLIDOS + " VARCHAR(100), " +
            DIAGNOSTICOPERSONA_CAMPO_ALIAS + " VARCHAR(100), " +
            DIAGNOSTICOPERSONA_CAMPO_NUMERO + " INTEGER, " +
            DIAGNOSTICOPERSONA_CAMPO_TELEFONO1 + " CHAR(11), " +
            DIAGNOSTICOPERSONA_CAMPO_TELEFONO2 + " CHAR(11), " +
            DIAGNOSTICOPERSONA_CAMPO_DOMICILIO + " TEXT, " +
            DIAGNOSTICOPERSONA_CAMPO_FECHA_REGISTRO + " DATETIME, " +
            DIAGNOSTICOPERSONA_CAMPO_FECHA_MIGRACION + " DATETIME, " +
            DIAGNOSTICOPERSONA_CAMPO_SYNC+ " INTEGER);";
    /*** DELETE TABLA DIAGNOSTICOPERSONA ***/
    public static final String DELETE_TBL_DIAGNOSTICOPERSONA="DROP TABLE IF EXISTS "+DIAGNOSTICOPERSONA_TBL;



    /*** VALORES TABLA DIAGNOSTICORESULTADO***/
    public static final String DIAGNOSTICORESULTADO_TBL="diagnosticoresultado";
    public static final String DIAGNOSTICORESULTADO_CAMPO_ID="idDiagnosticoResultado";
    public static final String DIAGNOSTICORESULTADO_CAMPO_RESULTADO="resultado";
    public static final String DIAGNOSTICORESULTADO_CAMPO_DIAGNOSTICO_FK="diagnostico_idDiagnostico";
    public static final String DIAGNOSTICORESULTADO_CAMPO_DIAGNOSTICOGRUPO_FK="diagnosticogrupo_idDiagnosticoGrupo";
    public static final String DIAGNOSTICORESULTADO_CAMPO_DIAGNOSTICOCAMPO_FK="diagnosticocampo_idDiagnosticoCampo";
    public static final String DIAGNOSTICORESULTADO_CAMPO_FECHA_REGISTRO="fechaRegistro";
    public static final String DIAGNOSTICORESULTADO_CAMPO_SYNC="sync";
    /*** CREATE TABLA AREA ***/
    public static final String CREATE_TBL_DIAGNOSTICORESULTADO="CREATE TABLE " + DIAGNOSTICORESULTADO_TBL
            + "("+DIAGNOSTICORESULTADO_CAMPO_ID+" INTEGER, " +
            DIAGNOSTICORESULTADO_CAMPO_RESULTADO + " INTEGER, " +
            DIAGNOSTICORESULTADO_CAMPO_DIAGNOSTICO_FK + " INTEGER, " +
            DIAGNOSTICORESULTADO_CAMPO_DIAGNOSTICOGRUPO_FK + " INTEGER, " +
            DIAGNOSTICORESULTADO_CAMPO_DIAGNOSTICOCAMPO_FK + " INTEGER, " +
            DIAGNOSTICORESULTADO_CAMPO_FECHA_REGISTRO + " DATETIME, " +
            DIAGNOSTICORESULTADO_CAMPO_SYNC+ " INTEGER);";
    /*** DELETE TABLA ESTADO ***/
    public static final String DELETE_TBL_DIAGNOSTICORESULTADO="DROP TABLE IF EXISTS "+DIAGNOSTICORESULTADO_TBL;




    /*** VALORES TABLA MODULO ***/
    public static final String MODULO_TBL="modulos";
    public static final String MODULO_CAMPO_ID="idModulos";
    public static final String MODULO_CAMPO_MODULO="DescripcionModulo";
    public static final String MODULO_CAMPO_FECHA_REGISTRO="fechaRegistro";
    public static final String MODULO_CAMPO_SYNC="sync";
    /*** CREATE TABLA MODULO ***/
    public static final String CREATE_TBL_MODULO="CREATE TABLE " + MODULO_TBL
            + "("+MODULO_CAMPO_ID+" INTEGER, " +
            MODULO_CAMPO_MODULO + " VARCHAR(150), " +
            MODULO_CAMPO_FECHA_REGISTRO + " DATETIME, " +
            MODULO_CAMPO_SYNC+ " INTEGER);";
    /*** DELETE TABLA MODULO ***/
    public static final String DELETE_TBL_MODULO="DROP TABLE IF EXISTS "+MODULO_TBL;



    /*** VALORES TABLA PERSONA ***/
    public static final String PERSONA_TBL="persona";
    public static final String PERSONA_CAMPO_ID="idPersona";
    public static final String PERSONA_CAMPO_NOMBRES="NombresPersona";
    public static final String PERSONA_CAMPO_APELLIDOS="ApellidosPersona";
    public static final String PERSONA_CAMPO_APODO="Apodo";
    public static final String PERSONA_CAMPO_FECHA_NACIMIENTO="fechaNacimiento";
    public static final String PERSONA_CAMPO_NACIONALIDAD="Nacionalidad";
    public static final String PERSONA_CAMPO_LUGAR_RESIDENCIA="LugarResidencia";
    public static final String PERSONA_CAMPO_CLUB_ACTUAL="ClubActual";
    public static final String PERSONA_CAMPO_LIGA_ACTUAL="LigaActual";
    public static final String PERSONA_CAMPO_CELULAR="Celular";
    public static final String PERSONA_CAMPO_TELEFONO_FIJO="TelefonoFijo";
    public static final String PERSONA_CAMPO_BAUTIZO="Bautizo";
    public static final String PERSONA_CAMPO_COMUNION="Comunion";
    public static final String PERSONA_CAMPO_CONFIRMACION="Confirmacion";
    public static final String PERSONA_CAMPO_DNI="Dni";
    public static final String PERSONA_CAMPO_CORREO="Correo";
    public static final String PERSONA_CAMPO_APODERADO="ApoderadoNombre";
    public static final String PERSONA_CAMPO_APODERADO_CELULAR="CelularApoderado";
    public static final String PERSONA_CAMPO_CATEGORIA_ACTUAL="CategoriaActual";
    public static final String PERSONA_CAMPO_FECHA_REGISTRO="fechaRegistro";
    public static final String PERSONA_CAMPO_FECHA_UPDATE="fechaUpdate";
    public static final String PERSONA_CAMPO_ESTADO_FK="estado_idEstado";
    public static final String PERSONA_CAMPO_SYNC="sync";

    /*** CREATE TABLA PERSONA ***/
    public static final String CREATE_TBL_PERSONA="CREATE TABLE " + PERSONA_TBL
            + "("+PERSONA_CAMPO_ID+" INTEGER, " +
            PERSONA_CAMPO_NOMBRES + " VARCHAR(100), " +
            PERSONA_CAMPO_APELLIDOS + " VARCHAR(100), " +
            PERSONA_CAMPO_APODO + " VARCHAR(100), " +
            PERSONA_CAMPO_FECHA_NACIMIENTO + " VARCHAR(100), " +
            PERSONA_CAMPO_NACIONALIDAD + " VARCHAR(100), " +
            PERSONA_CAMPO_LUGAR_RESIDENCIA + " VARCHAR(100), " +
            PERSONA_CAMPO_CLUB_ACTUAL + " VARCHAR(100), " +
            PERSONA_CAMPO_LIGA_ACTUAL + " VARCHAR(100), " +
            PERSONA_CAMPO_CELULAR + " VARCHAR(100), " +
            PERSONA_CAMPO_TELEFONO_FIJO + " VARCHAR(100), " +
            PERSONA_CAMPO_BAUTIZO + " VARCHAR(100), " +
            PERSONA_CAMPO_COMUNION + " VARCHAR(100), " +
            PERSONA_CAMPO_CONFIRMACION + " VARCHAR(100), " +
            PERSONA_CAMPO_DNI + " VARCHAR(100), " +
            PERSONA_CAMPO_CORREO + " VARCHAR(100), " +
            PERSONA_CAMPO_APODERADO + " VARCHAR(100), " +
            PERSONA_CAMPO_APODERADO_CELULAR + " VARCHAR(100), " +
            PERSONA_CAMPO_CATEGORIA_ACTUAL + " VARCHAR(100), " +
            PERSONA_CAMPO_FECHA_REGISTRO + " VARCHAR(100), " +
            PERSONA_CAMPO_FECHA_UPDATE + " VARCHAR(100), " +
            PERSONA_CAMPO_ESTADO_FK + " VARCHAR(100), " +
            PERSONA_CAMPO_SYNC+ " INTEGER);";

    /*** DELETE TABLA PERSONA ***/
    public static final String DELETE_TBL_PERSONA="DROP TABLE IF EXISTS "+PERSONA_TBL;



    /*** VALORES TABLA POSICION ***/
    public static final String POSICION_TBL="posicion";
    public static final String POSICION_CAMPO_ID="idPosicion";
    public static final String POSICION_CAMPO_CODIGO="Codigo";
    public static final String POSICION_CAMPO_POSICION="DescripcionPosicion";
    public static final String POSICION_CAMPO_FECHA_REGISTRO="fechaRegistro";
    public static final String POSICION_CAMPO_FECHA_UPDATE="fechaUpdate";
    public static final String POSICION_CAMPO_ESTADO_FK="estado_idEstado";
    public static final String POSICION_CAMPO_SYNC="sync";
    /*** CREATE TABLA POSICION ***/
    public static final String CREATE_TBL_POSICION="CREATE TABLE " + POSICION_TBL
            + "("+POSICION_CAMPO_ID+" INTEGER, " +
            POSICION_CAMPO_CODIGO + " CHAR(4), " +
            POSICION_CAMPO_POSICION + " VARCHAR(150), " +
            POSICION_CAMPO_FECHA_REGISTRO + " DATETIME, " +
            POSICION_CAMPO_FECHA_UPDATE + " DATETIME, " +
            POSICION_CAMPO_ESTADO_FK + " INTEGER, " +
            POSICION_CAMPO_SYNC+ " INTEGER);";
    /*** DELETE TABLA POSICION ***/
    public static final String DELETE_TBL_POSICION="DROP TABLE IF EXISTS "+POSICION_TBL;

    /*** VALORES TABLA UBIGEO ***/
    public static final String UBIGEO_TBL="ubigeo";
    public static final String UBIGEO_CAMPO_ID="idUbigeo";
    public static final String UBIGEO_CAMPO_UBIGEO="DescripcionUbigeo";
    public static final String UBIGEO_CAMPO_FECHA_REGISTRO="fechaRegistro";
    public static final String UBIGEO_CAMPO_USUARIO_FK="usuario_idUsuario";
    public static final String UBIGEO_CAMPO_DEPARTAMENTO_FK="departamento_idDepartamento";
    public static final String UBIGEO_CAMPO_PROVINCIA_FK="provincia_idProvincia";
    public static final String UBIGEO_CAMPO_DISTRITO_FK="distrito_idDistrito";
    public static final String UBIGEO_CAMPO_MODULOS_FK="modulos_idmodulos";
    public static final String UBIGEO_CAMPO_SYNC="sync";
    /*** CREATE TABLA POSICION ***/
    public static final String CREATE_TBL_UBIGEO="CREATE TABLE " + UBIGEO_TBL
            + "("+UBIGEO_CAMPO_ID+" INTEGER, " +
            UBIGEO_CAMPO_UBIGEO + " TEXT, " +
            UBIGEO_CAMPO_FECHA_REGISTRO + " DATETIME, " +
            UBIGEO_CAMPO_USUARIO_FK + " INTEGER, " +
            UBIGEO_CAMPO_DEPARTAMENTO_FK + " INTEGER, " +
            UBIGEO_CAMPO_PROVINCIA_FK + " INTEGER, " +
            UBIGEO_CAMPO_DISTRITO_FK + " INTEGER, " +
            UBIGEO_CAMPO_MODULOS_FK + " INTEGER, " +
            UBIGEO_CAMPO_SYNC+ " INTEGER);";
    /*** DELETE TABLA POSICION ***/
    public static final String DELETE_TBL_UBIGEO="DROP TABLE IF EXISTS "+UBIGEO_TBL;


}
