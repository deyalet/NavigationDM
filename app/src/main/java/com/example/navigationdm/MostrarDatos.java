package com.example.navigationdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.HashMap;
import java.util.Map;

public class MostrarDatos extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    private TextView textUsuario;
    private DatabaseReference mRootReference;
    private TextView txtNombreUser;
    private ImageView imageView;
    private TextView control1;
    private TextView control2;
    private TextView control3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drwerLayout);
        navigationView = findViewById(R.id.navigationView);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();


        //Bundle bundle = this.getIntent().getExtras();
        //Map<String, String> datos = new HashMap<String, String>();
        //WebService ws= new WebService("http://uealecpeterson.net/ws/login.php?usr="
          //      + bundle.getString("Usr") + "&pass=" + bundle.getString("clave"),
            //    datos, actValidaLogin.this, actValidaLogin.this);


       mRootReference = FirebaseDatabase.getInstance().getReference();


        mRootReference.child("usuarios").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange( DataSnapshot datasnapshot) {
                for(final DataSnapshot snapshot : datasnapshot.getChildren()){

                    mRootReference.child("usaurios").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (datasnapshot.exists()) {

                                userDatos user = snapshot.getValue(userDatos.class);
                                String url = user.getUrl();
                                String nombre = user.getNombre();
                                String cargo = user.getCargo();
                                String c1 = user.getC1();
                                String c2 = user.getC2();
                                String c3 = user.getC3();

                                Log.e("NombreUsuario", "" + nombre);
                                Log.e("Cargo", "" + cargo);
                                Log.e("Control 1", "" + c1);
                                Log.e("Control 2", "" + c2);
                                Log.e("Control 3", "" + c3);

                                txtNombreUser.setText(nombre);
                                control1.setText(c1);
                                control2.setText(c2);
                                control3.setText(c3);
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });{

        }

    }
}