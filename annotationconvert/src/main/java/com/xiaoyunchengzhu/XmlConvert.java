package com.xiaoyunchengzhu;


import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

@AutoService(Processor.class)
public class XmlConvert extends AbstractProcessor{


    private Filer filer;
    private Elements elementUtils;
    public static String suffix= "_convert";
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer=processingEnv.getFiler();
        elementUtils = processingEnv.getElementUtils();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {

        Set<String> result= new HashSet<>();
        result.add(XmlId.class.getCanonicalName());
        return result;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(XmlId.class);
        Map<Element,Set<Xmlbean>> listId=new HashMap<>();
        for (Element element:elementsAnnotatedWith){
            Name simpleName = element.getSimpleName();
            Xmlbean bindBean=null;
            XmlId xmlFind = element.getAnnotation(XmlId.class);
            if (xmlFind!=null) {
                bindBean=new Xmlbean();

                String value = xmlFind.value();
                bindBean.setId(value);
            }
            if (bindBean!=null) {
                bindBean.setFildName(simpleName.toString());
                bindBean.setFieldtype(element.asType());
                Set<Xmlbean> bindBeanSet = listId.get(element.getEnclosingElement());
                if (bindBeanSet == null) {
                    bindBeanSet = new HashSet<>();
                    listId.put(element.getEnclosingElement(), bindBeanSet);
                }
                bindBeanSet.add(bindBean);
            }
        }
        for (Element classname:listId.keySet()){
            Set<Xmlbean> bindBeanSet=listId.get(classname);
            testtojava(elementUtils.getPackageOf(classname).toString(),((TypeElement)classname).toString(),bindBeanSet);
        }


        return false;
    }
    TypeSpec.Builder testtojava(String packagename, String classname, Set<Xmlbean> bindBean){
        TypeSpec.Builder builder = TypeSpec.classBuilder(classname.substring(classname.lastIndexOf(".") + 1, classname.length()) + suffix)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(ClassName.get(XmlFind.class), ClassName.bestGuess(classname)));
        builder.addMethod(methodfind(classname,bindBean));
        builder.addField(FieldSpec.builder(TypeVariableName.get("com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigManger"),"configManger",Modifier.PRIVATE).build());
        JavaFile javaFile = JavaFile.builder(packagename, builder.build())
                .build();
        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder;

    }

    /**
     * method
     * @param classname
     * @param bulletBeen
     * @return
     */
    public MethodSpec methodfind(String classname, Set<Xmlbean> bulletBeen){
        MethodSpec.Builder builder = MethodSpec.methodBuilder("find")
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addAnnotation(Override.class)
                .addParameter(TypeVariableName.get(classname), "goal");
        builder.addStatement("configManger=new com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigManger();");
        for (Xmlbean bindBean1:bulletBeen){
                builder.addStatement("goal."+bindBean1.getFildName()+"=configManger.convert(\""+bindBean1.getId()+"\");");
        }
        return builder.build();
    }

}
