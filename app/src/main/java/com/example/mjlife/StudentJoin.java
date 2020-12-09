package com.example.mjlife;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;

public class StudentJoin extends AppCompatActivity {
    private final int GALLERY_CODE = 10;
    EditText name_in, id_in, password_in, phone_in, passCheck_in;
    Button btnUpload, check, complete;
    DatabaseReference mDatabase;
    HashMap result;
    ImageView photo;
    private FirebaseStorage storage;
    StorageReference storageRef;
    String imagePath, imageName;
    int c=0, k=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        name_in = findViewById(R.id.name_in);
        id_in = findViewById(R.id.id_in);
        password_in = findViewById(R.id.password_in);
        passCheck_in = findViewById(R.id.passwordCheck_in);
        phone_in = findViewById(R.id.phone_in);
        btnUpload = findViewById(R.id.btnUpload);
        check = findViewById(R.id.check);
        complete = findViewById(R.id.complete);
        mDatabase = FirebaseDatabase.getInstance().getReference("Student");
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPicture();
            }
        });


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = id_in.getText().toString();
                k++;
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot generalSnapshot : snapshot.getChildren()) {
                            if (generalSnapshot.child("id").getValue().toString().equals(id)) {
                                //중복입니다. 학번을 다시 확인해주세요 다이얼로그
                                c++;
                                k=0;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (k == 0 || c != 0) {
                    //학번 중복확인을 해주세요 다이얼로그
                }
                else if(imagePath.equals("")){
                    //유체크 사진을 업로드 해주세요 다이얼로그
                }else {
                    Uri file = Uri.fromFile(new File(imagePath));
                    StorageReference ref = storageRef.child("general/"+file.getLastPathSegment());
                    UploadTask uploadTask =  ref.putFile(file);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            ;
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ;
                        }
                    });
                    final String name = name_in.getText().toString();
                    final String id = id_in.getText().toString();
                    final String phone = phone_in.getText().toString();
                    if (password_in.getText().toString() == passCheck_in.getText().toString()) {
                        final String password = password_in.getText().toString();
                        if (name.equals("") || id.equals("") || phone.equals("") || password.equals("")) {
                            //모든 항목을 채워주세요 다이얼로그
                        } else {
                            dbJoin(name, id, phone, password);
                            //가입완료 다이얼로그, 로그인 화면으로 전환
                        }
                    } else {
                        //비밀번호를 다시 확인해주세요 다이얼로그
                    }
                }
            }
        });


    }

    public void dbJoin(String name, String id, String phone, String password){
        result = new HashMap();
        result.put("name", name);
        result.put("id", id);
        result.put("phone", phone);
        result.put("password", password);
        mDatabase.child((result.get("id").toString())).setValue(result);
    }
    public void getPicture() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, GALLERY_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_CODE) {
            imagePath = getPath(data.getData());
            File f = new File(imagePath);
            photo.setImageURI(data.getData());
            imageName = imagePath.substring(imagePath.lastIndexOf("/") + 1);

        }
    }

    public String getPath(Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(this, uri, proj, null, null, null);

        Cursor cu = cursorLoader.loadInBackground();
        int index = cu.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cu.moveToFirst();

        return cu.getString(index);
    }

}
