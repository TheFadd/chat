package com.example.chat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GeneralUtils {

    private static Calendar getDate(Message message) {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(message.getDateOfCreation() * 1000);
        return date;
    }

    public static String getDay(Message message) {
        return getDate(message).get(Calendar.DAY_OF_MONTH) + "";
    }

    public static String getMonth(Message message) {
        Date date = getDate(message).getTime();
        return new SimpleDateFormat("MMMM").format(date);
    }

    public static String getYear(Message message) {
        return getDate(message).get(Calendar.YEAR) + "";
    }

    public static String getHours(Message message) {
        int hour = getDate(message).get(Calendar.HOUR_OF_DAY);
        String newHour;
        if (hour < 10) {
            newHour = "0" + hour;
        } else {
            newHour = hour + "";
        }
        return newHour;
    }

    public static String getMinutes(Message message) {
        int minutes = getDate(message).get(Calendar.MINUTE);
        String newMinutes;
        if (minutes < 10) {
            newMinutes = "0" + minutes;
        } else {
            newMinutes = minutes + "";
        }
        return newMinutes;
    }

    public static List<Message> getFakeData(){

        Message m1 = new Message(1,"Hi Man!",1555319130);
        Message m2 = new Message(2,"Hello!",1555319240);
        Message m3 = new Message(1,"How are u?",1555319350);
        Message m4 = new Message(1,"Did you see new episode of GOT ?",1555319355);
        Message m5 = new Message(2,"NO I DIDN'T!",1555319600);
        Message m6 = new Message(3);
        Message m7 = new Message(2,"YET",1555319615);
        Message m8 = new Message(1,"Will you watch it?",1555319805);
        Message m9 = new Message(2,"YEEES!!!",1555320130);
        Message m10 = new Message(1,"I have some spoilers for you!!!)))",1555320330);
        Message m11 = new Message(1,"Are you here?",1555320350);
        Message m12 = new Message (2,"NO!!!!!!!!",1555320800);
        Message m13 = new Message(3);

        List<Message> list = new ArrayList<>();
        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);
        list.add(m5);
        list.add(m6);
        list.add(m7);
        list.add(m8);
        list.add(m9);
        list.add(m10);
        list.add(m11);
        list.add(m12);
        list.add(m13);

        return  list;
    }
}
