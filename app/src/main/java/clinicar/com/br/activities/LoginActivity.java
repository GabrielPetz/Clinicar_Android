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

import clinicar.com.br.clinicar_android.R;


public class LoginActivity extends Activity {


    private EditText password;
    private EditText email;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        password = findViewById(R.id.password);
        email = findViewById(R.id.email);

        firebaseAuth = FirebaseAuth.getInstance();
    }


    public void login(View v) {

        String sLogin = email.getText().toString();
        String sPassword = password.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(sLogin, sPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Erro ao logar", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void goregister(View v) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

}
