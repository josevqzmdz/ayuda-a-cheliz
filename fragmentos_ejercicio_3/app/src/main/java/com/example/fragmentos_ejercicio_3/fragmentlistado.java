package com.example.fragmentos_ejercicio_3;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class fragmentlistado extends Fragment {

    private CorreosListener listener;

    private Correo[] datos =
            new Correo[]{
                    new Correo("Persona 1", "Asunto del correo 1", "Texto del correo 1"),
                    new Correo("Persona 2", "Asunto del correo 2", "Texto del correo 2"),
                    new Correo("Persona 3", "Asunto del correo 3", "Texto del correo 3"),
                    new Correo("Persona 4", "Asunto del correo 4", "Texto del correo 4"),
                    new Correo("Persona 5", "Asunto del correo 5", "Texto del correo 5")
            };
    private ListView lstListado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_listado, container, false);
    }

    /*
    Definimos una interfaz con el método asociado al evento, en este caso llamada
    CorreosListener con un único método llamado onCorreoSeleccionado(), declaramos
    un atributo de la clase con esta interfaz y definimos un método setXXXListener()
     para poder asignar el evento desde fuera de la clase
     */

    public interface CorreosListener {
        void onCorreoSeleccionado(Correo c);
    }

    public void setCorreosListener(CorreosListener listener) {
        this.listener = listener;
    }

    @Override
    public void onActivityCreated(Bundle state){
        super.onActivityCreated(state);
        lstListado = (ListView)getView().findViewById(R.id.LstListado);
        lstListado.setAdapter(new AdaptadorCorreos(this));

        lstListado.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> listener, View view, int pos, long id){
                if (listener != null) {
                    /*
                    view.setOnClickListener(
                            (View.OnClickListener) lstListado.getAdapter().getItem(pos)
                        );

                     */
                    }
                }
            });
        }//fin de onactivitystate

    class AdaptadorCorreos extends ArrayAdapter<Correo> {

        Activity context;

        AdaptadorCorreos(Fragment context){
            super(context.getActivity(), R.layout.listitem_correo, datos);
            this.context = context.getActivity();
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_correo, null);

            TextView lblDe = (TextView) item.findViewById(R.id.LblDe);
            lblDe.setText(datos[position].getDe());

            TextView lblAsunto = (TextView) item.findViewById(R.id.LblAsunto);
            lblAsunto.setText(datos[position].getAsunto());

            return(item);
        }
    }// fin de clase adaptadorcorreos

}
