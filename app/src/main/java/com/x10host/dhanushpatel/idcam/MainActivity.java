package com.x10host.dhanushpatel.idcam;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getCanonicalName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gateway-a.watsonplatform.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AlchemyService service = retrofit.create(AlchemyService.class);
        File root = Environment.getExternalStorageDirectory();
        Bitmap bMap = BitmapFactory.decodeFile(root + "/camtest/snapchat1.jpg");
        if(bMap==null){
            Log.i("bMap is null",Environment.getExternalStorageDirectory()+"/camtest/snapchat1.jpg");
        }
        else{
            Log.i("bMap is working, size:",bMap.getByteCount()+"");
        }

        /**
        //---
        InputStream inputStream = null;//You can get an inputStream using any IO API
        try {
            inputStream = new FileInputStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/camtest/", "jd.jpg"));
            if(inputStream==null){
                Log.i("ERROR:","FILEPATH ISN'T WORKING");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] bytes;
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bytes = output.toByteArray();
        String encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);
        //---
         **/

        //Call<ImageData> imageData = service.getData();

        Call<ImageData> call = service.getData();


            call.enqueue(new Callback<ImageData>() {
                @Override
                public void onResponse(Response<ImageData> response) {
                    ImageData imageData = response.body();
                    List<Face> faces = imageData.imageFaces;
                    for(Face f: faces) {
                        Log.v(TAG, "response: url: " + f.age.ageRange);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e(TAG, "It failed: " + t.toString());
                }
            });




//        try {
//            Response<ImageData> rID = imageData.execute();
//            ImageData ID = rID.body();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
    public static String encodeTobase64(Bitmap image)
    {
        Bitmap immagex=image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.e("LOOK", imageEncoded);
        return imageEncoded;
    }
    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
