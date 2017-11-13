package com.example.chezhenjun.filetransfer.tool;

import android.os.Environment;
import android.util.Log;

import com.example.chezhenjun.filetransfer.bean.FileInfo;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by chezhenjun on 2017/11/12.
 */

public class getDirectory {
    public  static ArrayList<FileInfo> getdirectory(String path){
//        String path= Environment.getExternalStorageDirectory().getAbsolutePath();
        ArrayList<FileInfo> ls=new ArrayList<FileInfo>();
        File[] files= new File(path).listFiles();
        if(files==null){
            return null;
        }
        for (int i=0;i<files.length;i++){
            FileInfo fi=new FileInfo();
            fi.setFilename(files[i].getName());
            fi.setFilePath(files[i].getAbsolutePath());
            fi.setSize(files[i].length());
            fi.setFileType(1);
            if(files[i].isDirectory()){
                fi.setDirectory(true);
            }else{
                fi.setDirectory(false);
            }
            ls.add(fi);
        }
        return  ls;
    }
}
