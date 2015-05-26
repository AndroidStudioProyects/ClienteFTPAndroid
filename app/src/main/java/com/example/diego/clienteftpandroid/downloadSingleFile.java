package com.example.diego.clienteftpandroid;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Diego on 24/05/2015.
 */
public class downloadSingleFile extends Thread {

    /**
     * @param ftpClient FTPclient object
     * @param remoteFilePath  FTP server file path
     * @param downloadFile   local file path where you want to save after download
     * @return status of downloaded file
     */




    public boolean downloadSingleFile(FTPClient ftpClient,
                                      String remoteFilePath, File downloadFile) {
        File parentDir = downloadFile.getParentFile();
        if (!parentDir.exists())
            parentDir.mkdir();
        OutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(
                    downloadFile));
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return ftpClient.retrieveFile(remoteFilePath, outputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public void run() {

    }
}
