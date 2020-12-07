package com.miapp.WikiAnimal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.miapp.WikiAnimal.Modelo.Animal;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Agregar extends AppCompatActivity {
    EditText txtNom, txtNomC, txtDt, txtUbicacion;
    Button btnIn, btnElim, btnBusc, btnAct;

    FirebaseDatabase FBData;
    DatabaseReference DBReference,DBMostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        txtNom = (EditText) findViewById(R.id.editText);
        txtNomC = (EditText) findViewById(R.id.editText2);
        txtDt = (EditText) findViewById(R.id.editTextTextMultiLine);
        txtUbicacion = (EditText) findViewById(R.id.editText4);

        btnIn = (Button) findViewById(R.id.Ingresar);
        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NOMBRE = txtNom.getText().toString().trim().toUpperCase();
                String NOMBREC = txtNomC.getText().toString().trim().toUpperCase();
                String DATOS = txtDt.getText().toString().trim().toUpperCase();
                String UBICACION = txtUbicacion.getText().toString().trim().toUpperCase();
                if(NOMBRE.equals("")||NOMBREC.equals("")||DATOS.equals("")||UBICACION.equals(""))
                {
                    validar();
                }
                else {
                    Animal A = new Animal();
                    A.setNombre(NOMBRE);
                    A.setNombreC(NOMBREC);
                    A.setDatos(DATOS);
                    A.setubicacion(UBICACION);
                    DBReference.child("Animal").child(A.getNombre()).setValue(A);
                    Toast.makeText(Agregar.this, "GUARDANDO !!!!!", Toast.LENGTH_LONG).show();
                    limpiar();
                }
            }
        });


        btnElim = (Button) findViewById(R.id.Eliminar);
        btnElim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NOMBRE = txtNom.getText().toString().trim().toUpperCase();
                if(NOMBRE.equals(""))
                {
                    validar();
                }
                else {
                    DBReference.child("Animal").child(NOMBRE).removeValue();
                    Toast.makeText(Agregar.this, "ELIMINANDO !!!!!", Toast.LENGTH_LONG).show();
                    limpiar();
                }
            }
        });

        btnBusc = (Button) findViewById(R.id.Consultar);
        btnBusc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int[] cont = {0};
                DatabaseReference bbdd = FirebaseDatabase.getInstance().getReference("Animal");
                /*DBMostrar = FirebaseDatabase.getInstance().getReference().child("Persona");
                DBMostrar.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            Toast.makeText(MainActivity.this, "ACA", Toast.LENGTH_LONG).show();
                            String RUN = txtRun.getText().toString().trim().toUpperCase();
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                Persona miP = snapshot.getValue(Persona.class);
                                if (miP.getRun().equals(RUN)) {
                                    txtNom.setText(miP.getNombre());
                                    txtCiudad.setText(miP.getCiudad());
                                    txtNum.setText(miP.getNUmero());
                                    break;
                                }
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                */
                String NOMBRE = txtNom.getText().toString().trim().toUpperCase();
                Query q=bbdd.orderByChild("nombre").equalTo(NOMBRE);

                /*
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int cont=0;
                        for(DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                            cont++;
                            Toast.makeText(MainActivity.this, "He encontrado "+cont, Toast.LENGTH_LONG).show();

                            Persona td =dataSnapshot.getValue(Persona.class);


                            System.out.println(td.getRun());



                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                */
                q.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {

                        for(DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                            cont[0]++;
                            System.out.println(dataSnapshot.getValue());

                            Animal miA = dataSnapshot.getValue(Animal.class);
                            txtNomC.setText(miA.getNombreC().toString());
                            txtDt.setText(miA.getDatos().toString());
                            txtUbicacion.setText(miA.getubicacion().toString());
                            Toast.makeText(Agregar.this, "ENCONTRADO !!! ", Toast.LENGTH_LONG).show();
                            break;
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                if(cont[0] ==0)
                {
                    Toast.makeText(Agregar.this, "NO EXISTE ESE ANIMAL!!! ", Toast.LENGTH_LONG).show();
                }
            }




        });


        btnAct = (Button) findViewById(R.id.Actualizar);
        btnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Agregar.this, "ACTUALIZANDO !!!!!", Toast.LENGTH_LONG).show();
            }
        });

        iniciar_firebase();

    } // FIN ONCREATE

    private void iniciar_firebase() {
        FirebaseApp.initializeApp(this);
        this.FBData = FirebaseDatabase.getInstance();
        this.DBReference = this.FBData.getReference();
    }

    private void limpiar() {
        this.txtNom.setText("");
        this.txtNomC.setText("");
        this.txtDt.setText("");
        this.txtUbicacion.setText("");
    }

    private void validar() {
        if(txtNom.getText().toString().equals(""))
        {
            txtNom.setError("Escriba Nombre");
        }
        if(txtNomC.getText().toString().equals(""))
        {
            txtNomC.setError("Escriba Nombre Cientifico");
        }
        if(txtDt.getText().toString().equals(""))
        {
            txtDt.setError("Escriba Datos");
        }
        if(txtUbicacion.getText().toString().equals(""))
        {
            txtUbicacion.setError("Escriba Ubicacion");
        }

    }


}