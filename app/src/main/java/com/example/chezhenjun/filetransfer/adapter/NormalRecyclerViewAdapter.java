package com.example.chezhenjun.filetransfer.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chezhenjun.filetransfer.R;
import com.example.chezhenjun.filetransfer.bean.FileInfo;
import com.example.chezhenjun.filetransfer.socket.sender;
import com.example.chezhenjun.filetransfer.tool.wifi;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chezhenjun on 2017/11/11.
 */


public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder> {


private final LayoutInflater mLayoutInflater;

private ArrayList<FileInfo> itemdata;
public Context context;
public NormalRecyclerViewAdapter(ArrayList<FileInfo> data, Context context) {
    itemdata = data;
  this.context=context;
    mLayoutInflater = LayoutInflater.from(context);
}

@Override
public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.file_item, parent, false),itemdata,context);
}

@Override
public void onBindViewHolder(NormalTextViewHolder holder, int position) {
    holder.mTextView.setText(itemdata.get(position).getFilename());
    if(itemdata.get(position).getDirectory()){
        holder.imgFile.setImageResource(R.drawable.ic_folder);
    }else{
        holder.imgFile.setImageResource(R.drawable.ic_text_file);
    }
}

@Override
public int getItemCount() {

    return itemdata == null ? 0 : itemdata.size();
}

public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text_view)
    TextView mTextView;
    @BindView(R.id.img_file)
    ImageView imgFile;
    @BindView(R.id.cv_item)
    CardView cvItem;
    ArrayList<FileInfo> itemdata;
 private Context context;

    NormalTextViewHolder(View view,ArrayList<FileInfo> itemdata,Context context) {
        super(view);
        this.itemdata=itemdata;
        this.context=context;
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.cv_item)
    void onItemClick() {
//       sender s=new sender("192.168.43.1",8080,itemdata.get(getPosition()));
//       s.run();
        if(wifi.iswifion(context)){
            Log.d("fuckyou", "wifi已打开,onClick--> position = " + getPosition());
        }


    }
}
}

