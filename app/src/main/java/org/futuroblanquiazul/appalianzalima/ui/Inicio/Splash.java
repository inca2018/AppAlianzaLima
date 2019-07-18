package org.futuroblanquiazul.appalianzalima.ui.Inicio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.futuroblanquiazul.appalianzalima.Conectividad.NetworkStateChecker;
import org.futuroblanquiazul.appalianzalima.Conectividad.Sync;
import org.futuroblanquiazul.appalianzalima.Data.Conexion.Local.ConstantesDb;
import org.futuroblanquiazul.appalianzalima.Data.Conexion.Local.DatabaseHelper;
import org.futuroblanquiazul.appalianzalima.Data.Conexion.Remoto.VolleySingleton;
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
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Name;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Perfil;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Persona;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Posicion;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Provincia;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Ubigeo;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Usuario;
import org.futuroblanquiazul.appalianzalima.R;
import org.futuroblanquiazul.appalianzalima.Utils.Constantes;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ObjectStreamException;
import java.util.HashMap;
import java.util.Map;

public class Splash extends AppCompatActivity {

    //database helper object
    private DatabaseHelper db;
    private TextView Status;

    //a broadcast to know weather the data is synced or not
    public static final String DATA_SAVED_BROADCAST = "org.futuroblanquiazul.datasaved";

