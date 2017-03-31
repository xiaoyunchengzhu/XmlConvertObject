# XmlConvertObject
一个xml配置文件转换对象工具
     通常我们在做客户端的时候，会把常用的配置以静态的方式存到一个对象中。比如url，客户端访问网络肯定会有一连串的url。管理这些url,常用的方法是在一个类中静态的方式作为成员变量。
这样，在程序启动的时候，会把url直接都加入到内存，获取url速度非常快，但是这样的弊端在以后程序功能不断变强，接口不断增多。这样的方式就显得不好了。除了这些不固定的配置
同时还有一些固定的配置，比如数据库的初始化配置，还有一些本地参数的配置。这些都需要管理。有时并不需要把所有的配置都放到内存中。
     其实那些配置还有一些要设置的参数，都可以用对象来整合起来。用对象的方式，更方便，更能清楚的表示所要展示或者配置的东西。比如要请求所需要的参数都需要用对象表示，这里说的不是请求业务数据，而是系统参数，
比如请求方式，请求超时，请求url,缓存超时，等。
        这个项目要解决的就是把这些参数设置都放到xml中，然后xml数据转换成你自定义的对象。同时想要从服务器更改数据，只需要更新最新的xml中的配置数据就可以了，不必更新整个app.这个项目，把xml配置放到了assets资源包下，
     如果更新了，要把xml文件放到本地存储里面，就可以从服务器时时更新这些配置。目前这块我没有实现。只是实现了数据配置的转换。
   
   更新内容：增加了注解方式来通过注解处理器绑定xml文件中的id 到 对象。对对象进行初始化，实例化。annotation processor 来预处理注解，Javapoet来生成类 在生成的类中对被注解的对象进行初始化。
   
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
	   //···
  }
  
  
  通过XmlResolve.find(this);这个方法，那么可以在之后愉快的使用被注解的对象了。
