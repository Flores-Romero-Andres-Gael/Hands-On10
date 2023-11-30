package Cromosomas;

import DataSets.DataSets;
import Roulette.Roulette;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Cromosomas {

    private static ArrayList<DataSets> nuevaPoblacion = new ArrayList<>();
    public static DataSets individuoConFitness9 = null;
    private static ArrayList<DataSets> Datos = new ArrayList<>(DataSets.Datos());
    public static int generacion = 1;

    public static void Crossover(ArrayList<DataSets> Datos, DataSets padre, int fitnessMax) {
        Random random = new Random();
        int Seleccion, Mut, Check;
        int Cross = random.nextInt(101);
        DataSets Hijo1 = padre;
        padre = null;

        //Ciclos para evitar que los random tengan 0 una vez se defina el crossover
        do {
            Seleccion = random.nextInt(10);
        } while (Seleccion == 0);

        do {
            Mut = random.nextInt(10);
        } while (Mut == 0);

        do {
            Check = random.nextInt(10);
        } while (Check == 0);

        //Inicio Crossover
        if (Cross >= 50) {
            System.out.println("\nResultado: " + Cross + " = REALIZAR CROSSOVER");
            DataSets madre = Roulette.Girar(Datos, Hijo1, fitnessMax);
            DataSets Hijo2 = madre;
            madre = null;

            System.out.println("\nPadre: x: " + Hijo1.x + ", B0: " + Arrays.toString(Hijo1.B0) + ", Fitness: " + Hijo1.fitness);
            System.out.println("Madre: x: " + Hijo2.x + ", B0: " + Arrays.toString(Hijo2.B0) + ", Fitness: " + Hijo2.fitness);
            System.out.println("Seleccion de Crossover: " + Seleccion);

            for (int i = 0; i < Seleccion; i++) {
                int temp = Hijo1.B0[i];
                Hijo1.B0[i] = Hijo2.B0[i];
                Hijo2.B0[i] = temp;
            }

            Hijo1.fitness = Roulette.CalcularFitness(Hijo1);
            Hijo2.fitness = Roulette.CalcularFitness(Hijo2);

            System.out.println("\nCromosomas.Cromosomas despues del Crossover: ");
            System.out.println("Hijo 1: x: " + Hijo1.x + ", B0: " + Arrays.toString(Hijo1.B0) + ", Fitness: " + Hijo1.fitness);
            System.out.println("Hijo 2: x: " + Hijo2.x + ", B0: " + Arrays.toString(Hijo2.B0) + ", Fitness: " + Hijo2.fitness);

            //Inicio Mutacion
            if (Mut >= 50) {
                System.out.println("\nResultado: " + Mut + " = SE REALIZA MUTACION");
                if (Check >= 50){
                    System.out.println("\nResultado: " + Check + " = REALIZAR MUTACION AL HIJO 1");
                    Mutation(Hijo1);
                }else{
                    System.out.println("\nResultado: " + Check + " = REALIZAR MUTACION AL HIJO 2");
                    Mutation(Hijo2);
                }

            } else {
                System.out.println("\nResultado: " + Mut + " = NO SE REALIZA MUTACION");

                if (Check >= 50){
                    System.out.println("\nResultado: " + Check + " = ALMACENAR HIJO 1");
                    AñadirPoblacion(Hijo1);
                }else{
                    System.out.println("\nResultado: " + Check + " = ALMACENAR HIJO 2");
                    AñadirPoblacion(Hijo2);
                }

            }

        } else {

            System.out.println("\nResultado: " + Cross + " = NO CROSSOVER");

            AñadirPoblacion(Hijo1);

            System.out.println("\nNueva población: ");
            for (DataSets dataSet : nuevaPoblacion) {
                System.out.println("x: " + dataSet.x + ", B0: " + Arrays.toString(dataSet.B0) + ", Fitness: " + dataSet.fitness);
            }

        }
    }

    public static void Mutation(DataSets Individual) {
        Random random = new Random();
        int gen = random.nextInt(Individual.B0.length);

        System.out.println("\nDataSet para mutación:");
        System.out.println("x: " + Individual.x + ", B0: " + Arrays.toString(Individual.B0) + ", Fitness: " + Individual.fitness);
        System.out.println("Gen a modificar: [" + gen + "].");

        if (Individual.B0[gen] == 0) {
            Individual.B0[gen] = 1;
        } else{
            Individual.B0[gen] = 0;
        }

        Individual.fitness = Roulette.CalcularFitness(Individual);
        System.out.println("x: " + Individual.x + ", B0: " + Arrays.toString(Individual.B0) + ", Fitness: " + Individual.fitness);
        AñadirPoblacion(Individual);

        System.out.println("\nNueva población después de la mutación:");
        for (DataSets dataSet : nuevaPoblacion) {
            System.out.println("x: " + dataSet.x + ", B0: " + Arrays.toString(dataSet.B0) + ", Fitness: " + dataSet.fitness);
        }

    }

    public static void AñadirPoblacion(DataSets NuevoX) {

        if (nuevaPoblacion.isEmpty()) {
            NuevoX.x = 1;
            nuevaPoblacion.add(NuevoX);
        }else {

            if (nuevaPoblacion.size() == Datos.size()){
                System.out.println("\nLA NUEVA POBLACION ESTA COMPLETA");
                generacion++;
                Datos = new ArrayList<>(nuevaPoblacion);
                nuevaPoblacion.clear();

            }else {

                NuevoX.x = 1;
                DataSets cromosomaConMayorX = nuevaPoblacion.get(0);

                for (DataSets cromosoma : nuevaPoblacion) {
                    if (cromosoma.x > cromosomaConMayorX.x) {
                        cromosomaConMayorX = cromosoma;
                    }
                }

                NuevoX.x += cromosomaConMayorX.x;
                nuevaPoblacion.add(NuevoX);

                System.out.println("\nNueva población: ");
                for (DataSets dataSet : nuevaPoblacion) {
                    System.out.println("x: " + dataSet.x + ", B0: " + Arrays.toString(dataSet.B0) + ", Fitness: " + dataSet.fitness);
                }
            }
        }

    }

}