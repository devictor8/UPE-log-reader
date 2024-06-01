package org.upe.logreader;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogData {
    private static final String filePath = "../UPE-log-reader/access.log";
    private String IP;
    private String date;
    private String type;
    private String URL;
    private int status;
    private int objectSize;
    private String OS;

    public static ArrayList<LogData> logReader() {
        ArrayList<LogData> arrayData = new ArrayList<>();
        Pattern pattern = Pattern.compile("^(\\S+) - - \\[(.+)\\] \"(.+?) (.+?) HTTP/1\\.1\" (\\S+) (\\S+) \"(.+?)\" \"(.+?)\"");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            LogData readLine;
            String line = reader.readLine();

            while (line != null) {
                readLine = new LogData();

                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {

                    readLine.IP = matcher.group(1);
                    readLine.date = matcher.group(2);
                    readLine.type = matcher.group(3);
                    readLine.URL = matcher.group(4);
                    readLine.status = Integer.parseInt(matcher.group(5));
                    readLine.objectSize = matcher.group(6).equals("-") ? 0 : Integer.parseInt(matcher.group(6));
                    readLine.OS = matcher.group(8);
                    arrayData.add(readLine);
                }
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayData;
    }

    public String getIP() {
        return IP;
    }

    public String getDate() {
        SimpleDateFormat datePatternOld = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        SimpleDateFormat newDate = new SimpleDateFormat("MMM/yyyy", Locale.ENGLISH);

        try {
            Date formatDate = datePatternOld.parse(this.date);
            return newDate.format(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Formato de data inválido";
        }
    }

    public String getOS() {
        Pattern osPattern = Pattern.compile("\\(.*?(Macintosh|Windows|Linux;/*|X11;Ubuntu|Fedora|Android).*?\\)");
        Matcher osMatcher = osPattern.matcher(this.OS);
        
        if(osMatcher.find()) {
            String os = osMatcher.group(1);
            if (os.contains("Linux")) {
                return os.contains("Android") ? "Mobile" : "Others";
            }
            if (os.contains("X11")) {
                System.out.println(os);
                return os.contains("Ubuntu") ? "Ubuntu" : "Others";
            }
            return os.contains("Android") ? "Mobile" : osMatcher.group(1);
        } else {
            return "Others";
        }
    }

    public int getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getURL() {
        return URL;
    }

    public int getObjectSize() {
        return objectSize;
    }
}
