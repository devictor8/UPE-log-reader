package org.upe.logreader;

import java.util.ArrayList;

public class Request {
    private static final String recursosGrandesPath = "./Análise/RecursosGrandes.txt";
    private static final String naoRespondidosNovembroPath = "./Análise/RecursosGrandes.txt";
    public ArrayList<String> status200 = new ArrayList<>();
    private ArrayList<String> status400 = new ArrayList<>();
    private ArrayList<String> postRequest;

    public void DataAnalysis(ArrayList<LogData> logData) {

        for (LogData lineData : logData) {
            if (validateDataSuccessfulRequest(lineData)) {
                status200.add(String.format("%s %s %s", lineData.getStatus(), lineData.getObjectSize(), lineData.getIP()));
            };
            if (validateDataUnsuccessfulRequest(lineData)) {
                status400.add(String.format("%s %s %s", lineData.getStatus(), lineData.getURL(), lineData.getDate()));
            };

        }
    }
    private boolean validateDataSuccessfulRequest(LogData dateLine) {
        return true;
    }

    private boolean validateDataUnsuccessfulRequest(LogData dateLine) {
        return true;
    }

    public void createStatus200File() {

    }

    public void createStatus400File() {

    }
}
