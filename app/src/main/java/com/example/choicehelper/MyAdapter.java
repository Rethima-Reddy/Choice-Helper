package com.example.choicehelper;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    TextView itemTV;
    //step-03: View holder class
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private LinearLayout convience;
        public MyViewHolder(LinearLayout linearLayout)
        {
            super(linearLayout);
            convience = linearLayout;
        }
    }

    // Step-04: Implement all the overridden methods.
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //create a LinearLayout holder
        LinearLayout myLinearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.choice_view,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(myLinearLayout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position)
    {
        // setting item name into each holder.
        itemTV = holder.convience.findViewById(R.id.itemTV);
        itemTV.setText(theModel.choiceList.get(position).item);
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                StyleableToast.makeText(holder.convience.getContext(),"Removed item : "+
                                theModel.choiceList.get(position).item,R.style.toastColor).show();
            }
        });

        if(holder.getAdapterPosition()%2 == 0) {
            itemTV.setBackgroundResource(R.color.gray);
        } else {
            itemTV.setBackgroundResource(R.color.paleorange);
        }
    }

    @Override
    public int getItemCount()
    {
        // return the size of an array.
        return theModel.choiceList.size();
    }

    // step-05: Create an array of items and initialize through a constructor.
    private ArrayList<String> items;
    public MyAdapter(ArrayList<String> items)
    {
        super();
        this.items = items;
    }

    // step-07: create a reference to the model
    public ChoiceModel theModel = null;

    public MyAdapter()
    {
        super();
        // implementing singleton
        theModel = ChoiceModel.getSingleton();
    }


}
