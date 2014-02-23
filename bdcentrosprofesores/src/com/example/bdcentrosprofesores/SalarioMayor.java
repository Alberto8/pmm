package com.example.bdcentrosprofesores;


import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SalarioMayor extends Activity {

	ListView mayor;
	SQLiteDatabase db;
	String salariosmayores[];
	CrearBase base;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.salario_mayor);

		mayor=(ListView)findViewById(R.id.listamayor);
		base=new CrearBase(this,"dbase",null,1);
		db=base.getReadableDatabase();

		getMaxSalario();


		ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, salariosmayores);
		mayor.setAdapter(adaptador);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.salario_mayor, menu);
		return true;
	}

	private void getMaxSalario(){

		Cursor c =db.rawQuery("select apellidos, salario from personal where salario in (select MAX(salario) from personal)", null);
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
