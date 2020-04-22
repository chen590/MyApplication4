package cn.edu.sdwu.android02.classroom.sn170507180130;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Ch10Activity1 extends AppCompatActivity {
private Integer count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.i(Ch10Activity1.class.toString(),"onCreate");
        setContentView(R.layout.layout_ch10_1);
        count=0;
        //接收数据
        Intent intent=getIntent();//获取界面跳转时使用的intent
        String text=intent.getStringExtra("text");
        TextView textView=(TextView)findViewById(R.id.ch10_1_tv);
        textView.setText(text);
    }

    public void finishClick(View view){
        finish();//关闭界面
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(Ch10Activity1.class.toString(),"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(Ch10Activity1.class.toString(),"onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i(Ch10Activity1.class.toString()," onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(Ch10Activity1.class.toString(),"onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i(Ch10Activity1.class.toString(),"onRestart");
    }
}
