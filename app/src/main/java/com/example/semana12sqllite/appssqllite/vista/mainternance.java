package com.example.semana12sqllite.appssqllite.vista;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semana12sqllite.R;
import com.example.semana12sqllite.appssqllite.controlador.ConexionHelper;
import com.example.semana12sqllite.appssqllite.controlador.utility;

public class mainternance extends AppCompatActivity {
    EditText txtid;
    EditText txtnombre;
    EditText txtcorreo;
    Button btnconsultar, btnupdate, btndelete;

    ConexionHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainternance);

        conn = new ConexionHelper(getApplicationContext(), "bd_usuarios", null, 1);

        txtid = (EditText) findViewById(R.id.editTextId);
        txtnombre = (EditText) findViewById(R.id.editTextNombre);
        txtcorreo = (EditText) findViewById(R.id.editTextCorreo);


        btnconsultar = (Button) findViewById(R.id.btnBuscarUsuario);
        btnupdate = (Button) findViewById(R.id.btnRegistrarUsuario);
        btndelete = (Button) findViewById(R.id.btnEliminarUsuario);

        btnconsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultar();
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarUsuario();
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarUsuario();
            }
        });
    }

    // metodo elimina registro
    private void eliminarUsuario() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {txtid.getText().toString()};

        db.delete(utility.TABLA_USUARIO, utility.CAMPO_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "ATENCION, se eliminó el usuario", Toast.LENGTH_LONG).show();
        txtid.setText("");
        limpiar();
        db.close();
    }

    // metodo actualiza registro
    private void actualizarUsuario() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {txtid.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(utility.CAMPO_NOMBRE, txtnombre.getText().toString());
        values.put(utility.CAMPO_CORREO, txtcorreo.getText().toString());

        db.update(utility.TABLA_USUARIO, values, utility.CAMPO_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "ATENCION, se actualizó el usuario", Toast.LENGTH_LONG).show();
        limpiar();
        db.close();
    }

    // metodo del boton buscar
    private void consultar() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {txtid.getText().toString()};

        try {
            Cursor cursor = db.rawQuery("SELECT " + utility.CAMPO_NOMBRE + "," + utility.CAMPO_CORREO +
                    " FROM " + utility.TABLA_USUARIO + " WHERE " + utility.CAMPO_ID + "=? ", parametros);

            cursor.moveToFirst();
            txtnombre.setText(cursor.getString(0));
            txtcorreo.setText(cursor.getString(1));

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ATENCION, documento no existe", Toast.LENGTH_LONG).show();
            limpiar();
        }
    }

    private void consultarSql() {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={txtid.getText().toString()};

        try {
            //select nombre,telefono from usuario where codigo=?
            Cursor cursor=db.rawQuery("SELECT "+utility.CAMPO_NOMBRE+","+utility.CAMPO_CORREO+
                    " FROM "+utility.TABLA_USUARIO+" WHERE "+utility.CAMPO_ID+"=? ",parametros);

            cursor.moveToFirst();
            txtnombre.setText(cursor.getString(0));
            txtcorreo.setText(cursor.getString(1));

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }

    }


    // metodo limpia los text
    private void limpiar() {
        txtnombre.setText("");
        txtcorreo.setText("");
    }
}
