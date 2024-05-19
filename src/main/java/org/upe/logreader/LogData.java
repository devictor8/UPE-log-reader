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

    public static ArrayList<LogData> logReader() {
        ArrayList<LogData> arrayData = new ArrayList<LogData>();
        Pattern pattern = Pattern.compile("^(\\S+) - - \\[(.+)\\] \"(.+?) (.+?) HTTP/1\\.1\" (\\S+) (\\S+) \"(.+?)\" \"(.+?)\"");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            LogData readLine;
            String line = reader.readLine();

            while (line != null) {
                line = reader.readLine();
                readLine = new LogData();
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {

                    readLine.IP = matcher.group(1);
                    readLine.date = matcher.group(2);
                    readLine.type = matcher.group(3);
                    readLine.URL = matcher.group(4);
                    readLine.status = matcher.group(5);
                    readLine.objectSize = matcher.group(6);
                    readLine.OS = matcher.group(8);

//                    System.out.println(readLine.IP);
//                    System.out.println(readLine.date);
//                    System.out.println(readLine.type);
//                    System.out.println(readLine.URL);
//                    System.out.println(readLine.status);
//                    System.out.println(readLine.objectSize);
//                    System.out.println(readLine.OS);

                    arrayData.add(readLine);


                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayData;
    }
}
