package com.khair.home_work2581;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

public static String imageSllidere="";

    ArrayList<SlideModel> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageSlider imageSlider=findViewById(R.id.image_slider1);

        try {
            JSONArray jsonArray=new JSONArray(imageSllidere);
            if (jsonArray.length()>0){
                JSONArray jsonArray1=jsonArray.getJSONArray(0);
                for (int z=0;z<jsonArray1.length();z++){
                    String imageslinks=jsonArray1.getString(z);
                    imageList.add(new SlideModel(imageslinks,null));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        imageSlider.setImageList(imageList);


    }
}