package com.example.bdcentrosprofesores;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SalarioMenor extends Activity {

	ListView menor;
	SQLiteDatabase db;
	String salariosmayores[];
	CrearBase base;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.salario_menor);
		menor=(ListView)findViewById(R.id.listamenor);
		base=new CrearBase(this,"dbase",null,1);
		db=base.getReadableDatabase();
		getMinSalario();

		ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, salariosmayores);
		menor.setAdapter(adaptador);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.salario_menor, menu);
		return true;
	}

	private void getMinSalario(){

		Cursor c =db.rawQuery("select apellidos, salario from personal where salario in (select MIN(salario) from personal)", null);
		salariosmayores=new String[c.getCount()];
		int i=0;
		if(c.moveToFirst()){
			do{
				salariosmayores[i]=c.getString(0)+" sueldo "+ String.valueOf(c.getFloat(1));
				i++;
			}while(c.moveToNext());
		}
	}

}
