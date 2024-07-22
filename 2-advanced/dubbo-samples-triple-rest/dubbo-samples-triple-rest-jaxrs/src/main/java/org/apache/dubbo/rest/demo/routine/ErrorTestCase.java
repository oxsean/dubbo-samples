package org.apache.dubbo.rest.demo.routine;

import org.apache.dubbo.rest.demo.pojo.Bean;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Form;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

@Path("/")
public interface ErrorTestCase {

    @GET
    @Path("/param/matrix/stringMap;m={map}")
    Map<String,String> sayMatrixStringMap(@MatrixParam("map")Map<String,String> value);

    @GET
    @Path("/param/header/list")
    List<String> sayHeader(@HeaderParam("name") List<String> values);

    @GET
    @Path("/mapping/consumeJson")
    @Consumes("*/json")
    @Produces("text/plain")
    String testConsumesJson(@QueryParam("name") String name);

    @GET
    @Path("/mapping/producesJson")
    @Produces("*/json")
    String testProducesJson(@QueryParam("name") String name);

    @GET
    @Path("/complex/default")
    String testDefault( @QueryParam("name") @DefaultValue("world")  String name);


    @GET
    @Path("/complex/beanForm")
    OptionalInt testBeanForm(@BeanParam Bean bean);

    @POST
    @Path("/complex/form")
    String testForm(@Context Form form);

    @POST
    @Path("/param/input")
    String testInPut(InputStream inputStream);

}
