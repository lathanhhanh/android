package com.lathanhhanh.binhlieuapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lathanhhanh.binhlieuapp.R;

public class ThongTinFragment extends Fragment {
    public TextView txtTuyen, txtBienSo, txtGiaVe, txtSoGhe, txtThoiGian1, txtThoiGian2, txtSoDienThoai, txtCallnow;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thongtin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtTuyen = view.findViewById(R.id.tv2Tuyen);
        txtBienSo = view.findViewById(R.id.tv2BienSo);
        txtGiaVe = view.findViewById(R.id.tv2GiaVe);
        txtSoGhe = view.findViewById(R.id.tv2SoGhe);
        txtThoiGian1 = view.findViewById(R.id.tv2ThoiGian1);
        txtThoiGian2 = view.findViewById(R.id.tv2ThoiGian2);
        txtSoDienThoai = view.findViewById(R.id.tv2SoDienThoai);

        txtCallnow = view.findViewById(R.id.callnow);
        txtCallnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+txtSoDienThoai.getText().toString()));
                v.getContext().startActivity(intent);
            }
        });
        txtSoDienThoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+txtSoDienThoai.getText().toString()));
                v.getContext().startActivity(intent);
            }
        });
    }
}
