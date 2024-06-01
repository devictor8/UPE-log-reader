//Grupo: Camila Jullyane, João Victor Salgado, Keisy Lizandra

package org.upe.logreader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Request requestData = new Request();
        OpSystem osData = new OpSystem();
        boolean running = true;
        while (running) {
            int option;
            System.out.println("1 - Recursos grandes respondidos\n2 - Não respondidos\n3 - % de requisições por SO\n4 - Média das requisições POST\n0 - Sair");
            option = sc.nextInt();
            
            switch (option) {
                case 0:
                    running = false;
                    break;
                case 1:
                    requestData.dataAnalysisStatus200();
                    break;
                case 2:
                    requestData.dataAnalysisStatus400();
                    break;
                case 3:
                    osData.analysisSystemType();
                    break;
                case 4:
                    requestData.averagePostRequest();
                    break;
            }
        }
        sc.close();
    }
}