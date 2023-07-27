package com.example.digitalstudentpassport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentPersonalDetails extends AppCompatActivity {
    EditText name_personal_details,roll_personal_details,email_personal_details,
            phone_personal_details,dob_personal_details,curraddress_personal_details,abcid_personal_details,aadhar_personal_details,pan_personal_details;
    Spinner dept_personal_details,year_personal_details,division_personal_details,gender_personal_details,caste_personal_details;
    Button SaveBtnPersonal_details;
    ImageView back_personal_details;
    CircleImageView profileImg;
    Uri imageuri;
    FirebaseAuth auth;
    FirebaseFirestore fStore;
    StorageReference storageReference;

    String name,email,phone,department,year,division,gender,caste;

    String [] department_select = {"Select","Computer Science","Electronics and Telecommunication","Information Technology"};
    String [] year_select = {"Select","FE","SE","TE","BE"};
    String [] div_select = {"Select","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X","XI"};
    String [] gender_select = {"Select","Male","Female","Other"};
    String [] caste_select = {"Select","General","OBC","EWS","SC","ST","NT"};
    ArrayAdapter<String> adapter_dept;
    ArrayAdapter<String> adapter_year;
    ArrayAdapter<String> adapter_div;
    ArrayAdapter<String> adapter_gender;
    ArrayAdapter<String> adapter_caste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_student_personal_details);
        init();
        instance();
        initiateCategories();
    }
    void init()
    {
        name_personal_details=findViewById(R.id.name_personal_details);
        roll_personal_details=findViewById(R.id.roll_personal_details);
        email_personal_details=findViewById(R.id.email_personal_details);
        phone_personal_details=findViewById(R.id.phone_personal_details);
        dob_personal_details=findViewById(R.id.dob_personal_details);
        curraddress_personal_details=findViewById(R.id.curraddress_personal_details);
        dept_personal_details=findViewById(R.id.dept_personal_details);
        year_personal_details=findViewById(R.id.year_personal_details);
        division_personal_details=findViewById(R.id.division_personal_details);
        gender_personal_details=findViewById(R.id.gender_personal_details);
        caste_personal_details=findViewById(R.id.caste_personal_details);
        abcid_personal_details=findViewById(R.id.abcid_personal_details);
        aadhar_personal_details=findViewById(R.id.aadhar_personal_details);
        pan_personal_details=findViewById(R.id.pan_personal_details);
        SaveBtnPersonal_details=findViewById(R.id.SaveBtnPersonal_details);
        back_personal_details=findViewById(R.id.back_personal_details);
        profileImg=findViewById(R.id.profileImg);
    }
    private void instance()
    {
        auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }
    private void initiateCategories()
    {

        //spinners
        adapter_dept = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,department_select);
        dept_personal_details.setAdapter(adapter_dept);

        adapter_year = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,year_select);
        year_personal_details.setAdapter(adapter_year);

        adapter_div = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,div_select);
        division_personal_details.setAdapter(adapter_div);

        adapter_gender = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,gender_select);
        gender_personal_details.setAdapter(adapter_gender);

        adapter_caste = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,caste_select);
        caste_personal_details.setAdapter(adapter_caste);

        //department select
        dept_personal_details.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                department = dept_personal_details.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Year select
        year_personal_details.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                year = year_personal_details.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //division select
        division_personal_details.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                division = division_personal_details.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Gender Select
        gender_personal_details.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender = gender_personal_details.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Caste Select
        caste_personal_details.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                caste = caste_personal_details.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //back button
        back_personal_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DashBoardNew.class));
                finish();
            }
        });
        //save button
        SaveBtnPersonal_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roll_no = roll_personal_details.getText().toString();
                String DOB = dob_personal_details.getText().toString();
                String current_address = curraddress_personal_details.getText().toString();
                String abcid = abcid_personal_details.getText().toString();
                String aadhar = aadhar_personal_details.getText().toString();
                String pan = pan_personal_details.getText().toString();

                if(check(roll_no))
                {
                    Toast.makeText(StudentPersonalDetails.this, "Enter Roll No", Toast.LENGTH_SHORT).show();
                }
                else if(department.equals("Select"))
                {
                    Toast.makeText(StudentPersonalDetails.this, "Select Branch", Toast.LENGTH_SHORT).show();
                }
                else if(year.equals("Select"))
                {
                    Toast.makeText(StudentPersonalDetails.this, "Select Branch", Toast.LENGTH_SHORT).show();
                }
                else if(division.equals("Select"))
                {
                    Toast.makeText(StudentPersonalDetails.this, "Select Division", Toast.LENGTH_SHORT).show();
                }
                else if(gender.equals("Select"))
                {
                    Toast.makeText(StudentPersonalDetails.this, "Select Gender", Toast.LENGTH_SHORT).show();
                }
                else if(caste.equals("Select"))
                {
                    Toast.makeText(StudentPersonalDetails.this, "Select Caste Category", Toast.LENGTH_SHORT).show();
                }
                else if(check(current_address))
                {
                    Toast.makeText(StudentPersonalDetails.this, "Enter Address", Toast.LENGTH_SHORT).show();
                }
                else if(check(DOB))
                {
                    Toast.makeText(StudentPersonalDetails.this, "Enter Date of Birth", Toast.LENGTH_SHORT).show();
                }
                else if(check(abcid))
                {
                    Toast.makeText(StudentPersonalDetails.this, "Enter ABC ID", Toast.LENGTH_SHORT).show();
                }
                else if(check(aadhar))
                {
                    Toast.makeText(StudentPersonalDetails.this, "Enter AADHAR NO.", Toast.LENGTH_SHORT).show();
                }
                else if(check(pan))
                {
                    Toast.makeText(StudentPersonalDetails.this, "Enter PAN NO.", Toast.LENGTH_SHORT).show();
                }
                else if(roll_no.length()!=5)
                {
                    Toast.makeText(StudentPersonalDetails.this, "Invalid Roll No.", Toast.LENGTH_SHORT).show();
                }
                else if(abcid.length()!=12)
                {
                    Toast.makeText(StudentPersonalDetails.this, "Invalid ABC ID", Toast.LENGTH_SHORT).show();
                }
                else if(aadhar.length()!=12)
                {
                    Toast.makeText(StudentPersonalDetails.this, "Invalid Aadhar No.", Toast.LENGTH_SHORT).show();
                }
                else if(pan.length()!=10)
                {
                    Toast.makeText(StudentPersonalDetails.this,"Invalid PAN NO.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(year.equals("FE"))
                    {
                        final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
                        progressDialog.setMessage("Saving , Please wait...");
                        progressDialog.show();
                        Map<String,Object> user_details = new HashMap<>();
                        DocumentReference documentReference = fStore.collection("FE").document(auth.getCurrentUser().getUid());
                        user_details.put("Name",name);
                        user_details.put("Email",email);
                        user_details.put("Phone Number",phone);
                        user_details.put("Roll No",roll_no);
                        user_details.put("Branch",department);
                        user_details.put("Year",year);
                        user_details.put("Division",division);
                        user_details.put("Gender",gender);
                        user_details.put("Caste Category",caste);
                        user_details.put("Current Address",current_address);
                        user_details.put("D.O.B",DOB);
                        user_details.put("ABC ID",abcid);
                        user_details.put("Aadhar No.",aadhar);
                        user_details.put("Pan No.",pan);

                        documentReference.set(user_details);

                        progressDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(),DashBoardNew.class));
                        finish();
                    }
                    else if(year.equals("SE"))
                    {
                        final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
                        progressDialog.setMessage("Saving , Please wait...");
                        progressDialog.show();
                        Map<String,Object> user_details = new HashMap<>();
                        DocumentReference documentReference = fStore.collection("SE").document(auth.getCurrentUser().getUid());
                        user_details.put("Name",name);
                        user_details.put("Email",email);
                        user_details.put("Phone Number",phone);
                        user_details.put("Roll No",roll_no);
                        user_details.put("Branch",department);
                        user_details.put("Year",year);
                        user_details.put("Division",division);
                        user_details.put("Gender",gender);
                        user_details.put("Caste Category",caste);
                        user_details.put("Current Address",current_address);
                        user_details.put("D.O.B",DOB);
                        user_details.put("ABC ID",abcid);
                        user_details.put("Aadhar No.",aadhar);
                        user_details.put("Pan No.",pan);

                        documentReference.set(user_details);

                        progressDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(),DashBoardNew.class));
                        finish();
                    }
                    else if(year.equals("TE"))
                    {
                        final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
                        progressDialog.setMessage("Saving , Please wait...");
                        progressDialog.show();
                        Map<String,Object> user_details = new HashMap<>();
                        DocumentReference documentReference = fStore.collection("TE").document(auth.getCurrentUser().getUid());
                        user_details.put("Name",name);
                        user_details.put("Email",email);
                        user_details.put("Phone Number",phone);
                        user_details.put("Roll No",roll_no);
                        user_details.put("Branch",department);
                        user_details.put("Year",year);
                        user_details.put("Division",division);
                        user_details.put("Gender",gender);
                        user_details.put("Caste Category",caste);
                        user_details.put("Current Address",current_address);
                        user_details.put("D.O.B",DOB);
                        user_details.put("ABC ID",abcid);
                        user_details.put("Aadhar No.",aadhar);
                        user_details.put("Pan No.",pan);

                        documentReference.set(user_details);

                        progressDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(),DashBoardNew.class));
                        finish();
                    }
                    else if(year.equals("BE"))
                    {
                        final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
                        progressDialog.setMessage("Saving , Please wait...");
                        progressDialog.show();
                        Map<String,Object> user_details = new HashMap<>();
                        DocumentReference documentReference = fStore.collection("BE").document(auth.getCurrentUser().getUid());
                        user_details.put("Name",name);
                        user_details.put("Email",email);
                        user_details.put("Phone Number",phone);
                        user_details.put("Roll No",roll_no);
                        user_details.put("Branch",department);
                        user_details.put("Year",year);
                        user_details.put("Division",division);
                        user_details.put("Gender",gender);
                        user_details.put("Caste Category",caste);
                        user_details.put("Current Address",current_address);
                        user_details.put("D.O.B",DOB);
                        user_details.put("ABC ID",abcid);
                        user_details.put("Aadhar No.",aadhar);
                        user_details.put("Pan No.",pan);

                        documentReference.set(user_details);

                        progressDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(),DashBoardNew.class));
                        finish();
                    }
                }
            }
        });
    }

    boolean check(String s)
    {
        if(s.equals(""))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            checkUserAccesLevel(auth.getUid());
        }
    }
    private void checkUserAccesLevel(String uid) {
        DocumentReference df = fStore.collection("Students").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                name = documentSnapshot.getString("Name");
                email = documentSnapshot.getString("Email");
                phone = documentSnapshot.getString("Phone number");

                name_personal_details.setText(name);
                email_personal_details.setText(email);
                phone_personal_details.setText(phone);
            }
        });
    }
    }