//Grupo: Camila Jullyane, João Victor Salgado, Keisy Lizandra

package org.upe.logreader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Request requestData = new Request();
        boolean running = true;
        boolean isFileRead = false;
        while (running) {
            int option;
            System.out.println("1 - Recursos grandes respondidos\n2 - Não respondidos\n3 - % de requisições por SO\n4 - Média das requisições POST\n0 - Sair");
            option = sc.nextInt();

            if (!isFileRead && option != 0) {
                requestData.DataAnalysis(LogData.logReader());
                isFileRead = true;
            }

            switch (option) {
                case 0:
                    running = false;
                    break;
                case 1:
                    requestData.createStatus200File();
                    break;
                case 2:
                    requestData.createStatus400File();
                    break;
                case 3:
                    break;
                case 4:
                    requestData.averagePostRequest();
                    break;
            }
        }
        sc.close();
    }
}