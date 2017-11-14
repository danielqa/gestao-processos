package br.com.softplan.sajadv.processos.resource;

import br.com.softplan.sajadv.processos.app.Functions;

import javax.annotation.ManagedBean;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@ManagedBean
@Path("enum")
public class EnumResource {

    @GET
    @Path("{className}")
    public Object getValues(@PathParam("className") String className) {
        return Functions.executeMethodByClassNameAndMethodName(className, "values");
    }
}
