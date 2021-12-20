package com.example.newsapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapp.R;
import com.example.newsapp.adapter.NewsRVAdapter;
import com.example.newsapp.models.ApiManager;
import com.example.newsapp.models.Articles;
import com.example.newsapp.models.NewsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements Callback<NewsResponse> {

    private RecyclerView mRecyclerView;
    private View view;
    private NewsRVAdapter newsAdapter;

    ArrayList<Articles> articles = new ArrayList<>();
    ArrayList<String> sources = new ArrayList<>();

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

        return view;
    }

    @Override
    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
        if (response.isSuccessful() && response.body() != null){

            for (int i = 0; i < response.body().articles.size(); i++){
                articles.add(response.body().getArticles().get(i));
            }

            //DOHVACANJE IZVORA
            /*for (int i = 0; i < articles.size(); i++){
                if (sources.contains(articles.get(i).getSource().getName())) {

                }
                else{
                    sources.add(articles.get(i).getSource().getName());
                }
            }*/

            newsAdapter = new NewsRVAdapter(articles, getContext());

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.setAdapter(newsAdapter);

        }
    }

    @Override
    public void onFailure(Call<NewsResponse> call, Throwable t) {
        t.printStackTrace();
    }
}