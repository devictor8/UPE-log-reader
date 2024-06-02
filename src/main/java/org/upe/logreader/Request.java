package org.upe.logreader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Request {
    private static final String recursosGrandesPath = "./Analise/RecursosGrandes.txt";
    private static final String naoRespondidosNovembroPath = "./Analise/naoRespondidosNovembro.txt";

    public void dataAnalysisStatus200() {
        ArrayList<LogData> data = LogData.logReader();
        ArrayList<String> status200 = new ArrayList<>();

        for (LogData dataLine : data) {
            if (dataLine.getStatus() >= 200 && dataLine.getStatus() <= 299 && dataLine.getObjectSize() >= 2000) {
                String line = String.format("%s %s %s\n", dataLine.getStatus(), dataLine.getObjectSize(), dataLine.getIP());
                status200.add(line);
            };
        }
        createFile(Request.recursosGrandesPath, status200);        
    }

    public void dataAnalysisStatus400() {
        ArrayList<LogData> data = LogData.logReader();
        ArrayList<String> status400 = new ArrayList<>();

        for(LogData dataLine : data) {
            if (dataLine.getDate().equals("Nov/2021") && dataLine.getStatus() >= 400 && dataLine.getStatus() <= 499) {
                String line = String.format("%s %s %s\n", dataLine.getStatus(), dataLine.getURL(), dataLine.getDate());
                status400.add(line);
            };
        }
        createFile(Request.naoRespondidosNovembroPath, status400);
    }

    public void createFile(String path, ArrayList<String> data) {
        try {
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for (String line : data) {
                writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Arquivo criado");
    }

    public void averagePostRequest() {
        ArrayList<LogData> data = LogData.logReader();
        int total = 0;
        int totalObjectSize = 0;
        
        for (LogData dataLine : data) {
            if (dataLine.getType().equals("POST") && dataLine.getDate().contains("/2021") && dataLine.getStatus() >= 200 && dataLine.getStatus() <= 299 ) {
                total++;
                totalObjectSize += dataLine.getObjectSize();
            };
        }
        
        double average = total > 0 ? (double) totalObjectSize / total : 0; 

        System.out.printf("A média de requisições POST em 2021: %.4f\n", average);
    }
}
