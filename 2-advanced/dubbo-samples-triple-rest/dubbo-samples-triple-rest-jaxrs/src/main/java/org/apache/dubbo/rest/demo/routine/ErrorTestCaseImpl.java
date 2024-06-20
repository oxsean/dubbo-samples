package org.apache.dubbo.rest.demo.routine;

import org.apache.dubbo.rest.demo.pojo.Bean;

import javax.annotation.Nonnull;
import javax.ws.rs.core.Form;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

public class ErrorTestCaseImpl implements ErrorTestCase {

    @Override
    public Map<String, String> sayMatrixStringMap(Map<String, String> value) {
        return value;
    }


    @Override
    public List<String> sayHeader(List<String> values) {
        return values;
    }

    @Override
    public String testConsumesJson(String name) {
        return "Hello "+name;
    }

    @Override
    public String testProducesJson(String name) {
        return "Hello "+name;
    }

    @Override
    public String testDefault(@Nonnull String name) {
        return "Hello "+name;
    }

    @Override
    public OptionalInt testBeanForm(Bean bean) {
        return bean.beanId;
    }

    @Override
    public String testForm(Form form) {
        System.out.println(form.asMap());
        return form.asMap().get("number").get(0);
    }


}
