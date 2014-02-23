package com.example.bdcentrosprofesores;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ConsultaProfesores extends Activity {

	public static final int CODIGO_RESPUESTA = 8;
	CrearBase base;
	private Profesores[] datos;

	class AdaptadorProfesor extends ArrayAdapter<Profesores> 
	{
		Activity a;
		AdaptadorProfesor(Activity b) 
		{
			super(b, R.layout.listado_profesores, datos);
			this.a = b;
		}
		public View getDropDownView (int position,View convertView,ViewGroup parent) {

			
			return getView (position, convertView, parent);
			}
		public View getView(int position,View convertView,ViewGroup parent) 
		{
		
			LayoutInflater inflater = a.getLayoutInflater();	
			View item = inflater.inflate(R.layout.listado_profesores, null);

			final TextView lblCodigo=(TextView)item.findViewById(R.id.codigo);
			final TextView lblDni = (TextView)item.findViewById(R.id.dni);
			final TextView lblApellidos = (TextView)item.findViewById(R.id.apellidos);
			final TextView lblEspecialidad = (TextView)item.findViewById(R.id.especialidad);			
			
			lblCodigo.setText(datos[position].getCodCentro());
			lblDni.setText(datos[position].getDni());
			lblApellidos.setText(datos[position].getApellidos());			
			lblEspecialidad.setText(datos[position].getEspecialidad());
			
			return(item);
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consulta_profesores);

		try 
		{

			String[] campos = new String[] {"cod_centro","dni","apellidos","especialidad"};

			base=new CrearBase(this,"dbase",null,1);
			SQLiteDatabase db=base.getReadableDatabase();


			Cursor rs=db.query("profesores", campos, null,null,null,null,null);

			datos=new Profesores[rs.getCount()+1];//Devuelve el numero de filas + 1 
			datos[0]= new Profesores("Codigo","Dni","Apellidos","Especialidad");
			int i=1;
	        if (rs.moveToFirst()) 
	        {
	                do 
	                {
	                		String cod=rs.getString(0);
	                		String dn=rs.getString(1);
	                		String ape=rs.getString(2);
	                        String esp=rs.getString(3);            

	                        datos[i]=new Profesores(cod,dn,ape,esp);
	                        i++;       
	                }
	                while (rs.moveToNext());
	        }

		}
		catch (Exception e) {
			
		}

		final Spinner spinner=(Spinner)findViewById(R.id.profesores);
		AdaptadorProfesor adaptador =new AdaptadorProfesor(this); 
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adaptador);


		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
				Intent intent = new Intent(ConsultaProfesores.this, MostrarProfesor.class);

				Bundle b = new Bundle();
				String codigocentro = ((Profesores)parent.getAdapter().getItem(position)).getCodCentro();
				String dni = ((Profesores)parent.getAdapter().getItem(position)).getDni();
				String apellidos = ((Profesores)parent.getAdapter().getItem(position)).getApellidos();
				String especialidad = ((Profesores)parent.getAdapter().getItem(position)).getEspecialidad();
				
				b.putString("Codigo", codigocentro);
				b.putString("Dni", dni);
				b.putString("Apellidos", apellidos);
				b.putString("Especialidad", especialidad);				

				intent.putExtras(b);


				if(position > 0)
				startActivityForResult(intent, CODIGO_RESPUESTA);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});


		Button insertar = (Button)findViewById(R.id.insertarProfesores);

		insertar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ConsultaProfesores.this, InsertarProfesor.class);

				startActivityForResult(intent, CODIGO_RESPUESTA);

			}
		});


	}

	protected void onActivityResult(int requestCode,int resultCode, Intent pData)            
    {
        if ( requestCode == CODIGO_RESPUESTA )//Si el código de respuesta es igual al requestCode
            {
            if (resultCode == Activity.RESULT_OK )//Si resultCode es igual a ok
                {
                    final String dato = pData.getExtras().getString(MostrarProfesor.DATO_SUBACTIVIDAD );//Obtengo el string de la subactividad
                    //Aquí se hara lo que se desee con el valor recuperado

                    SQLiteDatabase db=base.getWritableDatabase();
                    db.execSQL(dato);
                    
                    Intent intent = new Intent(ConsultaProfesores.this, ConsultaProfesores.class);
                    finish();
                    startActivity(intent);
                }
            }
    }

}
