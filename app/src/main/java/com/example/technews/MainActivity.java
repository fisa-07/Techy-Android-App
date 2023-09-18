package com.example.technews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    final private String API = "d4ea897a6ac245d38377c25e591e0beb";
    private NewsApiClient newsApiClient = new NewsApiClient(API);
    private ArrayList<String> news_title = new ArrayList<>();
    private ArrayList<String> news_url = new ArrayList<>();
    private ArrayList<String> news_source = new ArrayList<>();
    private ArrayList<String> news_author = new ArrayList<>();
    private ArrayList<String> news_image = new ArrayList<>();
    ProgressBar progress;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = findViewById(R.id.progressid);
        list = findViewById(R.id.listviewid);

        getNews();

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),news_title,news_source,news_author,news_image);
        list.setAdapter(customAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, WebView_Activity.class);
                intent.putExtra("url",news_url.get(i));
                startActivity(intent);
            }
        });
    }

    void getNews(){
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q("technology")
                        .language("en")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        progress.setVisibility(View.GONE);
                        for(int i = 0;i < 80;i++){
                            String title = response.getArticles().get(i).getTitle();
                            String source = response.getArticles().get(i).getPublishedAt();
                            source = source.substring(0,10);
                            String author = response.getArticles().get(i).getAuthor();
                            if(author.length()>25){
                                author = "Miscellaneous";
                            }
                            String newsurl = response.getArticles().get(i).getUrl();
                            String image = response.getArticles().get(i).getUrlToImage();

                            news_title.add(title);
                            news_source.add(source);
                            news_author.add(author);
                            news_url.add(newsurl);
                            news_image.add(image);
                        }
                    }
                    @Override
                    public void onFailure(Throwable throwable) {
                        String msg = throwable.getMessage();
                        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }
}