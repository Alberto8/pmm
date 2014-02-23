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

public class InsertarProfesor extends Activity {

	public static final String DATO_SUBACTIVIDAD="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insertar_profesor);

		Button insertar = (Button)findViewById(R.id.insertarProfesor);

		// Localizamos los controladores
			final TextView codigo = (TextView)findViewById(R.id.codigoCentro);
			final TextView dni = (TextView)findViewById(R.id.dni);
		    final TextView apellidos = (TextView)findViewById(R.id.apellidos);
		    final TextView especialidad = (TextView)findViewById(R.id.especialidad);	  

		insertar.setOnClickListener(new OnClickListener() {

		 	@Override
		 	public void onClick(View v) {


		 		String cod = codigo.getText().toString();
		 		String dn = dni.getText().toString();
		 		String ape = apellidos.getText().toString();
		 		String esp = especialidad.getText().toString();		 		
		 		Intent resultData = new Intent();

		 		String sentencia = "INSERT INTO profesores VALUES  ('"+cod+"', '"+dn+"', '"+ape+"', '"+esp+"')";


		 		resultData.putExtra(DATO_SUBACTIVIDAD, sentencia);	
				setResult(android.app.Activity.RESULT_OK, resultData);
                finish();


			}
		});



	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insertar_centros, menu);
		return true;
	}

}
