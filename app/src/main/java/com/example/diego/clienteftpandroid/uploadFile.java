package com.example.diego.clienteftpandroid;

import android.util.Log;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Diego on 24/05/2015.
 */
public class uploadFile {
    /**
     *
     * @param ftpClient FTPclient object
     * @param downloadFile local file which need to be uploaded.
     */
    public void uploadFile(FTPClient ftpClient, File downloadFile,String serverfilePath) {
        try {
            FileInputStream srcFileStream = new FileInputStream(downloadFile);
            boolean status = ftpClient.storeFile(serverfilePath,
                    srcFileStream);
            Log.e("Status", String.valueOf(status));
            srcFileStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
