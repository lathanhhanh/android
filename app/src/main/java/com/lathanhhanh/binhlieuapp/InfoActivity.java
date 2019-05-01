package com.lathanhhanh.binhlieuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.lathanhhanh.binhlieuapp.fragment.ThongTinFragment;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Bundle bundle = getIntent().getExtras();

        String dttuyen = bundle.getString("TUYEN", "");
        String dtbienso = bundle.getString("BIEN-SO", "");
        String dtgiave = bundle.getString("GIA-VE", "");
        String dtsoghe = bundle.getString("SO-GHE", "");
        String dtthoigian1 = bundle.getString("TIME1", "");
        String dtthoigian2 = bundle.getString("TIME2", "");
        String dtsodienthoai = bundle.getString("SDT", "");

        ThongTinFragment infoFragment = (ThongTinFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_info);
        infoFragment.txtTuyen.setText(dttuyen);
        infoFragment.txtBienSo.setText(dtbienso);
        infoFragment.txtGiaVe.setText(dtgiave);
        infoFragment.txtSoGhe.setText(dtsoghe);
        infoFragment.txtThoiGian1.setText(dtthoigian1);
        infoFragment.txtThoiGian2.setText(dtthoigian2);
        infoFragment.txtSoDienThoai.setText(dtsodienthoai);
    }
}
