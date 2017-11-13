package com.example.chezhenjun.filetransfer.tool;

import android.content.Context;
import android.net.wifi.WifiManager;

import com.example.chezhenjun.filetransfer.adapter.NormalRecyclerViewAdapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by chezhenjun on 2017/11/12.
 */

public class wifi {
public static ArrayList<String> getConnectedHotIP() {
    ArrayList<String> connectedIP = new ArrayList<String>();
    try {
        BufferedReader br = new BufferedReader(new FileReader(
                "/proc/net/arp"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] splitted = line.split(" +");
            if (splitted != null && splitted.length >= 4) {
                String ip = splitted[0];
                connectedIP.add(ip);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return connectedIP;
}
public static Boolean iswifion(Context context){
    WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
    //判断wifi是否开启
    if (!wifiManager.isWifiEnabled()) {
        wifiManager.setWifiEnabled(true);
        return  true;
    }
    else{
        return false;
    }
}
}
