package com.gg.givemepass.upload_file_2_php;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class FileUpload {
    private String mResponseMsg;
    private boolean isSucess;

    public interface OnFileUploadListener{
        void onFileUploadSuccess(String msg);
        void onFileUploadFail(String msg);
    }

    private OnFileUploadListener mOnFileUploadListener;

    public void setOnFileUploadListener(OnFileUploadListener listener){
        mOnFileUploadListener = listener;
    }

    public boolean isSucess() {
        return isSucess;
    }

    public FileUpload(){
        mResponseMsg = "";
        isSucess = false;
    }

    public void doFileUpload(String path) {
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        DataInputStream inStream = null;
        String existingFileName = path;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        String urlString = "http://10.1.17.111:8888/upload.php";

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(existingFileName));
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + existingFileName + "\"" + lineEnd);
            dos.writeBytes(lineEnd);
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {

                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            }

            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            fileInputStream.close();
            dos.flush();
            dos.close();
            isSucess = true;
        } catch (MalformedURLException e){
            Log.e("", e.getMessage());
            isSucess = false;
        } catch (IOException e) {
            Log.e("", e.getMessage());
            isSucess = false;
        }

        try {
            inStream = new DataInputStream(conn.getInputStream());
            String str;
            while ((str = inStream.readLine()) != null) {
                mResponseMsg = str;
            }
            inStream.close();

        } catch (IOException e) {
            isSucess = false;
            mResponseMsg = e.getMessage();
        }
        if(mOnFileUploadListener != null) {
            if (isSucess) {
                mOnFileUploadListener.onFileUploadSuccess(mResponseMsg);
            } else{
                mOnFileUploadListener.onFileUploadFail(mResponseMsg);
            }
        }
    }
}
