package com.android.dynamicforms;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static android.content.ContentValues.TAG;

public class Database {

    FirebaseFirestore db;
    HashMap<String, Object> data;
    Context context;
    DocumentSnapshot ds;
    Database(Context context){
        this.context = context;
        db = FirebaseFirestore.getInstance();
        data = new HashMap<String, Object>();
    }

    void createStructure(HashMap<String, Object> formStructure, String title, Context context){
//        db.collection("users")
//                .add(formStructure).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
//            }
//        })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });

        db.collection("user").document(title).set(formStructure);
        Toast.makeText(context, "Structure Created", Toast.LENGTH_LONG).show();

    }

    HashMap<String, Object> fetchStructure(String id){



        final HashMap<String, Object>[] form_dat = new HashMap[]{new HashMap<String, Object>()};
        db.collection("user").document(id).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        ds = documentSnapshot;
                        form_dat[0] = (HashMap<String, Object>) documentSnapshot.getData();
                        Log.d(TAG, "onSuccess: " + form_dat[0].size());
                        data = new HashMap<String, Object>();
                    }
                });
        if(ds == null){
            Log.d("Waiting", "DDD");
            CountDownLatch done = new CountDownLatch(1);
            done.countDown();
        }
        return form_dat[0];

    }

    void storeData(String formID, HashMap<String,Object> data){

    }

    void FetchData(String formId){

    }
}
