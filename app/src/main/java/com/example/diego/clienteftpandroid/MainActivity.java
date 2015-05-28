package com.example.diego.clienteftpandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;


public class MainActivity extends ActionBarActivity {
   // connnectingwithFTP cliente;
    ConnectUploadAsync cliente;
    uploadFile subir;
    downloadSingleFile bajarArchivo;
    Button btn_Subir, btn_Conectar;
    EditText edit_ServerFtp,edit_Puerto,edit_User,edit_Pass;
    File Archivo;
    TextView text_Bytes;
  String userName,pass,ip;

    ProgressBar progressbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        levantar_xml();
        Botones();
        CargarPreferencias();


    }

    @Override
    protected void onPause() {
        super.onPause();
        GuardarPreferencias();


    }

    @Override
    protected void onResume() {
        super.onResume();

        CargarPreferencias();
    }

    private void Botones() {

        btn_Conectar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
            //    GuardarPreferencias();
               ip=edit_ServerFtp.getText().toString();
                userName=edit_User.getText().toString();
                pass=edit_Pass.getText().toString();

            //    cliente=new connnectingwithFTP(ip,userName,pass,getApplicationContext());
             //   cliente.start();
           cliente = new ConnectUploadAsync(getApplicationContext(),ip,userName,pass,MainActivity.this);
                cliente.execute();

            }
        });

        btn_Subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subir=new uploadFile();

            }
        });
    }

    private void levantar_xml() {
        btn_Conectar= (Button) findViewById(R.id.btn_Conectar);
        btn_Subir= (Button) findViewById(R.id.btn_Subir);

        text_Bytes=(TextView)findViewById(R.id.text_Bytes);

        edit_ServerFtp=(EditText)findViewById(R.id.edit_ServerFtp);
        edit_Puerto=(EditText)findViewById(R.id.edit_Puerto);
        edit_User=(EditText)findViewById(R.id.edit_User);
        edit_Pass=(EditText)findViewById(R.id.edit_Pass);
        progressbar=(ProgressBar)findViewById(R.id.progressBar);
     }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    ///////////////////// PREFERENCIAS DE USUARIO ////////////////
    public void CargarPreferencias(){

        SharedPreferences mispreferencias=getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);
        edit_ServerFtp.setText(mispreferencias.getString("edit_IP", "localhost"));
        edit_Puerto.setText(mispreferencias.getString("edit_Port", "21"));
        edit_User.setText(mispreferencias.getString("edit_User", "idirect"));
        edit_Pass.setText(mispreferencias.getString("edit_Pass", "IDIRECT"));
        Log.d("Android_FTP", "Preferencias Cargadas");


    }

    public void GuardarPreferencias() {
        SharedPreferences mispreferencias = getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mispreferencias.edit();
        editor.putString("edit_IP", edit_ServerFtp.getText().toString());
        editor.putString("edit_Port", edit_Puerto.getText().toString());
        editor.putString("edit_User", edit_User.getText().toString());
        editor.putString("edit_Pass", edit_Pass.getText().toString());
        editor.commit();
        Log.d("Android_FTP", "Preferencias Almacenadas");

    }
// //////////////////////////////////////////////////////////
}
