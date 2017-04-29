package com.example;

import java.util.Random;

public class TellingJoke {

    private String[] myJokes;
    private Random rand;

    public TellingJoke() {
        myJokes = new String[8];
        myJokes[0] = "Teacher asked him again to name an animal that starts with alphabet ‘T’?";
        myJokes[1] = "Teacher asked the students to tell the most common word used by students in a classroom";
        myJokes[2] = "Teacher asked the students to tell the importance of the year 1809";
        myJokes[3] = "Teacher: Which one is closer, Sun or Africa? ";
        myJokes[4] = "Teacher announced that “students, we will have only half a day of school in this morning.";
        myJokes[5] = "Teacher: Why are you late?";
        myJokes[6] = "Math Teacher: Sara, what do you get when you subtract 897 from 1824 and add 176 and divide the answer by 3?";
        myJokes[7] = "Teacher said the students to convert the sentence \"I killed a person\" into future tense";
        rand = new Random();
    }

    public String getRandomJoke() {

        return myJokes[rand.nextInt(myJokes.length)];
    }
}
