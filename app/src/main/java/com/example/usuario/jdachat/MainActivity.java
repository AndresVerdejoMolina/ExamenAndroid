package com.example.usuario.jdachat;;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements BlankFragment.LoginListener {
    private FirebaseAuth mAuth;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        Toolbar tool = findViewById(R.id.toolbar);
        setSupportActionBar(tool);

        if (networkInfo != null && networkInfo.isConnected()) {
            if(networkInfo.getType()==ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(getApplicationContext(), "<WARNING>¡CIUDADO, estás usando datos mobiles!<WARNING>",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "No tienes conexion a internet!",
                    Toast.LENGTH_SHORT).show();
        }
        ImageButton button = (ImageButton) findViewById(R.id.searchImageButton);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                FragmentManager fm= getSupportFragmentManager();
                Fragment fragment = new BlankFragment();
                fm.beginTransaction().replace(R.id.fragment_container, fragment, "LOGIN").commit();
            }
        });
    }

    @Override
    public void login(final String fecha, final String comensales, final String nombre, final String telefono, final String comentarios) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
                if (TextUtils.isEmpty(fecha) && TextUtils.isEmpty(comensales) && TextUtils.isEmpty(nombre) && TextUtils.isEmpty(telefono) && TextUtils.isEmpty(comentarios)) {
                    Toast.makeText(getApplicationContext(), "No puedes reservar, tienes campos vacios!", Toast.LENGTH_SHORT).show();
                    return;
                }
            Reservas reservas = new Reservas(fecha, nombre, telefono, comensales,comentarios);
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("reserva").child(reservas.getFecha()).setValue(fecha);


        } else {
            Toast.makeText(getApplicationContext(), "Que no tienes conexion a internet te dicen...",
                    Toast.LENGTH_LONG).show();
        }
    }
}
