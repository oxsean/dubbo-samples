package org.apache.dubbo.rest.demo.routine;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rest.demo.pojo.Bean;

import javax.ws.rs.core.Form;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

@DubboService
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
    public String testDefault(String name) {
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

    @Override
    public String testInPut(InputStream inputStream) {
        String content = "";
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                os.write(buffer, 0, length);
            }

             content = os.toString(StandardCharsets.UTF_8);

            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}
