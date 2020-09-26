package com.android.dynamicforms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CreateFormActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Context context;
    ArrayList<FormItem> list;
    ArrayList<String> types;
    LinearLayout formLayout;
    DynamicForm dynamicForm ;
    ArrayList<EditText> ets;
    EditText etTitle;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_form);
        list = new ArrayList<FormItem>();
        formLayout = findViewById(R.id.formLayout);
        types = new ArrayList<String>();
        Button addRow = findViewById(R.id.btnAddRow);
        ets = new ArrayList<EditText>();
        dynamicForm = new DynamicForm(context);
        db = new Database(context);
        etTitle = findViewById(R.id.formTitle);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("ID",""+adapterView.getId());
        String type = adapterView.getItemAtPosition(i).toString();
        int index = adapterView.getId();
        types.add(index,type);

        if(type == "Radio Button" || type == "DropDown"){
            EditText et = dynamicForm.addValues(getApplicationContext(),formLayout,"Enter Comma Seperated Value");
            ets.add(index,et);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void createForm(View view) {

        HashMap<String, Object> formStructure = new HashMap<>();

        for(int i = 0; i < list.size();i++){
            String type = types.get(i);
            Log.d(list.get(i).getName().getText().toString(),type);
            formStructure.put(list.get(i).getName().getText().toString(),type);
            if(type == "Radio Button" || type == "DropDown"){
                String[] vals = ets.get(i).getText().toString().split(",");
                Log.d("Values", Arrays.toString(vals));
                formStructure.put(list.get(i).getName().getText().toString() + "_val", vals);
            }
        }

        String title = etTitle.getText().toString();
        if(title == "" || title.isEmpty() || title == null){
            Toast toast = Toast.makeText(CreateFormActivity.this, "Please Provide", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            db.createStructure(formStructure, etTitle.getText().toString());
        }


    }

    public void addRow(View view) {

        FormItem item = dynamicForm.addRow(formLayout,getApplicationContext(),list.size());
        item.getType().setOnItemSelectedListener(this);
        list.add(item);
    }
}