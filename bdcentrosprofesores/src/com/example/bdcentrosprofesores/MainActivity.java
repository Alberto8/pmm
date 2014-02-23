package com.example.bdcentrosprofesores;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button bton1 = (Button)findViewById(R.id.Centros);
		Button bton2 = (Button)findViewById(R.id.Personal);
		Button bton3 = (Button)findViewById(R.id.Profesores);
		Button bton4 = (Button)findViewById(R.id.Consultas);

		bton1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ConsultaCentros.class);

				startActivity(intent);

			}
		});

		bton2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ConsultaPersonal.class);

				startActivity(intent);

			}
		});

		bton3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ConsultaProfesores.class);

				startActivity(intent);

			}
		});
		
		bton4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Consultas.class);

				startActivity(intent);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
