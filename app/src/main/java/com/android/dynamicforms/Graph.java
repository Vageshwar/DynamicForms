package com.android.dynamicforms;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class Graph extends AppCompatActivity {

    Button mBtnAdd;
    RadioGroup mRgAllButtons;

    private static final String TAG = "const";
    FirebaseFirestore db;

    Database database =new Database(getApplicationContext());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_graph );


        mRgAllButtons = findViewById(R.id.radiogroup);
        //
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number =5; //Integer.parseInt(.getText().toString().trim());
                addRadioButtons(number);
            }
        });
        DocumentReference docRef = db.collection("user").document("SF");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

// Source can be CACHE, SERVER, or DEFAULT.
        Source source = Source.CACHE;

// Get the document, forcing the SDK to use the offline cache
        docRef.get(source).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    // Document found in the offline cache
                    DocumentSnapshot document = task.getResult();
                    Log.d(TAG, "Cached document data: " + document.getData());
                } else {
                    Log.d(TAG, "Cached get failed: ", task.getException());
                }
            }
        });
        String s="form-structure";
        db.collection("user").document(s).collection("form_data").get()
                .addOnCompleteListener(
                        new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                    }
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        }
                );



    }
    public void hash(HashMap<String,Object> fields){
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            GraphView linegraph = (GraphView) findViewById(R.id.line_graph);
            LineGraphSeries<DataPoint> lineSeries = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(0, (int)value),
            });
            linegraph.addSeries(lineSeries);
        }
    }

    public void addRadioButtons(int number) {
        mRgAllButtons.setOrientation(LinearLayout.VERTICAL);
        //
        for (int i = 1; i <= number; i++) {
            RadioButton rdbtn = new RadioButton(this);
            //lableRG = findViewById(R.id.LRg);
            //String LableRg = lableRG.getText().toString().trim();
            //lableBtn = findViewById(R.id.LRbtn);
            //String LableBtn = lableBtn.getText().toString().trim();
            rdbtn.setId(View.generateViewId());
            //rdbtn.setText(LableBtn);
            rdbtn.setOnClickListener((View.OnClickListener) this);
            mRgAllButtons.addView(rdbtn);
        }
    }
}
