package org.apache.dubbo.rest.demo.routine;

import org.apache.dubbo.common.io.StreamUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rest.demo.pojo.Bean;

import javax.ws.rs.core.Form;
import java.io.IOException;
import java.io.InputStream;
import java.util.OptionalInt;

@DubboService
public class ErrorTestCaseImpl implements ErrorTestCase {

    @Override
    public String sayMatrixStringMap(String key, String name) {
        return name;
    }

    @Override
    public String sayHeader(String name, String test) {
        return name + ' ' + test;
    }

    @Override
    public String testConsumesJson(String name) {
        return "Hello " + name;
    }

    @Override
    public String testProducesJson(String name) {
        return "Hello " + name;
    }

    @Override
    public String testDefault(String name) {
        return "Hello " + name;
    }

    @Override
    public OptionalInt testBeanForm(Bean bean) {
        return bean.beanId;
    }

    @Override
    public String testForm(Form form) {
        return form.asMap().get("number").get(0);
    }

    @Override
    public String testInPut(InputStream inputStream) {
        try {
            return StreamUtils.toString(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
