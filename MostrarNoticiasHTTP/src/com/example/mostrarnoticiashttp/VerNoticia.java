package com.example.mostrarnoticiashttp;

import com.example.lectornoticias.R;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class VerNoticia extends Activity {

	private Conexion conexion;
	private SQLiteDatabase baseDatos;
	private String[] datos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ver);
		conexion=new Conexion(this,"DBNoticias",null,1);
		baseDatos=conexion.getReadableDatabase();
		mostrar();
		ListView lista=(ListView)findViewById(R.id.lista);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, datos);
		lista.setAdapter(adapter);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ver, menu);
		return true;
	}
	
	public void mostrar(){
		String[] campos = new String[] {"noticia"};

        Cursor noti = baseDatos.query("Noticias", campos, null, null, null, null, null);
        datos=new String[noti.getCount()];
        System.out.println(noti.getCount());
        int i=0;
        if (noti.moveToFirst()) {
                do {                		
                		String noticia=noti.getString(0);                      
                        System.out.println(noticia);
                        datos[i]=noticia;
                        i++;
                        
                } while (noti.moveToNext());
        }
        baseDatos.close();
	}

}
