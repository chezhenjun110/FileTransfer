package com.example.chezhenjun.filetransfer.socket;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.example.chezhenjun.filetransfer.bean.FileInfo;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by chezhenjun on 2017/11/12.
 */

public class sender extends  BaseTransfer  implements  Runnable{

    private final  String TAG="fuckyou";
public String targethost;
public int port;
public FileInfo fileInfo;
private int BYTE_SIZE_DATA=1024;
public  sender(String host,int portnum,FileInfo fileInfo){
    this.fileInfo=fileInfo;
    this.port=portnum;
    this.targethost=host;
}
@Override
public void run() {
        Socket socket;
        try {
            int len=0;
            socket = new Socket(targethost,port);
            OutputStream os = socket.getOutputStream();
            BufferedOutputStream mOutputStream=new BufferedOutputStream(os);
            StringBuilder headerSb = new StringBuilder();
            String jsonStr = FileInfo.toJsonStr(fileInfo);
            jsonStr = TYPE_FILE + SPERATOR + jsonStr;
            headerSb.append(jsonStr);
            int leftLen = BYTE_SIZE_HEADER - jsonStr.getBytes(UTF_8).length;
            for(int i=0; i < leftLen; i++){
                headerSb.append(" ");
            }
            Log.i(TAG,headerSb.toString());
            byte[] headbytes = headerSb.toString().getBytes(UTF_8);
            mOutputStream.write(headbytes);
            StringBuilder screenshotSb = new StringBuilder();
            for(int i=0; i < BYTE_SIZE_SCREENSHOT ; i++){
                screenshotSb.append(" ");
            }
            byte[] screenshotBytes = screenshotSb.toString().getBytes(UTF_8);

            mOutputStream.write(screenshotBytes);
            InputStream fis = new FileInputStream(new File(fileInfo.getFilePath()));
            byte[] bytes = new byte[BYTE_SIZE_DATA];
            while((len=fis.read(bytes)) != -1){
                mOutputStream.write(bytes, 0, len);
            }
            mOutputStream.flush();
            mOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

