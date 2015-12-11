package com.chacach.dbandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by alexander on 18/09/15.
 */
public class DB extends SQLiteOpenHelper {
    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Prueba", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table datos(nombre text, apellido text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public String guardar(String nombre, String apellido){
        String mensaje="";
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre",nombre);
        contenedor.put("apellido", apellido);
        try {
            database.insertOrThrow("datos",null,contenedor);
            mensaje="Ingresado Correctamente";
        }catch (SQLException e){
            mensaje="No Ingresado";
        }
        database.close();
        return mensaje;
    }
    public  String actualizar(String Buscar,String Nombre,String Apellido){
        String Mensaje ="";
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre",Nombre);
        contenedor.put("apellido",Apellido);
        int cantidad = database.update("datos", contenedor, "nombre='" + Buscar + "'", null);
        if(cantidad!=0){
            Mensaje="Actualizado Correctamente";
        }else{
            Mensaje="No Actualizado";
        }
        database.close();
        return Mensaje;
    }

    public String[] buscar_reg(String buscar){
        String[] datos= new String[3];
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM datos WHERE nombre ='"+buscar+"'";
        Cursor registros = database.rawQuery(q, null);
        if(registros.moveToFirst()){
            for(int i = 0 ; i<2;i++){
                datos[i]= registros.getString(i);

            }
            datos[2]= "Encontrado";
        }else{

            datos[2]= "No se encontro a "+buscar;
        }
        database.close();
        return datos;
    }
    public String eliminar(String Nombre){
        String mensaje ="";
        SQLiteDatabase database = this.getWritableDatabase();
        int cantidad =database.delete("datos", "nombre='" + Nombre + "'", null);
        if (cantidad !=0){
            mensaje="Eliminado Correctamente";

        }
        else{
            mensaje = "No existe";
        }
        database.close();
        return mensaje;
    }
    public ArrayList llenar_lv(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM datos";
        Cursor registros = database.rawQuery(q,null);
        if(registros.moveToFirst()){
            do{
                lista.add(registros.getString(1));
            }while(registros.moveToNext());
        }
        return lista;

    }

}

