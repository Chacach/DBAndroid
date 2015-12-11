package com.chacach.dbandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class Lista extends AppCompatActivity {
    ListView lv ;
    ArrayList<String> lista;
    ArrayAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        lv = (ListView)findViewById(R.id.lista);
        DB db = new DB(getApplicationContext(),null,null,1);
        lista = db.llenar_lv();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista);
        lv.setAdapter(adaptador);
    }

}
