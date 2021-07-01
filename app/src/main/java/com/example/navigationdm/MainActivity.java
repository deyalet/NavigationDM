package com.example.navigationdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void btnIngresar(View view){
        Intent intent = new Intent(MainActivity.this, MostrarDatos.class);
        EditText txtUsuario = (EditText)findViewById(R.id.txtNombre);
        EditText txtContra = (EditText)findViewById(R.id.txtPass);
        Bundle b = new Bundle();
        b.putString("USER", txtUsuario.getText().toString());
        b.putString("CONTRA",txtContra.getText().toString());
        intent.putExtras(b);
        startActivity(intent);
    }

}