package com.example.project2020_2021.Databases;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project2020_2021.Attribute.StudentAttr;
import com.example.project2020_2021.StudentsLogIn.StudentRegistration;
import com.example.project2020_2021.StudentsProfile.StudentProfile;
import com.example.project2020_2021.StudentsSignUp.VerifyOPT3;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FirbaseAuthenticationClass extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    final DatabaseReference reference = database.getReference("Users");

    public void LoginUser(String EMAIL, String PASSWORD, final Activity activity) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(EMAIL, PASSWORD)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uid = mAuth.getCurrentUser().getUid();
                            reference.child("Students").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    try{
                                        if(snapshot.exists()){
                                            startActivity(new Intent(getApplicationContext(), StudentProfile.class));
                                        }
                                        else
                                            Toast.makeText(getApplicationContext(), "Students login only!", Toast.LENGTH_LONG).show();

                                    }
                                    catch (Exception e){}
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
               // progressDialog.dismiss();
            }
        });
    }


    public void RegisterUser(String stuname, String stutype, String stuemail, String stupass, String stucountry, String stucity, String stuaddress, String stuphone, String stuteachertype, String stussub, String stugender, String studate, VerifyOPT3 verifyOPT3, ProgressDialog progressDialog) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(stuemail, stupass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images/" + FirebaseDatabase.getInstance().getReference().child("Users").push().getKey());
                            storageReference.putFile(ImagePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                               @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                   Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                                   while (!uriTask.isSuccessful()) ;
                                    Uri downloadUri = uriTask.getResult();

                                    StudentAttr userAttr = new StudentAttr();
                                    userAttr.setStuname(stuname);
                                    userAttr.setStuaddress(stuaddress);
                                    userAttr.setStucountry(stucountry);
                                    userAttr.setStucity(stucity);
                                    userAttr.setStudate(studate);
                                    userAttr.setStuemail(stuemail);
                                    userAttr.setStuphone(stuphone);
                                    userAttr.setStuaddress(stuaddress);
                                    userAttr.setStupass(stupass);
                                    userAttr.setStutype(stutype);
                                    userAttr.setStuyeachertype(stuteachertype);
                                    userAttr.setStutype(stussub);
                                    userAttr.setStugender(stugender);
                                    reference.child(uid).setValue(userAttr);
                                     verifyOPT3.startActivity(new Intent(verifyOPT3, StudentProfile.class));
                                    Toast.makeText(verifyOPT3, "Account Created", Toast.LENGTH_SHORT).show();
                                    verifyOPT3.finish();
                                    progressDialog.dismiss();

                                }
                            });


                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(verifyOPT3, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }
}
