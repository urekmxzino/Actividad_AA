package com.aravena.actividad_semana_aa.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.aravena.actividad_semana_aa.models.Autor;
import com.aravena.actividad_semana_aa.models.Editorial;
import com.aravena.actividad_semana_aa.models.Estante;
import com.aravena.actividad_semana_aa.models.Libro;

import java.util.ArrayList;

public class DbLibro extends DbHelper{
    Context context;
    public DbLibro(@Nullable Context context) {
        super(context);
        this.context = context;

    }

    public long insertarLibro(Libro libro){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        long res = 0;

        ContentValues valores = new ContentValues();
        valores.put("titulo",libro.getTitulo());
        valores.put("descripcion",libro.getDescripcion());
        valores.put("fechaP",libro.getFechaP());
        valores.put("copias",libro.getCopias());
        valores.put("cantP",libro.getCantP());
        valores.put("autor",libro.getAutor().getId());
        valores.put("editorial",libro.getEditorial().getId());
        valores.put("estante",libro.getEstante().getId());

        res = db.insert(DbHelper.TABLE_LIBROS,null,valores);

        return res;
    }


    public ArrayList<Libro> getLibros(){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ArrayList<Libro> libros = new ArrayList<>();
        Cursor cursor = null;
        Libro libro= null;
        DbAutor dbaut = new DbAutor(context);
        DbEditorial dbedit = new DbEditorial(context);
        DbEstante dbest = new DbEstante(context);

        cursor = db.rawQuery("SELECT * FROM "+DbHelper.TABLE_LIBROS,null);
        if(cursor.moveToFirst()){
            do{
                Autor aut = dbaut.getAutor(cursor.getInt(6));
                Editorial edit = dbedit.getEditorial(cursor.getInt(7));
                Estante est = dbest.getEstante(cursor.getInt(8));

                libro = new Libro(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        aut,
                        edit,
                        est
                );
                libros.add(libro);
            }while(cursor.moveToNext());
        }
        return libros;
    }

    public int eliminarLibro(int id){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        int res = db.delete(TABLE_LIBROS,"id = ?",new String[]{ String.valueOf(id) });
        return res;
    }


    public int actualizarLibro(int id, String titulo,
                               String descripcion, String fechaP,int copias,int cantP){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put("titulo",titulo);
        values.put("descripcion",descripcion);
        values.put("fechaP",fechaP);
        values.put("copias",copias);
        values.put("cantP",cantP);

        int resultado = db.update(TABLE_LIBROS,values,
                "id = ?", new String[] { String.valueOf(id) } );

        return resultado;

    }
}
