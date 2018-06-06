package com.felipefvs.myent;

import android.content.Intent;
import android.graphics.drawable.Drawable;
//import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.felipefvs.myent.database.FirebaseInterface;
import com.felipefvs.myent.model.Favorite;
import com.felipefvs.myent.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference firebaseReference;

    EditText mName;
    EditText mLastName;
    EditText mEmail;
    EditText mPassword;
    EditText mConfirPassword;
    Button mSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mName = findViewById(R.id.mNameEditText);
        mLastName = findViewById(R.id.mLastNameEditText);
        mEmail = findViewById(R.id.mEmailEditText);
        mPassword = findViewById(R.id.mPasswordEditText);
        mConfirPassword = findViewById(R.id.mConfirmPasswordEditText);
        mSignInButton = findViewById(R.id.mSignInButton);

        firebaseAuth = FirebaseInterface.getFirebaseAuth();
        firebaseReference = FirebaseInterface.getFirebase();


        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String msg = validade();
                if(!msg.isEmpty()) {
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    return;
                }

                User newUser = new User();
                newUser.setName(mName.getText().toString());
                newUser.setLastname(mLastName.getText().toString());
                newUser.setEmail(mEmail.getText().toString());
                newUser.setFavorites(new ArrayList<Favorite>());

                createUser(newUser, mPassword.getText().toString());

                Intent i = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(i);

            }
        });

        EditText confirmPassword = findViewById(R.id.mConfirmPasswordEditText);

        confirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(!b){

                    Drawable dw;

                    if(!isPasswordConfirmed()) {
                        dw = getResources().getDrawable(R.drawable.edittext_error_bg);
                        Toast.makeText(getApplicationContext(), "As senhas não estão compatíveis", Toast.LENGTH_SHORT).show();

                    } else {
                        dw = getResources().getDrawable(R.drawable.edittextbackground);
                    }

                    view.setBackground(dw);

                }


            }
        });

    }

    private boolean isPasswordConfirmed() {
        String pw = mPassword.getText().toString();
        String cPw = mConfirPassword.getText().toString();

        return pw.equals(cPw);
    }

    private String validade() {
        if(mName.getText().toString().isEmpty() ||
                mPassword.getText().toString().isEmpty() ||
                mEmail.getText().toString().isEmpty() ||
                !isPasswordConfirmed()) {
            return "Existem campos inválidos";
        }

        if(mPassword.length() < 6)
            return "A senha deve conter mais de 6 caracteres!";

        return "";
    }

    private void createUser(final User user, final String password) {

        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), password)
                .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(Task<AuthResult> task) {

                    if( task.isSuccessful() ) {

                    String userId = firebaseAuth.getCurrentUser().getUid();

                    firebaseReference.child("users").child(userId).setValue(user);

                    Toast.makeText(getApplicationContext(), "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Erro no cadastrado!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_LONG).show();
                }
            }

        });

    }
}
