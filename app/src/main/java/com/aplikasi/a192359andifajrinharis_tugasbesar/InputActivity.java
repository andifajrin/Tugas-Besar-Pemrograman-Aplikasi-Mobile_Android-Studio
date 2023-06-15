package com.aplikasi.a192359andifajrinharis_tugasbesar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText latitude, longitude, lokasi;
    Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        db = new DatabaseHelper(this);

        latitude = (EditText) findViewById(R.id.edtText_Latitude);
        longitude = (EditText) findViewById(R.id.edtText_Longitude);
        lokasi = (EditText) findViewById(R.id.edtText_Tempat);
        simpan = (Button) findViewById(R.id.btn_Simpan);

        //register
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strLatitude = latitude.getText().toString();
                String strLongitude = longitude.getText().toString();
                String strLokasi = lokasi.getText().toString();


                if (latitude.getText().toString().length()==0){
                    latitude.setError("Harap diisi!");
                }else if (longitude.getText().toString().length()==0){
                    longitude.setError("Harap diisi!");
                }else {
                    Boolean daftar = db.insertTempat(strLatitude, strLongitude, strLokasi);
                    if (daftar == true) {
                        Toast.makeText(getApplicationContext(), "Berhasil di simpan", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(InputActivity.this, MainActivity.class);
                        startActivity(loginIntent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Gagal di Simpan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}