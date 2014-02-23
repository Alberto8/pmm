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

public class MostrarPersonal extends Activity {

	 public static final String DATO_SUBACTIVIDAD="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mostrar_personal);

		Button guardar = (Button)findViewById(R.id.guardarPersonal);
		
			final TextView codigo = (TextView)findViewById(R.id.codigoCentro);
			final TextView dni = (TextView)findViewById(R.id.dni);
		    final TextView apellidos = (TextView)findViewById(R.id.apellidos);
		    final TextView funcion = (TextView)findViewById(R.id.funcion);
		    final TextView salario = (TextView)findViewById(R.id.salario);
		  
		    codigo.setKeyListener(null);

		    													// cogemos la informacion que habiamos pasado antes 
		   	 Bundle b = this.getIntent().getExtras();

		   	 codigo.setText(b.getString("Codigo"));
		   	 dni.setText(b.getString("Dni"));
		   	 apellidos.setText(b.getString("Apellidos"));
		     funcion.setText(b.getString("Funcion"));
		     salario.setText(b.getString("Salario"));
		   	 


		guardar.setOnClickListener(new OnClickListener() {

		 	@Override
		 	public void onClick(View v) {

		 		String cod = codigo.getText().toString();
		 		String dn = dni.getText().toString();
		 		String ape = apellidos.getText().toString();
		 		String fun = funcion.getText().toString();
		 		String sal = salario.getText().toString();		 		
		 		Intent resultData = new Intent();

		 		String sentencia = "UPDATE personal SET  dni = '"+dn+"', apellidos = '"+ape+"', funcion = '"+fun+"', salario = '"+sal+"' WHERE cod_centro = '"+cod+"'";


		 		resultData.putExtra(DATO_SUBACTIVIDAD, sentencia);	
				setResult(android.app.Activity.RESULT_OK, resultData);
                finish();


			}
		});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mostrar_personal, menu);
		return true;
	}

}
