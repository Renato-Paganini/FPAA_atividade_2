import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class QuickSortTest {
    public static void main(String[] args) {
        int size = 10000;
        int numTests = 10;
        int[][] randomArrays = new int[numTests][size];
        int[][] sortedArrays = new int[numTests][size];
        Random rand = new Random();

        // Preenche os vetores
        for (int i = 0; i < numTests; i++) {
            for (int j = 0; j < size; j++) {
                randomArrays[i][j] = rand.nextInt(size);
                sortedArrays[i][j] = j;
            }
        }

        long startTime, endTime;
        long totalRandomTime = 0, totalSortedTime = 0;

        // Executa o QuickSort em cada vetor e acumula o tempo total
        for (int i = 0; i < numTests; i++) {
            startTime = System.currentTimeMillis();
            quickSort(randomArrays[i], 0, size - 1);
            endTime = System.currentTimeMillis();
            totalRandomTime += endTime - startTime;

            startTime = System.currentTimeMillis();
            quickSort(sortedArrays[i], 0, size - 1);
            endTime = System.currentTimeMillis();
            totalSortedTime += endTime - startTime;
        }

        // Calcula os tempos médios em milissegundos
        double averageRandomTime = (double) totalRandomTime / numTests;
        double averageSortedTime = (double) totalSortedTime / numTests;

        // Escreve os resultados em um arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultado_quicksort.txt"))) {
            writer.write("Tempo medio para vetores aleatorios: " + averageRandomTime + " ms\n");
            writer.write("Tempo medio para vetores ordenados: " + averageSortedTime + " ms\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;// searching for lowest index
                }
            }
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
    }
}