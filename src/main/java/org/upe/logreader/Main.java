//Grupo: Camila Jullyane, João Victor Salgado, Keisy Lizandra

package org.upe.logreader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String acess = "../UPE-log-reader/access.log";

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        int option;

        try (Scanner scanner = new Scanner(new File(acess))) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                System.out.println(linha);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("1 - Recursos grandes respondidos\n2 - Não respondidos\n3 - % de requisições por SO\n4 - Média das requisições POST\n0 - Sair");
        option = sc.nextInt();

        switch (option) {
            case 0:
                System.out.println("case 0");
        }
    }
}