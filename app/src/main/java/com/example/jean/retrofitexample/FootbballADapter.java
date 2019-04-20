package com.example.jean.retrofitexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jean.retrofitexample.model.HistoryData;

import java.util.List;

public class FootbballADapter extends RecyclerView.Adapter<FootbballADapter.FootBallViewHolder> {

    List<HistoryData> historyDataList;
    Context mContext;
    private View.OnClickListener mOnItemClickListener;

    public FootbballADapter(List<HistoryData> historyDataList, Context mContext)
    {
        this.historyDataList = historyDataList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FootbballADapter.FootBallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view, parent, false);
        return new FootBallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FootbballADapter.FootBallViewHolder holder, int position) {
        HistoryData data = historyDataList.get(position);

        Glide.with(mContext)
                .load(data.getGambar())
                .into(holder.img_gambar);
        holder.txt_nama.setText(data.getNama());
        holder.txt_umur.setText(data.getAge());

    }

    @Override
    public int getItemCount() {
        return historyDataList.size();
    }

    public class FootBallViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_nama;
        private TextView txt_umur;
        private ImageView img_gambar;

        public FootBallViewHolder(View itemView)
        {
            super(itemView);

            txt_nama =(TextView) itemView.findViewById(R.id.txt_nama);
            txt_umur =(TextView) itemView.findViewById(R.id.txt_umur);
            img_gambar =(ImageView) itemView.findViewById(R.id.img_gambar);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

}
