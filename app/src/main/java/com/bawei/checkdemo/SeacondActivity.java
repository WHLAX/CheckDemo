package com.bawei.checkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class SeacondActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mylist;
    private Button checkAll;
    private Button checkAllNo;
    private ArrayList<MyBean> list;
    private Button checkNo;
    private int checkNum; // 记录选中的条目数量
    private MyAdapter adapter;
    private Button yes;
    private List<MyBean> myBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seacond);
        initData();
        initView();
    }

    private void initData() {

        list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add(new MyBean("biaoti" + i, "neirong" + i, false));
        }
    }

    private void initView() {
        mylist = (RecyclerView) findViewById(R.id.recycler);
        checkAll = (Button) findViewById(R.id.bt1);
        checkAllNo = (Button) findViewById(R.id.bt2);
        yes = (Button) findViewById(R.id.bt3);
        checkAll.setOnClickListener(this);
        checkAllNo.setOnClickListener(this);
        checkNo = (Button) findViewById(R.id.bt2);
        checkNo.setOnClickListener(this);
        yes.setOnClickListener(this);
        adapter = new MyAdapter(list, this);
        mylist.setLayoutManager(new LinearLayoutManager(this));
        mylist.setItemAnimator(new DefaultItemAnimator());
        mylist.setAdapter(adapter);
        myBeanList = new ArrayList<>();
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {


                if (MyAdapter.getIsSelected().get(position)) {
                    MyAdapter.getIsSelected().put(position, false);
                } else {
                    MyAdapter.getIsSelected().put(position, true);
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                checkAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 遍历list的长度，将MyAdapter中的map值全部设为true
                        for (int i = 0; i < list.size(); i++) {
                            MyAdapter.getIsSelected().put(i, true);
                        }
                        // 数量设为list的长度
                        checkNum = list.size();
                        // 刷新listview和TextView的显示
                        dataChanged();
                    }
                });
                break;
            case R.id.bt2:

                checkAllNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 遍历list的长度，将已选的设为未选，未选的设为已选
                        for (int i = 0; i < list.size(); i++) {
                            if (MyAdapter.getIsSelected().get(i)) {
                                MyAdapter.getIsSelected().put(i, false);
                                checkNum--;
                            } else {
                                MyAdapter.getIsSelected().put(i, true);
                                checkNum++;
                            }
                        }
                        // 刷新listview和TextView的显示
                        dataChanged();
                    }
                });

                break;
            case R.id.bt3:
                checkNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 遍历list的长度，将已选的按钮设为未选
                        for (int i = 0; i < list.size(); i++) {
                            if (MyAdapter.getIsSelected().get(i)) {
                                MyAdapter.getIsSelected().put(i, false);
                                checkNum--;// 数量减1
                            }
                        }
                        // 刷新listview和TextView的显示
                        dataChanged();
                    }
                });
                break;
            case R.id.bt4:
                for (int i = 0; i < list.size(); i++) {
                    if (MyAdapter.getIsSelected().get(i)) {
                        myBeanList.add(list.get(i));
                    } else {
                        myBeanList.remove(list.get(i));
                    }
                }
                Intent intent = new Intent(SeacondActivity.this, Show.class);
                intent.putExtra("data", myBeanList.toString());
                startActivity(intent);
                myBeanList.clear();
                break;
        }
    }


    // 刷新listview和TextView的显示
    private void dataChanged() {
        // 通知listView刷新
        adapter.notifyDataSetChanged();
    }


    class MyBean {

        private String title;
        private String content;
        private boolean flag;

        public MyBean(String title, String content, boolean flag) {
            this.title = title;
            this.content = content;
            this.flag = flag;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        @Override
        public String toString() {
            return "MyBean{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", flag=" + flag +
                    '}';
        }
    }


    ;
}