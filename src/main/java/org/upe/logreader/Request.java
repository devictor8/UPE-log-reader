package org.upe.logreader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Request {
    private static final String recursosGrandesPath = "./Analise/RecursosGrandes.txt";
    private static final String naoRespondidosNovembroPath = "./Analise/naoRespondidosNovembro.txt";
    private ArrayList<String> status200;
    private ArrayList<String> status400;
    private float[] postRequest;

    public void DataAnalysis(ArrayList<LogData> logData) {
        status200 = new ArrayList<>();
        status400 = new ArrayList<>();
        postRequest = new float[]{0, 0};

        for (LogData lineData : logData) {
            if (lineData.getStatus() >= 200 && lineData.getStatus() <= 299 && lineData.getObjectSize() >= 2000) {
                status200.add(String.format("%s %s %s\n", lineData.getStatus(), lineData.getObjectSize(), lineData.getIP()));
            };
            if (lineData.getDate().equals("Nov/2021") && lineData.getStatus() >= 400 && lineData.getStatus() <= 499) {
                status400.add(String.format("%s %s %s\n", lineData.getStatus(), lineData.getURL(), lineData.getDate()));
            };
            if (lineData.getType().equals("POST") && lineData.getDate().contains("2021")) {
                postRequest[0]++;
                postRequest[1] += lineData.getObjectSize();
            };
        }
    }

    public void createStatus200File() {
        try {
            File file = new File(Request.recursosGrandesPath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(Request.recursosGrandesPath));
            for (String line : status200) {
                writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Arquivo criado");
    }

    public void createStatus400File() {
        try {
            File file = new File(Request.naoRespondidosNovembroPath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(Request.naoRespondidosNovembroPath));
            for (String line : status400) {
                writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Arquivo criado");
    }

    public void averagePostRequest() {
        System.out.printf("%.2f\n", postRequest[1]/postRequest[0]);
    }
}

