package com.example.diego.clienteftpandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;


public class MainActivity extends ActionBarActivity {
    connnectingwithFTP cliente;
    uploadFile subir;
    downloadSingleFile bajarArchivo;
    Button btn_Subir, btn_Conectar;
    File Archivo;
String ip="192.168.0.103",userName="idirect",pass="IDIRECT";

    /*********  work only for Dedicated IP ***********/
    static final String FTP_HOST= "192.168.0.103";

    /*********  FTP USERNAME ***********/
    static final String FTP_USER = "idirect";

    /*********  FTP PASSWORD ***********/
    static final String FTP_PASS  ="IDIRECT";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        levantar_xml();
        Botones();



    }

    private void Botones() {
        btn_Conectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(btn_Conectar.getText().toString().equals("Conectar")){
                cliente=new connnectingwithFTP(ip,userName,pass);
                cliente.start();
                   btn_Conectar.setText("Desconectar");
               }else{


                   cliente.Desconectar();
                   btn_Conectar.setText("Conectar");
               }
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
}
