package com.example.chezhenjun.filetransfer.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by chezhenjun on 2017/11/12.
 */

public class FileInfo {
public String getFilename() {
    return filename;
}

public void setFilename(String filename) {
    this.filename = filename;
}

private  String filename;
private String filePath;
private int fileType;
private long size;

public Boolean getDirectory() {
    return isDirectory;
}

public void setDirectory(Boolean directory) {
    isDirectory = directory;
}

private Boolean isDirectory;

public String getFilePath() {
    return filePath;
}

public int getFileType() {
    return fileType;
}

public long getSize() {
    return size;
}

public void setFilePath(String filePath) {
    this.filePath = filePath;
}

public void setFileType(int fileType) {
    this.fileType = fileType;
}

public void setSize(long size) {
    this.size = size;
}



public static String toJsonStr(FileInfo fileInfo){
    String jsonStr = "";
    JSONObject jsonObject = new JSONObject();
    try {
        jsonObject.put("filePath", fileInfo.getFilePath());
        jsonObject.put("fileType", fileInfo.getFileType());
        jsonObject.put("size", fileInfo.getSize());
    } catch (JSONException e) {
        e.printStackTrace();
    }
    return jsonObject.toString();
}

public static FileInfo toObject(String jsonStr){
    FileInfo fileInfo = new FileInfo();
    try {
        JSONObject jsonObject =  new JSONObject(jsonStr);
        String filePath = (String) jsonObject.get("filePath");
        long size = jsonObject.getLong("size");
        int type = jsonObject.getInt("fileType");

        fileInfo.setFilePath(filePath);
        fileInfo.setSize(size);
        fileInfo.setFileType(type);
    } catch (JSONException e) {
        e.printStackTrace();
    }
    return fileInfo;
}
}
