package com.felipefvs.myent;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.felipefvs.myent.database.FirebaseInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseInterface.getFirebaseAuth();

        if(firebaseAuth.getCurrentUser() != null)
        {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }

        final EditText email = findViewById(R.id.mEmailEditText);
        final EditText password = findViewById(R.id.mPasswordEditText);

        Button login = findViewById(R.id.mLoginButton);

        TextView signIn = findViewById(R.id.mSignInTextView);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                try {

                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if( task.isSuccessful() ) {

                                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(i);

                                        Toast.makeText(getApplicationContext(), "Logado com sucesso!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Erro no login!", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            });

                } catch (java.lang.IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(), "Erro no login: " + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    return;
                }





            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignInActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        menu.findItem(R.id.mLogoutItem).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.mLogoutItem) {

            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }


        /*switch (item.getItemId()) {
            case R.id.mLogoutItem:
                newGame();
                return true;
            case R.id.help:
                showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }*/
    }
}
