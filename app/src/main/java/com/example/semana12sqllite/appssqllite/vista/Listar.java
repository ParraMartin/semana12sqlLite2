package com.example.semana12sqllite.appssqllite.vista;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semana12sqllite.appssqllite.controlador.utility;
import com.example.semana12sqllite.appssqllite.controlador.ConexionHelper;
import com.example.semana12sqllite.appssqllite.modelo.Usuario;
import com.example.semana12sqllite.R;

import java.util.ArrayList;

public class Listar extends AppCompatActivity {
    ListView listViewUsuarios;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUsuarios;

    ConexionHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        // Configuración del ListView
        listViewUsuarios=(ListView) findViewById(R.id.listviewUsuarios);

        // Inicialización de la conexión a la base de datos
        conn=new ConexionHelper(getApplicationContext(), "bd_usuarios", null, 1 );
        listViewUsuarios= (ListView) findViewById(R.id.listviewUsuarios);

        consultarListaUsuarios();

        // Creación de un ArrayAdapter para mostrar la lista en el ListView
        ArrayAdapter adaptador=new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacion);
        listViewUsuarios.setAdapter(adaptador);

        // Configuración del evento de clic en un elemento de la lista
        listViewUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String informacion="id: " + listaUsuarios.get(position).getId() +"\n";
                informacion+="Nombre: " + listaUsuarios.get(position).getNombre() +"\n";
                informacion+="Correo: " + listaUsuarios.get(position).getCorreo() +"\n";
                Toast.makeText(getApplicationContext(), informacion,Toast.LENGTH_LONG).show();
            }
        });

    }

    private void consultarListaUsuarios() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Usuario usuario=null;
        listaUsuarios=new ArrayList<Usuario>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ utility.TABLA_USUARIO, null);
        while (cursor.moveToNext()){
            usuario=new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setCorreo(cursor.getString(2));
            listaUsuarios.add(usuario);
        }
        obtenerLista();
    }

    private void obtenerLista(){
        listaInformacion=new ArrayList<String>();
        for (int i=0; i<listaUsuarios.size(); i++){
            listaInformacion.add(listaUsuarios.get(i).getId()+ " - "
                    + listaUsuarios.get(i).getNombre());
        }
    }

}