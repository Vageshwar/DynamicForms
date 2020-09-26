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
    ArrayList<FormItem> list;
    ArrayList<String> types;
    LinearLayout formLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_form);
        list = new ArrayList<FormItem>();
        formLayout = findViewById(R.id.formLayout);
        types = new ArrayList<String>();
        Button addRow = findViewById(R.id.btnAddRow);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("ID",""+adapterView.getId());
        String type = adapterView.getItemAtPosition(i).toString();
        types.add(adapterView.getId(),adapterView.getItemAtPosition(i).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void createForm(View view) {

        HashMap<String, String> map = new HashMap<>();

        for(int i = 0; i < list.size();i++){
            Log.d(list.get(i).getName().getText().toString(),types.get(i));
//            Log.d(item.getName().getText().toString(), type);
        }
    }

    public void addRow(View view) {
        DynamicForm dynamicForm = new DynamicForm(context);
        FormItem item = dynamicForm.addRow(formLayout,getApplicationContext(),list.size());
        item.getType().setOnItemSelectedListener(this);
        list.add(item);
    }
}