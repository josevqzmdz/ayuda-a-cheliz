package com.example.fragmentos_ejercicio_3;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

public class DetalleActivity extends FragmentActivity {

    public static final String EXTRA_TEXTO =
            "net.sgoliver.android.fragments.EXTRA_TEXTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        FragmentDetalle detalle =
                (FragmentDetalle)getSupportFragmentManager()
                        .findFragmentById(R.id.FrgDetalle);

        detalle.mostrarDetalle(getIntent().getStringExtra(EXTRA_TEXTO));
    }
}
