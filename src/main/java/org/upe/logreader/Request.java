package org.upe.logreader;

import java.util.ArrayList;

public class Request {
    private static final String recursosGrandesPath = "./Análise/RecursosGrandes.txt";
    private static final String naoRespondidosNovembroPath = "./Análise/RecursosGrandes.txt";
    private ArrayList<LogData> dataArray;
    public ArrayList<String> status200;
    private ArrayList<String> status400;
    private ArrayList<String> postRequest;

    public void DataAnalysis(ArrayList<LogData> logData) {
        this.dataArray = logData;

        for (LogData lineData : this.dataArray) {
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
