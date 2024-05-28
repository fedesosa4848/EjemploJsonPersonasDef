
/*
Escribe una clase que represente una cuenta bancaria, con un saldo y un número de cuenta.
La clase debe tener un método para retirar que reciba una cantidad de dinero y lance una
excepción de tipo SaldoInsuficienteException si el saldo es menor que la cantidad a retirar.
La excepción SaldoInsuficienteException debe ser una clase personalizada que herede de
Exception y tenga un constructor que reciba un mensaje de error.

 */


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;


import java.io.*;

import java.util.Random;
import java.util.Scanner;


public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("nombre","federico");
            jsonObject.put("edad",28);

            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("ciudad","mar del plata");
            jsonObject1.put("direccion","salta");
            jsonObject1.put("altura",1500);

            JSONArray jsonArray = new JSONArray();

            for(int i = 0; i < 3; i++){
                jsonArray.put(random.nextInt(i+10));
            }

            jsonObject.put("Ubicacion",jsonObject1);
            jsonObject.put("Calificaciones:",jsonArray);

            //System.out.println(jsonObject.toString());
            JsonUtiles.grabar(jsonObject, "datos");

        }catch (JSONException e){
            System.out.println(e.getMessage());
        }



        try {
            //Convierte los datos del data en bytes.
            FileOutputStream fileOutputStream = new FileOutputStream("datos.bin");
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);

            for(int i = 0; i < 3; i ++) {
                dataOutputStream.writeUTF("Fede");
                dataOutputStream.writeInt(28);
                dataOutputStream.writeBoolean(true);
                dataOutputStream.writeDouble(1.68);
            }

            dataOutputStream.close();

            /*
            VERSION 1 :

            FileInputStream fileInputStream = new FileInputStream("datos.bin");
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            */

            /*
            Hacemos un ciclo infinito que lea todos lo datos que hay en el archivo, cuando termine de leerlos
            se ejecutra la excepcion EOF que nos indica el fin del archivo. Es una excepcion "buena", ya que
            nos indica el fin del archivo.

             *//*
            int lectura = 1;
            while (lectura ==1) {
                System.out.println(dataInputStream.readUTF());
                System.out.println(dataInputStream.readInt());
                System.out.println(dataInputStream.readBoolean());
                System.out.println(dataInputStream.readDouble());
                System.out.println("\n------------------------------------\n");

            }

            dataInputStream.close();


        } catch (EOFException e) {
            System.out.println("Fin del archivo");*/

            //Version 2: Esto se conoce como "nested try blocks" (bloques try anidados).

            FileInputStream fileInputStream = new FileInputStream("datos.bin");
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);

            while (true) {
                try {
                    System.out.println(dataInputStream.readUTF());
                    System.out.println(dataInputStream.readInt());
                    System.out.println(dataInputStream.readBoolean());
                    System.out.println(dataInputStream.readDouble());
                } catch (EOFException e) {
                    System.out.println("Fin del archivo");
                    break; // Sal del bucle cuando se alcanza el final del archivo
                }
            }

            dataInputStream.close();
        }catch (FileNotFoundException e) {
            System.err.println("El archivo no fue encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al trabajar con el archivo: " + e.getMessage());
        }



        //Version 1


        try{


            FileOutputStream fileOutputStream = new FileOutputStream("datosCuentas.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            CuentaBancaria nueva = new CuentaBancaria(123,5000);

            objectOutputStream.writeObject(nueva);
            objectOutputStream.close();

            FileInputStream fileInputStream = new FileInputStream("datosCuentas.bin");

            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            CuentaBancaria aux = (CuentaBancaria) objectInputStream.readObject();

            System.out.println(aux);

            objectOutputStream.close();

        }catch (EOFException e){
            System.out.println(e.getMessage());
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }



        //Version 2

        try{


            FileOutputStream fileOutputStream = new FileOutputStream("datosCuentas.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            CuentaBancaria nueva = new CuentaBancaria(123,5000);

            objectOutputStream.writeObject(nueva);
            objectOutputStream.close();

            FileInputStream fileInputStream = new FileInputStream("datosCuentas.bin");

            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            try{
                while (true){
                    CuentaBancaria aux = (CuentaBancaria) objectInputStream.readObject();
                    System.out.println(aux);
                }
            }catch (EOFException e){
                System.out.println("Fin archivo");
            }

            objectOutputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " );

        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada: " );
        } catch (IOException e){
            System.out.println("Error de entrada/salida: " );
        }








        /*
         double retiro = scanner.nextDouble();

        try{
            System.out.println(nueva.retiro(retiro));
        }catch (ExcepcionCuenta e){
            System.out.println(e.getMessage());
        }
         */
    }
}