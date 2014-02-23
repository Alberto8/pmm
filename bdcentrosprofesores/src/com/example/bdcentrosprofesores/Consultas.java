package com.example.bdcentrosprofesores;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Consultas extends Activity {

	Button min;
	Button gruposmay;
	Button gruposmen;
	Button max;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consultas);
		max=(Button)findViewById(R.id.bmaxsal);
		min=(Button)findViewById(R.id.bminsal);
		gruposmay=(Button)findViewById(R.id.bagrupadosmay);
		gruposmen=(Button)findViewById(R.id.bagrupadosmen);

		max.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i=new Intent(Consultas.this,SalarioMayor.class);
				startActivity(i);

			}
		});

min.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i=new Intent(Consultas.this,SalarioMenor.class);
				startActivity(i);

			}
		});

		gruposmay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i=new Intent(Consultas.this,MayorSalarioGrupo.class);
				startActivity(i);

			}
		});

		gruposmen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i=new Intent(Consultas.this,MenorSalarioGrupo.class);
				startActivity(i);

			}
		});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.consultas, menu);
		return true;
	}



}