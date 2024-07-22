package org.apache.dubbo.rest.demo.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorTest {


    private static final String providerAddress = System.getProperty("dubbo.address", "localhost");
    @Test
    public void testInput(){
        RestClient defaultClient = RestClient.create();
        ResponseEntity<String> result = defaultClient.post()
                .uri("http://" + providerAddress + ":50052/param/input")
                .header("test","hello")
                .retrieve()
                .toEntity(String.class);
//        Assert.assertEquals("Hello", result.getBody());
        System.out.println(result.getBody());
    }

    @Test
    public void testMatrixStringMap(){
//        矩阵参数map返回list
        RestClient defaultClient = RestClient.create();
        ResponseEntity<Map<String,String>> result = defaultClient.get()
                .uri("http://" + providerAddress + ":50052/param/matrix/stringMap;m=map=name1=world;name2=hello")
                .header("Content-type", "application/json")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<Map<String,String>>() {
                });
        Assert.assertEquals("Hello world", result.getBody());
    }
     @Test
    public void testHeaderList(){
//        请求头中只能传递多个参数，在接收方只能剩余一个
        ResponseEntity<List<String>> response = RestClient.create().get()
                .uri("http://" + providerAddress + ":50052/param/header/list")
                .header( "Content-type","application/json")
//                .header("test","world")
                .header("name","Hello ","1")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<String>>() {});
        Assert.assertEquals("Hello world",response.getBody());
    }

    @Test
    public void testProducesJson(){
        ResponseEntity<String> response = RestClient.create().get()
                .uri("http://" + providerAddress + ":50052/mapping/producesJson?name={name}","world")
//                .header( "Content-type","text/plain")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("Hello world",response.getBody());
    }

        @Test
    public void testConsumeJson(){
        ResponseEntity<String> response = RestClient.create().get()
                .uri("http://" + providerAddress + ":50052/mapping/consumeJson?name={name}","world")
                .header( "Content-type","*/json")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("Hello world",response.getBody());
    }


    @Test
    public void testBeanForm(){
        ResponseEntity<String> response = RestClient.create().get()
                .uri("http://" + providerAddress + ":50052/complex/beanForm?beanId=1")
                .header("Content-type", "application/json")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals(Integer.valueOf(1), response.getBody());
    }


    @Test
    public void testFrom(){
        MultiValueMap<String, Long> map = new LinkedMultiValueMap<>();
        map.add("number",1L);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("value",1);
        ResponseEntity<String> response = RestClient.create().post()
                .uri("http://" + providerAddress + ":50052/complex/form")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(map)
                .retrieve()
                .toEntity(String.class);
//        Assert.assertEquals(Long.valueOf(1), response.getBody());
        System.out.println(response.getBody());
    }

        @Test
    public void testDefault(){
        ResponseEntity<String> response = RestClient.create().get()
                .uri("http://" + providerAddress + ":50052/complex/default")
                .header("Content-type", "application/json")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("Hello world", response.getBody());
    }
}
