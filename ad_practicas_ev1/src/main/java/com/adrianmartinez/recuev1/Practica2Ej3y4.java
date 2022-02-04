/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class Practica2Ej3y4 extends Practica2Ej1 {
   
    public static void ejercicio3() throws IOException {
        String opcion = "";
        do{
            System.out.println("\n\n");
            File f = new File("..\\ad_practicas_ev1\\src\\Practica2\\ejercicio1.txt");
            System.out.println("--Operaciones--");
            System.out.println("[1] Añadir Registro");
            System.out.println("[2] Recuperar Registro");
            System.out.println("[3] Modificar Registro");
            System.out.println("[4] Borrar Registro");
            System.out.println("[5] Atrás");
            System.out.print("Seleccione una operación: ");
            Scanner sc = new Scanner(System.in);
            opcion = sc.nextLine();
            switch(opcion){
                case "1":
                    Practica2Ej3y4 operador = new Practica2Ej3y4(f);
                    operador.anyadir();
                    break;
                case "2":
                    operador = new Practica2Ej3y4(f);
                    operador.recuperar();
                    break;
                case "3":
                    operador = new Practica2Ej3y4(f);
                    operador.modificar();
                    break;
                case "4":
                    operador = new Practica2Ej3y4(f);
                    operador.borrar();
                    
            }
        }
        while(Integer.parseInt(opcion)<=4);
    }
    
    public static void ejercicio4() throws IOException{
        String opcion = "";
        do{
            System.out.println("\n\n");
            String nomFichero = "..\\ad_practicas_ev1\\src\\Practica2\\ejercicio1.txt";
            File f = new File(nomFichero);
            System.out.println("--Operaciones--");
            System.out.println("[1] Añadir Registro");
            System.out.println("[2] Recuperar Registro");
            System.out.println("[3] Modificar Registro");
            System.out.println("[4] Borrar Registro");
            System.out.println("[5] Compactar fichero");
            System.out.println("[6] Atrás");
            System.out.print("Seleccione una operación: ");
            Scanner sc = new Scanner(System.in);
            opcion = sc.nextLine();
            switch(opcion){
                case "1":
                    Practica2Ej3y4 operador = new Practica2Ej3y4(f);
                    operador.anyadir();
                    break;
                case "2":
                    operador = new Practica2Ej3y4(f);
                    operador.recuperar();
                    break;
                case "3":
                    operador = new Practica2Ej3y4(f);
                    operador.modificar();
                    break;
                case "4":
                    operador = new Practica2Ej3y4(f);
                    operador.borrar();
                    break;
                case "5":
                    operador = new Practica2Ej3y4(f);
                    operador.compactar(nomFichero);
                    break;
                    
            }
        }
        while(Integer.parseInt(opcion)<=5);
    }
    
    // apartado 3 
    public void borrar() throws IOException{
        boolean registroEncontrado = false;
        String nombre = "";
        String dni;
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Introduzca un DNI para borrar: ");
        Scanner sc = new Scanner(System.in);
        dni = sc.nextLine();
        BufferedWriter bfw;
        bfw = new BufferedWriter(new FileWriter(fichero));
        for (String line : lines) {
            String[] split = line.split(";");
            if(dni.equals(split[0])){
                bfw.write("*");
            }
            bfw.write(line);
            bfw.newLine();
        }
        bfw.close(); 
        System.out.println("borrado con exito");
         
    }
    
    //apartado 4
    public void compactar(String nomFichero) throws FileNotFoundException, IOException{
       char marca = '*';
        //Crea el fichero temporal
        String fichero = nomFichero;
        File f = new File(fichero);
        BufferedReader br = new BufferedReader(new FileReader(f));
        File fTemp = File.createTempFile(nomFichero, "");
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(fTemp));
        String linea = br.readLine();
        while(linea != null){
            if(marca == linea.charAt(0)) {
                //System.out.println("entro " + linea);
                //bw.newLine();
            linea = br.readLine();
                boolean principioLinea = true,
                        espacios = false,
                        primerAlfab = false;
                for(int i = 0; i < linea.length(); i++){
                    char c = linea.charAt(i);
                    if(Character.isWhitespace(c)){
                        if(!espacios && !principioLinea){
                            bw.write(c);
                        }
                        espacios = true;
                    }
                    else if(Character.isAlphabetic(c)){
                        if(!primerAlfab){
                            bw.write(Character.toUpperCase(c));
                            primerAlfab = true;
                        }
                        else{
                            bw.write(c);
                        }
                        espacios = false;
                        principioLinea = false;
                    }
                }
                bw.newLine();
                linea = br.readLine();
            }
            else{
                
                bw.newLine();
                linea = br.readLine();
            }
        }
        br.close();
        bw.close();
        System.out.println("fTemp = " + fTemp.getAbsolutePath());
        Files.move(fTemp.toPath(),f.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
    
    //Constructor de la clase
    public Practica2Ej3y4(File archivo){
        super(archivo); // llamando al constructor de la clase padre
    }

}
    


