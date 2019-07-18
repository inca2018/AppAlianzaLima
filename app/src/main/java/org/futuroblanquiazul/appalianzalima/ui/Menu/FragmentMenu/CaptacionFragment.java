package org.futuroblanquiazul.appalianzalima.ui.Menu.FragmentMenu;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.futuroblanquiazul.appalianzalima.Data.Conexion.Local.DatabaseHelper;
import org.futuroblanquiazul.appalianzalima.Data.Conexion.Remoto.VolleySingleton;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Modulo;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Ubigeo;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Usuario;
import org.futuroblanquiazul.appalianzalima.R;
import org.futuroblanquiazul.appalianzalima.Utils.Constantes;
import org.futuroblanquiazul.appalianzalima.ui.Captacion.UbigeoActivity;
import org.futuroblanquiazul.appalianzalima.ui.Inicio.LoginActivity;
import org.futuroblanquiazul.appalianzalima.ui.Menu.MenuActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class CaptacionFragment extends Fragment {
    public Context mContext;
    public Button accion1, accion2, accion3, accion5;
    public TextView texto_ubigeo_Capta, texto_ubigeo_Capta_masivo, texto_ubigeo_barrio;
    public ImageView imagen_ubigeo_Capta, imagen_ubigeo_Capta_masivo, imagen_ubigeo_barrio;

    public static final String DATA_SAVED_BROADCAST = "org.futuroblanquiazul.datasaved2";
    private BroadcastReceiver Sincronizador;
    public static final int CODIGO_CAPTACION_INDIVIDUAL=1;
    public static final int CODIGO_CAPTACION_MASIVA=2;

    //database helper object
    private DatabaseHelper db;

    public CaptacionFragment() {
        // Required empty public constructor
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        db = new DatabaseHelper(mContext);
        Sincronizador=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                //if there is a network
                SharedPreferences pref = getActivity().getSharedPreferences("SesionAlianza", getActivity().MODE_PRIVATE);
                int idUsuario=pref.getInt("idUsuario",-1);
                if (activeNetwork != null ) {
                    //if connected to wifi or mobile data plan
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        RecuperarUbigeoRemoto(idUsuario,mContext);
                        Log.e("INCA-CAPTACION","Detecto Activo la Conexion!");
                    }else{
                        RecuperarUbigeoLocal(idUsuario,mContext);
                        Log.e("INCA-CAPTACION","Detecto Inactivo la Conexion!");
                    }
                }else{
                    RecuperarUbigeoLocal(idUsuario,mContext);
                    Log.e("INCA-CAPTACION","Detecto Inactivo la Conexion!");
                }
            }
        };
        getActivity().registerReceiver(Sincronizador, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_captacion, container, false);
        texto_ubigeo_Capta=v.findViewById(R.id.texto_ubigeo);
        imagen_ubigeo_Capta=v.findViewById(R.id.icon_ubigeo);
        texto_ubigeo_Capta_masivo=v.findViewById(R.id.texto_ubigeo_masivo);
        imagen_ubigeo_Capta_masivo=v.findViewById(R.id.icon_ubigeo_masivo);
        texto_ubigeo_barrio=v.findViewById(R.id.texto_ubigeo_barrio);
        imagen_ubigeo_barrio=v.findViewById(R.id.icon_ubigeo_barrio);
        accion1=v.findViewById(R.id.accion_1);
        accion2=v.findViewById(R.id.accion_2);
        accion3=v.findViewById(R.id.accion_3);
        accion5=v.findViewById(R.id.accion_5);
        Acciones();
        return v;
    }
    public void RecuperarUbigeoRemoto(final int id_Usuario,final Context context) {
        final String idUsuario = String.valueOf(id_Usuario).trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_CAPTACION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                    String UbigeoCaptacion=jsonResponse.getString("moduloCaptacion");
                                    String UbigeoCaptacionMasiva=jsonResponse.getString("moduloCaptacionMasiva");

                                    SharedPreferences pref =  getActivity().getSharedPreferences("SesionAlianza", getActivity().MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();

                                    if(UbigeoCaptacion==null || UbigeoCaptacion.equalsIgnoreCase("null")){
                                        SpannableString mitextoU = new SpannableString("SELECCIONE UBICACION DE TRABAJO");
                                        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
                                        texto_ubigeo_Capta.setText(mitextoU);
                                        imagen_ubigeo_Capta.setImageResource(R.mipmap.icon_next);
                                    }else{
                                        editor.putInt("CapIdDepartamento",jsonResponse.getInt("CapIdDepartamento"));
                                        editor.putInt("CapIdProvincia",jsonResponse.getInt("CapIdProvincia"));
                                        editor.putInt("CapIdDistrito",jsonResponse.getInt("CapIdDistrito"));
                                        editor.putString("UbigeoCaptacion",UbigeoCaptacion);

                                        SpannableString mitextoU = new SpannableString("UBICACION: "+UbigeoCaptacion);
                                        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
                                        texto_ubigeo_Capta.setText(mitextoU);
                                        imagen_ubigeo_Capta.setImageResource(R.mipmap.icon_update);
                                    }

                                    if(UbigeoCaptacionMasiva==null || UbigeoCaptacionMasiva.equalsIgnoreCase("null")){
                                        SpannableString mitextoU = new SpannableString("SELECCIONE UBICACION DE TRABAJO");
                                        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
                                        texto_ubigeo_Capta_masivo.setText(mitextoU);
                                        imagen_ubigeo_Capta_masivo.setImageResource(R.mipmap.icon_next);
                                    }else{
                                        editor.putInt("CapMIdDepartamento",jsonResponse.getInt("CapMIdDepartamento"));
                                        editor.putInt("CapMIdProvincia",jsonResponse.getInt("CapMIdProvincia"));
                                        editor.putInt("CapMIdDistrito",jsonResponse.getInt("CapMIdDistrito"));
                                        editor.putString("UbigeoCaptacionMasiva",UbigeoCaptacionMasiva);

                                        SpannableString mitextoU = new SpannableString("UBICACION: "+UbigeoCaptacionMasiva);
                                        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
                                        texto_ubigeo_Capta_masivo.setText(mitextoU);
                                        imagen_ubigeo_Capta_masivo.setImageResource(R.mipmap.icon_update);
                                    }
                            }else{
                                RecuperarUbigeoLocal(id_Usuario,context);
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
                params.put("idUsuario", idUsuario);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void RecuperarUbigeoLocal(final int id_Usuario,final Context context){
        Usuario usuario=new Usuario();
        usuario.setIdUsuario(id_Usuario);
        Modulo moduloCap=new Modulo();
        moduloCap.setIdModulo(CODIGO_CAPTACION_INDIVIDUAL);
        Modulo moduloCapM=new Modulo();
        moduloCapM.setIdModulo(CODIGO_CAPTACION_MASIVA);

        Ubigeo ubigeoCap=db.RecuperarUbigeo(usuario,moduloCap);
        Ubigeo ubigeoCapM=db.RecuperarUbigeo(usuario,moduloCapM);


        SharedPreferences pref =  getActivity().getSharedPreferences("SesionAlianza", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        if(ubigeoCap.getDepartamento()==null){
            SpannableString mitextoU = new SpannableString("SELECCIONE UBICACION DE TRABAJO");
            mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
            texto_ubigeo_Capta.setText(mitextoU);
            imagen_ubigeo_Capta.setImageResource(R.mipmap.icon_next);
        }else{
            editor.putInt("CapIdDepartamento", ubigeoCap.getDepartamento().getIdDepartamento());
            editor.putInt("CapIdProvincia",ubigeoCap.getProvincia().getIdProvincia());
            editor.putInt("CapIdDistrito",ubigeoCap.getDistrito().getIdDistrito());
            editor.putString("UbigeoCaptacion",ubigeoCap.getDepartamento().getDepartamento()+"/"+ubigeoCap.getProvincia().getProvincia()+"/"+ubigeoCap.getDistrito().getDistrito());

            SpannableString mitextoU = new SpannableString("UBICACION: "+ubigeoCap.getDepartamento().getDepartamento()+"/"+ubigeoCap.getProvincia().getProvincia()+"/"+ubigeoCap.getDistrito().getDistrito());
            mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
            texto_ubigeo_Capta.setText(mitextoU);
            imagen_ubigeo_Capta.setImageResource(R.mipmap.icon_update);
        }
        if(ubigeoCapM.getDepartamento()==null){
            SpannableString mitextoU = new SpannableString("SELECCIONE UBICACION DE TRABAJO");
            mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
            texto_ubigeo_Capta_masivo.setText(mitextoU);
            imagen_ubigeo_Capta_masivo.setImageResource(R.mipmap.icon_next);
        }else{
            editor.putInt("CapMIdDepartamento",ubigeoCapM.getDepartamento().getIdDepartamento());
            editor.putInt("CapMIdProvincia",ubigeoCapM.getProvincia().getIdProvincia());
            editor.putInt("CapMIdDistrito",ubigeoCapM.getDistrito().getIdDistrito());
            editor.putString("UbigeoCaptacionMasiva",ubigeoCapM.getDepartamento().getDepartamento()+"/"+ubigeoCapM.getProvincia().getProvincia()+"/"+ubigeoCapM.getDistrito().getDistrito());

            SpannableString mitextoU = new SpannableString("UBICACION: "+ubigeoCapM.getDepartamento().getDepartamento()+"/"+ubigeoCapM.getProvincia().getProvincia()+"/"+ubigeoCapM.getDistrito().getDistrito());
            mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
            texto_ubigeo_Capta_masivo.setText(mitextoU);
            imagen_ubigeo_Capta_masivo.setImageResource(R.mipmap.icon_update);
        }
    }

    private void Acciones() {
        /* accion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GestionUbigeo.CAPTACION_UBIGEO.isEstado()==true){

                    Usuario.SESION_ACTUAL.setPersona_metodologia(null);
                    Usuario.SESION_ACTUAL.setPersona_barrio(null);
                    Persona.PERSONA_TEMP.setId(0);
                    Usuario.SESION_ACTUAL.setPersona_metodologia_pruebas(null);
                    Usuario.SESION_ACTUAL.setPersona_metodologia(null);
                    Usuario.SESION_ACTUAL.setPersona_fase_pruebas(null);

                    Intent intent= new Intent(mContext,PruebaDiagnosticoActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(mContext, "Seleccione Ubicación de Trabajo", Toast.LENGTH_SHORT).show();
                }
            }
        });
        accion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(GestionUbigeo.CAPTACION_UBIGEO.isEstado()==true){
                    Usuario.SESION_ACTUAL.setPersona_metodologia(null);
                    Usuario.SESION_ACTUAL.setPersona_barrio(null);
                    Persona.PERSONA_TEMP.setId(0);
                    Usuario.SESION_ACTUAL.setPersona_metodologia_pruebas(null);
                    Usuario.SESION_ACTUAL.setPersona_metodologia(null);
                    Usuario.SESION_ACTUAL.setPersona_fase_pruebas(null);

                    Intent intent= new Intent(mContext,ListaPersonaSeguimientoActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(mContext, "Seleccione Ubicación de Trabajo", Toast.LENGTH_SHORT).show();
                }
            }
        });
        accion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(GestionUbigeo.CAPTACION_UBIGEO_MASIVO.isEstado()==true){
                    Usuario.SESION_ACTUAL.setPersona_metodologia(null);
                    Usuario.SESION_ACTUAL.setPersona_barrio(null);
                    Persona.PERSONA_TEMP.setId(0);
                    Usuario.SESION_ACTUAL.setPersona_metodologia_pruebas(null);
                    Usuario.SESION_ACTUAL.setPersona_metodologia(null);
                    Usuario.SESION_ACTUAL.setPersona_fase_pruebas(null);

                    Intent intent= new Intent(mContext,ListaMasivosActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(mContext, "Seleccione Ubicación de Trabajo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        accion5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario.SESION_ACTUAL.setPersona_metodologia(null);
                Usuario.SESION_ACTUAL.setPersona_barrio(null);
                Persona.PERSONA_TEMP.setId(0);
                Usuario.SESION_ACTUAL.setPersona_metodologia_pruebas(null);
                Usuario.SESION_ACTUAL.setPersona_metodologia(null);
                Usuario.SESION_ACTUAL.setPersona_fase_pruebas(null);
                Intent intent= new Intent(mContext, BarrioIntimoActivity.class);
                startActivity(intent);
            }
        });*/

        imagen_ubigeo_Capta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent= new Intent(mContext,UbigeoActivity.class);
                    intent.putExtra("TYPE","CAPTACION");
                    startActivity(intent);
            }
        });
        imagen_ubigeo_Capta_masivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(mContext,UbigeoActivity.class);
                intent.putExtra("TYPE","CAPTACION_MASIVA");
                startActivity(intent);
            }
        });


        /*imagen_ubigeo_barrio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GestionUbigeo.CAPTACION_UBIGEO_BARRIO.isEstado()==true){
                    Intent intent= new Intent(mContext,UbigeoActivity.class);
                    intent.putExtra("TYPE","UPDATE");
                    intent.putExtra("MODULO","3");
                    startActivity(intent);
                }else{
                    Intent intent= new Intent(mContext,UbigeoActivity.class);
                    intent.putExtra("TYPE","NEW");
                    intent.putExtra("MODULO","3");
                    startActivity(intent);
                }
            }
        });*/

    }
}
