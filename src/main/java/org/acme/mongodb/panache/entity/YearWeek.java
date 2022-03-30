package org.acme.mongodb.panache.entity;

public class YearWeek {
    int year;
    String monthOfDayFirst;
    String monthOfDayLast;
    int dayFirst;
    int dayLast;

    public YearWeek() {
    }

    public YearWeek(int year, String monthOfDayFirst, String monthOfDayLast, int dayFirst, int dayLast) {
        this.year = year;
        this.monthOfDayFirst = monthOfDayFirst;
        this.monthOfDayLast = monthOfDayLast;
        this.dayFirst = dayFirst;
        this.dayLast = dayLast;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonthOfDayFirst() {
        return monthOfDayFirst;
    }

    public void setMonthOfDayFirst(String monthOfDayFirst) {
        this.monthOfDayFirst = monthOfDayFirst;
    }

    public String getMonthOfDayLast() {
        return monthOfDayLast;
    }

    public void setMonthOfDayLast(String monthOfDayLast) {
        this.monthOfDayLast = monthOfDayLast;
    }

    public int getDayFirst() {
        return dayFirst;
    }

    public void setDayFirst(int dayFirst) {
        this.dayFirst = dayFirst;
    }

    public int getDayLast() {
        return dayLast;
    }

    public void setDayLast(int dayLast) {
        this.dayLast = dayLast;
    }
}
