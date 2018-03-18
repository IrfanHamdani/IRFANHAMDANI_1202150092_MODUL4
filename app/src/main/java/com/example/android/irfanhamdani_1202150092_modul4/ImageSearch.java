package com.example.android.irfanhamdani_1202150092_modul4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

public class ImageSearch extends AppCompatActivity {

    EditText url;
    Button btn_carigambar ;
    ImageView gmbr;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);

        url =(EditText)findViewById(R.id.edit_carigambar);

        gmbr = (ImageView)findViewById(R.id.gambar);
        btn_carigambar = (Button) findViewById(R.id.btn_carigambar);

        btn_carigambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String linkurl = url.getText().toString();
                new DownloadImage().execute(linkurl);
            }
        });
    }

    private class DownloadImage extends AsyncTask<String,Void,Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(ImageSearch.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Download Image Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {

            String imageURL = URL[0];
            Bitmap bitmap = null;
            try {
                // Download Image from URL
                InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // Set the bitmap into ImageView
            gmbr.setImageBitmap(result);
            // Close progressdialog
            mProgressDialog.dismiss();
        }
    }
}