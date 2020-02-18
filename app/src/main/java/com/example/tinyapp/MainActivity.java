package com.example.tinyapp;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.Gravity.START;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

//        SharedPreferences.Editor editor=getSharedPreferences("table_data",0).edit();
//        for(int i=0;i<7;i++){
//            for(int j=0;j<6;j++){
//                String key=i+""+j;
//                editor.putString(key,"软件项目开发管理(480430)\n余华云\n单1-15,东13-B-408c");
//            }
//        }
//        editor.apply();

    }

    private void init(){
        final DrawerLayout drawerLayout=findViewById(R.id.slide_menu);
        ImageView btn=findViewById(R.id.open_slide_menu);

        load_table();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    //加载课表
    private void load_table(){
        //课表主体
        LinearLayout linearLayout=findViewById(R.id.table_main);
        //查看课表按钮
        final Button btn_course_show=findViewById(R.id.big_show);
        btn_course_show.setVisibility(View.INVISIBLE);
        btn_course_show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button btn_course_show_temp = findViewById(R.id.big_show);
                btn_course_show_temp.setVisibility(View.INVISIBLE);
            }
        });

        for(int i=0;i<8;i++) {
            LinearLayout layout = new LinearLayout(this);

            layout.setOrientation(LinearLayout.VERTICAL);
            final LinearLayout.LayoutParams Params =  new LinearLayout.LayoutParams(30, ViewGroup.LayoutParams.WRAP_CONTENT);
            if(i!=0){
                Params.weight=1;
                Params.leftMargin=8;
            }
            else Params.weight=(float)0.5;

            if(i==7) Params.rightMargin=10;
            layout.setLayoutParams(Params);
            //View view_sub=inflater3.inflate(R.id.day0, null);

            TextView tv = new TextView(this);
            if(i==0)tv.setText("时间");
            else if(i==1)tv.setText("周一");
            else if(i==2)tv.setText("周二");
            else if(i==3)tv.setText("周三");
            else if(i==4)tv.setText("周四");
            else if(i==5)tv.setText("周五");
            else if(i==6)tv.setText("周六");
            else if(i==7)tv.setText("周日");
            //tv.setBackgroundColor(Color.BLUE);
            tv.setGravity(Gravity.CENTER);
            layout.addView(tv);


            SharedPreferences s=getSharedPreferences("table_data",0);

            for(int j=0;j<6;j++){
                //int ii=i-1;
                String str=(i-1)+""+j;
                String course=s.getString(str,"noCourse");
                if(i==0){
                    TextView tv1 = new TextView(this);
                    tv1.setHeight(420);
                    //tv1.setBackgroundColor(Color.GRAY);
                    tv1.setGravity(Gravity.CENTER);
                    if(j==0)tv1.setText("8:00\n\t-\n9:35");
                    else if(j==1) tv1.setText("10:05\n-\n11:40");
                    else if(j==2) tv1.setText("14:00\n-\n15:35");
                    else if(j==3) tv1.setText("16:05\n-\n17:40");
                    else if(j==4) tv1.setText("19:00\n-\n20:35");
                    else tv1.setText("21:05\n-\n22:40");
                    tv1.setTextColor(Color.BLUE);
                    tv1.setTextSize(9);
                    layout.addView(tv1);
                    continue;
                }
                else {
                    Button btn = new Button(this);
                    //btn.setText("8:00\n-\n9:35");
                    btn.setText(course);
                    btn.setHeight(400);
                    btn.setTextSize(10);
                    btn.setId(R.id.btn0);                //为按钮设置一个标记，来确认是按下了哪一个按钮
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Button b=findViewById(v.getId());
                            //LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(500,1000);
                            //b.setTextSize(35);

                            //Button big_show=findViewById(R.id.big_show);
                            String str=String.valueOf(b.getText());
                            btn_course_show.setText(str);
                            btn_course_show.setVisibility(View.VISIBLE);
                            btn_course_show.bringToFront();
//                            LinearLayout l=v.findViewById(R.id.po);l.bringToFront();

                        }
                    });

                    if (i % 2 == 0) {
                        if (j % 2 == 0) btn.setBackgroundResource(R.drawable.button_style);
                        else btn.setBackgroundResource(R.drawable.button_style2);
                    } else {
                        if (j % 2 != 0) btn.setBackgroundResource(R.drawable.button_style3);
                        else btn.setBackgroundResource(R.drawable.button_style4);
                    }
                    LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    Params1.bottomMargin = 20;
                    btn.setLayoutParams(Params1);
                    layout.addView(btn);
                }
            }
            linearLayout.addView(layout);
//            LinearLayout ll=findViewById(R.id.lll);
//            ll.bringToFront();//将布局或控件置于上层
        }
    }
}
