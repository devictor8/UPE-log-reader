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

        for (LogData lineData : data) {
            if (lineData.getStatus() >= 200 && lineData.getStatus() <= 299 && lineData.getObjectSize() >= 2000) {
                status200.add(String.format("%s %s %s\n", lineData.getStatus(), lineData.getObjectSize(), lineData.getIP()));
            };
        }
        createFile(Request.recursosGrandesPath, status200);        
    }

    public void dataAnalysisStatus400() {
        ArrayList<LogData> data = LogData.logReader();
        ArrayList<String> status400 = new ArrayList<>();

        for(LogData lineData : data) {
            if (lineData.getDate().equals("Nov/2021") && lineData.getStatus() >= 400 && lineData.getStatus() <= 499) {
                status400.add(String.format("%s %s %s\n", lineData.getStatus(), lineData.getURL(), lineData.getDate()));
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
        float[] postRequest = new float[]{0, 0};

        for (LogData lineData : data) {
            if (lineData.getType().equals("POST") && lineData.getDate().contains("2021")) {
                System.out.printf("%s %s\n", lineData.getType(), lineData.getDate());
                postRequest[0]++;
                postRequest[1] += lineData.getObjectSize();
            };
        }

        System.out.printf("%.2f\n", postRequest[1]/postRequest[0]);
    }
}
