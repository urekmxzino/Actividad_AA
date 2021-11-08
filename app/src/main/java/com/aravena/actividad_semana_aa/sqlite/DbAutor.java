package com.aravena.actividad_semana_aa.sqlite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.aravena.actividad_semana_aa.models.Autor;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DbAutor extends DbHelper{
    Context context;
    public DbAutor(@Nullable Context context){
        super(context);
        this.context = context;
    }

    public long insertarAutor(Autor autor){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        long res=0;

        ContentValues valores = new ContentValues();
        valores.put("nombre", autor.getNombre());
        valores.put("apellido",autor.getApellido());
        valores.put("nacionalidad",autor.getNacionalidad());

        res = db.insert(DbHelper.TABLE_AUTOR,null,valores);
        return res;
    }


    public ArrayList<Autor> getAutores(){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        ArrayList<Autor> autores = new ArrayList<>();
        Cursor cursor = null;
        Autor autor = null;
        cursor = db.rawQuery("SELECT * FROM "+DbHelper.TABLE_AUTOR,null);
        if(cursor.moveToFirst()){
            do{
                autor = new Autor(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                autores.add(autor);
            }while(cursor.moveToNext());
        }
        return autores;


    }
    public Autor getAutor(int id){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        Autor autorObtenido;
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT * FROM "+DbHelper.TABLE_AUTOR+" WHERE id = ?",new String[]{String.valueOf(id)});
        if(cursor.moveToFirst()){

                autorObtenido = new Autor(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                return autorObtenido;
            }else{
                return null;
            }
        }

    public int eliminarAutor(int id){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        int res = db.delete(TABLE_AUTOR,"id = ?",new String[]{ String.valueOf(id) });
        return res;
    }

    public int actualizarAutor(int id, String nombre,
                                  String apellido, String nacionalidad){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put("nombre",nombre);
        values.put("apellido",apellido);
        values.put("nacionalidad",nacionalidad);

        int resultado = db.update(TABLE_AUTOR,values,
                "id = ?", new String[] { String.valueOf(id) } );

        return resultado;

    }

    }

