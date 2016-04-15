package com.xiaoyunchengzhu.xmlconvertobject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigManger;
import com.xiaoyunchengzhu.xmlconvertobject.model.DataUrlBean;
import com.xiaoyunchengzhu.xmlconvertobject.model.Person;
import com.xiaoyunchengzhu.xmlconvertobject.model.Response;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @InjectView(R.id.show)
     TextView show;
    @InjectView(R.id.showlist1)
     Button showList1;
    @InjectView(R.id.showlist2)
    Button showList2;
    @InjectView(R.id.showlist3)
    Button showList3;
    @InjectView(R.id.dataUrlBean1)
    Button showdataUrlBean1;
    @InjectView(R.id.person1)
    Button showperson1;
    @InjectView(R.id.response1)
    Button showresponse1;
    @InjectView(R.id.response2)
    Button showresponse2;
    @InjectView(R.id.showlihua)
    Button showshowlihua;
    @InjectView(R.id.showboy1)
    Button showshowboy1;
    @InjectView(R.id.showerror)
    Button showshowerror;
    Gson gson;
    ConfigManger configManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        gson=new Gson();
        configManger=new ConfigManger();
        inite();


    }

    private void inite()
    {
        showList1.setOnClickListener(this);
        showList2.setOnClickListener(this);
        showList3.setOnClickListener(this);
        showdataUrlBean1.setOnClickListener(this);
        showperson1.setOnClickListener(this);
        showresponse1.setOnClickListener(this);
        showresponse2.setOnClickListener(this);
        showshowlihua.setOnClickListener(this);
        showshowboy1.setOnClickListener(this);
        showshowerror.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String content=null;
        switch (v.getId())
        {
            case R.id.showlist1:
                List<DataUrlBean> list1= (List<DataUrlBean>) configManger.convertList("list1");
                content=gson.toJson(list1);
                break;
            case R.id.showlist2:
                List<String> list2= (List<String>) configManger.convertList("list2");
                content=gson.toJson(list2);
                break;
            case R.id.showlist3:
                List<Response> list3= (List<Response>) configManger.convertList("list3");
                content=gson.toJson(list3);
                break;
            case R.id.dataUrlBean1:
                DataUrlBean dataUrlBean1= (DataUrlBean) configManger.convertObject("dataUrlBean1");
                content=gson.toJson(dataUrlBean1);
                break;
            case R.id.person1:
                Person person1= (Person) configManger.convertObject("person1");
                content=gson.toJson(person1);
                break;
            case R.id.response1:
                Response response1= (Response) configManger.convertObject("response1");
                content=gson.toJson(response1);
                break;
            case R.id.response2:
                Response response2= (Response) configManger.convertObject("response2");
                content=gson.toJson(response2);
                break;
            case R.id.showlihua:
                content= (String) configManger.convertString("lihua");
                break;
            case R.id.showboy1:
                content= (String) configManger.convertString("boy1");
                break;
            case R.id.showerror:
                content= (String) configManger.convertString("error");
                break;

        }
        show.setText(content);
    }
}
