package com.example.quotes_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.*;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DirectAction;
import android.content.Context;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends Activity {

    int windowwidth; 
    int screenCenter; 
    int x_cord, y_cord, x, y; 
    int Likes = 0; 
    public RelativeLayout parentView; 
    float alphaValue = 0; 
    private Context context;  

    //ArrayList<> userDataModelArrayList;  
    //  @SuppressWarnings("deprecation") 
    // @SuppressLint("NewApi") 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestWindowFeature(Window.FEATURE_NO_TITLE); 

        context = MainActivity.this;  

        parentView = (RelativeLayout) findViewById(R.id.card_view);  

        windowwidth = getWindowManager().getDefaultDisplay().getWidth();  

        screenCenter = windowwidth / 2;  

        //userDataModelArrayList = new ArrayList<>();  

      //  getArrayData();  

      //  for (int i = 0; i < userDataModelArrayList.size(); i++) {  

        //    LayoutInflater inflate = 
          //  (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  

        //    final View containerView = inflate.inflate(R.layout.custom_tinder_layout, null);  

         //   ImageView userIMG = (ImageView) containerView.findViewById(R.id.userIMG); 
         //   RelativeLayout relativeLayoutContainer = (RelativeLayout) containerView.findViewById(R.id.relative_container);  

         ///   LayoutParams layoutParams = new LayoutParams( 
            //        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);  
        //    containerView.setLayoutParams(layoutParams);  

        //    containerView.setTag(i); 
          //  userIMG.setBackgroundResource(userDataModelArrayList.get(i).getPhoto());  

            //            m_view.setRotation(i); 
            //containerView.setPadding(0, i, 0, 0);  

         //   LayoutParams layoutTvParams = new LayoutParams( 
           //         LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);   


            // ADD dynamically next TextView on image. 
           final TextView tvLike = new TextView(context); 
            tvLike.setLayoutParams(layoutTvParams); 
            tvLike.setPadding(10, 10, 10, 10); 
            tvLike.setBackgroundDrawable(getResources().getDrawable(R.drawable.btnlikeback)); 
            tvLike.setText("LIKE"); 
            tvLike.setGravity(Gravity.CENTER); 
            tvLike.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD)); 
            tvLike.setTextSize(25); 
            tvLike.setTextColor(ContextCompat.getColor(context, R.color.red)); 
            tvLike.setX(20); 
            tvLike.setY(100); 
            tvLike.setRotation(-50); 
            tvLike.setAlpha(alphaValue); 
            relativeLayoutContainer.addView(tvLike);  

            // ADD dynamically Previous TextView on image. 
            final TextView tvUnLike = new TextView(context); 
            tvUnLike.setLayoutParams(layoutTvParams); 
            tvUnLike.setPadding(10, 10, 10, 10); 
            tvUnLike.setBackgroundDrawable(getResources().getDrawable(R.drawable.btnlikeback)); 
            tvUnLike.setText("UNLIKE"); 
            tvUnLike.setGravity(Gravity.CENTER); 
            tvUnLike.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD)); 
            tvUnLike.setTextSize(25); 
            tvUnLike.setTextColor(ContextCompat.getColor(context, R.color.red)); 
            tvUnLike.setX(500); 
            tvUnLike.setY(150); 
            tvUnLike.setRotation(50); 
            tvUnLike.setAlpha(alphaValue); 
            relativeLayoutContainer.addView(tvUnLike);  

            //TextView tvName = (TextView) containerView.findViewById(R.id.tvName); 
          //  TextView tvTotLikes = (TextView) containerView.findViewById(R.id.tvTotalLikes);  

           // tvName.setText(userDataModelArrayList.get(i).getName()); 
           // tvTotLikes.setText(userDataModelArrayList.get(i).getTotalLikes());  

            // Touch listener on the image layout to swipe image right or left. 
            relativeLayoutContainer.setOnTouchListener(new View.OnTouchListener() {  

                @Override 

                public boolean onTouch(View v, MotionEvent event) {  

                    x_cord = (int) event.getRawX(); 
                    y_cord = (int) event.getRawY();  

                    View containerView;
                    containerView.setX(0); 
                    containerView.setY(0);  

                    switch (event.getAction()) { 
                        case MotionEvent.ACTION_DOWN:  

                            x = (int) event.getX(0); 
                            y = (int) event.getY(0);  

                            Log.v("On touch", x + " " + y); 
                            break; 
                        case MotionEvent.ACTION_MOVE:  

                            x_cord = (int) event.getRawX(); 
                            // smoother animation. 
                            y_cord = (int) event.getRawY();  

                            containerView.setX(x_cord - x); 
                            containerView.setY(y_cord - y);  

                            if (x_cord >= screenCenter) { 
                                containerView.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32))); 
                                if (x_cord > (screenCenter + (screenCenter / 2))) { 
                                    tvLike.setAlpha(1); 
                                    if (x_cord > (windowwidth - (screenCenter / 4))) { 
                                        Likes = 2; 
                                    } else { 
                                        Likes = 0; 
                                    } 
                                } else { 
                                    Likes = 0; 
                                    tvLike.setAlpha(0); 
                                } 
                                tvUnLike.setAlpha(0); 
                            } else { 
                                // rotate image while moving 
                                containerView.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32))); 
                                if (x_cord < (screenCenter / 2)) { 
                                    tvUnLike.setAlpha(1); 
                                    if (x_cord < screenCenter / 4) { 
                                        Likes = 1; 
                                    } else { 
                                        Likes = 0; 
                                    } 
                                } else { 
                                    Likes = 0; 
                                    tvUnLike.setAlpha(0); 
                                } 
                                tvLike.setAlpha(0); 
                            }  
                            break; 
                        case MotionEvent.ACTION_UP:  

                            x_cord = (int) event.getRawX(); 
                            y_cord = (int) event.getRawY();  

                            Log.e("X Point", "" + x_cord + " , Y " + y_cord); 
                            tvUnLike.setAlpha(0); 
                            tvLike.setAlpha(0);  

                            if (Likes == 0) { 
                                Toast.makeText(context, "NOTHING", Toast.LENGTH_SHORT).show(); 
                                Log.e("Event_Status :-> ", "Nothing"); 
                                containerView.setX(0); 
                                containerView.setY(0); 
                                containerView.setRotation(0); 
                            } else if (Likes == 1) { 
                                Toast.makeText(context, "UNLIKE", Toast.LENGTH_SHORT).show(); 
                                Log.e("Event_Status :-> ", "UNLIKE"); 
                                parentView.removeView(containerView); 
                            } else if (Likes == 2) { 
                                Toast.makeText(context, "LIKED", Toast.LENGTH_SHORT).show(); 
                                Log.e("Event_Status :-> ", "Liked"); 
                                parentView.removeView(containerView); 
                            } 
                            break; 
                        default: 
                            break; 
                    } 
                    return true; 
                } 
            });  

            parentView.addView(containerView);  
        }  
    }  
}*/
