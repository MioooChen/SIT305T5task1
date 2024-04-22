package com.example.task1;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsApiClient {

    private OkHttpClient client;
    private static final String newsURL = "https://newsdata.io/api/1/news?apikey=pub_42476ea21c974b8befb3e3bda891371f54a75&country=us";

    public NewsApiClient() {
        client = new OkHttpClient();
    }

    public List<NewsItem> fetchTopNews() throws IOException {
        List<NewsItem> newsItems = new ArrayList<>();

        Request request = new Request.Builder()
                .url(newsURL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String jsonData = response.body().string();
            newsItems = processJsonData(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newsItems;
    }

    public List<NewsItem> fetchListNews() throws IOException {
        List<NewsItem> newsItems = new ArrayList<>();

        Request request = new Request.Builder()
                .url(newsURL + "&category=business")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String jsonData = response.body().string();
            newsItems = processJsonData(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newsItems;
    }

    public List<NewsItem> fetchRelatedNews() throws IOException {
        List<NewsItem> newsItems = new ArrayList<>();

        Request request = new Request.Builder()
                .url(newsURL + "&category=food")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String jsonData = response.body().string();
            newsItems = processJsonData(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newsItems;
    }

    private List<NewsItem> processJsonData(String jsonData) {
        List<NewsItem> newsItems = new ArrayList<>();
        Gson gson = new Gson();
        NewsApiResponse newsApiResponse = gson.fromJson(jsonData, NewsApiResponse.class);

        if (newsApiResponse != null && newsApiResponse.status.equals("success")) {
            newsItems = newsApiResponse.results;
        }

        return newsItems;
    }
}

class NewsApiResponse {
    public String status;
    public int totalResults;
    public List<NewsItem> results;
}