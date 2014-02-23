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

public class InsertarCentros extends Activity {

	public static final String DATO_SUBACTIVIDAD="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insertar_centros);

		Button insertar = (Button)findViewById(R.id.insertarCentro);

		// Localizamos los controladores
			final TextView codigo = (TextView)findViewById(R.id.codigoCentro);
			final TextView tipo = (TextView)findViewById(R.id.tipoCentro);
		    final TextView nombre = (TextView)findViewById(R.id.nombreCentro);
		    final TextView direccion = (TextView)findViewById(R.id.direccionCentro);
		    final TextView telefono = (TextView)findViewById(R.id.telefonoCentro);
		    final TextView numplazas = (TextView)findViewById(R.id.numeroplazas);




		insertar.setOnClickListener(new OnClickListener() {

		 	@Override
		 	public void onClick(View v) {


		 		String cod = codigo.getText().toString();
		 		String tip = tipo.getText().toString();
		 		String nom = nombre.getText().toString();
		 		String dir = direccion.getText().toString();
		 		String tel = telefono.getText().toString();
		 		String num = numplazas.getText().toString();
		 		Intent resultData = new Intent();


		 		String sentencia = "INSERT INTO centros VALUES  ('"+cod+"', '"+tip+"', '"+nom+"', '"+dir+"','"+tel+"','"+num+"')";


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
