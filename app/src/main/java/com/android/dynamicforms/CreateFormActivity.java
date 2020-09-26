package com.android.dynamicforms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateFormActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Context context;
    String type;
    ArrayList<FormItem> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_form);
        list = new ArrayList<FormItem>();
        final LinearLayout formLayout = findViewById(R.id.formLayout);
        Button addRow = findViewById(R.id.btnAddRow);
        addRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DynamicForm dynamicForm = new DynamicForm(context);
               FormItem item = dynamicForm.addRow(formLayout,getApplicationContext(),"Test");
               list.add(item);
            }
        });

        type = "Text";


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        type = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void createForm(View view) {

        HashMap<String, String> map = new HashMap<>();

        for(FormItem item: list){
            item.getType().setOnItemSelectedListener(this);
            map.put(item.getName().getText().toString(),type);
            Log.d(item.getName().getText().toString(), type);
        }
    }
}