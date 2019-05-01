package com.lathanhhanh.binhlieuapp.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lathanhhanh.binhlieuapp.R;
import com.lathanhhanh.binhlieuapp.adapter.DataAdapter;
import com.lathanhhanh.binhlieuapp.api.RetrofitClient;
import com.lathanhhanh.binhlieuapp.model.Data;
import com.lathanhhanh.binhlieuapp.model.XeKhach;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XeKhachFragment extends Fragment {

    public static Fragment newInstance(String value) {

        Bundle args = new Bundle();
        args.putString("DATA", value);
        XeKhachFragment fragment = new XeKhachFragment();
        fragment.setArguments(args);
        return fragment;
    }
    String value = "all";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        value = getArguments().getString("DATA");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_xekhach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView rv = view.findViewById(R.id.recyclerview);

        Bundle bd = getArguments();
        if(bd != null){
            String tuyen = bd.getString("DATA", "");
            SQLiteDatabase db;
            String sql;
            if(tuyen == "all"){
                sql = "SELECT * FROM thongtin";
            }else{
                sql = "SELECT * FROM thongtin WHERE tuyen LIKE '"+tuyen+"'";
            }

            db = SQLiteDatabase.openDatabase("data/data/com.lathanhhanh.binhlieuapp/databases/binhlieu.db",null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor c = db.rawQuery(sql, null);
            List<XeKhach> datas = new ArrayList<>();

            if (c.moveToFirst()){
                do {
                    XeKhach x = new XeKhach();
                    x.setId(c.getString(0));
                    x.setTuyen(c.getString(1));
                    x.setBienso(c.getString(2));
                    x.setGiave(c.getString(3));
                    x.setSoghe(c.getString(4));
                    x.setThoigian1(c.getString(5));
                    x.setThoigian2(c.getString(6));
                    x.setSodienthoai(c.getString(7));
                    x.setGhichu(c.getString(8));
                    datas.add(x);
                } while(c.moveToNext());
            }
            c.close();
            db.close();

            DataAdapter adapter = new DataAdapter(datas);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            rv.setLayoutManager(layoutManager);
            rv.setItemAnimator(new DefaultItemAnimator());
            rv.setAdapter(adapter);
        }
    }
}
