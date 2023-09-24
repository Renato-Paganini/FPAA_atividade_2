import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Script {
    public static void main(String[] args) {

        int seed = 666;
        int[] sizes = { 62500, 125000, 250000, 375000 };

        try (FileWriter writer = new FileWriter("results.txt")) {
            for (int size : sizes) {

                long quickSortTime = 0;
                long selectionSortTime = 0;

                for (int i = 0; i < 50; i++) {
                    int[] arr = generateRandomArray(size, seed);
                    int[] arrCopy = Arrays.copyOf(arr, arr.length);
                    System.out.println(arr.length);
                    long startTime = System.currentTimeMillis();
                    quickSort(arr, 0, arr.length - 1);
                    long endTime = System.currentTimeMillis();
                    quickSortTime += (endTime - startTime);

                    startTime = System.currentTimeMillis();
                    selectionSort(arrCopy);
                    endTime = System.currentTimeMillis();
                    selectionSortTime += (endTime - startTime);

                    seed++;
                }
                writer.write("Average time for quicksort with size " + size + ": " + quickSortTime / 50.0 + "\n");
                writer.write(
                        "Average time for selection sort with size " + size + ": " + selectionSortTime / 50.0 + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static int[] generateRandomArray(int size, int seed) {
        Random rand = new Random(seed);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt();
        }
        return arr;
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