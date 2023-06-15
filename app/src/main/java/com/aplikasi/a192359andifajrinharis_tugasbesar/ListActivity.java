package com.aplikasi.a192359andifajrinharis_tugasbesar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Ref;

public class ListActivity extends AppCompatActivity {
    String[] daftar;
    String[][] kirim;
    ListView listView;
    Menu menu;
    protected Cursor cursor;
    DatabaseHelper db;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = new DatabaseHelper(this);
        RefreshList();
        list = (ListView) findViewById(R.id.lisView);

    }

    public void RefreshList() {
        cursor = db.selectListTempat();
        if(cursor != null){
            daftar = new String[cursor.getCount()];
            kirim = new String[cursor.getCount()][3];
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {

                cursor.moveToPosition(i);
                daftar[i] = "Lokasi: "
                        + cursor.getString(3).toString() + "\nLatitude : "
                        + cursor.getString(1).toString() + "   Longitude : "
                        + cursor.getString(2).toString();
                kirim[i][0] = cursor.getString(3).toString();
                kirim[i][1] = cursor.getString(1).toString();
                kirim[i][2] = cursor.getString(2).toString();

                listView = (ListView) findViewById(R.id.lisView);
                listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
                listView.setSelected(true);
            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                    final String lat = kirim[arg2][1];
                    final String lon = kirim[arg2][2];
                    final String lokasi = kirim[arg2][0];
                    Intent home = new Intent(ListActivity.this, MapsActivity.class);
                    home.putExtra("lat",lat);
                    home.putExtra("longs",lon);
                    home.putExtra("lokasi",lokasi);

                    startActivity(home);
                }
            });
        }else{
            daftar = new String[1];
            daftar[0] = "Tidak Ada Data";

            listView = (ListView) findViewById(R.id.lisView);
            listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
            listView.setSelected(true);
        }



    }

}