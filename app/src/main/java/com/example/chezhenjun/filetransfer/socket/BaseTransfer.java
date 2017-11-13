package com.example.chezhenjun.filetransfer.socket;

/**
 * Created by chezhenjun on 2017/11/12.
 */

public class BaseTransfer {
public static final String SPERATOR = "::";
public static final int BYTE_SIZE_HEADER    = 1024 * 10;
public static final int BYTE_SIZE_SCREENSHOT    = 1024 * 40;
public static final int BYTE_SIZE_DATA      = 1024 * 4;


public static final int TYPE_FILE = 1; //文件类型
public static final int TYPE_MSG = 2;  //消息类型

public static final String UTF_8 = "UTF-8";
}
