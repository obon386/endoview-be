conn = new Mongo();
db = conn.getDB("endo");
db.Trainings.find({}).forEach( function(item){
        if (typeof(item.start_time) == "string"){
//            print(item.start_time);
			const aa = item.start_time.split(" ");
			var tt = aa[0] + " " + aa[1];
//            print(tt);
			var dd = new Date(tt);
            print(dd.toISOString("de-DE"));
            item.start_time = new Date(dd);
            db.Trainings.save(item);
        }
    }
);