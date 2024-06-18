package com.example.randomusergenerator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {
    private static final String LOGTAG = DetailActivity.class.getName();

    public static final String PERSON_ID = "personId"; // Provide some unique string here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int id = getIntent().getExtras().getInt(PERSON_ID);
        Log.d(LOGTAG, "onCreate called with PERSON_ID = " + id);

        Person person = PersonManager.getPerson(id);
        Log.d(LOGTAG, "person[id] = " + person.getFirstName() + "-" + person.getLastName() + "-" + person.getPicture());

        TextView nameView = (TextView) findViewById(R.id.nameView);
        nameView.setText(person.getFullName());

        TextView allInfoView = (TextView) findViewById(R.id.allDataView);
        allInfoView.setText(person.getAllInfo());

        ImageView pictureView = (ImageView) findViewById(R.id.pictureView);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(person.getPicture()).getContent());
            pictureView.setImageBitmap(bitmap);
        } catch (Exception e) {
            Log.d(LOGTAG, "couldnt find image");
        }
    }

}
