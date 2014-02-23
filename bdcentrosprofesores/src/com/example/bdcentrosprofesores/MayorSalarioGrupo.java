package com.example.bdcentrosprofesores;


import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MayorSalarioGrupo extends Activity {

	ListView mayor;
	SQLiteDatabase db;
	String salariomayor[];
	CrearBase base;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mayor_salario_grupos);

		mayor=(ListView)findViewById(R.id.listmayorgrupo);
		base=new CrearBase(this,"dbase",null,1);
		db=base.getReadableDatabase();
		getMaxSalario();

		ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, salariomayor);


		mayor.setAdapter(adaptador);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mayor_salario_grupo, menu);
		return true;
	}

	private void getMaxSalario(){
		String select="SELECT apellidos,salario,funcion from personal where salario in (select MAX(salario) from personal group by funcion) ";
		Cursor c =db.rawQuery(select,null);
		salariomayor=new String[c.getCount()];
		System.out.println(c.getCount());
		int i=0;
		if(c.moveToFirst()){
			do{
				salariomayor[i]=c.getString(0)+" "+c.getString(2)+"  sueldo "+ String.valueOf(c.getFloat(1));
				i++;
			}while(c.moveToNext());
		}
	}

}
