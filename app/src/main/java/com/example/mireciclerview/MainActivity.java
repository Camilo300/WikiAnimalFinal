package com.example.mireciclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar p;
    private TextView texto;
    private Handler handler = new Handler();

    RecyclerView MiRecycler;
    String nombre[];
    String nombre_cientifico[];
    String datos[];
    Button ubicacion;
    int imagenes [] = {R.drawable.lobo,  R.drawable.tigre,  R.drawable.delfines, R.drawable.guepardo, R.drawable.tiburon, R.drawable.leon, R.drawable.pinguino, R.drawable.elefante};
    int marca [] = {R.drawable.marcalobo,  R.drawable.marcatigre,  R.drawable.marcadelfin, R.drawable.marcaguepardo, R.drawable.marcatiburon, R.drawable.marcaleon, R.drawable.marcapinguino, R.drawable.marcaelefante};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MiRecycler = (RecyclerView) findViewById(R.id.MIRecicler);
        nombre = getResources().getStringArray(R.array.Nombre);
        nombre_cientifico = getResources().getStringArray(R.array.Nombre_cientifico);
        datos = getResources().getStringArray(R.array.Datos);
        texto = (TextView) findViewById(R.id.Label);
        p = (ProgressBar) findViewById(R.id.Pbar);
        ubicacion = (Button) findViewById(R.id.Ubicacion);;
        progreso();

        MiAdapter AD = new MiAdapter(this, this.nombre, this.nombre_cientifico,this.datos, this.imagenes, this.marca);
        MiRecycler.setAdapter(AD);
        MiRecycler.setLayoutManager(new LinearLayoutManager(this));
        MiRecycler.setVisibility(View.GONE);
        ubicacion.setVisibility(View.GONE);

    }
    public void progreso()
    {
        p.setMax(100);
        p.setProgress(0);

        new Thread(new Runnable() {

            /*
            Se inicia un HILO, el cual permitira ejecutar en segundo plano
            la carga de la barra de progreso
             */
            @Override
            public void run() {

                for ( int i= 0; i <= 100; i++) {
                    final int finalI = i;
                    handler.post(new Runnable() {
                        public void run() {
                            p.setProgress(finalI);
                            texto.setText(finalI +" % ");
                        }
                    });

                    try {Thread.sleep(50);
                    } catch (Exception ex) { }
                }

                runOnUiThread(new Runnable() {
                    public void run() {
                        texto.setVisibility(View.GONE);
                        p.setVisibility(View.GONE);
                        MiRecycler.setVisibility(View.VISIBLE);
                        ubicacion.setVisibility(View.VISIBLE);
                        ubicacion.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent inten = new Intent(MainActivity.this,GPS.class);
                                startActivity(inten);
                            }
                        });
                    }
                });
            }
        }).start();


    }
}