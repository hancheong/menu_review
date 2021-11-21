package com.example.menu_review;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    int col = Color.BLACK, shape = 1, wid = 8 ,style = 1;

    class YourView extends View {
        Paint pt;
        float x1,y1,x2,y2;
        YourView(Context c){
            super(c);
            pt = new Paint();
        }

        @Override
        protected void onDraw(Canvas cv) {
            super.onDraw(cv);

            pt.setColor(col);
            pt.setStrokeWidth(wid);
            if(style == 1){
                pt.setStyle(Paint.Style.STROKE);
            }else if(style ==2){
                pt.setStyle(Paint.Style.FILL);
            }

            if(shape == 1 ){
                cv.drawLine(x1,y1,x2,y2,pt);
            }else if(shape == 2) {
                cv.drawRect(x1,y1,x2,y2,pt);
            }else if(shape == 3){
                cv.drawCircle(20,50,50,pt);
            }else if(shape == 4){
                cv.drawLine(x1,y1,x2,y2,pt);
                cv.drawLine(x2,y2,2*(x1-x2/2), y2, pt);
                cv.drawLine(x1,y1,2*(x1-x2/2), y2, pt);
            }


        }

        @Override
        public boolean onTouchEvent(MotionEvent ev) {
            switch (ev.getAction()){
                case MotionEvent.ACTION_DOWN:
                    x1 = ev.getX();
                    y1 = ev.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    x2 = ev.getX();
                    y2 = ev.getY();
                    //draw
                    invalidate();
                    break;

                case MotionEvent.ACTION_MOVE:
                    x2 = ev.getX();
                    y2 = ev.getY();
                    invalidate();
                    break;
            }
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        YourView aa;
        aa = new YourView(this);
        setContentView(aa);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu color;
        SubMenu wd;
        SubMenu shape;
        shape = menu.addSubMenu("Shape");
        wd = menu.addSubMenu("width");
        color = menu.addSubMenu("color");
        color.add(0,101,0,"RED");
        color.add(0,102,0,"BLUE");
        color.add(0,103,0,"GREEN");
        color.add(0,105,0,"RANDOM");

        wd.add(0,201,0,"wid1");
        wd.add(0,202,0,"wid3");
        wd.add(0,203,0,"wid5");

        shape.add(0,1,0,"line");
        shape.add(0,2,0,"rectangle");
        shape.add(0,3,0,"circle");
        shape.add(0,4,0,"triangle");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 101 :
                col = Color.RED;
                break;
            case 102 :
                col = Color.BLUE;
                break;
            case 103:
                col = Color.GREEN;
                break;
            case 105:
                // 0,255,255 =sky
                //255, 255, 0 = yellow
                // 255, 0 ,255 = margenta
                col = Color.rgb(255,0,0);
                break;

            case 201:
                wid = 1;
                break;
            case 202:
                wid = 3;
                break;
            case 203:
                wid = 5;
                break;

            case 1:
                shape =1;
                break;
            case 2:
                shape =2;
                break;
            case 3:
                shape =3;
                break;
            case 4:
                shape =4;
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}