package com.xiaoyunchengzhu.xmlconvertobject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;
import com.xiaoyunchengzhu.XmlId;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.XmlResolve;
import com.xiaoyunchengzhu.xmlconvertobject.model.DataUrlBean;
import com.xiaoyunchengzhu.xmlconvertobject.model.Person;
import com.xiaoyunchengzhu.xmlconvertobject.model.Response;
import java.util.List;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @InjectView(R.id.show)
     TextView show;
    Gson gson;
    @XmlId("list1")
    List<DataUrlBean> list1;
    @XmlId("list2")
    List<String> list2;
    @XmlId("list3")
    List<Response> list3;
    @XmlId("dataUrlBean1")
    DataUrlBean dataUrlBean1;
    @XmlId("person1")
    Person person1;
    @XmlId("response1")
    Response response1;
    @XmlId("response2")
    Response response2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XmlResolve.find(this);
        ButterKnife.inject(this);
        gson=new Gson();
    }

    @OnClick({R.id.showlist1,R.id.showlist2,R.id.showlist3,R.id.dataUrlBean1, R.id.person1,R.id.response1,R.id.response2,R.id.showboy1,R.id.int1,R.id.long1,R.id.float1,R.id.double1,R.id.char1,R.id.boolean1
    ,R.id.boolean2,R.id.map1})
    public void onClick(View v) {
        String content="";
        switch (v.getId())
        {
            case R.id.showlist1:
                content=gson.toJson(list1);
                break;
            case R.id.showlist2:
                content=gson.toJson(list2);
                break;
            case R.id.showlist3:
                content=gson.toJson(list3);
                break;
            case R.id.dataUrlBean1:
                content=gson.toJson(dataUrlBean1);
                break;
            case R.id.person1:
                content=gson.toJson(person1);
                break;
            case R.id.response1:
                content=gson.toJson(response1);
                break;
            case R.id.response2:
                content=gson.toJson(response2);
                break;
            case R.id.showboy1:
                content+= XmlResolve.find("boy1");
                break;
            case R.id.int1:
                content+= XmlResolve.find("int1");
                break;
            case R.id.long1:
                content+= XmlResolve.find("long1");
                break;
            case R.id.float1:
                content+= XmlResolve.find("float1");
                break;
            case R.id.double1:
                content+= XmlResolve.find("double1");
                break;
            case R.id.char1:
                content+= XmlResolve.find("char1");
                break;
            case R.id.boolean1:
                content+= XmlResolve.find("boolean1");
                break;
            case R.id.boolean2:
                content+= XmlResolve.find("boolean2");
                break;
            case R.id.map1:
                content= XmlResolve.find("map1").toString();
                break;



        }
        show.setText(content);
    }
}
