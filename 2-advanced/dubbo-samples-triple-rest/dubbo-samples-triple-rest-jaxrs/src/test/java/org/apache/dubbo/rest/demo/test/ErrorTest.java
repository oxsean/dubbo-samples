package org.apache.dubbo.rest.demo.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.HashMap;

public class ErrorTest {

    private static final String providerAddress = System.getProperty("dubbo.address", "localhost");

    @Test
    public void testInput() {
        RestClient defaultClient = RestClient.create();
        ResponseEntity<String> result = defaultClient.post()
                .uri(uri("/param/input"))
                .body("hello")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("hello", result.getBody());
        System.out.println(result.getBody());
    }

    @Test
    public void testMatrixStringMap() {
//        矩阵参数map返回list
        RestClient defaultClient = RestClient.create();
        ResponseEntity<String> result = defaultClient.get()
                .uri(uri("/param/matrix/stringMap;name=hello"))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("\"hello\"", result.getBody());
    }

    @Test
    public void testHeaderList() {
//        请求头中只能传递多个参数，在接收方只能剩余一个
        ResponseEntity<String> response = RestClient.create().get()
                .uri(uri("/param/header/list"))
                .accept(MediaType.APPLICATION_JSON)
                .header("test", "world")
                .header("name", "Hello ")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("\"Hello world\"", response.getBody());
    }

    @Test
    public void testProducesJson() {
        ResponseEntity<String> response = RestClient.create().get()
                .uri(uri("/mapping/producesJson?name={name}"),"world")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("\"Hello world\"", response.getBody());
    }

    @Test
    public void testConsumeJson() {
        ResponseEntity<String> response = RestClient.create().post()
                .uri(uri("/mapping/consumeJson"))
                .contentType(MediaType.APPLICATION_JSON)
                .body("\"world\"")
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("Hello world", response.getBody());
    }

    @Test
    public void testBeanForm() {
        ResponseEntity<Integer> response = RestClient.create().get()
                .uri(uri("/complex/beanForm?id=1"))
                .retrieve()
                .toEntity(Integer.class);
        Assert.assertEquals(Integer.valueOf(1), response.getBody());
    }

    @Test
    public void testFrom() {
        MultiValueMap<String, Long> map = new LinkedMultiValueMap<>();
        map.add("number", 1L);
        ResponseEntity<Long> response = RestClient.create().post()
                .uri(uri("/complex/form"))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(map)
                .retrieve()
                .toEntity(Long.class);
        Assert.assertEquals(Long.valueOf(1), response.getBody());
        System.out.println(response.getBody());
    }

    @Test
    public void testDefault() {
        ResponseEntity<String> response = RestClient.create().get()
                .uri(uri("/complex/default"))
                .accept(MediaType.TEXT_PLAIN)
                .retrieve()
                .toEntity(String.class);
        Assert.assertEquals("Hello world", response.getBody());
    }

    private static String uri(String path){
        return "http://" + providerAddress + ":50052" + path;
    }
}
