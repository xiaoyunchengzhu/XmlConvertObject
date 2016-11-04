package com.xiaoyunchengzhu.xmlconvertobject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    @InjectView(R.id.showboy1)
    Button showshowboy1;
    @InjectView(R.id.int1)
    Button int1;
    @InjectView(R.id.long1)
    Button long1;
    @InjectView(R.id.float1)
    Button float1;
    @InjectView(R.id.double1)
    Button double1;
    @InjectView(R.id.char1)
    Button char1;
    @InjectView(R.id.boolean1)
    Button boolean1;
    @InjectView(R.id.boolean2)
    Button boolean2;
    @InjectView(R.id.map1)
    Button map1;
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
        showshowboy1.setOnClickListener(this);
        int1.setOnClickListener(this);

        long1.setOnClickListener(this);
        float1.setOnClickListener(this);
        double1.setOnClickListener(this);
        char1.setOnClickListener(this);
        boolean1.setOnClickListener(this);
        boolean2.setOnClickListener(this);
        map1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String content="";
        switch (v.getId())
        {
            case R.id.showlist1:
                List<DataUrlBean> list1=  configManger.convert("list1");
                content=gson.toJson(list1);
                break;
            case R.id.showlist2:
                List<String> list2=  configManger.convert("list2");
                content=gson.toJson(list2);
                break;
            case R.id.showlist3:
                List<Response> list3= configManger.convert("list3");
                content=gson.toJson(list3);
                break;
            case R.id.dataUrlBean1:
                DataUrlBean dataUrlBean1=  configManger.convert("dataUrlBean1");
                content=gson.toJson(dataUrlBean1);
                break;
            case R.id.person1:
                Person person1= configManger.convert("person1");
                content=gson.toJson(person1);
                break;
            case R.id.response1:
                Response response1=  configManger.convert("response1");
                content=gson.toJson(response1);
                break;
            case R.id.response2:
                Response response2= configManger.convert("response2");
                content=gson.toJson(response2);
                break;
            case R.id.showboy1:
                content+=  configManger.convert("boy1");
                break;
            case R.id.int1:
                content+=configManger.convert("int1");
                break;

            case R.id.long1:
                content+=configManger.convert("long1");
                break;
            case R.id.float1:
                content+=configManger.convert("float1");
                break;
            case R.id.double1:
                content+=configManger.convert("double1");
                break;
            case R.id.char1:
                content+=configManger.convert("char1");
                break;
            case R.id.boolean1:
                content+=configManger.convert("boolean1");
                break;
            case R.id.boolean2:
                content+=configManger.convert("boolean2");
                break;
            case R.id.map1:
                content=configManger.convert("map1").toString();
                break;



        }
        show.setText(content);
    }
}
