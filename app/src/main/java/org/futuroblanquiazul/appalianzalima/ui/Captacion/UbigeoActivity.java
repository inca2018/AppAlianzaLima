package org.futuroblanquiazul.appalianzalima.ui.Captacion;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.futuroblanquiazul.appalianzalima.Data.Conexion.Local.ConstantesDb;
import org.futuroblanquiazul.appalianzalima.Data.Conexion.Local.DatabaseHelper;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Departamento;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Distrito;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Name;
import org.futuroblanquiazul.appalianzalima.Data.Modelos.Provincia;
import org.futuroblanquiazul.appalianzalima.R;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UbigeoActivity extends AppCompatActivity {
    Spinner departamento,provincias,distritos;
    //List<Unidad_Territorial> DepartamentosLista,ProvinciasLista,DistritoLista;
    List<Departamento> DepartamentosLista;
    List<Provincia> ProvinciasLista;
    List<Distrito> DistritoLista;
    String[] spinner_departamentos,spinner_provincias,spinner_distritos;
    Context mcontext;
    int pos_depa=0,pos_prov=0,pos_dist=0;
    ProgressDialog progressDialog;
    boolean depa_accion=false,prov_accion=false,dist_accion=false;
    Button card_guardar;

    Departamento SeleccionDepartamento;
    Provincia SeleccionProvincia;
    Distrito SeleccionDistrito;

    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubigeo);
        mcontext=this;
        db = new DatabaseHelper(mcontext);

        SeleccionDepartamento=new Departamento();
        SeleccionProvincia=new Provincia();
        SeleccionDistrito=new Distrito();

        DepartamentosLista=new ArrayList<>();
        ProvinciasLista=new ArrayList<>();
        DistritoLista=new ArrayList<>();

        departamento=findViewById(R.id.spinner_departamento);
        provincias=findViewById(R.id.spinner_provincia);
        distritos=findViewById(R.id.spinner_distrito);
        card_guardar=findViewById(R.id.guardar_ubigeo);

        String OPCION=getIntent().getStringExtra("TYPE");

        SharedPreferences pref = getSharedPreferences("SesionAlianza",MODE_PRIVATE);
        int CapIdDepartamento=pref.getInt("CapIdDepartamento",-1);
        int CapIdProvincia=pref.getInt("CapIdProvincia",-1);
        int CapIdDistrito=pref.getInt("CapIdDistrito",-1);

        int CapMIdDepartamento=pref.getInt("CapMIdDepartamento",-1);
        int CapMIdProvincia=pref.getInt("CapMIdProvincia",-1);
        int CapMIdDistrito=pref.getInt("CapMIdDistrito",-1);

        if(OPCION.equalsIgnoreCase("CAPTACION")){
            if(CapIdDepartamento!=-1){
                Listar_Departamentos_update(mcontext,CapIdDepartamento,CapIdProvincia,CapIdDistrito);
            }else{
                Listar_Departamentos(mcontext);
            }

        }else if(OPCION.equalsIgnoreCase("CAPTACION_MASIVA")){
            if(CapMIdDepartamento!=-1){
                Listar_Departamentos_update(mcontext,CapMIdDepartamento,CapMIdProvincia,CapMIdDistrito);
            }else{
                Listar_Departamentos(mcontext);
            }
        }

        departamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                for(int x=0;x<DepartamentosLista.size();x++){
                    if(DepartamentosLista.get(x).getDepartamento().equalsIgnoreCase(String.valueOf(item))){
                        SeleccionDepartamento=DepartamentosLista.get(x);
                    }
                }
                if(depa_accion==true){
                    System.out.println("TRUE DEPA CHANGE");
                }else{
                    ProvinciasLista.clear();
                    DistritoLista.clear();
                    Listar_Provincias(SeleccionDepartamento.getIdDepartamento(),mcontext);
                    System.out.println("FALSE DEPA CHANGE");
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //not required
            }
        });
        provincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                for(int x=0;x<ProvinciasLista.size();x++){
                    if(ProvinciasLista.get(x).getProvincia().equalsIgnoreCase(String.valueOf(item))){
                        SeleccionProvincia=ProvinciasLista.get(x);
                    }
                }

                if(prov_accion==false){
                    DistritoLista.clear();
                    Listar_Distritos(SeleccionProvincia.getIdProvincia(),mcontext);
                    System.out.println("FALSE PROVINCIA CHANGE");
                }else{
                    System.out.println("TRUE PROVINCIA CHANGE");
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //not required
            }
        });
        distritos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Object item = adapterView.getItemAtPosition(i);
                for(int x=0;x<DistritoLista.size();x++){
                    if(DistritoLista.get(x).getDistrito().equalsIgnoreCase(String.valueOf(item))){
                        SeleccionDistrito=DistritoLista.get(x);
                    }
                }
                if(dist_accion==true){
                    System.out.println("TRUE DISTRITO CHANGE");
                }else{
                    System.out.println("FALSE DISTRITO CHANGE");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //not required
            }
        });
    }
    private void Listar_Departamentos(final Context context){
        Cursor cursor = db.getDepartamentos();
        if (cursor.moveToFirst()) {
            do {
                Departamento departamento=new Departamento();
                departamento.setIdDepartamento(cursor.getInt(cursor.getColumnIndex(ConstantesDb.DEPARTAMENTO_CAMPO_ID)));
                departamento.setDepartamento(cursor.getString(cursor.getColumnIndex(ConstantesDb.DEPARTAMENTO_CAMPO_DEPARTAMENTO)));
                DepartamentosLista.add(departamento);
            } while (cursor.moveToNext());
        }

        spinner_departamentos=new String[DepartamentosLista.size()];
        for(int i=0;i<DepartamentosLista.size();i++){
            spinner_departamentos[i]=DepartamentosLista.get(i).getDepartamento();
        }
        ArrayAdapter<String> adapter_arr=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,spinner_departamentos);
        departamento.setAdapter(adapter_arr);
    }
    private void Listar_Provincias(final int idDepartamento,final Context context){
        Cursor cursor = db.getProvincias(idDepartamento);
        if (cursor.moveToFirst()) {
            do {
                Provincia provincia=new Provincia();
                provincia.setIdProvincia(cursor.getInt(cursor.getColumnIndex(ConstantesDb.PROVINCIA_CAMPO_ID)));
                provincia.setProvincia(cursor.getString(cursor.getColumnIndex(ConstantesDb.PROVINCIA_CAMPO_PROVINCIA)));
                ProvinciasLista.add(provincia);
            } while (cursor.moveToNext());
        }

        spinner_provincias=new String[ProvinciasLista.size()];
        for(int i=0;i<ProvinciasLista.size();i++){
            spinner_provincias[i]=ProvinciasLista.get(i).getProvincia();
        }
        ArrayAdapter<String> adapter_arr=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,spinner_provincias);
        provincias.setAdapter(adapter_arr);

        System.out.println("PROVINCIAS NEW");
    }
    private void Listar_Distritos(final int idProvincia,final Context context) {
        Cursor cursor = db.getDistritos(idProvincia);
        if (cursor.moveToFirst()) {
            do {
                Distrito distrito=new Distrito();
                distrito.setIdDistrito(cursor.getInt(cursor.getColumnIndex(ConstantesDb.DISTRITO_CAMPO_ID)));
                distrito.setDistrito(cursor.getString(cursor.getColumnIndex(ConstantesDb.DISTRITO_CAMPO_DISTRITO)));
                DistritoLista.add(distrito);
            } while (cursor.moveToNext());
        }

        spinner_distritos=new String[DistritoLista.size()];
        for(int i=0;i<DistritoLista.size();i++){
            spinner_distritos[i]=DistritoLista.get(i).getDistrito();
        }
        ArrayAdapter<String> adapter_arr=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,spinner_distritos);
        distritos.setAdapter(adapter_arr);
        System.out.println("DISTRITOS NEW");


    }
    private void Listar_Departamentos_update(final Context context,final int idDepartamento,final int idProvincia,final int idDistrito) {
        depa_accion=true;

        Cursor cursor = db.getDepartamentos();
        if (cursor.moveToFirst()) {
            do {
                Departamento departamento=new Departamento();
                departamento.setIdDepartamento(cursor.getInt(cursor.getColumnIndex(ConstantesDb.DEPARTAMENTO_CAMPO_ID)));
                departamento.setDepartamento(cursor.getString(cursor.getColumnIndex(ConstantesDb.DEPARTAMENTO_CAMPO_DEPARTAMENTO)));
                DepartamentosLista.add(departamento);
            } while (cursor.moveToNext());
        }


        //LLENAR SPINNER DEPARTAMENTOS
        spinner_departamentos=new String[DepartamentosLista.size()];
        for(int i=0;i<DepartamentosLista.size();i++){
            spinner_departamentos[i]=DepartamentosLista.get(i).getDepartamento();
        }
        ArrayAdapter<String> adapter_arr=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,spinner_departamentos);

        departamento.setAdapter(adapter_arr);

        //RECUPERANDO DEPARTAMENTO ACTUAL DEL MODULO DEL USUARIO
        String depa=db.RecuperarDepartamentoName(idDepartamento);

        for(int i=0;i<spinner_departamentos.length;i++){
            if(spinner_departamentos[i].equalsIgnoreCase(depa)){
                pos_depa=i;
            }
        }

        departamento.setSelection(pos_depa);
        //RECUPERANDO PROVINCIAS
        Listar_Provincias_update(idDepartamento,mcontext,idProvincia,idDistrito);
        System.out.println("DEPARTAMENTOS UPDATE");

    }
    private void Listar_Provincias_update(final int idDepartamento,final Context context,final int idProvincia,final int idDistrito) {
        prov_accion=true;

        Cursor cursor = db.getProvincias(idDepartamento);
        if (cursor.moveToFirst()) {
            do {
                Provincia provincia=new Provincia();
                provincia.setIdProvincia(cursor.getInt(cursor.getColumnIndex(ConstantesDb.PROVINCIA_CAMPO_ID)));
                provincia.setProvincia(cursor.getString(cursor.getColumnIndex(ConstantesDb.PROVINCIA_CAMPO_PROVINCIA)));
                ProvinciasLista.add(provincia);
            } while (cursor.moveToNext());
        }

        spinner_provincias=new String[ProvinciasLista.size()];
        for(int i=0;i<ProvinciasLista.size();i++){
            spinner_provincias[i]=ProvinciasLista.get(i).getProvincia();
        }
        ArrayAdapter<String> adapter_arr=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,spinner_provincias);

        provincias.setAdapter(adapter_arr);


        //RECUPERANDO PROVINCIA ACTUAL DEL MODULO DEL USUARIO
        String provi=db.RecuperarProvinciaName(idProvincia);

        for(int i=0;i<spinner_provincias.length;i++){
            if(spinner_provincias[i].equalsIgnoreCase(provi)){
                pos_prov=i;
            }
        }

        provincias.setSelection(pos_prov);


        Listar_Distritos_update(idProvincia,mcontext,idDistrito);
        System.out.println("PROVINCIAS UPDATE");


    }
    private void Listar_Distritos_update(int idProvincia, final Context context,final int idDistrito) {

        dist_accion=true;

        Cursor cursor = db.getDistritos(idProvincia);
        if (cursor.moveToFirst()) {
            do {
                Distrito distrito=new Distrito();
                distrito.setIdDistrito(cursor.getInt(cursor.getColumnIndex(ConstantesDb.DISTRITO_CAMPO_ID)));
                distrito.setDistrito(cursor.getString(cursor.getColumnIndex(ConstantesDb.DISTRITO_CAMPO_DISTRITO)));
                DistritoLista.add(distrito);
            } while (cursor.moveToNext());
        }


        spinner_distritos=new String[DistritoLista.size()];
        for(int i=0;i<DistritoLista.size();i++){
            spinner_distritos[i]=DistritoLista.get(i).getDistrito();
        }
        ArrayAdapter<String> adapter_arr=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,spinner_distritos);

        distritos.setAdapter(adapter_arr);

        //RECUPERANDO DISTRITO ACTUAL DEL MODULO DEL USUARIO
        String dist=db.RecuperarDistritoName(idDistrito);
        for(int i=0;i<spinner_distritos.length;i++){
            if(spinner_distritos[i].equalsIgnoreCase(dist)){
                pos_dist=i;
            }
        }
        distritos.setSelection(pos_dist);
        depa_accion=false;
        prov_accion=false;
        dist_accion=false;

        System.out.println("DISTRITOS UPDATE");
    }


}
