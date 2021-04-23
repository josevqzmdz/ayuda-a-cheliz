package com.example.reddit_app3;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

// TODO: enlazar datos obtenidos por reddit al cardview / recycleview
// https://developer.android.com/guide/topics/ui/layout/recyclerview#java
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(TextView v){
            super(v);
            textView = v;
        }
    }

    // provee un constructor
    public MyAdapter(String[] myDataset){
        mDataset = myDataset;
    }

    // create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // create a new view
        // TODO: cambiar my_text_view por el nombre del textView
        //TextView v = (TextView) LayoutInflater.from(parent.getContext())
                //.inflate(R.layout.my_text_view, parent, false);
        // TODO: agregar informacion arrojada por el XMLparserz
        //MyViewHolder vh = new MyViewHolder(v);
        //return vh;
        return null;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }

}
