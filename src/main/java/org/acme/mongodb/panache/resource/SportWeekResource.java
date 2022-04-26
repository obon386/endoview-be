package org.acme.mongodb.panache.resource;

import org.acme.mongodb.panache.PictureMap;
import org.acme.mongodb.panache.entity.Person;
import org.acme.mongodb.panache.entity.YearWeek;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

@Path("/weeks")
@Consumes("application/json")
@Produces("application/json")
public class SportWeekResource {
    @Inject
    PictureMap pictureMap;

    @GET
    @Path("/test")
    public Person test() {
        Person person = new Person();
        person.setName("TestP");
        return person;
    }

    @GET
    @Path("/testw")
    public Response testW() {
        pictureMap.init();
        return Response.status(201).build();
    }

    @GET
    @Path("/{year}")
    public List<YearWeek> listWeeks(@PathParam("year") int year) {
        Calendar calendar = Calendar.getInstance();
        List<YearWeek> yearWeeks = new ArrayList<>();
        Calendar calToday = Calendar.getInstance();
        int latestWeek = (calToday.get(Calendar.YEAR) == year) ?
                calToday.get(Calendar.WEEK_OF_YEAR) :
                52;


        for (int i = latestWeek; i > 0; i--) {
            calendar.setWeekDate(year, i, Calendar.MONDAY);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            Month monthOfDayFirst = Month.of(calendar.get(Calendar.MONTH) + 1);
            int dayFirst = calendar.get(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            Month monthOfDayLast = Month.of(calendar.get(Calendar.MONTH) + 1);
            int dayLast = calendar.get(Calendar.DAY_OF_MONTH);

            yearWeeks.add(new YearWeek(year, monthOfDayFirst.getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                    monthOfDayLast.getDisplayName(TextStyle.SHORT, Locale.ENGLISH), dayFirst, dayLast));

            System.out.printf("%s %s - %s %s%n", monthOfDayFirst.getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                    dayFirst, monthOfDayLast.getDisplayName(TextStyle.SHORT, Locale.ENGLISH), dayLast);
        }

        return yearWeeks;
    }


}
