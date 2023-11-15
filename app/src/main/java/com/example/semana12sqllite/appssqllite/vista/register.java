package com.example.semana12sqllite.appssqllite.vista;

import android.content.ContentValues;
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

public class register extends AppCompatActivity {
    EditText textid;
    EditText txtNombre;
    EditText txtcorreo;
    Button btnregistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textid = findViewById(R.id.editTextId);
        txtcorreo = findViewById(R.id.editTextCorreo);
        txtNombre = findViewById(R.id.editTextNombre);
        btnregistrar = findViewById(R.id.btnRegistrarUsuario);

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();
            }
        });
    }

    private void registrarUsuario() {
        ConexionHelper conn = new ConexionHelper(this, "bd_usuarios", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(utility.CAMPO_ID, textid.getText().toString());
        contentValues.put(utility.CAMPO_NOMBRE, txtNombre.getText().toString());
        contentValues.put(utility.CAMPO_CORREO, txtcorreo.getText().toString());

        long idResultante = db.insert(utility.TABLA_USUARIO, null, contentValues);
        Toast.makeText(getApplicationContext(), "ATENCION, id Registrado..." + idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }
}
