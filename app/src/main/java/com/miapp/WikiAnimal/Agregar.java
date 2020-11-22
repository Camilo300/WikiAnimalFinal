package com.miapp.WikiAnimal;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Agregar extends Activity {
    private EditText et1, et2, et3, et4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editTextTextMultiLine);
        et4 = (EditText) findViewById(R.id.editText4);

    }

    public void alta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "misanimales", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String nombre = et1.getText().toString().trim().toUpperCase();
        String nombreCientifico = et2.getText().toString().trim().toUpperCase();
        String datos = et3.getText().toString().trim().toUpperCase();
        String ubicacion = et4.getText().toString().trim().toUpperCase();

        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("nombreCientifico", nombreCientifico);
        registro.put("datos",datos);
        registro.put("ubicacion", ubicacion);

        bd.insert("animal", null, registro);
        bd.close();

        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        Toast.makeText(this, "Datos cargados", Toast.LENGTH_SHORT).show();
    }

    // Hacemos búsqueda de usuario por RUN
    public void consulta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "misanimales", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre = et1.getText().toString().trim().toUpperCase();
        Cursor fila = bd.rawQuery(
                "select nombreCientifico, datos, ubicacion from animal where nombre='"+nombre+"'", null);

        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe ningún registro",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void baja(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "misanimales", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre = et1.getText().toString().trim().toUpperCase();
        // aquí borro de la base de datos el usuario por el run
        int cant = bd.delete("animal", "nombre='" + nombre+"'", null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");

        if (cant == 1)
            Toast.makeText(this, "Eliminado",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe registro",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "misanimales", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre      = et1.getText().toString().trim().toUpperCase();
        String nombreCientifico   = et2.getText().toString().trim().toUpperCase();
        String datos   = et3.getText().toString().trim().toUpperCase();
        String ubicacion   = et4.getText().toString().trim().toUpperCase();

        ContentValues registro = new ContentValues();

        registro.put("nombreCientifico", nombreCientifico);
        registro.put("datos", datos);
        registro.put("ubicacion", ubicacion);

        int cant = bd.update("animal", registro, "nombre= '" + nombre+"'", null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "Datos modificados con éxito", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existen datos",Toast.LENGTH_SHORT).show();
    }
}