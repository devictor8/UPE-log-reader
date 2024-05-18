//Grupo: Camila Jullyane, João Victor Salgado, Keisy Lizandra

package org.upe.logreader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList data = LogData.logReader();
        int option;

        System.out.println("1 - Recursos grandes respondidos\n2 - Não respondidos\n3 - % de requisições por SO\n4 - Média das requisições POST\n0 - Sair");
        option = sc.nextInt();

        switch (option) {
            case 0:
                System.out.println("case 0");
        }
    }
}