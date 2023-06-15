package com.aplikasi.a192359andifajrinharis_tugasbesar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button register;
    TextView login;
    EditText  stb, nama, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        stb = (EditText)findViewById(R.id.edtText_stb);
        nama = (EditText)findViewById(R.id.edtText_nama);
        username = (EditText)findViewById(R.id.edtText_usernameRegist);
        password = (EditText)findViewById(R.id.edtText_passwordRegist);
        login = (TextView) findViewById(R.id.btn_loginRegist);
        register = (Button)findViewById(R.id.btn_registerRegist);

        //login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

        //register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strStb = stb.getText().toString();
                String strNama = nama.getText().toString();
                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();

                //check form
                if(stb.getText().toString().length()==0) {
                    stb.setError("Harap diisi!");
                }else if (nama.getText().toString().length()==0){
                    nama.setError("Harap diisi!");
                }else if (username.getText().toString().length()==0){
                    username.setError("Harap diisi!");
                }else if (password.getText().toString().length()==0){
                    password.setError("Harap diisi!");
                }else {
                    Boolean daftar = db.insertUser(strStb, strNama, strUsername, strPassword);
                    if (daftar == true) {
                        Toast.makeText(getApplicationContext(), "Daftar Berhasil", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Daftar Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
