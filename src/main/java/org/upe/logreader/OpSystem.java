package org.upe.logreader;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OpSystem {
    private static final String sistemasOperacionaisPath = "./Analise/sistemasOperacionais.txt";

    public void analysisSystemType() {
        ArrayList<LogData> data = LogData.logReader();
        HashMap<String, Double> osData2021 = new HashMap<>();
        int totalRequest = 0;
        
        for(LogData dataLine : data) {
            String os = dataLine.getOS();
            if (dataLine.getDate().contains("/2021")) {
                if (os.equals("Linux") || os.equals("Others")) {
                    osData2021.put("Others", osData2021.getOrDefault("Others", 0.0) +1 );
                } else {
                    osData2021.put(os, osData2021.getOrDefault(os, 0.0) + 1);
                }    
                totalRequest++;
            }
        }
        createFile(osData2021, totalRequest);
    }

    public void createFile(HashMap<String, Double> data, int totalRequest) {
        try {
            File file = new File(OpSystem.sistemasOperacionaisPath);
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(OpSystem.sistemasOperacionaisPath));
            
            String[] systems = {"Windows", "Macintosh", "Ubuntu", "Fedora", "Mobile", "Others"};
            for (String system : systems) {
                double percentage = (data.getOrDefault(system, 0.0) / totalRequest) * 100;
                String line = String.format("%s %.4f\n", system.equals("Others") ? "Linux, outros" : system, percentage);
                writer.write(line);
            }
            
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
