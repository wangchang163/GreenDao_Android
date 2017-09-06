package com.wangchang.greendao_android.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wangchang.greendao_android.MyApplication;
import com.wangchang.greendao_android.R;
import com.wangchang.greendao_android.adapter.DaoAdapter;
import com.wangchang.greendao_android.db.AppDbHelper;
import com.wangchang.greendao_android.db.User;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private AppDbHelper dbHelper;
    private List<User> userList=new ArrayList<>();
    private RecyclerView recyclerView;
    private DaoAdapter commonAdapter;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper= MyApplication.appDbHelper;
//        dbHelper.clear();
        commonAdapter=new DaoAdapter();
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //增
                userList= dbHelper.loadAll();
                index=userList.size()-1;
                User user = new User();
                user.setAge(20);
                user.setDesc("三好学生");
                user.setName("张三");
                user.setSex("男");
                user.setId(index++);
                dbHelper.saveUser(user);
                //查
                userList= dbHelper.loadAll();
                commonAdapter.addData(userList);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //删
                if(userList.size()==0){
                    return;
                }
                userList = dbHelper.loadAll();
                dbHelper.deleteUser(userList.get(userList.size() - 1));
                index--;
                //查
                userList= dbHelper.loadAll();
                commonAdapter.addData(userList);

            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index==0){
                    return;
                }
                //改
                User user=dbHelper.query(--index);
                user.setName("旋风鸣人");
                dbHelper.update(user);
                commonAdapter.notifyDataSetChanged();

            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //查
                userList= dbHelper.loadAll();
                commonAdapter.addData(userList);
            }
        });

        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(commonAdapter);
    }

}
