package com.android.dynamicforms;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class Database {

    FirebaseFirestore db;
    Context context;
    Database(Context context){
        this.context = context;
        db = FirebaseFirestore.getInstance();
    }

    void createStructure(HashMap<String, Object> formStructure, String title){
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
    }

    void fetchStructure(String id){

    }

    void storeData(String formID, HashMap<String,Object> data){

    }

    void FetchData(String formId){

    }
}
