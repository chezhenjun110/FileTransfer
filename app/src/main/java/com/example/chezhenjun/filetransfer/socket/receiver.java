package com.example.chezhenjun.filetransfer.socket;

import android.os.Environment;

import com.example.chezhenjun.filetransfer.bean.FileInfo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by chezhenjun on 2017/11/12.
 */

public class receiver extends BaseTransfer implements Runnable{
Socket socket;

public  receiver(Socket msocket){
    this.socket=msocket;
}
public String targethost;
public int port;
public FileInfo fileInfo;
private int BYTE_SIZE_DATA=1024;
@Override
public void run() {
    InputStream mInputStream=null;
    try {

        socket = new Socket(targethost,port);
         mInputStream=socket.getInputStream();
        byte[] headerBytes = new byte[BYTE_SIZE_HEADER];
        int headTotal = 0;
        int readByte = -1;

        while((readByte = mInputStream.read()) != -1){
            headerBytes[headTotal] = (byte) readByte;
            headTotal ++;
            if(headTotal == headerBytes.length){
                break;
            }
        }
        String jsonStr = new String(headerBytes, UTF_8);
        String[] strArray = jsonStr.split(SPERATOR);
        jsonStr = strArray[1].trim();
        fileInfo = FileInfo.toObject(jsonStr);
      long filesize=fileInfo.getSize();
      String filepath= Environment.getExternalStorageDirectory().getAbsolutePath();
      OutputStream os=new FileOutputStream(new File(filepath));
        byte[] bytes = new byte[BYTE_SIZE_DATA];
        int len = 0;
        while((len=mInputStream.read(bytes)) != -1){
            os.write(bytes, 0, len);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }finally {
        if(mInputStream!=null){
            try {
                mInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(socket != null && socket.isConnected()){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
}
