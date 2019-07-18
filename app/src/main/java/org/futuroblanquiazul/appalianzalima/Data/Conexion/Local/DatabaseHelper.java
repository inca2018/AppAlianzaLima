package org.futuroblanquiazul.appalianzalima.Data.Conexion.Local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.futuroblanquiazul.appalianzalima.Data.Modelos.Area;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.CopaOro;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.CopaPlata;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Departamento;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Diagnostico;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.DiagnosticoCampo;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.DiagnosticoGrupo;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.DiagnosticoPersona;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.DiagnosticoResultado;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Distrito;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Estado;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Modulo;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Perfil;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Persona;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Posicion;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Provincia;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Ubigeo;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Usuario;
import org.futuroblanquiazul.appalianzalima.Utils.Constantes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Constants for Database name, table name, and column names
    public static final String DB_NAME = "futuroblanquiazul";
    public static final String TABLE_NAME = "names";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_DATE = "fechaRegistro";
    //database version
    private static final int DB_VERSION = 1;
    //Constructor
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    //creating the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME
                + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME +
                " VARCHAR, " + COLUMN_STATUS +
                " TINYINT,"+COLUMN_DATE+
                " DATETIME);";
        db.execSQL(sql);

        db.execSQL(ConstantesDb.CREATE_TBL_AREA);
        db.execSQL(ConstantesDb.CREATE_TBL_COPAORO);
        db.execSQL(ConstantesDb.CREATE_TBL_COPAPLATA);
        db.execSQL(ConstantesDb.CREATE_TBL_DEPARTAMENTO);
        db.execSQL(ConstantesDb.CREATE_TBL_DIAGNOSTICO);
        db.execSQL(ConstantesDb.CREATE_TBL_DIAGNOSTICOCAMPO);
        db.execSQL(ConstantesDb.CREATE_TBL_DIAGNOSTICOGRUPO);
        db.execSQL(ConstantesDb.CREATE_TBL_DIAGNOSTICOPERSONA);
        db.execSQL(ConstantesDb.CREATE_TBL_DIAGNOSTICORESULTADO);
        db.execSQL(ConstantesDb.CREATE_TBL_DISTRITO);
        db.execSQL(ConstantesDb.CREATE_TBL_ESTADO);
        db.execSQL(ConstantesDb.CREATE_TBL_MODULO);
        db.execSQL(ConstantesDb.CREATE_TBL_PERFIL);
        db.execSQL(ConstantesDb.CREATE_TBL_PERSONA);
        db.execSQL(ConstantesDb.CREATE_TBL_POSICION);
        db.execSQL(ConstantesDb.CREATE_TBL_PROVINCIA);
        db.execSQL(ConstantesDb.CREATE_TBL_UBIGEO);
        db.execSQL(ConstantesDb.CREATE_TBL_USUARIO);

    }
    //upgrading the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(sql);

        db.execSQL(ConstantesDb.DELETE_TBL_AREA);
        db.execSQL(ConstantesDb.DELETE_TBL_COPAORO);
        db.execSQL(ConstantesDb.DELETE_TBL_COPAPLATA);
        db.execSQL(ConstantesDb.DELETE_TBL_DEPARTAMENTO);
        db.execSQL(ConstantesDb.DELETE_TBL_DIAGNOSTICO);
        db.execSQL(ConstantesDb.DELETE_TBL_DIAGNOSTICOCAMPO);
        db.execSQL(ConstantesDb.DELETE_TBL_DIAGNOSTICOGRUPO);
        db.execSQL(ConstantesDb.DELETE_TBL_DIAGNOSTICOPERSONA);
        db.execSQL(ConstantesDb.DELETE_TBL_DIAGNOSTICORESULTADO);
        db.execSQL(ConstantesDb.DELETE_TBL_DISTRITO);
        db.execSQL(ConstantesDb.DELETE_TBL_ESTADO);
        db.execSQL(ConstantesDb.DELETE_TBL_MODULO);
        db.execSQL(ConstantesDb.DELETE_TBL_PERFIL);
        db.execSQL(ConstantesDb.DELETE_TBL_PERSONA);
        db.execSQL(ConstantesDb.DELETE_TBL_POSICION);
        db.execSQL(ConstantesDb.DELETE_TBL_PROVINCIA);
        db.execSQL(ConstantesDb.DELETE_TBL_UBIGEO);
        db.execSQL(ConstantesDb.DELETE_TBL_USUARIO);

        onCreate(db);
    }


    /*
     * This method is taking two arguments
     * first one is the name that is to be saved
     * second one is the status
     * 0 means the name is synced with the server
     * 1 means the name is not synced with the server
     * */
    public boolean addName(String name, int status) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String Tiempo=simpleDateFormat.format(calendar.getTime());


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_STATUS, status);
        contentValues.put(COLUMN_DATE,Tiempo);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }
    /*
     * This method taking two arguments
     * first one is the id of the name for which
     * we have to update the sync status
     * and the second one is the status that will be changed
     * */
    public boolean updateNameStatus(int id, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STATUS, status);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + "=" + id, null);
        db.close();
        return true;
    }

    public String  RecuperarFechaLimite() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select MAX("+COLUMN_DATE+") from "+TABLE_NAME+" where "+COLUMN_STATUS+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
           return fecha= fila.getString(0);
        }else{
            return fecha=null;
        }
    }
    /*
     * this method will give us all the name stored in sqlite
     * */
    public Cursor getNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    /*
     * this method is for getting all the unsynced name
     * so that we can sync it with database
     * */
    public Cursor getUnsyncedNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_STATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }


    /*
       Funciones Nuevas
     */
    public String  RecuperarFechaArea() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select IFNULL(MAX("+ConstantesDb.AREA_CAMPO_FECHA_REGISTRO+"),'1900-01-01 00:00:00') as fecha from "+ConstantesDb.AREA_TBL+" where "+ConstantesDb.AREA_CAMPO_SYNC+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            fecha= fila.getString(0);
        }
        return fecha;
    }
    public boolean addArea(Area area) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDb.AREA_CAMPO_ID,area.getIdArea());
        contentValues.put(ConstantesDb.AREA_CAMPO_DESCRIPCION,area.getArea());
        contentValues.put(ConstantesDb.AREA_CAMPO_FECHA_REGISTRO,area.getFechaRegistro());
        contentValues.put(ConstantesDb.AREA_CAMPO_FECHA_UPDATE,area.getFechaUpdate());
        contentValues.put(ConstantesDb.AREA_CAMPO_ESTADO_FK,area.getEstado().getIdEstado());
        contentValues.put(ConstantesDb.AREA_CAMPO_SYNC,area.getSync());
        db.insert(ConstantesDb.AREA_TBL, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getAreas() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.AREA_TBL + " ORDER BY " + ConstantesDb.AREA_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String  RecuperarFechaCopaOro() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select IFNULL(MAX("+ConstantesDb.COPAORO_CAMPO_FECHA_REGISTRO+"),'1900-01-01 00:00:00') as fecha from "+ConstantesDb.COPAORO_TBL+" where "+ConstantesDb.COPAORO_CAMPO_SYNC+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            fecha= fila.getString(0);
        }
        return fecha;
    }
    public boolean addCopaOro(CopaOro copaoro) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDb.COPAORO_CAMPO_ID,copaoro.getIdCopaOro());
        contentValues.put(ConstantesDb.COPAORO_CAMPO_DESCRIPCION,copaoro.getDescripcionCopaOro());
        contentValues.put(ConstantesDb.COPAORO_CAMPO_FECHA_REGISTRO,copaoro.getFechaRegistro());
        contentValues.put(ConstantesDb.COPAORO_CAMPO_FECHA_UPDATE,copaoro.getFechaUpdate());
        contentValues.put(ConstantesDb.COPAORO_CAMPO_ESTADO_FK,copaoro.getEstado().getIdEstado());
        contentValues.put(ConstantesDb.COPAORO_CAMPO_SYNC,copaoro.getSync());
        db.insert(ConstantesDb.COPAORO_TBL, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getCopaOro() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.COPAORO_TBL + " ORDER BY " + ConstantesDb.COPAORO_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String  RecuperarFechaCopaPlata() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select IFNULL(MAX("+ConstantesDb.COPAPLATA_CAMPO_FECHA_REGISTRO+"),'1900-01-01 00:00:00') as fecha from "+ConstantesDb.COPAPLATA_TBL+" where "+ConstantesDb.COPAPLATA_CAMPO_SYNC+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            fecha= fila.getString(0);
        }
        return fecha;
    }
    public boolean addCopaPlata(CopaPlata copaplata) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDb.COPAPLATA_CAMPO_ID,copaplata.getIdCopaPlata());
        contentValues.put(ConstantesDb.COPAPLATA_CAMPO_DESCRIPCION,copaplata.getDescripcionCopaPlata());
        contentValues.put(ConstantesDb.COPAPLATA_CAMPO_FECHA_REGISTRO,copaplata.getFechaRegistro());
        contentValues.put(ConstantesDb.COPAPLATA_CAMPO_FECHA_UPDATE,copaplata.getFechaUpdate());
        contentValues.put(ConstantesDb.COPAPLATA_CAMPO_ESTADO_FK,copaplata.getEstado().getIdEstado());
        contentValues.put(ConstantesDb.COPAPLATA_CAMPO_SYNC,copaplata.getSync());
        db.insert(ConstantesDb.COPAPLATA_TBL, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getCopaPlata() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.COPAPLATA_TBL + " ORDER BY " + ConstantesDb.COPAPLATA_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String  RecuperarFechaDepartamento() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select IFNULL(MAX("+ConstantesDb.DEPARTAMENTO_CAMPO_FECHA_REGISTRO+"),'1900-01-01 00:00:00') as fecha from "+ConstantesDb.DEPARTAMENTO_TBL+" where "+ConstantesDb.DEPARTAMENTO_CAMPO_SYNC+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            fecha= fila.getString(0);
        }
        return fecha;
    }
    public boolean addDepartamento(Departamento departamento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDb.DEPARTAMENTO_CAMPO_ID,departamento.getIdDepartamento());
        contentValues.put(ConstantesDb.DEPARTAMENTO_CAMPO_DEPARTAMENTO,departamento.getDepartamento());
        contentValues.put(ConstantesDb.DEPARTAMENTO_CAMPO_FECHA_REGISTRO,departamento.getFechaRegistro());
        contentValues.put(ConstantesDb.DEPARTAMENTO_CAMPO_SYNC,departamento.getSync());
        db.insert(ConstantesDb.DEPARTAMENTO_TBL, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getDepartamento() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.DEPARTAMENTO_TBL + " ORDER BY " + ConstantesDb.DEPARTAMENTO_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String  RecuperarFechaProvincia() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select IFNULL(MAX("+ConstantesDb.PROVINCIA_CAMPO_FECHA_REGISTRO+"),'1900-01-01 00:00:00') as fecha from "+ConstantesDb.PROVINCIA_TBL+" where "+ConstantesDb.PROVINCIA_CAMPO_SYNC+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            fecha= fila.getString(0);
        }
        return fecha;
    }
    public boolean addProvincia(Provincia provincia) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDb.PROVINCIA_CAMPO_ID,provincia.getIdProvincia());
        contentValues.put(ConstantesDb.PROVINCIA_CAMPO_PROVINCIA,provincia.getProvincia());
        contentValues.put(ConstantesDb.PROVINCIA_CAMPO_FECHA_REGISTRO,provincia.getFechaRegistro());
        contentValues.put(ConstantesDb.PROVINCIA_CAMPO_DEPARTAMENTO_FK,provincia.getDepartamento().getIdDepartamento());
        contentValues.put(ConstantesDb.PROVINCIA_CAMPO_SYNC,provincia.getSync());
        db.insert(ConstantesDb.PROVINCIA_TBL, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getProvincia() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.PROVINCIA_TBL + " ORDER BY " + ConstantesDb.PROVINCIA_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String  RecuperarFechaDistrito() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select IFNULL(MAX("+ConstantesDb.DISTRITO_CAMPO_FECHA_REGISTRO+"),'1900-01-01 00:00:00') as fecha from "+ConstantesDb.DISTRITO_TBL+" where "+ConstantesDb.DISTRITO_CAMPO_SYNC+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            fecha= fila.getString(0);
        }
        return fecha;
    }
    public boolean addDistrito(Distrito distrito) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDb.DISTRITO_CAMPO_ID,distrito.getIdDistrito());
        contentValues.put(ConstantesDb.DISTRITO_CAMPO_DISTRITO,distrito.getDistrito());
        contentValues.put(ConstantesDb.DISTRITO_CAMPO_PROVINCIA_FK,distrito.getProvincia().getIdProvincia());
        contentValues.put(ConstantesDb.DISTRITO_CAMPO_FECHA_REGISTRO,distrito.getFechaRegistro());
        contentValues.put(ConstantesDb.DISTRITO_CAMPO_SYNC,distrito.getSync());
        db.insert(ConstantesDb.DISTRITO_TBL, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getDistrito() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.DISTRITO_TBL + " ORDER BY " + ConstantesDb.DISTRITO_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String  RecuperarFechaDiagnostico() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select IFNULL(MAX("+ConstantesDb.DIAGNOSTICO_CAMPO_FECHA_REGISTRO+"),'1900-01-01 00:00:00') as fecha from "+ConstantesDb.DIAGNOSTICO_TBL+" where "+ConstantesDb.DIAGNOSTICO_CAMPO_SYNC+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            fecha= fila.getString(0);
        }
        return fecha;
    }
    public boolean addDiagnostico(Diagnostico diagnostico) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDb.DIAGNOSTICO_CAMPO_ID,diagnostico.getIdDiagnostico());
        contentValues.put(ConstantesDb.DIAGNOSTICO_CAMPO_CATEGORIA,diagnostico.getCategoria());
        contentValues.put(ConstantesDb.DIAGNOSTICO_CAMPO_INTERCAR,diagnostico.getIntercar());
        contentValues.put(ConstantesDb.DIAGNOSTICO_CAMPO_POSICIONSUGERIDA1,diagnostico.getPosicionSugerida1() );
        contentValues.put(ConstantesDb.DIAGNOSTICO_CAMPO_POSICIONSUGERIDA2,diagnostico.getPosicionSugerida2() );
        contentValues.put(ConstantesDb.DIAGNOSTICO_CAMPO_PIERNA_DERECHA,diagnostico.getPiernaDerecha());
        contentValues.put(ConstantesDb.DIAGNOSTICO_CAMPO_PIERNA_IZQUIERDA,diagnostico.getPiernaIzquierda() );
        contentValues.put(ConstantesDb.DIAGNOSTICO_CAMPO_FECHA_REGISTRO,diagnostico.getFechaRegistro());
        contentValues.put(ConstantesDb.DIAGNOSTICO_CAMPO_PERSONA_FK,diagnostico.getPersona().getIdPersona());
        contentValues.put(ConstantesDb.DIAGNOSTICO_CAMPO_UBIGEO_FK,diagnostico.getUbigeo().getIdUbigeo());
        contentValues.put(ConstantesDb.DIAGNOSTICO_CAMPO_MODULO_FK,diagnostico.getModulo().getIdModulo());
        contentValues.put(ConstantesDb.DIAGNOSTICO_CAMPO_SYNC,diagnostico.getSync());
        db.insert(ConstantesDb.DIAGNOSTICO_TBL, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getDiagnostico() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.DIAGNOSTICO_TBL + " ORDER BY " + ConstantesDb.DIAGNOSTICO_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String  RecuperarFechaDiagnosticoCampo() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select IFNULL(MAX("+ConstantesDb.DIAGNOSTICOCAMPO_CAMPO_FECHA_REGISTRO+"),'1900-01-01 00:00:00') as fecha from "+ConstantesDb.DIAGNOSTICOCAMPO_TBL+" where "+ConstantesDb.DIAGNOSTICOCAMPO_CAMPO_SYNC+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            fecha= fila.getString(0);
        }
        return fecha;
    }
    public boolean addDiagnosticoCampo(DiagnosticoCampo diagnosticoCampo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDb.DIAGNOSTICOCAMPO_CAMPO_ID,diagnosticoCampo.getIdDiagnosticoCampo() );
        contentValues.put(ConstantesDb.DIAGNOSTICOCAMPO_CAMPO_DESCRIPCION,diagnosticoCampo.getCampo() );
        contentValues.put(ConstantesDb.DIAGNOSTICOCAMPO_CAMPO_FECHA_REGISTRO,diagnosticoCampo.getFechaRegistro() );
        contentValues.put(ConstantesDb.DIAGNOSTICOCAMPO_CAMPO_FECHA_UPDATE,diagnosticoCampo.getFechaUpdate());
        contentValues.put(ConstantesDb.DIAGNOSTICOCAMPO_CAMPO_ESTADO_FK,diagnosticoCampo.getEstado().getIdEstado() );
        contentValues.put(ConstantesDb.DIAGNOSTICOCAMPO_CAMPO_SYNC,diagnosticoCampo.getSync());
        db.insert(ConstantesDb.DIAGNOSTICOCAMPO_TBL, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getDiagnosticoCampo() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.DIAGNOSTICOCAMPO_TBL + " ORDER BY " + ConstantesDb.DIAGNOSTICOCAMPO_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String  RecuperarFechaDiagnosticoGrupo() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select IFNULL(MAX("+ConstantesDb.DIAGNOSTICOGRUPO_CAMPO_FECHA_REGISTRO+"),'1900-01-01 00:00:00') as fecha from "+ConstantesDb.DIAGNOSTICOGRUPO_TBL+" where "+ConstantesDb.DIAGNOSTICOGRUPO_CAMPO_SYNC+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            fecha= fila.getString(0);
        }
        return fecha;
    }
    public boolean addDiagnosticoGrupo(DiagnosticoGrupo diagnosticoGrupo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDb.DIAGNOSTICOGRUPO_CAMPO_ID,diagnosticoGrupo.getIdDiagnosticoGrupo());
        contentValues.put(ConstantesDb.DIAGNOSTICOGRUPO_CAMPO_DESCRIPCION,diagnosticoGrupo.getGrupo());
        contentValues.put(ConstantesDb.DIAGNOSTICOGRUPO_CAMPO_FECHA_REGISTRO,diagnosticoGrupo.getFechaRegistro());
        contentValues.put(ConstantesDb.DIAGNOSTICOGRUPO_CAMPO_FECHA_UPDATE,diagnosticoGrupo.getFechaUpdate());
        contentValues.put(ConstantesDb.DIAGNOSTICOGRUPO_CAMPO_SYNC,diagnosticoGrupo.getSync());
        db.insert(ConstantesDb.DIAGNOSTICOGRUPO_TBL, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getDiagnosticoGrupo() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.DIAGNOSTICOGRUPO_TBL + " ORDER BY " + ConstantesDb.DIAGNOSTICOGRUPO_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String  RecuperarFechaDiagnosticoPersona() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select IFNULL(MAX("+ConstantesDb.DIAGNOSTICOPERSONA_CAMPO_FECHA_REGISTRO+"),'1900-01-01 00:00:00') as fecha from "+ConstantesDb.DIAGNOSTICOPERSONA_TBL+" where "+ConstantesDb.DIAGNOSTICOPERSONA_CAMPO_SYNC+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            fecha= fila.getString(0);
        }
        return fecha;
    }
    public boolean addDiagnosticoPersona(DiagnosticoPersona diagnosticoPersona) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDb.DIAGNOSTICOPERSONA_CAMPO_ID,diagnosticoPersona.getIdDiagnosticoPersona());
        contentValues.put(ConstantesDb.DIAGNOSTICOPERSONA_CAMPO_NOMBRES,diagnosticoPersona.getNombres());
        contentValues.put(ConstantesDb.DIAGNOSTICOPERSONA_CAMPO_APELLIDOS,diagnosticoPersona.getApellidos());
        contentValues.put(ConstantesDb.DIAGNOSTICOPERSONA_CAMPO_ALIAS,diagnosticoPersona.getAlias());
        contentValues.put(ConstantesDb.DIAGNOSTICOPERSONA_CAMPO_NUMERO,diagnosticoPersona.getNumero());
        contentValues.put(ConstantesDb.DIAGNOSTICOPERSONA_CAMPO_TELEFONO1,diagnosticoPersona.getTelefono1());
        contentValues.put(ConstantesDb.DIAGNOSTICOPERSONA_CAMPO_TELEFONO2,diagnosticoPersona.getTelefono2());
        contentValues.put(ConstantesDb.DIAGNOSTICOPERSONA_CAMPO_DOMICILIO,diagnosticoPersona.getDomicilio());
        contentValues.put(ConstantesDb.DIAGNOSTICOPERSONA_CAMPO_FECHA_REGISTRO,diagnosticoPersona.getFechaRegistro());
        contentValues.put(ConstantesDb.DIAGNOSTICOPERSONA_CAMPO_FECHA_MIGRACION,diagnosticoPersona.getFechaMigracion());
        contentValues.put(ConstantesDb.DIAGNOSTICOPERSONA_CAMPO_SYNC,diagnosticoPersona.getSync());
        db.insert(ConstantesDb.DIAGNOSTICOPERSONA_TBL, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getDiagnosticoPersona() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.DIAGNOSTICOPERSONA_TBL + " ORDER BY " + ConstantesDb.DIAGNOSTICOPERSONA_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String  RecuperarFechaDiagnosticoResultado() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select IFNULL(MAX("+ConstantesDb.DIAGNOSTICORESULTADO_CAMPO_FECHA_REGISTRO+"),'1900-01-01 00:00:00') as fecha from "+ConstantesDb.DIAGNOSTICORESULTADO_TBL+" where "+ConstantesDb.DIAGNOSTICORESULTADO_CAMPO_SYNC+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            fecha= fila.getString(0);
        }
        return fecha;
    }
    public boolean addDiagnosticoResultado(DiagnosticoResultado diagnosticoResultado) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDb.DIAGNOSTICORESULTADO_CAMPO_ID,diagnosticoResultado.getIdDiagnosticoResultado());
        contentValues.put(ConstantesDb.DIAGNOSTICORESULTADO_CAMPO_RESULTADO,diagnosticoResultado.getResultado());
        contentValues.put(ConstantesDb.DIAGNOSTICORESULTADO_CAMPO_FECHA_REGISTRO,diagnosticoResultado.getFechaRegistro());
        contentValues.put(ConstantesDb.DIAGNOSTICORESULTADO_CAMPO_DIAGNOSTICO_FK,diagnosticoResultado.getDiagnostico().getIdDiagnostico()); contentValues.put(ConstantesDb.DIAGNOSTICORESULTADO_CAMPO_DIAGNOSTICOGRUPO_FK,diagnosticoResultado.getDiagnosticoGrupo().getIdDiagnosticoGrupo());
        contentValues.put(ConstantesDb.DIAGNOSTICORESULTADO_CAMPO_DIAGNOSTICOCAMPO_FK,diagnosticoResultado.getDiagnosticoCampo().getIdDiagnosticoCampo());
        contentValues.put(ConstantesDb.DIAGNOSTICORESULTADO_CAMPO_SYNC,diagnosticoResultado.getSync());
        db.insert(ConstantesDb.DIAGNOSTICORESULTADO_TBL, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getDiagnosticoResultado() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.DIAGNOSTICORESULTADO_TBL + " ORDER BY " + ConstantesDb.DIAGNOSTICORESULTADO_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String  RecuperarFechaEstado() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select IFNULL(MAX("+ConstantesDb.ESTADO_CAMPO_FECHA_REGISTRO+"),'1900-01-01 00:00:00') as fecha from "+ConstantesDb.ESTADO_TBL+" where "+ConstantesDb.ESTADO_CAMPO_SYNC+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            fecha= fila.getString(0);
        }
        return fecha;
    }
    public boolean addEstado(Estado estado) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDb.ESTADO_CAMPO_ID,estado.getIdEstado());
        contentValues.put(ConstantesDb.ESTADO_CAMPO_GRUPO,estado.getGrupo());
        contentValues.put(ConstantesDb.ESTADO_CAMPO_DESCRIPCION,estado.getEstado());
        contentValues.put(ConstantesDb.ESTADO_CAMPO_FECHA_REGISTRO,estado.getFechaRegistro());
        contentValues.put(ConstantesDb.ESTADO_CAMPO_SYNC,estado.getSync());
        db.insert(ConstantesDb.ESTADO_TBL, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getEstado() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.ESTADO_TBL + " ORDER BY " + ConstantesDb.ESTADO_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String  RecuperarFechaModulo() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select IFNULL(MAX("+ConstantesDb.MODULO_CAMPO_FECHA_REGISTRO+"),'1900-01-01 00:00:00') as fecha from "+ConstantesDb.MODULO_TBL+" where "+ConstantesDb.MODULO_CAMPO_SYNC+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            fecha= fila.getString(0);
        }
        return fecha;
    }
    public boolean addModulo(Modulo modulo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDb.MODULO_CAMPO_ID,modulo.getIdModulo());
        contentValues.put(ConstantesDb.MODULO_CAMPO_MODULO  ,modulo.getModulo());
        contentValues.put(ConstantesDb.MODULO_CAMPO_FECHA_REGISTRO  ,modulo.getFechaRegistro());
        contentValues.put(ConstantesDb.MODULO_CAMPO_SYNC ,modulo.getSync());
        db.insert(ConstantesDb.MODULO_TBL, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getModulo() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.MODULO_TBL + " ORDER BY " + ConstantesDb.MODULO_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String  RecuperarFechaPerfil() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select IFNULL(MAX("+ConstantesDb.PERFIL_CAMPO_FECHA_REGISTRO+"),'1900-01-01 00:00:00') as fecha from "+ConstantesDb.PERFIL_TBL+" where "+ConstantesDb.PERFIL_CAMPO_SYNC+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            fecha= fila.getString(0);
        }
        return fecha;
    }
    public boolean addPerfil(Perfil perfil) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDb.PERFIL_CAMPO_ID,perfil.getIdPerfil());
        contentValues.put(ConstantesDb.PERFIL_CAMPO_DESCRIPCION  ,perfil.getPerfil());
        contentValues.put(ConstantesDb.PERFIL_CAMPO_FECHA_REGISTRO  ,perfil.getFechaRegistro());
        contentValues.put(ConstantesDb.PERFIL_CAMPO_FECHA_UPDATE ,perfil.getFechaUpdate());
        contentValues.put(ConstantesDb.PERFIL_CAMPO_ESTADO_FK ,perfil.getEstado().getIdEstado());
        contentValues.put(ConstantesDb.PERFIL_CAMPO_SYNC ,perfil.getSync());
        db.insert(ConstantesDb.PERFIL_TBL, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getPerfil() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.PERFIL_TBL + " ORDER BY " + ConstantesDb.PERFIL_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String  RecuperarFechaPersona() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select IFNULL(MAX("+ConstantesDb.PERSONA_CAMPO_FECHA_REGISTRO+"),'1900-01-01 00:00:00') as fecha from "+ConstantesDb.PERSONA_TBL+" where "+ConstantesDb.PERSONA_CAMPO_SYNC+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            fecha= fila.getString(0);
        }
        return fecha;
    }
    public boolean addPersona(Persona persona) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDb.PERSONA_CAMPO_ID,persona.getIdPersona());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_NOMBRES,persona.getNombres());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_APELLIDOS,persona.getApellidos());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_APODO,persona.getApodo());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_FECHA_NACIMIENTO,persona.getFechaNacimiento());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_NACIONALIDAD,persona.getNacionalidad());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_LUGAR_RESIDENCIA,persona.getLugarResidencia());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_CLUB_ACTUAL,persona.getClubActual());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_LIGA_ACTUAL,persona.getLigaActual());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_CELULAR,persona.getCelular());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_TELEFONO_FIJO,persona.getTelefonoFijo());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_BAUTIZO,persona.getBautizo());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_COMUNION,persona.getComunion());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_CONFIRMACION,persona.getConfirmacion());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_DNI,persona.getDni());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_CORREO,persona.getCorreo());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_APODERADO,persona.getApoderado());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_APODERADO_CELULAR,persona.getApoderadoTelefono());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_CATEGORIA_ACTUAL,persona.getCategoriaActual());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_FECHA_REGISTRO,persona.getFechaRegistro());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_FECHA_UPDATE,persona.getFechaUpdate());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_ESTADO_FK,persona.getEstado().getIdEstado());
        contentValues.put(ConstantesDb.PERSONA_CAMPO_SYNC,persona.getSync());
        db.insert(ConstantesDb.PERSONA_TBL, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getPersona() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.PERSONA_TBL + " ORDER BY " + ConstantesDb.PERSONA_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }


    public String  RecuperarFechaPosicion() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select IFNULL(MAX("+ConstantesDb.POSICION_CAMPO_FECHA_REGISTRO+"),'1900-01-01 00:00:00') as fecha from "+ConstantesDb.POSICION_TBL+" where "+ConstantesDb.POSICION_CAMPO_SYNC+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            fecha= fila.getString(0);
        }
        return fecha;
    }
    public boolean addPosicion(Posicion posicion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDb.POSICION_CAMPO_ID,posicion.getIdPosicion());
        contentValues.put(ConstantesDb.POSICION_CAMPO_CODIGO,posicion.getCodigo());
        contentValues.put(ConstantesDb.POSICION_CAMPO_POSICION,posicion.getPosicion());
        contentValues.put(ConstantesDb.POSICION_CAMPO_FECHA_REGISTRO,posicion.getFechaRegistro());
        contentValues.put(ConstantesDb.POSICION_CAMPO_FECHA_UPDATE,posicion.getFechaUpdate());
        contentValues.put(ConstantesDb.POSICION_CAMPO_ESTADO_FK,posicion.getEstado().getIdEstado());
        contentValues.put(ConstantesDb.POSICION_CAMPO_SYNC,posicion.getSync());
        db.insert(ConstantesDb.POSICION_TBL, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getPosicion() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.POSICION_TBL + " ORDER BY " + ConstantesDb.POSICION_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String  RecuperarFechaUbigeo() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select IFNULL(MAX("+ConstantesDb.UBIGEO_CAMPO_FECHA_REGISTRO+"),'1900-01-01 00:00:00') as fecha from "+ConstantesDb.UBIGEO_TBL+" where "+ConstantesDb.UBIGEO_CAMPO_SYNC+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            fecha= fila.getString(0);
        }
        return fecha;
    }
    public boolean addUbigeo(Ubigeo ubigeo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDb.UBIGEO_CAMPO_ID,ubigeo.getIdUbigeo());
        contentValues.put(ConstantesDb.UBIGEO_CAMPO_UBIGEO,ubigeo.getDescripcion());
        contentValues.put(ConstantesDb.UBIGEO_CAMPO_USUARIO_FK,ubigeo.getUsuario().getIdUsuario());
        contentValues.put(ConstantesDb.UBIGEO_CAMPO_DEPARTAMENTO_FK,ubigeo.getDepartamento().getIdDepartamento());
        contentValues.put(ConstantesDb.UBIGEO_CAMPO_PROVINCIA_FK,ubigeo.getProvincia().getIdProvincia());
        contentValues.put(ConstantesDb.UBIGEO_CAMPO_DISTRITO_FK,ubigeo.getDistrito().getDistrito());
        contentValues.put(ConstantesDb.UBIGEO_CAMPO_MODULOS_FK,ubigeo.getModulo().getIdModulo());
        contentValues.put(ConstantesDb.UBIGEO_CAMPO_SYNC,ubigeo.getSync());
        db.insert(ConstantesDb.UBIGEO_TBL, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getUbigeo() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.UBIGEO_TBL + " ORDER BY " + ConstantesDb.UBIGEO_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String  RecuperarFechaUsuario() {
        String fecha="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select IFNULL(MAX("+ConstantesDb.USUARIO_CAMPO_FECHA_REGISTRO+"),'1900-01-01 00:00:00') as fecha from "+ConstantesDb.USUARIO_TBL+" where "+ConstantesDb.USUARIO_CAMPO_SYNC+"=1  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            fecha= fila.getString(0);
        }
        return fecha;
    }
    public boolean addUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDb.USUARIO_CAMPO_ID,usuario.getIdUsuario());
        contentValues.put(ConstantesDb.USUARIO_CAMPO_USUARIO,usuario.getUsuario());
        contentValues.put(ConstantesDb.USUARIO_CAMPO_PASSWORD,usuario.getPassword());
        contentValues.put(ConstantesDb.USUARIO_CAMPO_NOMBRES,usuario.getNombres());
        contentValues.put(ConstantesDb.USUARIO_CAMPO_APELLIDOS,usuario.getApellidos());
        contentValues.put(ConstantesDb.USUARIO_CAMPO_DNI,usuario.getDni());
        contentValues.put(ConstantesDb.USUARIO_CAMPO_CARGO,usuario.getCargo());
        contentValues.put(ConstantesDb.USUARIO_CAMPO_CORREO,usuario.getCorreo());
        contentValues.put(ConstantesDb.USUARIO_CAMPO_FECHA_REGISTRO,usuario.getFechaRegistro());
        contentValues.put(ConstantesDb.USUARIO_CAMPO_FECHA_UPDATE,usuario.getFechaUpdate());
        contentValues.put(ConstantesDb.USUARIO_CAMPO_IMAGEN,usuario.getImagen());
        contentValues.put(ConstantesDb.USUARIO_CAMPO_PERFIL_FK,usuario.getPerfil().getIdPerfil());
        contentValues.put(ConstantesDb.USUARIO_CAMPO_AREA_FK,usuario.getArea().getIdArea());
        contentValues.put(ConstantesDb.USUARIO_CAMPO_ESTADO_FK,usuario.getEstado().getIdEstado());
        contentValues.put(ConstantesDb.USUARIO_CAMPO_SYNC,usuario.getSync());
        db.insert(ConstantesDb.USUARIO_TBL, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getUsuario() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.USUARIO_TBL + " ORDER BY " + ConstantesDb.USUARIO_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Usuario  RecuperarDatosUsuario(String Usuario) {
        Usuario usuario=new Usuario();
        Perfil perfil=new Perfil();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select u."+ConstantesDb.USUARIO_CAMPO_ID+",u."+ConstantesDb.USUARIO_CAMPO_USUARIO+",u."+ConstantesDb.USUARIO_CAMPO_PASSWORD +",u."+ConstantesDb.USUARIO_CAMPO_NOMBRES+",u."+ConstantesDb.USUARIO_CAMPO_APELLIDOS+",u."+ConstantesDb.USUARIO_CAMPO_IMAGEN+",per."+ConstantesDb.PERFIL_CAMPO_ID+",per."+ConstantesDb.PERFIL_CAMPO_DESCRIPCION+" from "+ConstantesDb.USUARIO_TBL+"  u inner join "+ConstantesDb.PERFIL_TBL+" per on u."+ConstantesDb.USUARIO_CAMPO_PERFIL_FK+"=per."+ConstantesDb.PERFIL_CAMPO_ID+"  where u."+ConstantesDb.USUARIO_CAMPO_USUARIO+"='"+Usuario+"'  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            usuario.setIdUsuario(fila.getInt(0));
            usuario.setUsuario(fila.getString(1));
            usuario.setPassword(fila.getString(2));
            usuario.setNombres(fila.getString(3));
            usuario.setApellidos(fila.getString(4));
            usuario.setImagen(fila.getString(5));
            perfil.setIdPerfil(fila.getInt(6));
            perfil.setPerfil(fila.getString(7));
            usuario.setPerfil(perfil);
        }
        return usuario;
    }

    public Ubigeo  RecuperarUbigeo(Usuario usuario,Modulo modulo) {

        SQLiteDatabase db = this.getReadableDatabase();

        String sql="select dep."+ConstantesDb.DEPARTAMENTO_CAMPO_ID+",dep."+ConstantesDb.DEPARTAMENTO_CAMPO_DEPARTAMENTO+",prov."+ConstantesDb.PROVINCIA_CAMPO_ID+",prov."+ConstantesDb.PROVINCIA_CAMPO_PROVINCIA+",dist."+ConstantesDb.DISTRITO_CAMPO_ID+",dist."+ConstantesDb.DISTRITO_CAMPO_DISTRITO+" from "+
                ConstantesDb.UBIGEO_TBL+" ub inner join "+ConstantesDb.DEPARTAMENTO_TBL+" dep " +
                "on ub."+ConstantesDb.UBIGEO_CAMPO_DEPARTAMENTO_FK+"=dep."+ConstantesDb.DEPARTAMENTO_CAMPO_ID+
                " inner join "+ConstantesDb.PROVINCIA_TBL+" prov on  " +
                "prov."+ConstantesDb.PROVINCIA_CAMPO_ID+"=ub."+ConstantesDb.UBIGEO_CAMPO_PROVINCIA_FK+
                " inner join "+ConstantesDb.DISTRITO_TBL+" dist on  " +
                "ub."+ConstantesDb.UBIGEO_CAMPO_DISTRITO_FK+"=dist."+ConstantesDb.DISTRITO_CAMPO_ID+
                " where ub."+ConstantesDb.UBIGEO_CAMPO_SYNC+"=1 and ub."+ConstantesDb.UBIGEO_CAMPO_MODULOS_FK+"="+modulo.getIdModulo()+" and ub."+ConstantesDb.UBIGEO_CAMPO_USUARIO_FK+"="+usuario.getIdUsuario()+" ORDER BY ub."+ConstantesDb.UBIGEO_CAMPO_ID+" DESC   LIMIT 1;";

        Ubigeo temporal=new Ubigeo();
        Departamento depa=new Departamento();
        Provincia prov=new Provincia();
        Distrito dist=new Distrito();

        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            depa.setIdDepartamento(fila.getInt(0));
            depa.setDepartamento(fila.getString(1));
            prov.setIdProvincia(fila.getInt(2));
            prov.setProvincia(fila.getString(3));
            dist.setIdDistrito(fila.getInt(4));
            dist.setDistrito(fila.getString(5));
            temporal.setDepartamento(depa);
            temporal.setProvincia(prov);
            temporal.setDistrito(dist);
        }
        return temporal;
    }




    public Cursor getDepartamentos() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.DEPARTAMENTO_TBL + " ORDER BY " + ConstantesDb.DEPARTAMENTO_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }


    public String  RecuperarDepartamentoName(int idDepartamento) {
        String Nombre="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select "+ConstantesDb.DEPARTAMENTO_CAMPO_DEPARTAMENTO+" from "+ConstantesDb.DEPARTAMENTO_TBL+" where "+ConstantesDb.DEPARTAMENTO_CAMPO_ID+"="+idDepartamento+"  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            Nombre= fila.getString(0);
        }
        return Nombre;
    }



    public Cursor getProvincias(int idDepartamento) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.PROVINCIA_TBL + " where  "+ConstantesDb.PROVINCIA_CAMPO_DEPARTAMENTO_FK+"="+idDepartamento+"   ORDER BY " + ConstantesDb.PROVINCIA_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String  RecuperarProvinciaName(int idProvincia) {
        String Nombre="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select "+ConstantesDb.PROVINCIA_CAMPO_PROVINCIA+" from "+ConstantesDb.PROVINCIA_TBL+" where "+ConstantesDb.PROVINCIA_CAMPO_ID+"="+idProvincia+"  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            Nombre= fila.getString(0);
        }
        return Nombre;
    }
    public Cursor getDistritos(int idProvincia) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + ConstantesDb.DISTRITO_TBL + " where "+ConstantesDb.DISTRITO_CAMPO_PROVINCIA_FK+"="+idProvincia+" ORDER BY " + ConstantesDb.DISTRITO_CAMPO_ID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String  RecuperarDistritoName(int idDistrito) {
        String Nombre="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="select "+ConstantesDb.DISTRITO_CAMPO_DISTRITO+" from "+ConstantesDb.DISTRITO_TBL+" where "+ConstantesDb.DISTRITO_CAMPO_ID+"="+idDistrito+"  LIMIT 1;";
        Cursor fila = db.rawQuery(sql,null);
        if(fila.moveToFirst()) {
            Nombre= fila.getString(0);
        }
        return Nombre;
    }
}