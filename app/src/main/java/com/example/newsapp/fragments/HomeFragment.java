package com.example.newsapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapp.R;
import com.example.newsapp.adapter.NewsRVAdapter;
import com.example.newsapp.adapter.SourcesRVAdapter;
import com.example.newsapp.interfaces.RecyclerViewClickInterface;
import com.example.newsapp.listeners.SourceListener;
import com.example.newsapp.models.ApiManager;
import com.example.newsapp.models.Articles;
import com.example.newsapp.models.NewsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements Callback<NewsResponse>, RecyclerViewClickInterface {

    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewSource;
    private View view;
    private ImageButton mBtnRefresh;
    private NewsRVAdapter newsAdapter;
    private SourcesRVAdapter sourceAdapter;

    ArrayList<Articles> articles = new ArrayList<>();
    ArrayList<Articles> articlesList = new ArrayList<>();
    ArrayList<Articles> selectedSourceList = new ArrayList<>();
    ArrayList<String> sources = new ArrayList<>();
    ArrayList<String> izvori = new ArrayList<>();

    public SourceListener sourceListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApiManager.getInstance().service().getArticles().enqueue(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerViewSource = view.findViewById(R.id.recyclerViewSource);
        mBtnRefresh = view.findViewById(R.id.btnRefresh);

        sourceListener.setSource(sources);

        mBtnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSource(sources);

                sourceListener.setObrisi(1);

                //RV Vijesti
                newsAdapter = new NewsRVAdapter(articles, getContext());

                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mRecyclerView.setAdapter(newsAdapter);
            }
        });

        return view;
    }

    @Override
    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
        if (response.isSuccessful() && response.body() != null){

            for (int i = 0; i < response.body().articles.size(); i++){
                articles.add(response.body().getArticles().get(i));
            }

            //DOHVACANJE IZVORA
            for (int i = 0; i < articles.size(); i++){
                if (sources.contains(articles.get(i).getSource().getName())) {

                }
                else{
                    sources.add(articles.get(i).getSource().getName());
                }
            }

            getSource(sources);

            //RV Vijesti
            newsAdapter = new NewsRVAdapter(articles, getContext());

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.setAdapter(newsAdapter);
        }
    }

    public void getSource(ArrayList<String> source){
        //RV Izvori
        sourceAdapter = new SourcesRVAdapter(source, getContext(), this);

        mRecyclerViewSource.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewSource.setAdapter(sourceAdapter);
    }

    @Override
    public void onFailure(Call<NewsResponse> call, Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (sourceListener.getIzvor() != null) {
            articlesList.clear();
            izvori.clear();

            for (int i = 0; i < sourceListener.getIzvor().size(); i++) {

                for (int j = 0; j < articles.size(); j++){
                    if (sourceListener.getIzvor().get(i).equals(articles.get(j).getSource().getName())){
                        articlesList.add(articles.get(j));

                        if (!izvori.contains(articles.get(j).getSource().getName())) {
                            izvori.add(articles.get(j).getSource().getName());
                        }
                    }
                }
            }

            if (sourceListener.getIzvor().isEmpty()){
                getSource(sources);

                //RV Vijesti
                newsAdapter = new NewsRVAdapter(articles, getContext());

                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mRecyclerView.setAdapter(newsAdapter);
            }
            else{
                getSource(izvori);

                //RV Vijesti
                newsAdapter = new NewsRVAdapter(articlesList, getContext());

                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mRecyclerView.setAdapter(newsAdapter);
            }
        }
    }

    @Override
    public void onItemClick(int position) {
        String izvorArticle = articles.get(position).getSource().getName();

        selectedSourceList.clear();

        if (!izvori.isEmpty()){
            String izvor = izvori.get(position);
            for (int i = 0; i < articlesList.size(); i++){
                if (izvor.equals(articlesList.get(i).getSource().getName())){
                    selectedSourceList.add(articlesList.get(i));
                }
            }

            //RV Vijesti
            newsAdapter = new NewsRVAdapter(selectedSourceList, getContext());

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.setAdapter(newsAdapter);
        }
        else{
            for (int i = 0; i < articles.size(); i++){
                if (izvorArticle.equals(articles.get(i).getSource().getName())){
                    selectedSourceList.add(articles.get(i));
                }
            }

            //RV Vijesti
            newsAdapter = new NewsRVAdapter(selectedSourceList, getContext());

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.setAdapter(newsAdapter);
        }

    }
}