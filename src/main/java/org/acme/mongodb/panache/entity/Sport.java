package org.acme.mongodb.panache.entity;

public enum Sport {
    RUN,
    RIDE,
    INDOOR_RIDE,
    SWIM,
    UNKNOWN;


//    private final int code;

//    Sport(int code) {
//        this.code = code;
//    }

    public static Sport of(Integer sport) {
        if (sport == 0)
            return RUN;
        if (sport == 1)
            return RIDE;
        if (sport == 2)
            return RIDE;
        if (sport == 21)
            return INDOOR_RIDE;
        if (sport == 20)
            return SWIM;
        return UNKNOWN;
    }
}
