package Roulette;

import DataSets.DataSets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Roulette {


    public static DataSets Girar(ArrayList<DataSets> Datos, DataSets padre, int FitnessMax) {
        Random random = new Random();

        double porcentajeAcumulado = 0;
        DataSets cromosomaSeleccionado = null;

        while (cromosomaSeleccionado == null || cromosomaSeleccionado == padre) {
            porcentajeAcumulado = 0;
            double seleccion = random.nextDouble() * 100;

            for (DataSets dataSet : Datos) {
                porcentajeAcumulado += ((double) dataSet.fitness / FitnessMax) * 100;

                if (seleccion <= porcentajeAcumulado) {
                    cromosomaSeleccionado = dataSet;
                    break;
                }
            }
        }

        System.out.println("Cromosoma seleccionada: x: " + cromosomaSeleccionado.x + ", B0: " + Arrays.toString(cromosomaSeleccionado.B0) + ", Fitness: " + cromosomaSeleccionado.fitness);
        return cromosomaSeleccionado;

    }

    public static int Porcentaje(ArrayList<DataSets> Datos){
        int FitnessMax = 0;

        for (DataSets dataSet : Datos) {
            FitnessMax += dataSet.fitness;
        }

        System.out.println("Fitness Total: " + FitnessMax);
        System.out.println("Porcentaje del Fitness: ");

        for (DataSets dataSet : Datos) {
            double porcentaje = ((double) dataSet.fitness / FitnessMax) * 100;
            System.out.println("x: " + dataSet.x + ", Porcentaje de Fitness: " + porcentaje + "%");
        }

        return FitnessMax;

    }

    public static int CalcularFitness(DataSets dataSet) {
        int suma = 0;
        for (int gene : dataSet.B0) {
            suma += gene;
        }
        return suma;
    }



}