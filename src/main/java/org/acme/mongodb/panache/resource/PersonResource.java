package org.acme.mongodb.panache.resource;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.panache.common.Parameters;
import org.acme.mongodb.panache.entity.Person;
import org.bson.types.ObjectId;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Path("/entity/persons")
@Consumes("application/json")
@Produces("application/json")
public class PersonResource {
    @GET
    public List<Person> list() {
        return Person.listAll();
    }

    @GET
    @Path("/{id}")
    public Person get(@PathParam("id") String id) {
        return Person.findById(new ObjectId(id));
    }

    @POST
    public Response create(Person person) {
        person.persist();
        return Response.status(201).build();
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, Person person) {
//        List<PanacheMongoEntityBase> list = Person.find("id", new ObjectId(id)).list();
//        long upd = Person.update("another_name", person.getName()).where("_id", new ObjectId(id));

//        List<Map<String, String>> mapList = Arrays.asList(Map.of("k1", "a1"), Map.of("k1", "a2"));
//        long upd = Person.update("test_list=?1", mapList).where("_id", new ObjectId(id));
//        System.out.println("Updated: " + upd);

          person.update();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        Person person = Person.findById(new ObjectId(id));
        person.delete();
    }

    @GET
    @Path("/search/{name}")
    public Person search(@PathParam("name") String name) {
        return Person.findByName(name);
    }

    @DELETE
    public void deleteAll(){
        Person.deleteAll();
    }
}
