package com.example.mireciclerview;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Agregar extends Activity {
    private EditText et1, et2, et3, et4, et5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editTextTextMultiLine);
        et4 = (EditText) findViewById(R.id.editText4);
        et5 = (EditText) findViewById(R.id.editText5);

    }

    public void alta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "misanimales", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String nombre    = et1.getText().toString().trim().toUpperCase();
        String nombreC = et2.getText().toString().trim().toUpperCase();
        String datos = et3.getText().toString().trim().toUpperCase();
        String longitud = et4.getText().toString().trim().toUpperCase();
        String latitud = et5.getText().toString().trim().toUpperCase();

        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("nombre cientifico", nombreC);
        registro.put("datos", datos);
        registro.put("longitud", longitud);
        registro.put("latitud", latitud);

        bd.insert("animal", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        Toast.makeText(this, "Datos cargados", Toast.LENGTH_SHORT).show();
    }

    public void consulta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "misanimales", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre = et1.getText().toString().trim().toUpperCase();
        Cursor fila = bd.rawQuery(
                "select nombreC, datos, longitud, latitud from animal where nombre='"+nombre+"'", null);

        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
            et5.setText(fila.getString(3));
        }
        else {
            Toast.makeText(this, "No existe registro con ese nombre",
                    Toast.LENGTH_SHORT).show();
            bd.close();
        }
    }

    public void baja(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "misanimales", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre = et1.getText().toString().trim().toUpperCase();
        int cant = bd.delete("animal", "nombre='" + nombre+"'", null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");

        if (cant == 1) {
            Toast.makeText(this, "Eliminado",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "No existe registro",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "misanimales", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre      = et1.getText().toString().trim().toUpperCase();
        String nombreC   = et2.getText().toString().trim().toUpperCase();
        String datos   = et3.getText().toString().trim().toUpperCase();
        String longitud   = et4.getText().toString().trim().toUpperCase();
        String latitud   = et5.getText().toString().trim().toUpperCase();

        ContentValues registro = new ContentValues();

        registro.put("nombre", nombre);
        registro.put("nombre cientifico", nombreC);
        registro.put("datos", datos);
        registro.put("longitud", longitud);
        registro.put("latitud", latitud);

        int cant = bd.update("animal", registro, "nombre= '" + nombre+"'", null);
        bd.close();
        if (cant == 1) {
            Toast.makeText(this, "Datos modificados con éxito", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "No existe registro", Toast.LENGTH_SHORT).show();
        }
    }
}