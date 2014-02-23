package com.example.bdcentrosprofesores;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class InsertarPersonal extends Activity {

	public static final String DATO_SUBACTIVIDAD="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insertar_personal);

		Button insertar = (Button)findViewById(R.id.insertarPersonal);
		
			final TextView codigo = (TextView)findViewById(R.id.codigoCentro);
			final TextView dni = (TextView)findViewById(R.id.dni);
		    final TextView apellidos = (TextView)findViewById(R.id.apellidos);
		    final TextView funcion = (TextView)findViewById(R.id.funcion);
		    final TextView salario = (TextView)findViewById(R.id.salario);
		    

		insertar.setOnClickListener(new OnClickListener() {

		 	@Override
		 	public void onClick(View v) {


		 		String cod = codigo.getText().toString();
		 		String dn = dni.getText().toString();
		 		String ape = apellidos.getText().toString();
		 		String fun = funcion.getText().toString();
		 		String sal = salario.getText().toString();		 		
		 		Intent resultData = new Intent();


		 		String sentencia = "INSERT INTO personal VALUES  ('"+cod+"', '"+dn+"', '"+ape+"', '"+fun+"','"+sal+"')";


		 		resultData.putExtra(DATO_SUBACTIVIDAD, sentencia);	
				setResult(android.app.Activity.RESULT_OK, resultData);
                finish();

			}
		});



	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insertar_personal, menu);
		return true;
	}

}
