package com.my.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        FirebaseDatabase.getInstance().getReference().child("FirebaseDemo").child("1").setValue("Salamy");
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("Khalid","Developer");
//        map.put("Ali","Engineering");
//
//        FirebaseDatabase.getInstance().getReference().child("MultiData").updateChildren(map);
//
//        final Button btnAddName = findViewById(R.id.btn_add_name);
//        final TextInputEditText editTextName =findViewById(R.id.edit_text_name);
//        final ListView listView = findViewById(R.id.list_view);
//        final ArrayList<String> list = new ArrayList<>();
//        final ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list_item,list);
//        listView.setAdapter(adapter);
//
//        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Language");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                list.clear();
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    list.add(dataSnapshot.getValue().toString());
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        btnAddName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String name = editTextName.getText().toString().trim();
//                if(name.isEmpty()){
//                    Toast.makeText(MainActivity.this, "Please Enter Name ", Toast.LENGTH_SHORT).show();
//                }else{
//                    FirebaseDatabase.getInstance().getReference().child("FirebaseDemo").push().child("Name").setValue(name);
//                }
//            }
//        });
    }
}