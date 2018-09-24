package clinicar.com.br.clinicar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import clinicar.com.br.clinicar.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText password;
    private EditText email;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        password = findViewById(R.id.password);
        email = findViewById(R.id.email);

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
