package com.mac.training.data1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "F - ";
    private static final int RC_SIGN_IN = 357 ;
    EditText mEditText1;
    Button mButtonSendText;

    //Firebase Variables
    DatabaseReference myRef;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText1 = (EditText) findViewById(R.id.mEditText1);
        mButtonSendText = (Button) findViewById(R.id.mButtonSendText);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("ElKiMan");
        myRef.setValue("HELLO joNA LETS START OVER..");

        mButtonSendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Toast.makeText(getBaseContext(), mEditText1.getText().toString(), Toast.LENGTH_LONG);
                        myRef.setValue(mEditText1.getText().toString());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){

        }else {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setProviders(
                                    AuthUI.EMAIL_PROVIDER,
                                    AuthUI.GOOGLE_PROVIDER,
                                    AuthUI.FACEBOOK_PROVIDER)
                            .build(),
                    RC_SIGN_IN);
        }

    }

    public void onWrite(View view){
        myRef.setValue(mEditText1.getText().toString());
    }
}
