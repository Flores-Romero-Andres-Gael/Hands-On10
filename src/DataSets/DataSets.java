package DataSets;

import java.util.ArrayList;
import java.util.Random;

public class DataSets {

    public static ArrayList<DataSets> Datos = new ArrayList<DataSets>();
    public int x, fitness;
    public int []B0;

    public DataSets(int x,int[] B0, int fitness){
        this.x = x;
        this.B0 = B0;
        this.fitness = fitness;
    }

    public static ArrayList<DataSets> Datos() {

        ArrayList<DataSets> Datos = new ArrayList<>();
        int Poblacion = 6;
        int Genes = 10;
        Random random = new Random();

        for (int i = 1; i <= Poblacion; i++) {
            int[] randomB0 = new int[Genes];

            for (int j = 0; j < Genes; j++) {
                randomB0[j] = random.nextInt(2);
            }

            int fitness = calcularFitness(randomB0);

            Datos.add(new DataSets(i, randomB0, fitness));
        }

        return Datos;
    }

    public static int calcularFitness(int[] genes) {
        int suma = 0;
        for (int gene : genes) {
            suma += gene;
        }
        return suma;
    }


}
