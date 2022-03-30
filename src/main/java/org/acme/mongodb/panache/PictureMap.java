package org.acme.mongodb.panache;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import org.acme.mongodb.panache.entity.Training;

import javax.enterprise.context.ApplicationScoped;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class PictureMap {


    public void init() {
        String dirLocation = "C:\\dev\\endomondo-archive\\Workouts";

        try {
            List<File> files = Files.list(Paths.get(dirLocation))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .map(Path::toFile)
//                    .limit(1000)
                    .collect(Collectors.toList());

            for (File file : files) {
                parse(file);
            }
        } catch (Exception e) {
            System.out.println("Error while reading the directory: " + e);
            e.printStackTrace();
        }
    }

    private void parse(File f) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        List workout = mapper.readValue(f, List.class);

        String startTime = readFieldValue(workout, "start_time", String.class);

        List pictures = readFieldValue(workout, "pictures", List.class);

        if (pictures != null && pictures.size() > 0) {
            List<String> picUrlList = extractPicUrls(pictures);

            System.out.println("startTime: " + startTime);
            System.out.println("picUrlList: " + picUrlList);

            if (picUrlList != null) {
                String queryString = "start_time like ?1";
                String startTimeRegex = "/^" + startTime.substring(0, startTime.indexOf('.')) + "/";
                Training training = Training.find(queryString, startTimeRegex).firstResult();
                training.picUrlList = picUrlList;
                training.update();
                System.out.println("******************** updated ");
//            long updated = Training.update("picUrlList", picUrlList.toArray() ).where(queryString, startTimeRegex);
            }

        }

//        System.out.println("pictures: " + pictures);

    }

    private List<String> extractPicUrls(List pictures) {
        if (pictures == null) {
            return null;
        }

        ArrayList<String> res = new ArrayList<>();

        for (Object o1: pictures) {
            List l1 = (List) o1;
            List picInfo = readFieldValue(l1, "picture", List.class);

            List l3 = (List) picInfo.get(0);
            String url = readFieldValue(l3, "url", String.class);
            System.out.println(url);
            res.add(url);

        }

//        List l1 = (List) pictures.get(0);
//
//
//        for (Object o: l1) {
//            List l2 = (List) o;
//            List picInfo = readFieldValue(l2, "picture", List.class);
//            List l3 = (List) picInfo.get(0);
//            String url = readFieldValue(l3, "url", String.class);
//            System.out.println(url);
//            res.add(url);
//        }

        return res;
    }

    private <T> T readFieldValue(List mapList, String key, Class<T> clazz) {
        for (Object o: mapList) {
            Map<String, ?> entry = (Map<String, ?>) o;
            if (entry.containsKey(key)) {
                return clazz.cast(entry.get(key));
            }
        }

        return null;
    }


}
