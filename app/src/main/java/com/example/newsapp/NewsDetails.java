package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetails extends AppCompatActivity {

    String title,content,description,imageURL,url;
    private TextView mTextViewTitle, mTextViewSubTitle, mTextViewContent;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        mTextViewTitle = findViewById(R.id.tvNewsTitle);
        mTextViewSubTitle = findViewById(R.id.tvNewsSubTitle);
        mTextViewContent = findViewById(R.id.tvNewsContent);
        mImageView = findViewById(R.id.imageViewNews);

        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        description = getIntent().getStringExtra("description");
        imageURL = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");

        mTextViewTitle.setText(title);
        mTextViewSubTitle.setText(description);
        mTextViewContent.setText(content);
        Picasso.get().load(imageURL).into(mImageView);
    }
}