package com.android.dynamicforms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class CreateFormActivity extends AppCompatActivity {

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_form);
        final LinearLayout formLayout = findViewById(R.id.formLayout);
        Button addRow = findViewById(R.id.btnAddRow);
        addRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DynamicForm dynamicForm = new DynamicForm(context);
                dynamicForm.addRow(formLayout,getApplicationContext(),"Test");
            }
        });
    }
}