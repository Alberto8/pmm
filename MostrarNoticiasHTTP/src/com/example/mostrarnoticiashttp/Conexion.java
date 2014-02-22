package com.example.mostrarnoticiashttp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexion extends SQLiteOpenHelper {
	
	String cadSQL = "CREATE TABLE IF NOT EXISTS Noticias (codigo INTEGER PRIMARY KEY, noticia varchar(100))";

	public Conexion(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);		
	}

	@Override
	public void onCreate(SQLiteDatabase bd) {
		
		bd.execSQL(cadSQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
