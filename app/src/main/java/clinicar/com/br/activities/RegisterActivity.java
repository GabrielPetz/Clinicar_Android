package clinicar.com.br.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import clinicar.com.br.clinicar_android.R;


public class RegisterActivity extends Activity {

    private EditText password;
    private EditText email;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        mDataBase = FirebaseDatabase.getInstance().getReference("user");
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void register(View v) {
        String sLogin = email.getText().toString();
        String sPassword = password.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(sLogin, sPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Registrado com sucesso", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Erro no registro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void gologin(View v) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }


}
