package com.example.diego.clienteftpandroid;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;



/**
 * Created by Diego on 24/05/2015.
 */
public class connnectingwithFTP extends Thread{
    String ip, userName,pass;
Context contexto;
    FTPClient mFtpClient;

    public connnectingwithFTP(String ip, String userName, String pass,Context contexto) {
     this.ip=ip;
        this.userName=userName;
        this.pass=pass;
        this.contexto=contexto;
    }

    public void Desconectar(){

        try {
            mFtpClient.disconnect();
            Log.d("Api FTP","Desconectad0");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {

        boolean status = false;
        try {
            mFtpClient = new FTPClient();
            mFtpClient.setConnectTimeout(10 * 1000);
            mFtpClient.connect(InetAddress.getByName(ip));
            status = mFtpClient.login(userName, pass);
            Log.d("Api FTP","Se conecto: "+ String.valueOf(status));
            if (FTPReply.isPositiveCompletion(mFtpClient.getReplyCode())) {
                mFtpClient.setFileType(FTP.BINARY_FILE_TYPE);
                mFtpClient.enterLocalPassiveMode();

                FTPFile[] mFileArray = mFtpClient.listFiles();
                Log.d("Api FTP","Numeros de archivos"+ String.valueOf(mFileArray.length));
                Log.d("Api FTP", "IP Server:" + String.valueOf(mFtpClient.getRemoteAddress()));


                // APPROACH #1: uploads first file using an InputStream
                File firstLocalFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Radiobases/Prueba.jpg");

                String firstRemoteFile = "Prueba.jpg";
                InputStream inputStream = new FileInputStream(firstLocalFile);
                boolean done = mFtpClient.storeFile(firstRemoteFile, inputStream);
                inputStream.close();
                if (done) {
                    System.out.println("el primer archivo se subio perfecto!!.");
            //        Toast.makeText(contexto,"Archivo subido...",Toast.LENGTH_SHORT).show();
                }else{System.out.println("Error de subida");}





            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
