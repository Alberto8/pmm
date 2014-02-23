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

public class ConsultaPersonal extends Activity {

	public static final int CODIGO_RESPUESTA = 8;
	CrearBase base;
	private Personal[] datos;

	class AdaptadorPersonal extends ArrayAdapter<Personal> 
	{
		Activity a;
		AdaptadorPersonal(Activity b) 
		{
			super(b, R.layout.listado_personal, datos);
			this.a = b;
		}
		public View getDropDownView (int position,View convertView,ViewGroup parent) {

			
			return getView (position, convertView, parent);
			}
		public View getView(int position,View convertView,ViewGroup parent) 
		{
			
			LayoutInflater inflater = a.getLayoutInflater();	
			View item = inflater.inflate(R.layout.listado_personal, null);

			final TextView lblCodigo=(TextView)item.findViewById(R.id.codigo);
			final TextView lblDni = (TextView)item.findViewById(R.id.dni);
			final TextView lblApellidos = (TextView)item.findViewById(R.id.apellidos);
			final TextView lblFuncion = (TextView)item.findViewById(R.id.funcion);
			final TextView lblSalario = (TextView)item.findViewById(R.id.salario);
			
			lblCodigo.setText(datos[position].getCodCentro());
			lblDni.setText(datos[position].getDni());
			lblApellidos.setText(datos[position].getApellido());			
			lblFuncion.setText(datos[position].getFuncion());
			lblSalario.setText(datos[position].getSalario());			

			return(item);
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consulta_personal);

		try 
		{

			String[] campos = new String[] {"cod_centro","dni","apellidos","funcion","salario"};

			base=new CrearBase(this,"dbase",null,1);
			SQLiteDatabase db=base.getReadableDatabase();


			Cursor rs=db.query("personal", campos, null,null,null,null,null);

			datos=new Personal[rs.getCount()+1];//Devuelve el numero de filas + 1 
			datos[0]= new Personal("Codigo","Dni","Apellidos","Funcion","Salario");
			int i=1;
	        if (rs.moveToFirst()) 
	        {
	                do 
	                {
	                		String cod=rs.getString(0);
	                		String dni=rs.getString(1);
	                		String ape=rs.getString(2);
	                        String fun=rs.getString(3);
	                        String sal=rs.getString(4);	                        


	                        datos[i]=new Personal(cod,dni,ape,fun,sal);
	                        i++;       
	                }
	                while (rs.moveToNext());
	        }

		}
		catch (Exception e) {
			
		}
		
		final Spinner spinner=(Spinner)findViewById(R.id.personal);
		AdaptadorPersonal adaptador =new AdaptadorPersonal(this); 
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adaptador);

		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
				Intent intent = new Intent(ConsultaPersonal.this, MostrarPersonal.class);

				Bundle b = new Bundle();
				String codigocentro = ((Personal)parent.getAdapter().getItem(position)).getCodCentro();
				String dni = ((Personal)parent.getAdapter().getItem(position)).getDni();
				String apellidos = ((Personal)parent.getAdapter().getItem(position)).getApellido();
				String funcion = ((Personal)parent.getAdapter().getItem(position)).getFuncion();
				String salario = ((Personal)parent.getAdapter().getItem(position)).getSalario();
				

				b.putString("Codigo", codigocentro);
				b.putString("Dni", dni);
				b.putString("Apellidos", apellidos);
				b.putString("Funcion", funcion);
				b.putString("Salario", salario);				

				intent.putExtras(b);


				if(position > 0)
				startActivityForResult(intent, CODIGO_RESPUESTA);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {   				

			}

		});


		Button insertar = (Button)findViewById(R.id.insertarPersonal);

		insertar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ConsultaPersonal.this, InsertarPersonal.class);

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
                    final String dato = pData.getExtras().getString(MostrarPersonal.DATO_SUBACTIVIDAD );//Obtengo el string de la subactividad
                    //Aquí se hara lo que se desee con el valor recuperado

                    SQLiteDatabase db=base.getWritableDatabase();
                    db.execSQL(dato);
                    
                    Intent intent = new Intent(ConsultaPersonal.this, ConsultaPersonal.class);
                    finish();
                    startActivity(intent);
                }
            }
    }

}
