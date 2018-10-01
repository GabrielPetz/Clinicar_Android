package clinicar.com.br.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import clinicar.com.br.clinicar_android.R;


public class HomeActivity extends Activity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void goUpdateUser(View v){
        Intent intent = new Intent(HomeActivity.this, EditUserActivity.class);
        startActivity(intent);
    }


}
