package com.samuelvialle.recyclerrssreader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    /** Déclaration des variables globales **/
    private String RSS_URL = "https://www.cnnturk.com/feed/rss/dunya/news"; // La version Turc
    private PullParseXML pullAndParseXML;
    private List<NewsModel> newsList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pullAndParseXML = new PullParseXML(RSS_URL);
        pullAndParseXML.downloadXML();

        while (pullAndParseXML.parsingComplete) ;
        newsList = pullAndParseXML.getPostList().subList(1, pullAndParseXML.getPostList().size());

        recyclerView = findViewById(R.id.recycler_view);

        NewsAdapter adp = new NewsAdapter(this, newsList);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adp);
        adp.setOnItemClickListener(onItemNewsClickListener);
    }

    View.OnClickListener onItemNewsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int i = viewHolder.getBindingAdapterPosition();
            NewsModel item = newsList.get(i);

            Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);
            intent.putExtra("link", item.getLink());
            startActivity(intent);
        }
    };
}