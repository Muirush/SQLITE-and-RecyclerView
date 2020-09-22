package com.symoh.imagesqliterv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    Context context;
    private   static  String DATABASE_NAME = "MyDb";
    private  static  int DATABASE_VERSION = 1;
    private  static  String createTableQuery = "create table offlineImages ( Id AUTOICREMENT "+",ImageName TEXT "+",image BLOB )";
    private  ByteArrayOutputStream objectByteArrayStream;
    private byte[] imageInBytes;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME,null , DATABASE_VERSION);
        this.context =context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableQuery);
        Toast.makeText(context, "Offline Storage activated", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void storeImage(ModelClass modelClass){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Bitmap bitmapImg = modelClass.getImage();

        objectByteArrayStream = new ByteArrayOutputStream();
        bitmapImg.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayStream);
        imageInBytes = objectByteArrayStream.toByteArray();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ImageName",modelClass.getImageName());
        contentValues.put("image ",imageInBytes);

        long checkIfQueryRuns = sqLiteDatabase.insert("offlineImages", null, contentValues);
        if (checkIfQueryRuns != -1){
            Toast.makeText(context, "You can now view this image in offline mode", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.close();
        }
        else {
            Toast.makeText(context, "Image not saved to offline, Please try again", Toast.LENGTH_SHORT).show();
        }


    }
    public ArrayList<ModelClass> getAll(){
         SQLiteDatabase liteDatabase = this.getReadableDatabase();
         ArrayList<ModelClass>  modelClassArrayList = new ArrayList<>();
        Cursor cursor = liteDatabase.rawQuery("select * from offlineImages order by Id desc", null);
        if (cursor.getCount() == 0){
            Toast.makeText(context, "No image available in the offline mode... Please connect to save some", Toast.LENGTH_SHORT).show();
            return null;
//            while (cursor.moveToNext()){
//                String imageName = cursor.getString(1);
//                byte[] imagebytes = cursor.getBlob(2);
//                 Bitmap objBitmap = BitmapFactory.decodeByteArray(imagebytes,0,imagebytes.length);
//                 modelClassArrayList.add(new ModelClass(imageName,objBitmap));
//
//            }

            
        }else {
            //Toast.makeText(context, "No image available in the offline mode... Please connect to save some", Toast.LENGTH_SHORT).show();
            while (cursor.moveToNext()){
               String imageName = cursor.getString(1);
                byte[] imagebytes = cursor.getBlob(2);
                 Bitmap objBitmap = BitmapFactory.decodeByteArray(imagebytes,0,imagebytes.length);
                 modelClassArrayList.add(new ModelClass(imageName,objBitmap));

            }
            return modelClassArrayList;
        }
    }
}
