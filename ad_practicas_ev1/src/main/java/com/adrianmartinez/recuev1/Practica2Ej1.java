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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Practica2Ej1 {
    
    Scanner scanner = new Scanner(System.in);
    File fichero = new File("..\\ad_practicas_ev1\\src\\Practica2\\ejercicio1.txt");
    
    public static void ejercicio1() throws IOException{
        String opcion = "";
        do{
            System.out.println("\n\n");
            File f = new File("..\\ad_practicas_ev1\\src\\Practica2\\ejercicio1.txt");
            System.out.println("--Operaciones--");
            System.out.println("[1] Añadir Registros");
            System.out.println("[2] Recuperar Registros");
            System.out.println("[3] Modificar Registros");
            System.out.println("[5] Atrás");
            System.out.println("Seleccione una operacion:");
            Scanner sc = new Scanner(System.in);
            opcion = sc.nextLine();
            switch(opcion){
                case "1":
                    Practica2Ej1 operador = new Practica2Ej1(f);
                    operador.anyadir();
                    break;
                case "2":
                    operador = new Practica2Ej1(f);
                    operador.recuperar();
                    break;
                case "3":
                    operador = new Practica2Ej1(f);
                    operador.modificar();
                    break;
            }
        }
        while(Integer.parseInt(opcion)<=3);
    }
    
    public void anyadir() throws FileNotFoundException, IOException{
        boolean dniRepetido = false;
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("entre");
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Pedimos los datos a añadir
        System.out.println("Introduzca un nombre:");
        Scanner sc = new Scanner(System.in);
        String nombre = sc.nextLine();
        System.out.println("Introduzca un DNI:");
        String dni = sc.nextLine();
        
        System.out.println(nombre+ " "+ dni);
        //Comprobar si hay DNI repetido
        Map<String, String> dniNameMap = new HashMap<>();
        for (String line : lines) {
            String[] split = line.split(";");
            dniNameMap.put(split[0], split[1]);
            for(int i = 0; i < split.length; i++){
                if(dni.equals(split[0])){
                    System.out.println("Error, DNI ya existente");
                    dniRepetido = true;
                    break;
                }  
            }
        }
        if(!dniRepetido){
            BufferedWriter bfw= new BufferedWriter(new FileWriter(fichero,true));
            bfw.write(dni + ";" + nombre);
            bfw.newLine();

            bfw.close();
        }
    }
        
    public void recuperar(){
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
        System.out.println("Introduzca un DNI para realizar la busqueda:");
        Scanner sc = new Scanner(System.in);
        dni = sc.nextLine();
        Map<String, String> dniNameMap = new HashMap<>();
        for (String line : lines) {
            String[] split = line.split(";");
            dniNameMap.put(split[0], split[1]);
            for(int i = 0; i < split.length; i+=2){
                if(dni.equals(split[0])){
                    nombre = split[1];
                    dni = split[0];
                    registroEncontrado = true;
                    break;
                } 
            }
        }
        if(registroEncontrado){
            System.out.println("Registro encontrado.");
            System.out.println("Nombre: " + nombre);
            System.out.println("DNI: " + dni);
        }
        else{
            System.out.println("Registro no encontrado.");
        }
    }
    
    public void modificar() throws IOException{
       
        String nombre = "";
        String dni;
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Introduzca un DNI para realizar la busqueda:");
        Scanner sc = new Scanner(System.in);
        dni = sc.nextLine();
        Map<String, String> dniNameMap = new HashMap<>();
        for (String line : lines) {
            String[] split = line.split(";");
            dniNameMap.put(split[0], split[1]);
            for(int i = 0; i < split.length; i+=2){
                if(dni.equals(split[0])){
                    System.out.println("\nQue campo desea modificar?");
                    System.out.println("[1] Nombre");
                    System.out.println("[2] DNI");
                    System.out.println("Escoja una opcion:");
                    sc = new Scanner(System.in);
                    String opcion = sc.nextLine();
                    switch(opcion){
                        case "1":
                            System.out.println("\n Introduzca un nuevo nombre: ");
                            String nuevoNombre = sc.nextLine();
                            split[1] = nuevoNombre;
                            String cadena = "";
                            
                            BufferedWriter bfw = new BufferedWriter(new FileWriter(fichero));
                            
                            // escribimos todos los registros anteriores menos el modificado
                            for (String fline : lines) {
                                String[] fsplit = fline.split(";");
                                if (! dni.equals(fsplit[0])) {
                                    bfw.write(fline);
                                    bfw.newLine();
                                } 
                            }
                            
                            // escribimos el registro modificado 
                            cadena = split[0] + ";" + split[1];
                            bfw.write(cadena);
                            bfw.newLine();
                            
                            bfw.close();
                           
                            break;
                        case "2":
                            System.out.println("\n Introduzca un nuevo dni: ");
                            String nuevoDni = sc.nextLine();
                            split[0] = nuevoDni;
                            break;
                    }
                    
                    String cadena = "";
                            
                    BufferedWriter bfw = new BufferedWriter(new FileWriter(fichero));

                    // escribimos todos los registros anteriores menos el modificado
                    for (String fline : lines) {
                        String[] fsplit = fline.split(";");
                        if (! dni.equals(fsplit[0])) {
                            bfw.write(fline);
                            bfw.newLine();
                        } 
                    }
                    // escribimos el registro modificado 
                    cadena = split[0] + ";" + split[1];
                    bfw.write(cadena);
                    bfw.newLine();

                    bfw.close();
                    
                    break;
                } 
            }
        }
    }
    //Constructor de la clase
    public Practica2Ej1(File archivo){
        fichero = archivo;
    }

    
}
    


