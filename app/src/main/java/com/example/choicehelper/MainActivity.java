package com.example.choicehelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myRV = null;
    private MyAdapter myAdapter =  null;
    private GestureDetectorCompat detector = null;
    Button addBTN;
    EditText itemNameET;
    Button clearBTN, resetBTN;

    // gesture listener
    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = myRV.findChildViewUnder(e.getX(),e.getY());
            if(view != null) {
                RecyclerView.ViewHolder holder = myRV.getChildViewHolder(view);
                if(holder instanceof MyAdapter.MyViewHolder) {
                    int position = holder.getAdapterPosition();

                    // handling single tap.
                    ChoiceModel choiceModel = ChoiceModel.getSingleton();
                    if(choiceModel.choiceList.size()<=1) {
                        StyleableToast.makeText(MainActivity.this,"List can't be empty"
                                ,R.style.toastAlert).show();
                    } else {
                        choiceModel.choiceList.remove(position);
                        myAdapter.notifyDataSetChanged();
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // Adding element into the list.
            addBTN = findViewById(R.id.addBTN);
            addBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemNameET = findViewById(R.id.itemNameET);
                    String itemName = itemNameET.getText().toString();
                    StyleableToast.makeText(MainActivity.this,"Added item: "+itemName+
                            " ",R.style.toastColor).show();
                    if(!itemName.isEmpty()) {
                        ChoiceModel choiceModel = ChoiceModel.getSingleton();
                        choiceModel.choiceList.add(new ChoiceModel.MyInfo(itemName));
                        myAdapter.notifyItemInserted(choiceModel.choiceList.size() - 1);
                        itemNameET.setText("");
                    }
                }
            });
        }
        catch (Exception e) {
            StyleableToast.makeText(this,"Enter valid Data " + e,R.style.toastColor).
                    show();
        }

        // Resetting the data.
        clearBTN = findViewById(R.id.clearBTN);
        clearBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoiceModel choiceModel = ChoiceModel.getSingleton();
                choiceModel.reset();
                StyleableToast.makeText(MainActivity.this,"Resetting the Data " ,
                        R.style.toastAlert).show();
                myAdapter.notifyDataSetChanged();
            }
        });

        // Clearing all data from list.
        resetBTN = findViewById(R.id.resetBTN);
        resetBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoiceModel choiceModel = ChoiceModel.getSingleton();
                choiceModel.clear();
                myAdapter.notifyDataSetChanged();
                StyleableToast.makeText(MainActivity.this,"Cleared the Data " ,
                        R.style.toastAlert).show();
            }
        });

        myAdapter = new MyAdapter();
        myRV = findViewById(R.id.myRV);
        myRV.setAdapter(myAdapter);

        RecyclerView.LayoutManager myManager =new LinearLayoutManager(this);
        myRV.setLayoutManager(myManager);

        // responding to taps
        detector = new GestureDetectorCompat(this, new RecyclerViewOnGestureListener());

        myRV.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return detector.onTouchEvent(e);
            }
        });
    }
}
