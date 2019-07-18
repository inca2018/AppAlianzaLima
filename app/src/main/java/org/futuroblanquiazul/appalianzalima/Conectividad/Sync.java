package org.futuroblanquiazul.appalianzalima.Conectividad;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;

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
import org.futuroblanquiazul.appalianzalima.Utils.Constantes;
import org.futuroblanquiazul.appalianzalima.ui.Inicio.Splash;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Sync {
    private Context context;
    private DatabaseHelper db;

    public Sync(Context context, DatabaseHelper db){
     this.context=context;
     this.db=db;
    }

    public String SincronizarArea(){
        final String fecha=String.valueOf(this.db.RecuperarFechaArea());
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
                                Log.i("INCA","ENVIAR REGISTROS EN SERVER CON DATOS AREAS");
                                MostrarAreas();
                            }else{
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
        return "Recuperando Areas!";
    }
    public void MostrarAreas(){
        Cursor cursor = this.db.getAreas();
        if (cursor.moveToFirst()) {
            do {
                Area area =new Area();
                area.setIdArea(cursor.getInt(cursor.getColumnIndex(ConstantesDb.AREA_CAMPO_ID)));
                area.setArea(cursor.getString(cursor.getColumnIndex(ConstantesDb.AREA_CAMPO_DESCRIPCION)));
                Log.i("RECUPERADO:","ID:"+area.getIdArea()+" "+area.getArea());
            } while (cursor.moveToNext());
        }
    }
}
