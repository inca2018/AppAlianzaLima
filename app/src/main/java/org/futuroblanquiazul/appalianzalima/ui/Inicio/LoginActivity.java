package org.futuroblanquiazul.appalianzalima.ui.Inicio;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.futuroblanquiazul.appalianzalima.Data.Conexion.Local.DatabaseHelper;
import org.futuroblanquiazul.appalianzalima.Data.Conexion.Remoto.VolleySingleton;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Usuario;
import org.futuroblanquiazul.appalianzalima.R;
import org.futuroblanquiazul.appalianzalima.Utils.Constantes;
import org.futuroblanquiazul.appalianzalima.Utils.Decrypto;
import org.futuroblanquiazul.appalianzalima.ui.Menu.MenuActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Context context;
    TextView tv_ingresar;
    EditText et_usuario;
    EditText et_password;
    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        db = new DatabaseHelper(this);

        tv_ingresar = findViewById(R.id.tv_ingresar);
        et_usuario  = findViewById(R.id.et_usuario);
        et_password = findViewById(R.id.et_password);

        tv_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    VerificarConexion();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void VerificarConexion() throws Exception {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null ) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                ValidarSesionRemoto();
            }else{
                ValidarSesionLocal();
            }
        }else{
            ValidarSesionLocal();
        }
    }
    public void ValidarSesionLocal() throws Exception {
        String Validacion=VerificarCampos();
        if(Validacion.length()!=0){
            Toast.makeText(context, "Complete los Campos:\n"+Validacion, Toast.LENGTH_SHORT).show();
        }else{
            Usuario usuario=db.RecuperarDatosUsuario(et_usuario.getText().toString().trim());
            Decrypto decrypto = new Decrypto("D4:6E:AC:3F:F0:BE");

            if(et_usuario.getText().toString().equalsIgnoreCase(usuario.getUsuario())){
                String passwordRecuperado = decrypto.decrypt(usuario.getPassword());
                if(et_password.getText().toString().equalsIgnoreCase(passwordRecuperado)){
                    SharedPreferences pref = getSharedPreferences("SesionAlianza", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putInt("idUsuario",usuario.getIdUsuario());
                    editor.putString("usuario",  usuario.getUsuario());
                    editor.putString("nombres",  usuario.getNombres());
                    editor.putString("apellidos",  usuario.getApellidos());
                    editor.putString("imagen",  usuario.getImagen());
                    editor.putInt("idPerfil",  usuario.getPerfil().getIdPerfil());
                    editor.putString("perfil",  usuario.getPerfil().getPerfil());
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    LoginActivity.this.startActivity(intent);
                    Toast.makeText(context, "Bienvenido "+usuario.getNombres()+" "+usuario.getApellidos(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Contraseña equivocada!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(context, "Usuario no Encontrado!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void ValidarSesionRemoto(){
        String Validacion=VerificarCampos();
        if(Validacion.length()!=0){
            Toast.makeText(context, "Complete los Campos:\n"+Validacion, Toast.LENGTH_SHORT).show();
        }else{

            VerificarUsuarioServidor(et_usuario.getText().toString(),et_password.getText().toString(),context);
        }
    }
    private void VerificarUsuarioServidor(final String usuario,final String password,final Context context){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                SharedPreferences pref =  getSharedPreferences("SesionAlianza", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putInt("idUsuario", jsonResponse.getInt("idUsuario"));
                                editor.putString("usuario",  jsonResponse.getString("usuario"));
                                editor.putString("nombres",  jsonResponse.getString("nombres"));
                                editor.putString("apellidos",  jsonResponse.getString("apellidos"));
                                editor.putString("imagen",  jsonResponse.getString("imagen"));
                                editor.putInt("idPerfil",  jsonResponse.getInt("idPerfil"));
                                editor.putString("perfil",  jsonResponse.getString("perfil"));
                                editor.commit();

                                String Mensaje=jsonResponse.getString("mensaje");
                                Toast.makeText(context, Mensaje, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                                LoginActivity.this.startActivity(intent);
                            }else{
                                String Mensaje=jsonResponse.getString("mensaje");
                                Toast.makeText(context, Mensaje, Toast.LENGTH_SHORT).show();
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
                params.put("operacion", "VerificarLogin");
                params.put("usuario", usuario);
                params.put("password", password);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void onBackPressed() {
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setTitle("SALIR")
                .setMessage("¿Desea Cerrar Aplicación?")
                .setPositiveButton("SI",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences pref = getSharedPreferences("SesionAlianza", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.clear();
                                editor.commit();

                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                finish();
                            }
                        })
                .setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
        builder.show();
    }
    public String VerificarCampos(){
        String mensaje="";
        String usuario = et_usuario.getText().toString().trim();
        String pass = et_password.getText().toString().trim();

        mensaje=(usuario.length()==0)?(mensaje+"- Ingrese Usuario.\n"):(mensaje+"");
        mensaje=(pass.length()==0)?(mensaje+"- Ingrese Contraseña.\n"):(mensaje+"");
        //mensaje=(usuario.length()<6)?(mensaje+"- Ingrese Usuario mayor o igual a 6 caracteres.\n"):(mensaje+"");
        mensaje=(pass.length()<6)?(mensaje+"- Ingrese Contraseña mayor o igual a a 6 caracteres.\n"):(mensaje+"");
        return  mensaje;
    }
}
