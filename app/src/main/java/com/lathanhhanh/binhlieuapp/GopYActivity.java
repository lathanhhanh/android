package com.lathanhhanh.binhlieuapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lathanhhanh.binhlieuapp.api.RetrofitClient;
import com.lathanhhanh.binhlieuapp.model.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GopYActivity extends AppCompatActivity {
    Button btnGopY;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gop_y);

        edt = findViewById(R.id.edtGopY);
        btnGopY = findViewById(R.id.btnGopY);

            btnGopY.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = manager.getActiveNetworkInfo();

                    if(networkInfo != null && networkInfo.isConnected()){
                        final ProgressDialog pDialog;
                        pDialog = new ProgressDialog(GopYActivity.this);
                        pDialog.setMessage("Đang gửi...");
                        pDialog.setIndeterminate(false);
                        pDialog.setCancelable(false);
                        pDialog.show();
                        RetrofitClient.ApiService  api = RetrofitClient.getApiService();
                        Call<Data> call = api.sendGopY(edt.getText().toString());
                        call.enqueue(new Callback<Data>() {
                            @Override
                            public void onResponse(Call<Data> call, Response<Data> response) {
                                pDialog.dismiss();
                                Toast.makeText(GopYActivity.this, "Gửi thành công thành công!", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getApplicationContext(), GopYDuocActivity.class);
                                startActivity(i);
                            }

                            @Override
                            public void onFailure(Call<Data> call, Throwable t) {
                                pDialog.dismiss();
                                Toast.makeText(GopYActivity.this, "Không có kết nối mạng!", Toast.LENGTH_LONG).show();
                            }
                        });
                    }else{
                        Toast.makeText(GopYActivity.this, "Không có kết nối mạng!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }
}
