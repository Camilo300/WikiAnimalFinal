package com.example.mireciclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DetallesActivity extends AppCompatActivity {

    private RadioGroup RG, RG2, RG3, RG4;
    private RadioButton RB, RB2, RB3, RB4;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        String nombre = "";
        String nombreC = "";
        String datos = "";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            nombre = extras.getString("nombre");
            nombreC = extras.getString("nombreC");
            datos = extras.getString("datos");


        }

        final TextView tvnombre = (TextView) findViewById(R.id.tvnombre);
        tvnombre.setText(nombre);
        final TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(nombreC);
        final TextView data = (TextView) findViewById(R.id.data);
        data.setText(datos);
        final ImageView img = (ImageView)  findViewById(R.id.imageView);

        RG = (RadioGroup) findViewById(R.id.rgb1);
        RG2 = (RadioGroup) findViewById(R.id.radioGroup2);
        RG3 = (RadioGroup) findViewById(R.id.rgb3);
        RG4 = (RadioGroup) findViewById(R.id.radioGroup4);

        RB = (RadioButton) RG.findViewById(RG.getCheckedRadioButtonId());

        RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /*
            Se establece un escuchador del click en el RADIOBUTTON GROUP
            Al presionar un BOTON,  se captura el GRUPO al cual pertenece y el ID
             */
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RB = (RadioButton) radioGroup.findViewById(i);
                String etiq = RB.getText().toString();
                boolean chk = RB.isChecked();
                if(chk)
                {
                    if(etiq.equals("Ocultar nombre")){
                        tvnombre.setVisibility(View.GONE);
                    }
                    else{
                        tvnombre.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        RB2 = (RadioButton) RG2.findViewById(RG2.getCheckedRadioButtonId());

        RG2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /*
            Se establece un escuchador del click en el RADIOBUTTON GROUP
            Al presionar un BOTON,  se captura el GRUPO al cual pertenece y el ID
             */
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RB2 = (RadioButton) radioGroup.findViewById(i);
                String etiq = RB2.getText().toString();
                boolean chk = RB2.isChecked();
                if(chk)
                {
                    if(etiq.equals("Ocultar imagen")){
                        img.setVisibility(View.GONE);
                    }
                    else{
                        img.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        RB3 = (RadioButton) RG3.findViewById(RG3.getCheckedRadioButtonId());

        RG3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /*
            Se establece un escuchador del click en el RADIOBUTTON GROUP
            Al presionar un BOTON,  se captura el GRUPO al cual pertenece y el ID
             */
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RB3 = (RadioButton) radioGroup.findViewById(i);
                String etiq = RB3.getText().toString();
                boolean chk = RB3.isChecked();
                if(chk)
                {
                    if(etiq.equals("Ocultar N.cientifico")){
                        textView.setVisibility(View.GONE);
                    }
                    else{
                        textView.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        RB4 = (RadioButton) RG4.findViewById(RG4.getCheckedRadioButtonId());

        RG4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /*
            Se establece un escuchador del click en el RADIOBUTTON GROUP
            Al presionar un BOTON,  se captura el GRUPO al cual pertenece y el ID
             */
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RB4 = (RadioButton) radioGroup.findViewById(i);
                String etiq = RB4.getText().toString();
                boolean chk = RB4.isChecked();
                if(chk)
                {
                    if(etiq.equals("Ocultar datos")){
                        data.setVisibility(View.GONE);
                    }
                    else{
                        data.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }
}