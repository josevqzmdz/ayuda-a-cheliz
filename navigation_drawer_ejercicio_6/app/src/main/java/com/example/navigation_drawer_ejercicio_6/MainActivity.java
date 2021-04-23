package com.example.navigation_drawer_ejercicio_6;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    /*
    Lo siguiente será implementar la lógica necesaria para responder
    a los eventos del menú de forma que cambiemos de fragment al pulsar
    cada opción. Esto lo haremos implementando el evento
    onNavigationItemSelected() del control NavigationView del menú lateral,
     lógica que añadiremos al final del método onCreate() de
      nuestra actividad principal.

      Para las tres secciones principales lo que hacemos en primer lugar es crear el
      nuevo fragment a mostrar dependiendo de la opción pulsada en el menú de navegación,
       que nos llega como parámetro (menuItem) del evento onNavigationItemSelected.
        En el siguiente paso hacemos uso del Fragment Manager
        congetSupportFragmentManager() para sustituir el contenido del FrameLayout
         que definimos en el layout de la actividad principal por el nuevo fragment
         creado. Posteriormente marcamos como seleccionada la opción pulsada del menú
          mediante el método setChecked() y actualizamos el título de la action bar
           por el de la opción seleccionada mediante setTitle().
Por su parte, para las dos opciones finales del menú podemos realizar por
 ejemplo cualquier otra acción que no implique cambio de fragment (como abrir
 una actividad independiente para mostrar una ayuda o las opciones de la aplicación).
  En mi caso de ejemplo me limito a mostrar un mensaje de log llamando a Log.i().
Por último, y en cualquier caso, cerramos el menú llamando al método closeDrawers()
del DrawerLayout.


     */

    //TODO 1: iconos ic_menu de menu_navview
    // TODO 2: icoono navheader de header_navview.xml
    // TODO 3: el codigo de este proyecto es una basura y no jala como lo instruye el tutorial. No sigas tutoriales de SGOLIVERNET
    private Toolbar appbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar appbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(appbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.bernie);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navview);

        navView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem){
                    boolean fragmentTransaction = false;
                    Fragment fragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.menu_seccion_1:
                            fragment = new Fragment1();
                            fragmentTransaction = true;
                            break;

                        case R.id.menu_seccion_2:
                            fragment = new Fragment2();
                            fragmentTransaction = true;
                            break;

                        case R.id.menu_opcion_3:
                            fragment = new Fragment3();
                            fragmentTransaction = true;
                            break;

                        case R.id.menu_seccion_3:
                            fragment = new Fragment3();
                            fragmentTransaction = true;
                            break;

                        // mensajes de debugging
                        case R.id.menu_opcion_1:
                            Log.i("NavigationView", "Pulsada opcion 1");
                            break;

                        case R.id.menu_opcion_2:
                            Log.i("NavigagtionView", "Pulsada opcion 2");
                            break;
                    }// fin de switch


                    if (fragmentTransaction){
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content_frame, fragment)
                                .commit();
                        menuItem.setChecked(true);
                        getSupportActionBar().setTitle(menuItem.getTitle());
                    }//fin de fragmentTransaction



                    drawerLayout.closeDrawers();

                    return true;
                }//fin de metodoo onNavigationItemSelected
            }
        );//fin de setNavigationItemSelectedListener
    }// fin de metodo oncreate

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
