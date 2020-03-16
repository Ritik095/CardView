package com.example.quotes_apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class message extends AppCompatActivity {

    EditText edit;
    TextView text;
    Button  butn;
    ImageView img1,img2,img3,img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        edit = (EditText)findViewById(R.id.edit);
        butn = (Button)findViewById(R.id.button);
        text = (TextView)findViewById(R.id.text_view);

        img1 = (ImageView)findViewById(R.id.imageView);
        img2 = (ImageView)findViewById(R.id.imageView2);
        img3 = (ImageView)findViewById(R.id.imageView3);
        img4 = (ImageView)findViewById(R.id.imageVIew4);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent whats = new Intent(Intent.ACTION_VIEW,Uri.parse("com.whatsapp"));
               // i.setAction(Intent.ACTION_SEND);
                //i.setPackage("com.whatsapp");

                startActivity(whats);
                return;

            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fb = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/"));
                startActivity(fb);
                return;
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent insta = new Intent(Intent.ACTION_VIEW,Uri.parse("com.insta"));
                startActivity(insta);
                return;
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
    }
}
