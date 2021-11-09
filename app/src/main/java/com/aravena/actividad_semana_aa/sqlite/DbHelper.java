package com.aravena.actividad_semana_aa.sqlite;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "LibreriaDB";
    public static final int DB_VERSION = 4;
    public static final String TABLE_LIBROS = "libros";
    public static final String TABLE_AUTOR = "autores";
    public static final String TABLE_ESTANTE = "estantes";
    public static final String TABLE_EDITORIAL = "editoriales";


    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);}

    @Override
    public void onCreate (SQLiteDatabase sqLiteDatabase){
        //Create table
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_LIBROS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL," +
                "descripcion TEXT NOT NULL," +
                "fechaP DATE NOT NULL," +
                "copias INTEGER NOT NULL," +
                "cantP INTEGER NOT NULL," +
                "autor INTEGER NOT NULL," +
                "editorial INTEGER NOT NULL," +
                "estante INTEGER NOT NULL," +
                "FOREIGN KEY (autor) REFERENCES autores(id)," +
                "FOREIGN KEY (editorial) REFERENCES editoriales(id)," +
                "FOREIGN KEY (estante) REFERENCES estantes(id))");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_AUTOR + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "apellido TEXT NOT NULL," +
                "nacionalidad TEXT NOT NULL)");


        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ESTANTE + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "letra TEXT NOT NULL," +
                "numero INTEGER NOT NULL," +
                "color TEXT NOT NULL)");


        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_EDITORIAL + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "nacionalidad TEXT NOT NULL)");


    }
    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase,int i, int i1){
        //se llama cuando hay nueva versión de la base de datos
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LIBROS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTOR);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EDITORIAL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ESTANTE);
        onCreate(sqLiteDatabase);
    }
}

