package com.lathanhhanh.binhlieuapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.lathanhhanh.binhlieuapp.adapter.XeKhachAdapter;
import com.lathanhhanh.binhlieuapp.api.RetrofitClient;
import com.lathanhhanh.binhlieuapp.model.Data;
import com.lathanhhanh.binhlieuapp.model.XeKhach;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager viewPager;
    SQLiteDatabase db;
    private List<XeKhach> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("binhlieu.db", MODE_PRIVATE, null);
        if(kiemtraCSDL(db)==false){
            taoCSDL();
            themCSDL();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = findViewById(R.id.pagerView);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        XeKhachAdapter adapter = new XeKhachAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.thongtin) {
            Intent i = new Intent(MainActivity.this, LienHeActivity.class);
            startActivity(i);
        } else if (id == R.id.gopy) {
            Intent i2 = new Intent(MainActivity.this, GopYActivity.class);
            startActivity(i2);
        } else if (id == R.id.update) {
            capnhatCSDL();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private boolean kiemtraCSDL(SQLiteDatabase db) {
        String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='thongtin'";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.getCount() > 0){
            return true;
        }
        cursor.close();
        return false;
    }
    private void taoCSDL()
    {
        db = openOrCreateDatabase("binhlieu.db", MODE_PRIVATE, null);
        String sql = "CREATE TABLE IF NOT EXISTS thongtin(id integer, tuyen text, bienso text,giave text, soghe text, thoigian1 text,thoigian2 text, sodienthoai text, ghichu text)";
        db.execSQL(sql);
    }
    private void themCSDL()
    {
        final ProgressDialog pDialog;
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Đang tải dữ liệu.. Vui lòng chờ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        RetrofitClient.ApiService  api = RetrofitClient.getApiService();
        final Call<Data> call = api.getData("all");
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                List<XeKhach> datas = response.body().getData();
                insertData(datas);
                pDialog.dismiss();
                Toast.makeText(MainActivity.this, "Cập nhật dữ liệu thành công!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                pDialog.dismiss();
            }
        });

    }
    private void insertData(List<XeKhach> datas)
    {
        db = openOrCreateDatabase("binhlieu.db", MODE_PRIVATE, null);
        this.datas = datas;
        for(int i=0; i< datas.size(); i++){
            XeKhach x = datas.get(i);
            String id = x.getId();
            String tuyen = x.getTuyen();
            String bienso = x.getBienso();
            String giave = x.getGiave();
            String soghe = x.getSoghe();
            String thoigian1 = x.getThoigian1();
            String thoigian2 = x.getThoigian2();
            String sodienthoai = x.getSodienthoai();
            String ghichu = x.getGhichu();

            String sql = "INSERT INTO thongtin(id, tuyen, bienso, giave, soghe, thoigian1,thoigian2, sodienthoai, ghichu) VALUES ('"+id+"', '"+tuyen+"','"+bienso+"', '"+giave+"', '"+soghe+"', '"+thoigian1+"', '"+thoigian2+"', '"+sodienthoai+"', '"+ghichu+"')";
            db.execSQL(sql);
        }
    }
    private void xoaCSDL()
    {
        db = openOrCreateDatabase("binhlieu.db", MODE_PRIVATE, null);
        String sql = "DROP TABLE thongtin";
        db.execSQL(sql);
    }
    private  void capnhatCSDL()
    {
        xoaCSDL();
        taoCSDL();
        themCSDL();
    }
}
