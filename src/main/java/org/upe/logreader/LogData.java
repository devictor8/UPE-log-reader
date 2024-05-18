package org.upe.logreader;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogData {
    private static final String filePath = "../UPE-log-reader/access.log";
    private String IP;
    private String date;
    private String type;
    private String URL;
    private String status;
    private String objectSize;
    private String OS;

    public static ArrayList logReader() {
        ArrayList arrayData = new ArrayList<LogData>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            LogData readLine;
            do {
                readLine = new LogData();
                String line = reader.readLine();
                String[] arrayLine = line.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)(?![^\\[]*\\])");
                readLine.IP = arrayLine[0];
                readLine.type = arrayLine[5];
                readLine.URL = arrayLine[6];


//                for (String name : arrayLine) {
//                    System.out.println(name);
//                }

                System.out.println(readLine.type);
                System.out.println(readLine.URL);

            } while (readLine != null);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayData;
    }
}