    //Broadcast receiver to know the sync status
   // private BroadcastReceiver broadcastReceiver;
      private BroadcastReceiver Sincronizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Status=findViewById(R.id.tvNotificacion);
        db = new DatabaseHelper(this);
        Sincronizador=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                //if there is a network
                if (activeNetwork != null ) {
                    //if connected to wifi or mobile data plan
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        LanzarFunciones(context);

                    }else{

                        Status.setText("Buscando Conexión al Servidor!");
                        LanzarListados();
                    }
                }else{
                    Status.setText("Buscando Conexión al Servidor!");
                    LanzarListados();
                }
            }
        };
        registerReceiver(Sincronizador, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public void LanzarFunciones(Context context){
        Log.e("INCA","Detecto Activo la Conexion!");

        String FechaArea=String.valueOf(db.RecuperarFechaArea());
        RecuperarRegistrosAreaServer(FechaArea,context);

        String FechaCopaOro=String.valueOf(db.RecuperarFechaCopaOro());
        RecuperarRegistrosCopaOro(FechaCopaOro,context);

        String FechaCopaPlata=String.valueOf(db.RecuperarFechaCopaPlata());
        RecuperarRegistrosCopaPlata(FechaCopaPlata,context);

        String FechaDepartamento=String.valueOf(db.RecuperarFechaDepartamento());
        RecuperarRegistrosDepartamento(FechaDepartamento,context);

        String FechaProvincia=String.valueOf(db.RecuperarFechaProvincia());
        RecuperarRegistrosProvincia(FechaProvincia,context);

        String FechaDistrito=String.valueOf(db.RecuperarFechaDistrito());
        RecuperarRegistrosDistrito(FechaDistrito,context);

        String FechaDiagnostico=String.valueOf(db.RecuperarFechaDiagnostico());
        RecuperarRegistrosDiagnostico(FechaDiagnostico,context);

        String FechaDiagnosticoCampo=String.valueOf(db.RecuperarFechaDiagnosticoCampo());
        RecuperarRegistrosDiagnosticoCampo(FechaDiagnosticoCampo,context);

        String FechaDiagnosticoGrupo=String.valueOf(db.RecuperarFechaDiagnosticoGrupo());
        RecuperarRegistrosDiagnosticoGrupo(FechaDiagnosticoGrupo,context);

        String FechaDiagnosticoPersona=String.valueOf(db.RecuperarFechaDiagnosticoPersona());
        RecuperarRegistrosDiagnosticoPersona(FechaDiagnosticoPersona,context);

        String FechaDiagnosticoResultado=String.valueOf(db.RecuperarFechaDiagnosticoResultado());
        RecuperarRegistrosDiagnosticoResultado(FechaDiagnosticoResultado,context);

        String FechaEstado=String.valueOf(db.RecuperarFechaEstado());
        RecuperarRegistrosEstado(FechaEstado,context);

        String FechaModulo=String.valueOf(db.RecuperarFechaModulo());
        RecuperarRegistrosModulo(FechaModulo,context);

        String FechaPerfil=String.valueOf(db.RecuperarFechaPerfil());
        RecuperarRegistrosPerfil(FechaPerfil,context);

        String FechaPersona=String.valueOf(db.RecuperarFechaPersona());
        RecuperarRegistrosPersona(FechaPersona,context);

        String FechaPosicion=String.valueOf(db.RecuperarFechaPosicion());
        RecuperarRegistrosPosicion(FechaPosicion,context);

        String FechaUbigeo=String.valueOf(db.RecuperarFechaUbigeo());
        RecuperarRegistrosUbigeo(FechaUbigeo,context);


        String FechaUsuario=String.valueOf(db.RecuperarFechaUsuario());
        RecuperarRegistrosUsuario(FechaUsuario,context);
    }
    public void LanzarListados(){
        MostrarAreas();
        MostrarCopaOro();
        MostrarCopaPlata();
        MostrarDepartamento();
        MostrarProvincia();
        MostrarDistrito();
        MostrarDiagnostico();
        MostrarDiagnosticoCampo();
        MostrarDiagnosticoGrupo();
        MostrarDiagnosticoPersona();
        MostrarDiagnosticoResultado();
        MostrarEstado();
        MostrarModulo();
        MostrarPerfil();
        MostrarPersona();
        MostrarPosicion();
        MostrarUbigeo();
        MostrarUsuario();
        mover();
    }

    private void RecuperarRegistrosAreaServer(final String fecha,final Context context){
        Log.i("INCA","Entro a RecuperarServer Area "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray Objetos=jsonResponse.getJSONArray("areas");
                                for(int i=0;i<Objetos.length();i++){
                                    JSONObject objeto= Objetos.getJSONObject(i);
                                    Area area=new Area();
                                    area.setIdArea(objeto.getInt("idArea"));
                                    area.setArea(objeto.getString("DescripcionArea"));
                                    area.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    area.setFechaUpdate(objeto.getString("fechaUpdate"));
                                    Estado estado=new Estado();
                                    estado.setIdEstado(objeto.getInt("estado_idEstado"));
                                    area.setEstado(estado);
                                    area.setSync(1);
                                    db.addArea(area);
                                    context.sendBroadcast(new Intent(Splash.DATA_SAVED_BROADCAST));
                                }
                                Log.i("INCA","REGISTROS EN SERVER CON DATOS AREAS");
                                Status.setText("Recuperando Areas!");
                                MostrarAreas();
                            }else{
                                Log.i("INCA","REGISTROS EN SERVER VACIO AREAS");
                                MostrarAreas();
                                Status.setText("Recuperando Areas!");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarAreas");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void MostrarAreas(){
        Cursor cursor = db.getAreas();
        if (cursor.moveToFirst()) {
            do {
                Area area =new Area();
                area.setIdArea(cursor.getInt(cursor.getColumnIndex(ConstantesDb.AREA_CAMPO_ID)));
                area.setArea(cursor.getString(cursor.getColumnIndex(ConstantesDb.AREA_CAMPO_DESCRIPCION)));
                Log.i("RECUPERADO:","ID:"+area.getIdArea()+" "+area.getArea());
            } while (cursor.moveToNext());
        }
    }

    private void RecuperarRegistrosCopaOro(final String fecha,final Context context){
        Log.i("INCA","Entro a RecuperarServer CopaOro "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray Objetos=jsonResponse.getJSONArray("copaoro");
                                for(int i=0;i<Objetos.length();i++){
                                    JSONObject objeto= Objetos.getJSONObject(i);
                                    CopaOro copaOro=new CopaOro();
                                    copaOro.setIdCopaOro(objeto.getInt("idCopaOro"));
                                    copaOro.setDescripcionCopaOro(objeto.getString("DescripcionCopaOro"));
                                    copaOro.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    copaOro.setFechaUpdate(objeto.getString("fechaUpdate"));
                                    Estado estado=new Estado();
                                    estado.setIdEstado(objeto.getInt("estado_idEstado"));
                                    copaOro.setEstado(estado);
                                    copaOro.setSync(1);

                                    db.addCopaOro(copaOro);
                                    context.sendBroadcast(new Intent(Splash.DATA_SAVED_BROADCAST));
                                }

                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS COPA ORO");
                                Status.setText("Recuperando Copa Oro!");
                                MostrarCopaOro();
                            }else{
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER VACIO COPA ORO");
                                Status.setText("Recuperando Copa Oro!");
                                MostrarCopaOro();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarCopaOro");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void MostrarCopaOro(){
        Cursor cursor = db.getCopaOro();
        if (cursor.moveToFirst()) {
            do {
                CopaOro copaOro =new CopaOro();
                copaOro.setIdCopaOro(cursor.getInt(cursor.getColumnIndex(ConstantesDb.COPAORO_CAMPO_ID)));
                copaOro.setDescripcionCopaOro(cursor.getString(cursor.getColumnIndex(ConstantesDb.COPAORO_CAMPO_DESCRIPCION)));
                Log.i("RECUPERADO:","ID:"+copaOro.getIdCopaOro()+" "+copaOro.getDescripcionCopaOro());
            } while (cursor.moveToNext());
        }
    }

    private void RecuperarRegistrosCopaPlata(final String fecha,final Context context){
        Log.i("INCA","Entro a RecuperarServer CopaPlata "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray Objetos=jsonResponse.getJSONArray("copaplata");
                                for(int i=0;i<Objetos.length();i++){
                                    JSONObject objeto= Objetos.getJSONObject(i);
                                    CopaPlata copaplata=new CopaPlata();
                                    copaplata.setIdCopaPlata(objeto.getInt("idCopaPlata"));
                                    copaplata.setDescripcionCopaPlata(objeto.getString("DescripcionCopaPlata"));
                                    copaplata.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    copaplata.setFechaUpdate(objeto.getString("fechaUpdate"));
                                    Estado estado=new Estado();
                                    estado.setIdEstado(objeto.getInt("estado_idEstado"));
                                    copaplata.setEstado(estado);
                                    copaplata.setSync(1);

                                    db.addCopaPlata(copaplata);
                                    context.sendBroadcast(new Intent(Splash.DATA_SAVED_BROADCAST));
                                }
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS COPA PLATA");
                                Status.setText("Recuperando Copa Plata!");
                                MostrarCopaPlata();
                            }else{
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER VACIO COPA PLATA");
                                MostrarCopaPlata();
                                Status.setText("Recuperando Copa Plata!");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarCopaPlata");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void MostrarCopaPlata(){
        Cursor cursor = db.getCopaPlata();
        if (cursor.moveToFirst()) {
            do {
                CopaPlata copaPlata =new CopaPlata();
                copaPlata.setIdCopaPlata(cursor.getInt(cursor.getColumnIndex(ConstantesDb.COPAPLATA_CAMPO_ID)));
                copaPlata.setDescripcionCopaPlata(cursor.getString(cursor.getColumnIndex(ConstantesDb.COPAPLATA_CAMPO_DESCRIPCION)));
                Log.i("RECUPERADO:","ID:"+copaPlata.getIdCopaPlata()+" "+copaPlata.getDescripcionCopaPlata());
            } while (cursor.moveToNext());
        }
    }

    private void RecuperarRegistrosDepartamento(final String fecha,final Context context){
        Log.i("INCA","Entro a RecuperarServer Departamento "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray Objetos=jsonResponse.getJSONArray("departamento");
                                for(int i=0;i<Objetos.length();i++){
                                    JSONObject objeto= Objetos.getJSONObject(i);
                                    Departamento departamento=new Departamento();
                                    departamento.setIdDepartamento(objeto.getInt("idDepartamento"));
                                    departamento.setDepartamento(objeto.getString("departamento"));
                                    departamento.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    departamento.setSync(1);
                                    db.addDepartamento(departamento);
                                    context.sendBroadcast(new Intent(Splash.DATA_SAVED_BROADCAST));
                                }
                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS DEPARTAMENTO");
                                Status.setText("Recuperando Departamentos!");
                                MostrarDepartamento();

                            }else{
                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER VACIO DEPARTAMENTO");
                                MostrarDepartamento();
                                Status.setText("Recuperando Departamentos!");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarDepartamento");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void MostrarDepartamento(){
        Cursor cursor = db.getDepartamento();
        if (cursor.moveToFirst()) {
            do {
                Departamento departamento =new Departamento();
                departamento.setIdDepartamento(cursor.getInt(cursor.getColumnIndex(ConstantesDb.DEPARTAMENTO_CAMPO_ID)));
                departamento.setDepartamento(cursor.getString(cursor.getColumnIndex(ConstantesDb.DEPARTAMENTO_CAMPO_DEPARTAMENTO)));
                Log.i("RECUPERADO:","ID:"+departamento.getIdDepartamento()+" "+departamento.getDepartamento());
            } while (cursor.moveToNext());
        }
    }

    private void RecuperarRegistrosProvincia(final String fecha,final Context context){
        Log.i("INCA","Entro a RecuperarServer Provincia "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray Objetos=jsonResponse.getJSONArray("provincias");
                                for(int i=0;i<Objetos.length();i++){
                                    JSONObject objeto= Objetos.getJSONObject(i);
                                    Provincia provincia=new Provincia();
                                    provincia.setIdProvincia(objeto.getInt("idProvincia"));
                                    provincia.setProvincia(objeto.getString("provincia"));
                                    provincia.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    Departamento departamento=new Departamento();
                                    departamento.setIdDepartamento(objeto.getInt("departamento_idDepartamento"));
                                    provincia.setDepartamento(departamento);
                                    provincia.setSync(1);
                                    db.addProvincia(provincia);
                                    context.sendBroadcast(new Intent(Splash.DATA_SAVED_BROADCAST));
                                }
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS PROVINCIA");
                                Status.setText("Recuperando Provincias!");
                                MostrarProvincia();
                            }else{
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER VACIO PROVINCIA");
                                MostrarProvincia();
                                Status.setText("Recuperando Provincias!");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarProvincia");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void MostrarProvincia(){
        Cursor cursor = db.getProvincia();
        if (cursor.moveToFirst()) {
            do {
                Provincia provincia =new Provincia();
                provincia.setIdProvincia(cursor.getInt(cursor.getColumnIndex(ConstantesDb.PROVINCIA_CAMPO_ID)));
                provincia.setProvincia(cursor.getString(cursor.getColumnIndex(ConstantesDb.PROVINCIA_CAMPO_PROVINCIA)));
                Log.i("RECUPERADO:","ID:"+provincia.getIdProvincia()+" "+provincia.getProvincia());
            } while (cursor.moveToNext());
        }
    }

    private void RecuperarRegistrosDistrito(final String fecha,final Context context){
        Log.i("INCA","Entro a RecuperarServer Distrito "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray Objetos=jsonResponse.getJSONArray("distritos");
                                for(int i=0;i<Objetos.length();i++){
                                    JSONObject objeto= Objetos.getJSONObject(i);
                                    Distrito distrito=new Distrito();
                                    distrito.setIdDistrito(objeto.getInt("idDistrito"));
                                    distrito.setDistrito(objeto.getString("distrito"));
                                    distrito.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    Provincia provincia=new Provincia();
                                    provincia.setIdProvincia(objeto.getInt("provincia_idprovincia"));
                                    distrito.setProvincia(provincia);
                                    distrito.setSync(1);
                                    db.addDistrito(distrito);
                                    context.sendBroadcast(new Intent(Splash.DATA_SAVED_BROADCAST));
                                }

                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS DISTRITO");
                                Status.setText("Recuperando Distritos!");
                                MostrarDistrito();
                            }else{
                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER VACIO DISTRITO");
                                MostrarDistrito();
                                Status.setText("Recuperando Distritos!");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarDistrito");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void MostrarDistrito(){
        Cursor cursor = db.getDistrito();
        if (cursor.moveToFirst()) {
            do {
                Distrito distrito =new Distrito();
                distrito.setIdDistrito(cursor.getInt(cursor.getColumnIndex(ConstantesDb.DISTRITO_CAMPO_ID)));
                distrito.setDistrito(cursor.getString(cursor.getColumnIndex(ConstantesDb.DISTRITO_CAMPO_DISTRITO)));
                Log.i("RECUPERADO:","ID:"+distrito.getIdDistrito()+" "+distrito.getDistrito());
            } while (cursor.moveToNext());
        }
    }

    private void RecuperarRegistrosDiagnostico(final String fecha,final Context context){
        Log.i("INCA","Entro a RecuperarServer Diagnostico "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray Objetos=jsonResponse.getJSONArray("diagnostico");
                                for(int i=0;i<Objetos.length();i++){
                                    JSONObject objeto= Objetos.getJSONObject(i);
                                    Diagnostico diagnostico=new Diagnostico();
                                    diagnostico.setIdDiagnostico(objeto.getInt("idDiagnostico"));
                                    diagnostico.setCategoria(objeto.getInt("Categoria"));
                                    diagnostico.setIntercar(objeto.getInt("Intercar"));
                                    diagnostico.setPosicionSugerida1(objeto.getInt("PosicionSugerida1"));
                                    diagnostico.setPosicionSugerida2(objeto.getInt("PosicionSugerida2"));
                                    diagnostico.setPiernaDerecha(objeto.getInt("PiernaDerecha"));
                                    diagnostico.setPiernaIzquierda(objeto.getInt("PiernaIzquierda"));
                                    diagnostico.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    Persona persona = new Persona();
                                    persona.setIdPersona(objeto.getInt("persona_idPersona"));
                                    diagnostico.setPersona(persona);
                                    CopaOro copaOro=new CopaOro();
                                    copaOro.setIdCopaOro(objeto.getInt("copaoro_idCopaOro"));
                                    diagnostico.setCopaOro(copaOro);
                                    CopaPlata copaPlata=new CopaPlata();
                                    copaPlata.setIdCopaPlata(objeto.getInt("copaplata_idCopaPlata"));
                                    diagnostico.setCopaPlata(copaPlata);
                                    DiagnosticoPersona diagnosticoPersona= new DiagnosticoPersona();
                                    diagnosticoPersona.setIdDiagnosticoPersona(objeto.getInt("diagnosticopersona_idDiagnosticoPersona"));
                                    diagnostico.setDiagnosticoPersona(diagnosticoPersona);
                                    Ubigeo ubigeo=new Ubigeo();
                                    ubigeo.setIdUbigeo(objeto.getInt("ubigeo_idUbigeo"));
                                    diagnostico.setUbigeo(ubigeo);
                                    Modulo modulo=new Modulo();
                                    modulo.setIdModulo(objeto.getInt("modulos_idModulos"));
                                    diagnostico.setModulo(modulo);
                                    diagnostico.setSync(1);
                                    db.addDiagnostico(diagnostico);
                                    context.sendBroadcast(new Intent(Splash.DATA_SAVED_BROADCAST));
                                }

                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS DIAGNOSTICO");
                                Status.setText("Recuperando Diagnosticos!");
                                MostrarDiagnostico();
                            }else{
                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER VACIO DIAGNOSTICO");
                                Status.setText("Recuperando Diagnosticos!");
                                MostrarDiagnostico();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarDiagnostico");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void MostrarDiagnostico(){
        Cursor cursor = db.getDiagnostico();
        if (cursor.moveToFirst()) {
            do {
                Diagnostico diagnostico =new Diagnostico();
                diagnostico.setIdDiagnostico(cursor.getInt(cursor.getColumnIndex(ConstantesDb.DIAGNOSTICO_CAMPO_ID)));
                diagnostico.setSync(cursor.getInt(cursor.getColumnIndex(ConstantesDb.DIAGNOSTICO_CAMPO_SYNC)));
                Log.i("RECUPERADO:","ID:"+diagnostico.getIdDiagnostico()+" "+diagnostico.getSync());
            } while (cursor.moveToNext());
        }
    }

    private void RecuperarRegistrosDiagnosticoCampo(final String fecha,final Context context){
        Log.i("INCA","Entro a RecuperarServer Diagnostico  Campo "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray Objetos=jsonResponse.getJSONArray("diagnosticoCampo");
                                for(int i=0;i<Objetos.length();i++){
                                    JSONObject objeto= Objetos.getJSONObject(i);
                                    DiagnosticoCampo diagnosticoCampo=new DiagnosticoCampo();
                                    diagnosticoCampo.setIdDiagnosticoCampo(objeto.getInt("idDiagnosticoCampo"));
                                    diagnosticoCampo.setCampo(objeto.getString("DescripcionCampo"));
                                    diagnosticoCampo.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    diagnosticoCampo.setFechaUpdate(objeto.getString("fechaUpdate"));
                                    Estado estado=new Estado();
                                    estado.setIdEstado(objeto.getInt("estado_idEstado"));
                                    diagnosticoCampo.setEstado(estado);
                                    DiagnosticoGrupo diagnosticoGrupo= new DiagnosticoGrupo();
                                    diagnosticoGrupo.setIdDiagnosticoGrupo(objeto.getInt("diagnosticogrupo_iddiagnosticogrupo"));
                                    diagnosticoCampo.setDiagnosticoGrupo(diagnosticoGrupo);
                                    diagnosticoCampo.setSync(1);
                                    db.addDiagnosticoCampo(diagnosticoCampo);
                                    context.sendBroadcast(new Intent(Splash.DATA_SAVED_BROADCAST));
                                }

                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS DIAGNOSTICO Campo");
                                Status.setText("Recuperando Diagnostico Campo!");
                                MostrarDiagnosticoCampo();

                            }else{
                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER VACIO DIAGNOSTICO Campo");
                                Status.setText("Recuperando Diagnostico Campo!");
                                MostrarDiagnosticoCampo();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarDiagnosticoCampo");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void MostrarDiagnosticoCampo(){
        Cursor cursor = db.getDiagnosticoCampo();
        if (cursor.moveToFirst()) {
            do {
                DiagnosticoCampo diagnosticoCampo =new DiagnosticoCampo();
                diagnosticoCampo.setIdDiagnosticoCampo(cursor.getInt(cursor.getColumnIndex(ConstantesDb.DIAGNOSTICOCAMPO_CAMPO_ID)));
                diagnosticoCampo.setSync(cursor.getInt(cursor.getColumnIndex(ConstantesDb.DIAGNOSTICOCAMPO_CAMPO_SYNC)));
                Log.i("RECUPERADO:","ID:"+diagnosticoCampo.getIdDiagnosticoCampo()+" "+diagnosticoCampo.getSync());
            } while (cursor.moveToNext());
        }
    }

    private void RecuperarRegistrosDiagnosticoGrupo(final String fecha,final Context context){
        Log.i("INCA","Entro a RecuperarServer Diagnostico  Grupo "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray Objetos=jsonResponse.getJSONArray("diagnosticoGrupo");
                                for(int i=0;i<Objetos.length();i++){
                                    JSONObject objeto= Objetos.getJSONObject(i);
                                    DiagnosticoGrupo diagnosticoGrupo=new DiagnosticoGrupo();
                                    diagnosticoGrupo.setIdDiagnosticoGrupo(objeto.getInt("idDiagnosticoGrupo"));
                                    diagnosticoGrupo.setGrupo(objeto.getString("DescripcionGrupo"));
                                    diagnosticoGrupo.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    diagnosticoGrupo.setFechaUpdate(objeto.getString("fechaUpdate"));
                                    diagnosticoGrupo.setSync(1);
                                    db.addDiagnosticoGrupo(diagnosticoGrupo);
                                    context.sendBroadcast(new Intent(Splash.DATA_SAVED_BROADCAST));
                                }

                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS DIAGNOSTICO GRUPO");
                                Status.setText("Recuperando Diagnostico Grupo!");
                                MostrarDiagnosticoGrupo();

                            }else{
                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER VACIO DIAGNOSTICO GRUPO");
                                Status.setText("Recuperando Diagnostico Grupo!");
                                MostrarDiagnosticoGrupo();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarDiagnosticoGrupo");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void MostrarDiagnosticoGrupo(){
        Cursor cursor = db.getDiagnosticoGrupo();
        if (cursor.moveToFirst()) {
            do {
                DiagnosticoGrupo diagnosticoGrupo =new DiagnosticoGrupo();
                diagnosticoGrupo.setIdDiagnosticoGrupo(cursor.getInt(cursor.getColumnIndex(ConstantesDb.DIAGNOSTICOGRUPO_CAMPO_ID)));
                diagnosticoGrupo.setSync(cursor.getInt(cursor.getColumnIndex(ConstantesDb.DIAGNOSTICOGRUPO_CAMPO_DESCRIPCION)));
                Log.i("RECUPERADO:","ID:"+diagnosticoGrupo.getIdDiagnosticoGrupo()+" "+diagnosticoGrupo.getSync());
            } while (cursor.moveToNext());
        }
    }

    private void RecuperarRegistrosDiagnosticoPersona(final String fecha,final Context context){
        Log.i("INCA","Entro a RecuperarServer Diagnostico  Persona "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray Objetos=jsonResponse.getJSONArray("diagnosticoPersona");
                                for(int i=0;i<Objetos.length();i++){
                                    JSONObject objeto= Objetos.getJSONObject(i);
                                    DiagnosticoPersona diagnosticoPersona=new DiagnosticoPersona();
                                    diagnosticoPersona.setIdDiagnosticoPersona(objeto.getInt("idDiagnosticoPersona"));
                                    diagnosticoPersona.setNombres(objeto.getString("Nombres"));
                                    diagnosticoPersona.setApellidos(objeto.getString("Apellidos"));
                                    diagnosticoPersona.setAlias(objeto.getString("Alias"));
                                    diagnosticoPersona.setNumero(objeto.getInt("Numero"));
                                    diagnosticoPersona.setTelefono1(objeto.getInt("telefono1"));
                                    diagnosticoPersona.setTelefono2(objeto.getInt("telefono2"));
                                    diagnosticoPersona.setDomicilio(objeto.getString("Domicilio"));
                                    diagnosticoPersona.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    diagnosticoPersona.setFechaMigracion(objeto.getString("fechaMigracion"));
                                    diagnosticoPersona.setSync(1);
                                    db.addDiagnosticoPersona(diagnosticoPersona);
                                    context.sendBroadcast(new Intent(Splash.DATA_SAVED_BROADCAST));
                                }

                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS DIAGNOSTICO PERSONA");
                                Status.setText("Recuperando Diagnostico Persona!");
                                MostrarDiagnosticoPersona();
                            }else{
                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER VACIO DIAGNOSTICO PERSONA");
                                Status.setText("Recuperando Diagnostico Persona!");
                                MostrarDiagnosticoPersona();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarDiagnosticoPersona");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void MostrarDiagnosticoPersona(){
        Cursor cursor = db.getDiagnosticoPersona();
        if (cursor.moveToFirst()) {
            do {
                DiagnosticoPersona diagnosticoPersona =new DiagnosticoPersona();
                diagnosticoPersona.setIdDiagnosticoPersona(cursor.getInt(cursor.getColumnIndex(ConstantesDb.DIAGNOSTICOPERSONA_CAMPO_ID)));
                diagnosticoPersona.setNombres(cursor.getString(cursor.getColumnIndex(ConstantesDb.DIAGNOSTICOPERSONA_CAMPO_NOMBRES)));
                Log.i("RECUPERADO:","ID:"+diagnosticoPersona.getIdDiagnosticoPersona()+" "+diagnosticoPersona.getNombres());
            } while (cursor.moveToNext());
        }
    }

    private void RecuperarRegistrosDiagnosticoResultado(final String fecha,final Context context){
        Log.i("INCA","Entro a RecuperarServer Diagnostico  RESULTADO "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray Objetos=jsonResponse.getJSONArray("diagnosticoResultado");
                                for(int i=0;i<Objetos.length();i++){
                                    JSONObject objeto= Objetos.getJSONObject(i);
                                    DiagnosticoResultado diagnosticoResultado=new DiagnosticoResultado();
                                    diagnosticoResultado.setIdDiagnosticoResultado(objeto.getInt("idDiagnosticoResultado"));
                                    diagnosticoResultado.setResultado(objeto.getInt("resultado"));
                                    diagnosticoResultado.setFechaRegistro(objeto.getString("fechaRegistro"));

                                    Diagnostico diagnostico=new Diagnostico();
                                    diagnostico.setIdDiagnostico(objeto.getInt("diagnostico_idDiagnostico"));
                                    diagnosticoResultado.setDiagnostico(diagnostico);

                                    DiagnosticoGrupo diagnosticoGrupo=new DiagnosticoGrupo();
                                    diagnosticoGrupo.setIdDiagnosticoGrupo(objeto.getInt("diagnosticogrupo_idDiagnosticoGrupo"));
                                    diagnosticoResultado.setDiagnosticoGrupo(diagnosticoGrupo);

                                    DiagnosticoCampo diagnosticoCampo=new DiagnosticoCampo();
                                    diagnosticoCampo.setIdDiagnosticoCampo(objeto.getInt("diagnosticocampo_idDiagnosticoCampo"));
                                    diagnosticoResultado.setDiagnosticoCampo(diagnosticoCampo);

                                    diagnosticoResultado.setSync(1);

                                    db.addDiagnosticoResultado(diagnosticoResultado);
                                    context.sendBroadcast(new Intent(Splash.DATA_SAVED_BROADCAST));
                                }

                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS DIAGNOSTICO RESULTADO");
                                Status.setText("Recuperando Diagnostico Resultado!");
                                MostrarDiagnosticoResultado();

                            }else{
                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER VACIO DIAGNOSTICO RESULTADO");
                                Status.setText("Recuperando Diagnostico Resultado!");
                                MostrarDiagnosticoResultado();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarDiagnosticoResultado");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void MostrarDiagnosticoResultado(){
        Cursor cursor = db.getDiagnosticoResultado();
        if (cursor.moveToFirst()) {
            do {
                DiagnosticoResultado diagnosticoResultado =new DiagnosticoResultado();
                diagnosticoResultado.setIdDiagnosticoResultado(cursor.getInt(cursor.getColumnIndex(ConstantesDb.DIAGNOSTICORESULTADO_CAMPO_ID)));
                diagnosticoResultado.setSync(cursor.getInt(cursor.getColumnIndex(ConstantesDb.DIAGNOSTICORESULTADO_CAMPO_SYNC)));
                Log.i("RECUPERADO:","ID:"+diagnosticoResultado.getIdDiagnosticoResultado()+" "+diagnosticoResultado.getSync());
            } while (cursor.moveToNext());
        }
    }

    private void RecuperarRegistrosEstado(final String fecha,final Context context){
        Log.i("INCA","Entro a RecuperarServer Diagnostico  ESTADO "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray Objetos=jsonResponse.getJSONArray("estados");
                                for(int i=0;i<Objetos.length();i++){
                                    JSONObject objeto= Objetos.getJSONObject(i);
                                    Estado estado=new Estado();
                                    estado.setIdEstado(objeto.getInt("idEstado"));
                                    estado.setGrupo(objeto.getInt("grupo"));
                                    estado.setEstado(objeto.getString("DescripcionEstado"));
                                    estado.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    estado.setSync(1);
                                    db.addEstado(estado);
                                    context.sendBroadcast(new Intent(Splash.DATA_SAVED_BROADCAST));
                                }

                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS ESTADO");
                                Status.setText("Recuperando Estados!");
                                MostrarEstado();

                            }else{
                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER VACIO DESTADO");

                                Status.setText("Recuperando Estados!");
                                MostrarEstado();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarEstado");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void MostrarEstado(){
        Cursor cursor = db.getEstado();
        if (cursor.moveToFirst()) {
            do {
                Estado estado =new Estado();
                estado.setIdEstado(cursor.getInt(cursor.getColumnIndex(ConstantesDb.ESTADO_CAMPO_ID)));
                estado.setEstado(cursor.getString(cursor.getColumnIndex(ConstantesDb.ESTADO_CAMPO_DESCRIPCION)));
                Log.i("RECUPERADO:","ID:"+estado.getIdEstado()+" "+estado.getEstado());
            } while (cursor.moveToNext());
        }
    }

    private void RecuperarRegistrosModulo(final String fecha,final Context context){
        Log.i("INCA","Entro a RecuperarServer Diagnostico  MODULO "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray Objetos=jsonResponse.getJSONArray("modulos");
                                for(int i=0;i<Objetos.length();i++){
                                    JSONObject objeto= Objetos.getJSONObject(i);
                                    Modulo modulo=new Modulo();
                                    modulo.setIdModulo(objeto.getInt("idModulos"));
                                    modulo.setModulo(objeto.getString("DescripcionModulo"));
                                    modulo.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    modulo.setSync(1);
                                    db.addModulo(modulo);
                                    context.sendBroadcast(new Intent(Splash.DATA_SAVED_BROADCAST));
                                }

                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS MODULO");
                                Status.setText("Recuperando Modulos!");
                                MostrarModulo();

                            }else{
                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER VACIO MODULO");
                                Status.setText("Recuperando Modulos!");
                                MostrarModulo();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarModulo");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void MostrarModulo(){
        Cursor cursor = db.getModulo();
        if (cursor.moveToFirst()) {
            do {
                Modulo modulo =new Modulo();
                modulo.setIdModulo(cursor.getInt(cursor.getColumnIndex(ConstantesDb.MODULO_CAMPO_ID)));
                modulo.setModulo(cursor.getString(cursor.getColumnIndex(ConstantesDb.MODULO_CAMPO_MODULO)));
                Log.i("RECUPERADO:","ID:"+modulo.getIdModulo()+" "+modulo.getModulo());
            } while (cursor.moveToNext());
        }
    }

    private void RecuperarRegistrosPerfil(final String fecha,final Context context){
        Log.i("INCA","Entro a RecuperarServer Diagnostico  PERFIL "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray Objetos=jsonResponse.getJSONArray("perfiles");
                                for(int i=0;i<Objetos.length();i++){
                                    JSONObject objeto= Objetos.getJSONObject(i);
                                    Perfil perfil=new Perfil();
                                    perfil.setIdPerfil(objeto.getInt("idPerfil"));
                                    perfil.setPerfil(objeto.getString("DescripcionPerfil"));
                                    perfil.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    perfil.setFechaUpdate(objeto.getString("fechaUpdate"));
                                    Estado estado=new Estado();
                                    estado.setIdEstado(objeto.getInt("estado_idEstado"));
                                    perfil.setEstado(estado);
                                    perfil.setSync(1);
                                    db.addPerfil(perfil);
                                    context.sendBroadcast(new Intent(Splash.DATA_SAVED_BROADCAST));
                                }

                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS PERFIL");
                                Status.setText("Recuperando Perfiles!");
                                MostrarPerfil();

                            }else{
                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER VACIO PERFIL");
                                Status.setText("Recuperando Perfiles!");
                                MostrarPerfil();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarPerfil");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void MostrarPerfil(){
        Cursor cursor = db.getPerfil();
        if (cursor.moveToFirst()) {
            do {
                Perfil perfil =new Perfil();
                perfil.setIdPerfil(cursor.getInt(cursor.getColumnIndex(ConstantesDb.PERFIL_CAMPO_ID)));
                perfil.setPerfil(cursor.getString(cursor.getColumnIndex(ConstantesDb.PERFIL_CAMPO_DESCRIPCION)));
                Log.i("RECUPERADO:","ID:"+perfil.getIdPerfil()+" "+perfil.getPerfil());
            } while (cursor.moveToNext());
        }
    }

    private void RecuperarRegistrosPersona(final String fecha,final Context context){
        Log.i("INCA","Entro a RecuperarServer Diagnostico  PERSONA "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray Objetos=jsonResponse.getJSONArray("personas");
                                for(int i=0;i<Objetos.length();i++){
                                    JSONObject objeto= Objetos.getJSONObject(i);
                                    Persona persona=new Persona();
                                    persona.setIdPersona(objeto.getInt("idPersona"));
                                    persona.setNombres(objeto.getString("NombresPersona"));
                                    persona.setApellidos(objeto.getString("ApellidosPersona"));
                                    persona.setApodo(objeto.getString("Apodo"));
                                    persona.setFechaNacimiento(objeto.getString("fechaNacimiento"));
                                    persona.setNacionalidad(objeto.getString("Nacionalidad"));
                                    persona.setLugarResidencia(objeto.getString("LugarResidencia"));
                                    persona.setClubActual(objeto.getString("ClubActual"));
                                    persona.setLigaActual(objeto.getString("LigaActual"));
                                    persona.setCelular(objeto.getInt("Celular"));
                                    persona.setTelefonoFijo(objeto.getInt("TelefonoFijo"));
                                    persona.setBautizo(objeto.getInt("Bautizo"));
                                    persona.setComunion(objeto.getInt("Comunion"));
                                    persona.setConfirmacion(objeto.getInt("Confirmacion"));
                                    persona.setDni(objeto.getInt("Dni"));
                                    persona.setCorreo(objeto.getString("Correo"));
                                    persona.setApoderado(objeto.getString("ApoderadoNombre"));
                                    persona.setApoderadoTelefono(objeto.getInt("CelularApoderado"));
                                    persona.setCategoriaActual(objeto.getString("CategoriaActual"));
                                    persona.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    persona.setFechaUpdate(objeto.getString("fechaUpdate"));
                                    Estado estado=new Estado();
                                    estado.setIdEstado(objeto.getInt("estado_idEstado"));
                                    persona.setEstado(estado);
                                    persona.setSync(1);
                                    db.addPersona(persona);
                                    context.sendBroadcast(new Intent(Splash.DATA_SAVED_BROADCAST));
                                }

                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS PERSONAS");
                                Status.setText("Recuperando Personas!");
                                MostrarPersona();

                            }else{
                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER VACIO PERSONAS");
                                Status.setText("Recuperando Personas!");
                                MostrarPersona();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarPersona");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void MostrarPersona(){
        Cursor cursor = db.getPersona();
        if (cursor.moveToFirst()) {
            do {
                Persona persona =new Persona();
                persona.setIdPersona(cursor.getInt(cursor.getColumnIndex(ConstantesDb.PERSONA_CAMPO_ID)));
                persona.setNombres(cursor.getString(cursor.getColumnIndex(ConstantesDb.PERSONA_CAMPO_NOMBRES)));
                Log.i("RECUPERADO:","ID:"+persona.getIdPersona()+" "+persona.getNombres());
            } while (cursor.moveToNext());
        }
    }

    private void RecuperarRegistrosPosicion(final String fecha,final Context context){
        Log.i("INCA","Entro a RecuperarServer Diagnostico  POSICIONES "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray Objetos=jsonResponse.getJSONArray("posiciones");
                                for(int i=0;i<Objetos.length();i++){
                                    JSONObject objeto= Objetos.getJSONObject(i);
                                    Posicion posicion=new Posicion();
                                    posicion.setIdPosicion(objeto.getInt("idPosicion"));
                                    posicion.setCodigo(objeto.getString("Codigo"));
                                    posicion.setPosicion(objeto.getString("DescripcionPosicion"));
                                    posicion.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    posicion.setFechaUpdate(objeto.getString("fechaUpdate"));
                                    Estado estado=new Estado();
                                    estado.setIdEstado(objeto.getInt("estado_idEstado"));
                                    posicion.setEstado(estado);
                                    posicion.setSync(1);
                                    db.addPosicion(posicion);
                                    context.sendBroadcast(new Intent(Splash.DATA_SAVED_BROADCAST));
                                }

                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS POSICIONES");
                                Status.setText("Recuperando Posiciones!");
                                MostrarPosicion();


                            }else{
                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER VACIO POSICIONES");
                                Status.setText("Recuperando Posiciones!");
                                MostrarPosicion();


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarPosicion");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void MostrarPosicion(){
        Cursor cursor = db.getPosicion();
        if (cursor.moveToFirst()) {
            do {
                Posicion posicion =new Posicion();
                posicion.setIdPosicion(cursor.getInt(cursor.getColumnIndex(ConstantesDb.POSICION_CAMPO_ID)));
                posicion.setPosicion(cursor.getString(cursor.getColumnIndex(ConstantesDb.POSICION_CAMPO_POSICION)));
                Log.i("RECUPERADO:","ID:"+posicion.getIdPosicion()+" "+posicion.getPosicion());
            } while (cursor.moveToNext());
        }
    }

    private void RecuperarRegistrosUbigeo(final String fecha,final Context context){
        Log.i("INCA","Entro a RecuperarServer  UBIGEO "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray Objetos=jsonResponse.getJSONArray("ubigeo");
                                for(int i=0;i<Objetos.length();i++){
                                    JSONObject objeto= Objetos.getJSONObject(i);
                                    Ubigeo ubigeo=new Ubigeo();
                                    ubigeo.setIdUbigeo(objeto.getInt("idUbigeo"));
                                    ubigeo.setDescripcion(objeto.getString("DescripcionUbigeo"));
                                    ubigeo.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    Usuario usuario=new Usuario();
                                    usuario.setIdUsuario(objeto.getInt("usuario_idUsuario"));
                                    ubigeo.setUsuario(usuario);
                                    Departamento departamento=new Departamento();
                                    departamento.setIdDepartamento(objeto.getInt("departamento_idDepartamento"));
                                    ubigeo.setDepartamento(departamento);
                                    Provincia provincia=new Provincia();
                                    provincia.setIdProvincia(objeto.getInt("provincia_idProvincia"));
                                    ubigeo.setProvincia(provincia);
                                    Distrito distrito=new Distrito();
                                    distrito.setIdDistrito(objeto.getInt("distrito_idDistrito"));
                                    ubigeo.setDistrito(distrito);
                                    Modulo modulo=new Modulo();
                                    modulo.setIdModulo(objeto.getInt("modulos_idmodulos"));
                                    ubigeo.setModulo(modulo);
                                    ubigeo.setSync(1);
                                    db.addUbigeo(ubigeo);
                                    context.sendBroadcast(new Intent(Splash.DATA_SAVED_BROADCAST));
                                }

                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS UBIGEO");
                                Status.setText("Recuperando Posiciones!");
                                MostrarUbigeo();

                            }else{
                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER VACIO UBIGEO");
                                Status.setText("Recuperando Posiciones!");
                                MostrarUbigeo();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarUbigeo");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void MostrarUbigeo(){
        Cursor cursor = db.getUbigeo();
        if (cursor.moveToFirst()) {
            do {
                Ubigeo ubigeo =new Ubigeo();
                ubigeo.setIdUbigeo(cursor.getInt(cursor.getColumnIndex(ConstantesDb.UBIGEO_CAMPO_ID)));
                ubigeo.setDescripcion(cursor.getString(cursor.getColumnIndex(ConstantesDb.UBIGEO_CAMPO_UBIGEO)));
                Log.i("RECUPERADO:","ID:"+ubigeo.getIdUbigeo()+" "+ubigeo.getDescripcion());
            } while (cursor.moveToNext());
        }
    }

    private void RecuperarRegistrosUsuario(final String fecha,final Context context){
        Log.i("INCA","Entro a RecuperarServer  USUARIO "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray Objetos=jsonResponse.getJSONArray("usuarios");
                                for(int i=0;i<Objetos.length();i++){
                                    JSONObject objeto= Objetos.getJSONObject(i);
                                    Usuario usuario=new Usuario();
                                    usuario.setIdUsuario(objeto.getInt("idUsuario"));
                                    usuario.setUsuario(objeto.getString("usuario"));
                                    usuario.setPassword(objeto.getString("password"));
                                    usuario.setNombres(objeto.getString("NombreUsuario"));
                                    usuario.setApellidos(objeto.getString("ApellidosUsuario"));
                                    usuario.setDni(objeto.getInt("Dni"));
                                    usuario.setCargo(objeto.getString("Cargo"));
                                    usuario.setCorreo(objeto.getString("Correo"));
                                    usuario.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    usuario.setFechaUpdate(objeto.getString("fechaUpdate"));
                                    usuario.setImagen(objeto.getString("imagen"));
                                    Perfil perfil =new Perfil();
                                    perfil.setIdPerfil(objeto.getInt("perfil_idPerfil"));
                                    usuario.setPerfil(perfil);
                                    Area area=new Area();
                                    area.setIdArea(objeto.getInt("area_idArea"));
                                    usuario.setArea(area);
                                    Estado estado=new Estado();
                                    estado.setIdEstado(objeto.getInt("estado_idEstado"));
                                    usuario.setEstado(estado);
                                    usuario.setSync(1);

                                    db.addUsuario(usuario);
                                    context.sendBroadcast(new Intent(Splash.DATA_SAVED_BROADCAST));
                                }

                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS USUARIO");
                                Status.setText("Recuperando Posiciones!");
                                MostrarUsuario();
                                Status.setText("Sistema Actualizado con Exito!");
                                mover();
                            }else{
                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER VACIO USUARIO");
                                Status.setText("Recuperando Posiciones!");
                                MostrarUsuario();
                                Status.setText("Sistema Actualizado con Exito!");
                                mover();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarUsuario");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void MostrarUsuario(){
        Cursor cursor = db.getUsuario();
        if (cursor.moveToFirst()) {
            do {
                Usuario usuario =new Usuario();
                usuario.setIdUsuario(cursor.getInt(cursor.getColumnIndex(ConstantesDb.USUARIO_CAMPO_ID)));
                usuario.setUsuario(cursor.getString(cursor.getColumnIndex(ConstantesDb.USUARIO_CAMPO_USUARIO)));
                Log.i("RECUPERADO:","ID:"+usuario.getIdUsuario()+" "+usuario.getUsuario());
            } while (cursor.moveToNext());
        }
    }

    void mover(){
        Intent intent = new Intent(Splash.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("INCA","Se quito el registro del evento!");
        unregisterReceiver(Sincronizador);

    }
}
