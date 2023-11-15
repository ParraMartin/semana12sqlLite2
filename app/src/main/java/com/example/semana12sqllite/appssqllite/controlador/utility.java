package com.example.semana12sqllite.appssqllite.controlador;

public class utility {
    public static final String TABLA_USUARIO = "Usuario";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_CORREO = "correo";

    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " +
            TABLA_USUARIO + " (" +
            CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CAMPO_NOMBRE + " TEXT, " +
            CAMPO_CORREO + " TEXT)";

}
