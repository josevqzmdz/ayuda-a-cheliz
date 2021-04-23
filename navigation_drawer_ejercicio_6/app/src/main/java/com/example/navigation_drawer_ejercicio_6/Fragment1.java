package com.example.navigation_drawer_ejercicio_6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment
{
    /*
    clase que infla el layout fragment_fragment1.xml
     */
    public Fragment1(){
        //constructor vacio
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }

}
