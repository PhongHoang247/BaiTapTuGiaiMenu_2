package com.phong.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.phong.baitaptugiaimenu_2.R;
import com.phong.model.SanPham;

public class SanPhamAdapter extends ArrayAdapter {
    Activity context;
    int resource;
    public SanPhamAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = this.context.getLayoutInflater().inflate(this.resource, null);
        TextView txtHienThi = (TextView) view.findViewById(R.id.txtHienThi);
        SanPham sp = (SanPham) getItem(position);
        txtHienThi.setText(sp.getName());
        return view;
    }
}
