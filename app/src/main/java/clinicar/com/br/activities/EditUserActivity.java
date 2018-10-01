package clinicar.com.br.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;

import clinicar.com.br.clinicar_android.R;
import clinicar.com.br.model.User;
import me.iwf.photopicker.PhotoPicker;


public class EditUserActivity extends Activity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;


    private EditText editEmail;
    private EditText editName;

    private ImageView imgSelected;
    private StorageReference mStorageRef;
    private ArrayList<String> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        editEmail = findViewById(R.id.editEmail);
        editName = findViewById(R.id.editName);

        firebaseAuth = FirebaseAuth.getInstance();

        String uid = firebaseAuth.getUid();
        String userId = firebaseAuth.getCurrentUser().getUid();
        getUserInfo(uid);

        photos = new ArrayList<>();
        imgSelected = findViewById(R.id.imgSelected);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference("user");
    }

    public void getUserInfo(String uid){
        Query mQuery = mDatabase.child(uid);
        User user = new User();
        mQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }


    public void photoPickerFunction(View view){
        PhotoPicker.builder()
                .setPhotoCount(1)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(this, PhotoPicker.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                imgSelected.setImageURI(Uri.parse(photos.get(0)));
            }
        }
    }

    private void resetForm(){
        photos.clear();
        imgSelected.setImageResource(0);
    }

    public void sendPhotoFunction(View view) {
        if(photos.size() > 0){
            Uri file = Uri.fromFile(new File(photos.get(0)));
            StorageReference photoRef = mStorageRef.child("images");
            photoRef.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(EditUserActivity.this, "Arquivo Enviado com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(EditUserActivity.this, "Falha ao enviar arquivo.", Toast.LENGTH_SHORT).show();
                }
            });
            resetForm();
        }else{
            Toast.makeText(this, "Nenhum arquivo carregado.", Toast.LENGTH_SHORT).show();
        }
    }


}
