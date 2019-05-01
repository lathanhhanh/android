package com.lathanhhanh.binhlieuapp.adapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lathanhhanh.binhlieuapp.InfoActivity;
import com.lathanhhanh.binhlieuapp.R;
import com.lathanhhanh.binhlieuapp.model.XeKhach;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    List<XeKhach> datas;

    public DataAdapter(List<XeKhach> datas){
        this.datas = datas;
    }
    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.xekhach_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder viewHolder, int position) {
        XeKhach x = datas.get(position);
        viewHolder.txtTuyen.setText("Bình Liêu - "+x.getTuyen());
        viewHolder.txtThoiGian.setText("Tại Bình Liêu: "+x.getThoigian1());
        viewHolder.txtThoiGian2.setText("Bến đối lưu: "+x.getThoigian2());
        viewHolder.txtSoDienThoai.setText("Số điện thoại: "+x.getSodienthoai());
        viewHolder.txtGhiChu.setText("Ghi chú: "+x.getGhichu());

        viewHolder.dttuyen = x.getTuyen();
        viewHolder.dtbienso = x.getBienso();
        viewHolder.dtgiave = x.getGiave();
        viewHolder.dtsoghe = x.getSoghe();
        viewHolder.dtthoigian1 = x.getThoigian1();
        viewHolder.dtthoigian2 = x.getThoigian2();
        viewHolder.dtsodienthoai = x.getSodienthoai();
        viewHolder.dtghichu = x.getGhichu();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTuyen, txtThoiGian, txtThoiGian2, txtSoDienThoai, txtGhiChu;
        String dttuyen, dtbienso, dtgiave, dtsoghe, dtthoigian1, dtthoigian2, dtsodienthoai, dtghichu;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtTuyen = itemView.findViewById(R.id.tvTuyen);
            txtThoiGian = itemView.findViewById(R.id.tvThoiGian);
            txtThoiGian2 = itemView.findViewById(R.id.tvThoiGian2);
            txtSoDienThoai = itemView.findViewById(R.id.tvSoDienThoai);
            txtGhiChu = itemView.findViewById(R.id.tvGhiChu);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), InfoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("TUYEN", dttuyen);
                    bundle.putString("BIEN-SO", dtbienso);
                    bundle.putString("GIA-VE", dtgiave);
                    bundle.putString("SO-GHE", dtsoghe);
                    bundle.putString("TIME1", dtthoigian1);
                    bundle.putString("TIME2", dtthoigian2);
                    bundle.putString("SDT", dtsodienthoai);
                    bundle.putString("GHI-CHU", dtghichu);

                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);
                }
            });

            ImageView img = itemView.findViewById(R.id.ivimage);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+dtsodienthoai));
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
