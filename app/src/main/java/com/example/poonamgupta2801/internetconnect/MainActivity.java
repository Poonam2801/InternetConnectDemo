package com.example.poonamgupta2801.internetconnect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

   ImageView downloadedImage;
    public void downloadImage(View view)  {


        try {
            DownloadImage downloadImageTask = new DownloadImage ();
            Bitmap myImage;
            myImage=downloadImageTask.execute ( "https://pbs.twimg.com/profile_images/897250392022540288/W1T-QjML_400x400.jpg" ).get ();
            downloadedImage.setImageBitmap(myImage);

        } catch (Exception e) {
            e.printStackTrace ();
        }

    }
    public class DownloadImage extends AsyncTask <String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                
                URL url = new URL ( urls[0] );
                HttpsURLConnection urlConnection= (HttpsURLConnection) url.openConnection ();
                urlConnection.connect ();
                InputStream inputStream = urlConnection.getInputStream ();
                Bitmap bitmap= BitmapFactory.decodeStream ( inputStream );
                return bitmap;


            } catch (MalformedURLException e) {
                e.printStackTrace ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
            return null;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        Button downLoad=(Button)findViewById ( R.id.downloadButton );
        downloadedImage=(ImageView)findViewById ( R.id.downloadImage );

    }
}
