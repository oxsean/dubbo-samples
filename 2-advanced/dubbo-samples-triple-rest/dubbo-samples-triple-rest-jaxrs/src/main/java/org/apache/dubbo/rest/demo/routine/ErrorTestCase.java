package org.apache.dubbo.rest.demo.routine;

import org.apache.dubbo.rest.demo.pojo.Bean;

import javax.annotation.Nonnull;
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
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

public interface ErrorTestCase {

    @GET
    @Path("/matrix/stringMap;{m}")
    Map<String,String> sayMatrixStringMap(@MatrixParam("map")Map<String,String> value);

    @GET
    @Path("/header/list")
    List<String> sayHeader(@HeaderParam("name") List<String> values);

    @GET
    @Path("/consumeJson")
    @Consumes("*/json")
    @Produces("text/plain")
    String testConsumesJson(@QueryParam("name") String name);

    @GET
    @Path("/producesJson")
    @Produces("*/json")
    String testProducesJson(@QueryParam("name") String name);

    @GET
    @Path("/default")
    String testDefault(@Nonnull @QueryParam("name") @DefaultValue("world")  String name);


    @GET
    @Path("/beanForm")
    OptionalInt testBeanForm(@BeanParam Bean bean);

    @POST
    @Path("/form")
    String testForm(@Context Form form);

}
