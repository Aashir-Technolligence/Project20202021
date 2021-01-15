package com.example.project2020_2021.StudentsSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2020_2021.Databases.StuUserHelperClass;
import com.example.project2020_2021.Databases.TeaUserHelperClass;
import com.example.project2020_2021.R;
import com.example.project2020_2021.StudentsLogIn.StudentRegistration;
import com.example.project2020_2021.StudentsProfile.StudentProfile;
import com.example.project2020_2021.TeachersProfile.TeacherProfile;
import com.example.project2020_2021.TeachersSignUp.TeacherSignUp4;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class StudentSignUp3 extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    ImageView backbtn;
    TextView ttext, subtext, tloginbtn;
    Button teanext3;
    TextInputLayout stuss, stutt;
    RadioGroup stuGender;
    RadioButton selectedGender;
    DatePicker studatePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up3);

        //for dropdown fields
        autoCompleteTextView = findViewById(R.id.autocompletetextstuteatype);
        String [] option = {"Home Teacher", "Online Teacher", "Teacher at Academy", "Teacher At My Place"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.option_item,option);
        //To set Default Values
        autoCompleteTextView.setText("Select Teacher Type");
        autoCompleteTextView.setAdapter(arrayAdapter);

        backbtn = (ImageView) findViewById(R.id.backbtn);
        ttext = (TextView) findViewById(R.id.register_teacher_title_text);
        subtext = (TextView) findViewById(R.id.register_teacher_text);
        tloginbtn = (TextView)  findViewById(R.id.student_loginbtn);
        teanext3 = (Button) findViewById(R.id.register_student_next_btn);
//
//        intent.putExtra("stuname",nameS);
//        intent.putExtra("stutype",typeS);
//        intent.putExtra("stuemail",emailS);
//        intent.putExtra("stupass",passS);
//        intent.putExtra("stucountry",stucountryS);
//        intent.putExtra("stucity",stucityS);
//        intent.putExtra("stuaddress",stuaddressS);
//        intent.putExtra("stuphoneno",stuphonefullS);
//
        Intent intent = getIntent();
        //getting all values passed from previous screen
        String _stuname = getIntent().getStringExtra("stuname");
        String _stutype = getIntent().getStringExtra("stutype");
        String _stuemail = getIntent().getStringExtra("stuemail");
        String _stupass = getIntent().getStringExtra("stupass");
        String _stucountry = getIntent().getStringExtra("stucountry");
        String _stucity = getIntent().getStringExtra("stucity");
        String _stuaddress = getIntent().getStringExtra("stuaddress");
        String _stuphone = getIntent().getStringExtra("stuphoneno");

        tloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudentRegistration.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(findViewById(R.id.student_loginbtn),"tloginbtn");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(StudentSignUp3.this,pairs);
                startActivity(intent,options.toBundle());
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentSignUp3.this, StudentSignUp2.class);
                startActivity(intent);
            }
        });

        stuss = (TextInputLayout) findViewById(R.id.stuss);
        stuGender = (RadioGroup) findViewById(R.id.stugenderrg);
        stutt = (TextInputLayout) findViewById(R.id.stuteachertype);
        studatePicker = (DatePicker) findViewById(R.id.stuagepicker);


        teanext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( !validateStuTeaType() | !validateStuSS() | !validateStuGender()){
                    return;
                }

//                Intent intent = new Intent(StudentSignUp3.this, VerifyOPT3.class);

                selectedGender = (RadioButton) findViewById(stuGender.getCheckedRadioButtonId());





                int day = studatePicker.getDayOfMonth();
                int month = studatePicker.getMonth();
                int year = studatePicker.getYear();

                //getting fields data
                String stugender = selectedGender.getText().toString();
                String studate = day+"/"+month+"/"+year;

                String stuteatypeS = stutt.getEditText().getText().toString().trim();
                String stussubS = stuss.getEditText().getText().toString().trim();

                //passing data
//                intent.putExtra("stuname",_stuname);
//                intent.putExtra("stutype",_stutype);
//                intent.putExtra("stuemail",_stuemail);
//                intent.putExtra("stupass",_stupass);
//                intent.putExtra("stucountry",_stucountry);
//                intent.putExtra("stucity",_stucity);
//                intent.putExtra("stuaddress",_stuaddress);
//                intent.putExtra("stuphoneno",_stuphone);
//                intent.putExtra("stuteatype",stuteatypeS);
//                intent.putExtra("stussub",stussubS);
//                intent.putExtra("stugender",stugender);
//                intent.putExtra("studate",studate);


                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(teanext3,"transition_register_btn");

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(_stuemail, _stupass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    StuUserHelperClass addNewUser = new StuUserHelperClass(_stuname, _stutype, _stuemail, _stupass, _stucountry, _stucity,
                                            _stuaddress, _stuphone, stuteatypeS, stussubS, stugender, studate);
                                    FirebaseDatabase.getInstance().getReference("Users").child("Students")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(addNewUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
                                                Intent intent = new Intent(StudentSignUp3.this, StudentProfile.class);
                                                startActivity(intent);

//                                                mAuth.getCurrentUser().sendEmailVerification()
//                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                            @Override
//                                                            public void onComplete(@NonNull Task<Void> task) {
//                                                                if (task.isSuccessful()) {
//                                                                    Toast.makeText(TeacherSignUp4.this, "Registration Successful! Check your Email for further Verification", Toast.LENGTH_LONG).show();
//                                                                } else {
//                                                                    Toast.makeText(TeacherSignUp4.this, "Registration UnSuccessful!", Toast.LENGTH_LONG).show();
//                                                                }
//                                                            }
//                                                        });
                                            } else {
                                                Toast.makeText(StudentSignUp3.this, "Registration UnSuccessful!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(StudentSignUp3.this, "Registration UnSuccessful!", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(StudentSignUp3.this,pairs);
//                startActivity(intent,options.toBundle());
            }
        });



    }

    private boolean validateStuSS()
    {
        String val = stuss.getEditText().getText().toString().trim();

        if (val.isEmpty())
        {
            stuss.setError("Field can not be Empty");
            return false;
        }
        else
        {
            stuss.setError(null);
            stuss.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validateStuGender()
    {
        if (stuGender.getCheckedRadioButtonId()==-1)
        {
            Toast.makeText(this,"Please Select Gender",Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean validateStuTeaType()
    {
        String val = autoCompleteTextView.getText().toString().trim();

        if (val.equals("Select Teacher Type"))
        {
            stutt.setError("Field can not be Empty");
            return false;
        }
        else
        {
            stutt.setError(null);
            stutt.setErrorEnabled(false);
            return true;
        }
    }


}