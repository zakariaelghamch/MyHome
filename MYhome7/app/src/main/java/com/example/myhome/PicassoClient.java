package com.example.myhome;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoClient {
      public  static  void downloadimage(Context c, String imageurl, ImageView img){

          if(imageurl.length()>0 && imageurl!=null){

          Picasso.with(c).load(imageurl).placeholder(R.drawable.placeholder_image).into(img);
          }else{
              Picasso.with(c).load(R.drawable.ic_launcher_background).into(img);
          }
      }
}
