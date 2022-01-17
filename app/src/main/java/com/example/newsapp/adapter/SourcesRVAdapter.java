package com.example.newsapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.interfaces.RecyclerViewClickInterface;
import com.example.newsapp.models.Articles;

import java.util.ArrayList;

public class SourcesRVAdapter extends RecyclerView.Adapter<SourcesRVAdapter.ViewHolder> {

    private ArrayList<String> izvoriList;
    private Context context;
    private RecyclerViewClickInterface recyclerViewClickInterface;

    public SourcesRVAdapter(ArrayList<String> izvoriList, Context context, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.izvoriList = izvoriList;
        this.context = context;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public SourcesRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sources_rv_item, parent, false);

        return new SourcesRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SourcesRVAdapter.ViewHolder holder, int position) {
        String izvor = izvoriList.get(position);
        holder.mIzvori.setText(izvor);
    }

    @Override
    public int getItemCount() {
        return izvoriList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mIzvori;
        private ImageView mSlika;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mIzvori = itemView.findViewById(R.id.tvImeIzvora);
            mSlika = itemView.findViewById(R.id.ivSource);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
