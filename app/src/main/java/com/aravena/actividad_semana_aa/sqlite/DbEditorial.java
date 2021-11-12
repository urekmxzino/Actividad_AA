package com.aravena.actividad_semana_aa.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.aravena.actividad_semana_aa.models.Editorial;

import java.util.ArrayList;

public class DbEditorial extends DbHelper {
    Context context;

    public DbEditorial(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public long insertarEditorial(Editorial editorial) {
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        long res = 0;

        ContentValues valores = new ContentValues();
        valores.put("nombre", editorial.getNombre());
        valores.put("nacionalidad", editorial.getNacionalidad());


        res = db.insert(DbHelper.TABLE_EDITORIAL, null, valores);

        return res;
    }

    public ArrayList<Editorial> getEditoriales() {
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ArrayList<Editorial> editoriales = new ArrayList<>();
        Cursor cursor = null;
        Editorial editorial = null;
        cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_EDITORIAL, null);
        if (cursor.moveToFirst()) {
            do {
                editorial = new Editorial(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );
                editoriales.add(editorial);
            } while (cursor.moveToNext());
        }
        return editoriales;

    }

    public Editorial getEditorial(int id) {
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        Editorial editorialObtenido;
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_EDITORIAL + " WHERE id = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {

            editorialObtenido = new Editorial(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
            return editorialObtenido;
        } else {
            return null;
        }


    }
    public int eliminarEditorial(int id){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        int res = db.delete(TABLE_EDITORIAL,"id = ?",new String[]{ String.valueOf(id) });
        return res;
    }


    public int actualizarEditorial(Editorial editorial){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();


        ContentValues valores = new ContentValues();
        valores.put("nombre", editorial.getNombre());
        valores.put("nacionalidad", editorial.getNacionalidad());

        int resultado = db.update(TABLE_EDITORIAL,valores,
                "id = ?", new String[] { String.valueOf(editorial.getId()) } );

        return resultado;

    }
}



