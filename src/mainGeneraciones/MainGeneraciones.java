package mainGeneraciones;

import Cromosomas.Cromosomas;
import DataSets.DataSets;
import Roulette.Roulette;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainGeneraciones {

    private static ArrayList<DataSets> Datos = new ArrayList<>(DataSets.Datos());

    public static void main(String[] args) {
        Funtion(Datos);
    }

    public static void Funtion(ArrayList<DataSets> Datos){

        while (true) {

            System.out.println("\nGeneración: " + Cromosomas.generacion);

            // Sort y mostrar la población actual
            Collections.sort(Datos, (a, b) -> Integer.compare(b.fitness, a.fitness));
            for (DataSets dataSet : Datos) {
                System.out.println("x: " + dataSet.x + ", B0: " + Arrays.toString(dataSet.B0) + ", Fitness: " + dataSet.fitness);
            }

            // Seleccionar el padre una sola vez
            DataSets padre = Datos.get(0);
            System.out.println("\nPadre: x: " + padre.x + ", B0: " + Arrays.toString(padre.B0) + ", Fitness: " + padre.fitness);

            // Si el padre tiene un fitness de 9 o superior, almacenar y salir del bucle
            if (padre.fitness >= 9) {
                Cromosomas.individuoConFitness9 = new DataSets(padre.x, padre.B0, padre.fitness);
                System.out.println("\n¡Se alcanzó un fitness de " + padre.fitness + " o superior en la generación " + Cromosomas.generacion + "!");
                break;
            }

            // Calcular el fitness máximo y realizar el crossover
            int fitnessMax = Roulette.Porcentaje(Datos);
            Cromosomas.Crossover(Datos, padre, fitnessMax);

        }

        // Muestra el individuo con fitness 9 o superior al final
        if (Cromosomas.individuoConFitness9 != null) {
            System.out.println("\nIndividuo con fitness de 9 o superior:");
            System.out.println("x: " + Cromosomas.individuoConFitness9.x + ", B0: " + Arrays.toString(Cromosomas.individuoConFitness9.B0) + ", Fitness: " + Cromosomas.individuoConFitness9.fitness);
        }
    }
}
