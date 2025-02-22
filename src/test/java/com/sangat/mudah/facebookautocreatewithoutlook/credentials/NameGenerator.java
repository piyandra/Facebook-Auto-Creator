package com.sangat.mudah.facebookautocreatewithoutlook.credentials;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NameGenerator {

    public String getFirstName() {
        return firstName;
    }

    public NameGenerator setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public NameGenerator setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    private String firstName;
    private String lastName;

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    private String day;
    private String month;
    private String year;

    public String nameGenerator() {
        ArrayList<String> nameList = new ArrayList<>();

        String filePath = "src/test/java/com/sangat/mudah/facebookautocreatewithoutlook/credentials/nama.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                nameList.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        return nameList.get(random.nextInt(nameList.size()));
    }

    public void setDay() {
        Random random = new Random();
        random.nextInt(28);
        this.day = String.valueOf(random);
    }
    public void setMonth() {
        Random random = new Random();
        List<String> monthList = List.of("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
        random.nextInt(monthList.size());
        this.month = monthList.get(random.nextInt(monthList.size()));
    }
    public void setYear() {
        Random random = new Random();
        ArrayList<String> yearList = new ArrayList<>();
        for (int i = 1950; i < 2000; i++) {
            yearList.add(String.valueOf(i));
        }
        random.nextInt(yearList.size());
        this.year = yearList.get(random.nextInt(yearList.size()));
    }
}