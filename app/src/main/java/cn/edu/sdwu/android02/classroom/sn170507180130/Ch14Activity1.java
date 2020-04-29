package cn.edu.sdwu.android02.classroom.sn170507180130;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Ch14Activity1 extends AppCompatActivity {
private MyOpenHelper myOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ch14_1);


    myOpenHelper=new MyOpenHelper(this);
    }
    public void insert(View view){
    //可以写方法打开数据库（如果数据库不存在，自动创建数据库）
    SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();
    try{
        //将插入的数据库放置在ContentValues中
        ContentValues contentValues=new ContentValues();
        contentValues.put("stuname","Tom");
        contentValues.put("stutel","136666666666");

        sqLiteDatabase.insert("student",null,contentValues);//所有操作结束后，调用setTransactionSuccessful方法，才会将数据保存到数据库
        sqLiteDatabase.setTransactionSuccessful();

    }catch(Exception e){
        Log.i(Ch14Activity1.class.toString(),e.toString());
    }finally{
        sqLiteDatabase.endTransaction();//结束事务
        //使用完毕将数据库关闭
        sqLiteDatabase.close();
    }
    }

    public void query(View view){
        //可以写方法打开数据库（如果数据库不存在，自动创建数据库）
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getReadableDatabase();//只读方式打开
        try{
            Cursor cursor=sqLiteDatabase.rawQuery("select * from student where stuname=?",new String[]{"tom"});
           while(cursor.moveToNext()){
               int id=cursor.getInt(cursor.getColumnIndex("id"));
               String stuname=cursor.getString(cursor.getColumnIndex("stuname"));
               String stutel=cursor.getString(cursor.getColumnIndex("stutel"));
               Log.i(Ch14Activity1.class.toString(),"id:"+id+",stuname:"+stuname+",stutel:"+stutel);
           }
           cursor.close();
        }catch(Exception e){
            Log.i(Ch14Activity1.class.toString(),e.toString());
        }finally{
            //使用完毕将数据库关闭
            sqLiteDatabase.close();
        }
    }
    public void delete(View view){
        //可以写方法打开数据库（如果数据库不存在，自动创建数据库）
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();
        try{
            //将插入的数据库放置在ContentValues中
            //事务处理
            sqLiteDatabase.beginTransaction();

            sqLiteDatabase.delete("student","id=?",new String[]{"1"});
            sqLiteDatabase.setTransactionSuccessful();//所有操作结束后，调用setTransactionSuccessful方法，才会将数据保存到数据库

        }catch(Exception e){
            Log.i(Ch14Activity1.class.toString(),e.toString());
        }finally{
            sqLiteDatabase.endTransaction();//结束事务
            //使用完毕将数据库关闭
            sqLiteDatabase.close();
        }
    }
    public void modify(View view){
        //可以写方法打开数据库（如果数据库不存在，自动创建数据库）
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();
        try{
            //将插入的数据库放置在ContentValues中
            //事务处理
            sqLiteDatabase.beginTransaction();
            ContentValues contentValues=new ContentValues();
            contentValues.put("stuname","Tom");

            contentValues.put("stutel","13999999999");

            sqLiteDatabase.update("student",contentValues,"id=?",new String[]{"2"});
            sqLiteDatabase.setTransactionSuccessful();//所有操作结束后，调用setTransactionSuccessful方法，才会将数据保存到数据库

        }catch(Exception e){
            Log.i(Ch14Activity1.class.toString(),e.toString());
        }finally{
            sqLiteDatabase.endTransaction();//结束事务
            //使用完毕将数据库关闭
            sqLiteDatabase.close();
        }
    }

}
