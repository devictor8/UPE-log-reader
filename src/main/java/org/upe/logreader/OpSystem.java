package org.upe.logreader;

import java.util.ArrayList;

public class OpSystem {

    public void analysisSystemType() {
        ArrayList<LogData> data = LogData.logReader();


        for(LogData dataLine : data) {
            if (dataLine.getOS().contains("Windows")) {


            }
        }
    }
  

    public void createFile() {

    }
}
