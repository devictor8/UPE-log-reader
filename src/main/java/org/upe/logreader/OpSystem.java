package org.upe.logreader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
        System.out.printf("Windows, %f\n", osData2021.getOrDefault("Windows", 0.0));
        System.out.printf("Macintosh, %f\n", osData2021.getOrDefault("Macintosh", 0.0));
        System.out.printf("Ubuntu, %f\n", osData2021.getOrDefault("Ubuntu", 0.0));
        System.out.printf("Fedora, %f\n", osData2021.getOrDefault("Fedora", 0.0));
        System.out.printf("Mobile, %f\n", osData2021.getOrDefault("Mobile", 0.0));
        System.out.printf("Outros, %f\n", osData2021.getOrDefault("Others", 0.0));
    }

    public void createFile(HashMap<String, Double> data, int totalRequest) {
        try {
            File file = new File(OpSystem.sistemasOperacionaisPath);
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(OpSystem.sistemasOperacionaisPath));
            writer.write(String.format("Windows %.4f\n", (data.getOrDefault("Windows", 0.0)/totalRequest) * 100));
            writer.write(String.format("Macintosh %.4f\n", (data.getOrDefault("Macintosh", 0.0)/totalRequest) * 100));
            writer.write(String.format("Ubuntu %.4f\n", (data.getOrDefault("Ubuntu", 0.0)/totalRequest) * 100));
            writer.write(String.format("Fedora %.4f\n", (data.getOrDefault("Fedora", 0.0)/totalRequest) * 100));
            writer.write(String.format("Mobile %.4f\n", (data.getOrDefault("Mobile", 0.0)/totalRequest) * 100));
            writer.write(String.format("Linux, outros %.4f\n", (data.getOrDefault("Others", 0.0)/totalRequest) * 100));
            
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Arquivo criado");
    }
}
