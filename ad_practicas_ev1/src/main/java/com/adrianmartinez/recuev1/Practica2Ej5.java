
package com.adrianmartinez.recuev1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Practica2Ej5 extends Practica2Ej1 {
   
    public static void ejercicio5() throws IOException {
        String opcion = "";
        do{
            System.out.println("\n\n");
            File f = new File("..\\ad_practicas_ev1\\src\\Practica2\\ejercicio1.txt");
            System.out.println("[1] Añadir Registro");
            System.out.println("[5] Atrás");
            Scanner sc = new Scanner(System.in);
            opcion = sc.nextLine();
            switch(opcion){
                case "1":
                    Practica2Ej5 operador = new Practica2Ej5(f);
                    operador.longitud();
                    break;   
            }
        }
        while(Integer.parseInt(opcion)<=1);
    }
    
    public static void longitud(){
    
    
    
    }
    
    
    
        //Constructor de la clase
    public Practica2Ej5(File archivo) {
        super(archivo); // llamando al constructor de la clase padre
    }
}

    
    
    
  
    


