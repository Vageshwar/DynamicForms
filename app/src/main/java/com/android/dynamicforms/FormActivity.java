package com.android.dynamicforms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class FormActivity extends AppCompatActivity {

    DynamicForm dynamicForm;
    LinearLayout formLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Intent i = this.getIntent();
        String title = i.getStringExtra("title");
        formLayout = findViewById(R.id.formLayout);
        TextView textView = findViewById(R.id.formTitle);
        textView.setText(title);
        Database db = new Database(getApplicationContext());
        dynamicForm = new DynamicForm(getApplicationContext());
        db.db.collection("user").document(title).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        HashMap<String, Object> data  = (HashMap<String, Object>) documentSnapshot.getData();
                        Log.d(TAG, "onSuccess: " + data.size());
                        buildForm(data);
                    }
                });

    }


    public void buildForm(HashMap<String,Object> formStructure){
        HashMap<String,Object> fields = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : formStructure.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            switch (value.toString()){
                case "Text":
                case "Number":
                case "Date":
                    EditText et = dynamicForm.addValues(getApplicationContext(),formLayout,key);
                    fields.put("EditText", et);
                    break;
                case "Radio Button":
                    fields.put("Radio Button","");
                    break;
                case "DropDown":
                    fields.put("DropDown", "");
                    break;

                default:
                    Log.d("Invalid", value.toString());

            }
            // you code here
        }
    }

    public void submit(View view) {
        Toast.makeText(FormActivity.this, "FFF", Toast.LENGTH_LONG).show();
    }
}