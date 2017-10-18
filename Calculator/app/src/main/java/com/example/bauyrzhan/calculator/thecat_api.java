package com.example.bauyrzhan.calculator;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;

public class thecat_api extends AppCompatActivity {

    ArrayList<String> cats = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();;
    ArrayList<String> ids = new ArrayList<>();;
    ArrayList<String> sources = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thecat_api);
        InputStream inputStream = getResources().openRawResource(R.raw.thecat);
        XmlToJson xmlToJson = new XmlToJson.Builder(inputStream, null).build();
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // convert to a JSONObject
        JSONObject jsonObject = xmlToJson.toJson();
        try {
            JSONArray images = jsonObject.getJSONObject("response").getJSONObject("data").getJSONObject("images").getJSONArray("image");
            for (int i = 0; i < images.length(); i++) {
                JSONObject img = images.getJSONObject(i);
                cats.add("Cat "+(i+1));
                String url = img.getString("url");
                urls.add(url);
                String id = img.getString("id");
                ids.add(id);
                String source = img.getString("source_url");
                sources.add(source);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, cats);
        ListView catListView = (ListView) findViewById(R.id.catListView);
        catListView.setAdapter(arrayAdapter);

        catListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    new DownloadImageTask((ImageView) findViewById(R.id.catImg))
                            .execute(urls.get(position));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                TextView catID = (TextView) findViewById(R.id.catID);
                catID.setText("ID: "+ids.get(position).toString());
                TextView catSource = (TextView) findViewById(R.id.catSource);
                catSource.setText("Source: "+sources.get(position).toString());
                TextView catURL = (TextView) findViewById(R.id.catURL);
                catURL.setText("URL: "+urls.get(position).toString());
            }
        });
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
