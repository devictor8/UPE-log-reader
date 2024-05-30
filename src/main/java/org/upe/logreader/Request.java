package org.upe.logreader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Request {
    private static final String recursosGrandesPath = "../Analise/RecursosGrandes.txt";
    private static final String naoRespondidosNovembroPath = "Analise\\RecursosGrandes.txt";
    public ArrayList<String> status200 = new ArrayList<>();
    public ArrayList<String> status400 = new ArrayList<>();
    private ArrayList<String> postRequest;

    public void DataAnalysis(ArrayList<LogData> logData) {

        for (LogData lineData : logData) {
            if (lineData.getStatus() >= 200 && lineData.getStatus() <= 299 && lineData.getObjectSize() >= 2000) {
                status200.add(String.format("%s %s %s", lineData.getStatus(), lineData.getObjectSize(), lineData.getIP()));
            };
            if (lineData.getDate().equals("Nov/2021") && lineData.getStatus() >= 400 && lineData.getStatus() <= 499) {
                status400.add(String.format("%s %s %s", lineData.getStatus(), lineData.getURL(), lineData.getDate()));
            };
        }
    }

    public void createStatus200File() {
        try {
            File file = new File(Request.recursosGrandesPath);
            if (!file.getParentFile().exists()) {
                if (file.getParentFile().mkdirs()) {
                System.out.println("Diretório criado com sucesso");
                } else {
                    System.out.println("Erro ao criar o diretório");
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(Request.recursosGrandesPath));
            for (String line : status200) {
                writer.write(line);
            }
            writer.close(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("arquivo criado");
    }

    public void createStatus400File() {

    }
}
