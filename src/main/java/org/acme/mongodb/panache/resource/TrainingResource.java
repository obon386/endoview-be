package org.acme.mongodb.panache.resource;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import org.acme.mongodb.panache.entity.Training;
import org.bson.types.ObjectId;
import javax.ws.rs.core.Response;

import javax.ws.rs.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/entity/trainings")
@Consumes("application/json")
@Produces("application/json")
public class TrainingResource {

    @GET
    public List<Training> list() {
        return Training.listAll();
    }

    @GET
    @Path("/week")
    public List<Training> getForWeek(@QueryParam("year") int year,
                               @QueryParam("month1") int monthOfDayFirst,
                               @QueryParam("day1") int dayFirst,
                               @QueryParam("month2") int monthOfDayLast,
                               @QueryParam("day2") int dayLast) {
        List<Training> res = new ArrayList<>();


        // TODO: handle previous year
        LocalDateTime startDate = LocalDateTime.of(year, monthOfDayFirst, dayFirst, 0, 0, 1);
        LocalDateTime endDate = LocalDateTime.of(year, monthOfDayLast, dayLast, 23, 59, 59);

        String queryString = "{start_time:{$gte:?1,$lt:?2}}";
        PanacheQuery<PanacheMongoEntityBase> query = Training.find(queryString,
                Sort.ascending("start_time"), startDate, endDate);

        //  and start_time < ?2

        List<Training> list = query.list();
//        Training training = Training.findById(new ObjectId(id));
        return list;
    }

    @GET
    @Path("/{id}")
    public Training get(@PathParam("id") String id) {
        Training training = Training.findById(new ObjectId(id));
        return training;
    }

    @POST
    @Path("/updatePictures/{id}")
    public Response updatePictures(@PathParam("id") String id) {
        Training training = Training.findById(new ObjectId(id));

        Date startTime = training.startTime;

        return Response.status(500).build();
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, Training training) {

        training.update();
    }

}
