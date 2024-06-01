package org.upe.logreader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OpSystem {
    private static final String sistemasOperacionaisPath = ".Analise/sistemasOperacionais.txt";


    public void analysisSystemType() {
        ArrayList<LogData> data = LogData.logReader();
        HashMap<String, Integer> osData = new HashMap<>();
        
        for(LogData dataLine : data) {
            String os = dataLine.getOS();
            // System.out.println(os);
            if (os.contains("Windows") && dataLine.getDate().contains("/2021")){
                osData.put("Windows", osData.getOrDefault("Windows", 0) + 1);

            } else if (os.contains("Macintosh") && dataLine.getDate().contains("/2021")) {
                osData.put("Macintosh", osData.getOrDefault("Macintosh", 0) + 1);

            } else if(os.contains("Ubunto") && dataLine.getDate().contains("/2021")) {
                osData.put("Ubunto", osData.getOrDefault("Ubunto", 0) + 1);

            } else if(os.contains("Fedora") && dataLine.getDate().contains("/2021")) {
                osData.put("Fedora", osData.getOrDefault("Fedora", 0) + 1);

            } else if(os.contains("Android") && dataLine.getDate().contains("/2021")) {
                osData.put("Mobile", osData.getOrDefault("Mobile", 0) + 1);

            } else if (dataLine.getDate().contains("/2021")) {
                osData.put("Others", osData.getOrDefault("Others", 0) + 1);
                
            }
        }

        System.out.printf("Windows, %d\n", osData.getOrDefault("Windows", 0));
        System.out.printf("Macintosh, %d\n", osData.getOrDefault("Macintosh", 0));
        System.out.printf("Ubunto, %d\n", osData.getOrDefault("Ubunto", 0));
        System.out.printf("Fedora, %d\n", osData.getOrDefault("Fedora", 0));
        System.out.printf("Mobile, %d\n", osData.getOrDefault("Mobile", 0));
        System.out.printf("Outros, %d\n", osData.getOrDefault("Others", 0));
    }

    public void createFile(HashMap<String, Integer> data) {
        File file = new File(OpSystem.sistemasOperacionaisPath);
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(OpSystem.sistemasOperacionaisPath));
            
            for (Map.Entry<String, Integer> line : data.entrySet()) {
                writer.write(line.getKey() + "," + line.getValue());
                writer.newLine();
            }
            writer.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
