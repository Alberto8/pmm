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

public class MostrarProfesor extends Activity {

	 public static final String DATO_SUBACTIVIDAD="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mostrar_profesores);

		Button guardar = (Button)findViewById(R.id.guardarProfesores);

		// Localizamos los controladores
			final TextView codigo = (TextView)findViewById(R.id.codigoCentro);
			final TextView dni = (TextView)findViewById(R.id.dni);
		    final TextView apellidos = (TextView)findViewById(R.id.apellidos);
		    final TextView especialidad = (TextView)findViewById(R.id.especialidad);
		    
		    codigo.setKeyListener(null);

		// recuperamos informacion del intent
		   	 Bundle b = this.getIntent().getExtras();

		   	 codigo.setText(b.getString("Codigo"));
		   	 dni.setText(b.getString("Dni"));
		   	 apellidos.setText(b.getString("Apellidos"));
		   	 especialidad.setText(b.getString("Especialidad"));		   	


		guardar.setOnClickListener(new OnClickListener() {

		 	@Override
		 	public void onClick(View v) {


		 		String cod = codigo.getText().toString();
		 		String dn = dni.getText().toString();
		 		String ape = apellidos.getText().toString();
		 		String esp = especialidad.getText().toString();		 		
		 		Intent resultData = new Intent();


		 		String sentencia = "UPDATE profesores SET  dni = '"+dn+"', apellidos = '"+ape+"', especialidad = '"+esp+"' WHERE cod_centro = '"+cod+"'";


		 		resultData.putExtra(DATO_SUBACTIVIDAD, sentencia);	
				setResult(android.app.Activity.RESULT_OK, resultData);
                finish();


			}
		});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mostrar_centro, menu);
		return true;
	}

}
