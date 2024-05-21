package com.khair.home_work2581;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    GridView gridView;

    ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();
    HashMap<String,String>hashMap;

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView=findViewById(R.id.grid_view);
        textView=findViewById(R.id.show);


        SlideModel("https://bit.ly/2YoJ77H","https://bit.ly/2YoJ77H",
                "https://bit.ly/2YoJ77H","https://bit.ly/2YoJ77H",
                "The animal population decreased by 58","The animal population decreased by 58",
                "The animal population decreased by 58","The animal population decreased by 58"
                ,ScaleTypes.CENTER_CROP);




        String url="https://dummyjson.com/products";



        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("products");
                    for (int x=0;x<jsonArray.length();x++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(x);
                        String title=jsonObject1.getString("title");
                        String description=jsonObject1.getString("description");
                        String price=jsonObject1.getString("price");
                        String rating=jsonObject1.getString("rating");
                        String stock=jsonObject1.getString("stock");
                        String brand=jsonObject1.getString("brand");
                        String thumbnail=jsonObject1.getString("thumbnail");
                        String discountPercentage=jsonObject1.getString("discountPercentage");



                   String[]images=new String[]{jsonObject1.getString("images")};
                   String imagesArray=Arrays.toString(images);


                        hashMap=new HashMap<>();
                        hashMap.put("title",title);
                        hashMap.put("description",description);
                        hashMap.put("price",price);
                        hashMap.put("rating",rating);
                        hashMap.put("stock",stock);
                        hashMap.put("brand",brand);
                        hashMap.put("images",imagesArray);
                        hashMap.put("discountPercentage",discountPercentage);
                        hashMap.put("thumbnail",thumbnail);
                        arrayList.add(hashMap);



                    }



                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                MyAdapter myAdapter=new MyAdapter();
                gridView.setAdapter(myAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(objectRequest);












    }
   //=================================================================================
public void SlideModel(String string,String string1,String string2,String string3,String tittle,String tittle1,String tittle2,String tittle3 ,ScaleTypes scaleTypes){

       ArrayList<SlideModel> imageList = new ArrayList<>(); // Create image list

       // Add images to the list
       imageList.add(new SlideModel(string, tittle, scaleTypes));
       imageList.add(new SlideModel(string1, tittle1, scaleTypes));
       imageList.add(new SlideModel(string2, tittle2, scaleTypes));
       imageList.add(new SlideModel(string3, tittle3, scaleTypes));

       ImageSlider imageSlider=findViewById(R.id.image_slider1);
       imageSlider.setImageList(imageList);





   }
///********************************************************************************

public class MyAdapter extends BaseAdapter{

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater  inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.layout,parent,false);


     TextView textView1=view.findViewById(R.id.textView2);
     TextView textView2=view.findViewById(R.id.textView3);
     TextView textView3=view.findViewById(R.id.textView4);
     TextView textView4=view.findViewById(R.id.textView5);
     TextView textView5=view.findViewById(R.id.textView6);
     TextView textView6=view.findViewById(R.id.textView7);
     ImageView imageView=view.findViewById(R.id.imageView);
     LinearLayout linearLayout=view.findViewById(R.id.layout);

     HashMap<String,String>hashMap=arrayList.get(position);
     String title=hashMap.get("title");
     String description=hashMap.get("description");
     String price=hashMap.get("price");
     String rating=hashMap.get("rating");
     String stock=hashMap.get("stock");
     String brand=hashMap.get("brand");
     String thumbnail=hashMap.get("thumbnail");
    String images=hashMap.get("images");
     String discountPercentage=hashMap.get("discountPercentage");

     textView1.setText(title);
     textView2.setText(description);
     textView3.setText("$"+price);
     textView4.setText("*"+stock);
     textView5.setText(brand);
     textView6.setText("*"+rating);

        Picasso.get().load(thumbnail).into(imageView);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity2.imageSllidere=images;
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });






        return view;

    }
}



 ///=================================================================================
}