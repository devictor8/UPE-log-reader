//Grupo: Camila Jullyane, João Victor Salgado, Keisy Lizandra

package org.upe.logreader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Request requestData = new Request();
        requestData.DataAnalysis(LogData.logReader());
        int option;

        for(String os : requestData.status200) {
            System.out.println(os);
        }

        System.out.println("1 - Recursos grandes respondidos\n2 - Não respondidos\n3 - % de requisições por SO\n4 - Média das requisições POST\n0 - Sair");
        option = sc.nextInt();

        boolean isFileRead = false;
        switch (option) {
            case 0:
                System.out.println("case 0");
                break;
            case 1:
                if(isFileRead) {

                } 
        }

        sc.close();
    }
}