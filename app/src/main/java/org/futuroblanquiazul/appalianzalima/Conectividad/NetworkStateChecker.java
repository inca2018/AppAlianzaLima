package org.futuroblanquiazul.appalianzalima.Conectividad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.futuroblanquiazul.appalianzalima.Data.Conexion.Local.ConstantesDb;
import org.futuroblanquiazul.appalianzalima.Data.Conexion.Local.DatabaseHelper;
import org.futuroblanquiazul.appalianzalima.Data.Conexion.Remoto.VolleySingleton;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Area;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Estado;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Name;
import org.futuroblanquiazul.appalianzalima.Utils.Constantes;
import org.futuroblanquiazul.appalianzalima.ui.Inicio.MainActivity;
import org.futuroblanquiazul.appalianzalima.ui.Inicio.Splash;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NetworkStateChecker extends BroadcastReceiver {
    //context and database helper object
    private Context context;
    private DatabaseHelper db;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        db = new DatabaseHelper(context);

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        //if there is a network
        if (activeNetwork != null ) {
            //if connected to wifi or mobile data plan
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {

                Log.e("INCA","Detecto Activo la Conexion!");
                //String Fecha=db.RecuperarFechaLimite();
                //RecuperarRegistrosServer(Fecha);
                String FechaArea=String.valueOf(db.RecuperarFechaArea());
                RecuperarRegistrosAreaServer(FechaArea);

            }
        }

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
    /*
     * method taking two arguments
     * name that is to be saved and id of the name from SQLite
     * if the name is successfully sent
     * we will update the status as synced in SQLite
     * */
    /* private void RecuperarRegistrosServer(final String fecha){
        Log.e("INCA","Entro a RecuperarServer "+fecha);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray names=jsonResponse.getJSONArray("names");
                                for(int i=0;i<names.length();i++){
                                    JSONObject objeto= names.getJSONObject(i);
                                    Name temp=new Name(objeto.getString("name"),1);
                                    db.addName(temp.getName(),temp.getStatus());
                                    context.sendBroadcast(new Intent(MainActivity.DATA_SAVED_BROADCAST));
                                }
                                //EnviarRegistros();
                                Log.e("INCA","ENVIAR REGISTROS EN SERVER CON DATOS");
                            }else{
                                //EnviarRegistros();
                                Log.e("INCA","ENVIAR REGISTROS EN SERVER VACIO");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "RecuperarInformacion");
                params.put("fecha", fecha);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }*/

    private void RecuperarRegistrosAreaServer(final String fecha){
        Log.i("INCA","Entro a RecuperarServer Area "+fecha);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_SYNC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                JSONArray names=jsonResponse.getJSONArray("areas");
                                for(int i=0;i<names.length();i++){
                                    JSONObject objeto= names.getJSONObject(i);
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

                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS AREAS");
                                MostrarAreas();
                            }else{
                                //EnviarRegistros();
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER VACIO AREAS");
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
    // FUNCIONES PARA ENVIAR DATOS DE OFF AL SERVER
   /* private void EnviarRegistros(){
        Log.e("INCA","EnviarRegistros al server");
        //getting all the unsynced names
        Cursor cursor = db.getUnsyncedNames();
        if (cursor.moveToFirst()) {
            do {
                //calling the method to save the unsynced name to MySQL
                saveName(
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME))
                );
            } while (cursor.moveToNext());
        }
    }
    private void saveName(final int id, final String name) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.getBoolean("success")) {
                                //updating the status in sqlite
                                db.updateNameStatus(id, MainActivity.NAME_SYNCED_WITH_SERVER);
                                //sending the broadcast to refresh the list
                                context.sendBroadcast(new Intent(MainActivity.DATA_SAVED_BROADCAST));
                                Log.e("INCA","Guardo registro en server");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "InsertarName");
                params.put("name", name);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }*/
}


