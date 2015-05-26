package com.example.diego.clienteftpandroid;

import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;



/**
 * Created by Diego on 24/05/2015.
 */
public class connnectingwithFTP extends Thread{
    String ip, userName,pass;

    FTPClient mFtpClient;
    public connnectingwithFTP(String ip, String userName, String pass) {
     this.ip=ip;
        this.userName=userName;
        this.pass=pass;
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
                mFtpClient.setFileType(FTP.ASCII_FILE_TYPE);
                mFtpClient.enterLocalPassiveMode();

                FTPFile[] mFileArray = mFtpClient.listFiles();
                Log.d("Api FTP","Numeros de archivos"+ String.valueOf(mFileArray.length));
                Log.d("Api FTP","IP Server:"+  String.valueOf(mFtpClient.getRemoteAddress()));
                downloadSingleFile baja=new downloadSingleFile();
                baja.start();
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
