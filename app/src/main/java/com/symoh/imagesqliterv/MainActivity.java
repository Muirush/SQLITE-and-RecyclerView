package com.symoh.imagesqliterv;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView img;
    private EditText edt;
    private Button btSave, btView;
    private static  final int PICK_IMAGE_REQUEST = 1;
    private Uri imgUri;
    private Bitmap imgToStore;
    DatabaseHandler databaseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.selector);
        edt = findViewById(R.id.fileName);
        btSave = findViewById(R.id.saveBtn);
        btView = findViewById(R.id.View);
        databaseHandler = new DatabaseHandler(this);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseImage();

            }
        });

        btView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, showImages.class);
                startActivity(intent);
            }
        });
    }
    public  void ChooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
    super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_REQUEST && resultCode ==RESULT_OK && data != null && data.getData() != null){
                imgUri = data.getData();
                imgToStore = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
                img.setImageBitmap(imgToStore);

        }

        }catch (Exception exe){
            Toast.makeText(this, exe.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void StoreImage( View view){
        if ( ! edt.getText().toString().isEmpty() && img.getDrawable() != null && imgToStore != null){
            databaseHandler.storeImage(new ModelClass(edt.getText().toString(),imgToStore));
            edt.setText("");

        }
        else {
            Toast.makeText(this, "Please select an image and enter title", Toast.LENGTH_SHORT).show();
        }
    }



}
