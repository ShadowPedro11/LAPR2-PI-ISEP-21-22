package app.domain.algorithm;


import java.util.Arrays;

public class BenchmarkAlgorithm {

    public static int[] benchmark(int[] differential) {
        int maxSum = 0;
        int sum;
        int maxSumStart = 0;
        int masSumEnd = 0;

        for (int i = 0; i < differential.length; i++) {
            sum = 0;
            for (int j = i; j < differential.length; j++) {
               sum += differential[j];
               if (sum > maxSum) {
                   maxSum = sum;
                   maxSumStart = i;
                   masSumEnd = j;
               }
            }
        }

        int[] diff = new int[masSumEnd - maxSumStart + 1];
        int cont = 0;
        for (int i = maxSumStart; i < masSumEnd + 1; i++) {
            diff[cont] = differential[i];
            cont++;
        }
        return diff;
    }

    public static int[] kadane(int[] seq) {
        int maxSoFar = 0;
        int maxEndingHere = 0;
        int startMaxSoFar = 0;
        int endMaxSoFar = 0;
        int startMaxEndingHere = 0;

        for(int i = 0; i < seq.length; ++i) {
            int elem = seq[i];
            int endMaxEndingHere = i + 1;
            if (maxEndingHere + elem < 0) {
                maxEndingHere = 0;
                startMaxEndingHere = i + 1;
            } else {
                maxEndingHere += elem;
            }

            if (maxSoFar < maxEndingHere) {
                maxSoFar = maxEndingHere;
                startMaxSoFar = startMaxEndingHere;
                endMaxSoFar = endMaxEndingHere;
            }
        }

        return Arrays.copyOfRange(seq, startMaxSoFar, endMaxSoFar);
    }

    public static void main(String[] args) {
        int[] example = {29, -32, -9, -25, 44, 12, -61, 51, -9, 44, 74, 4}; // 164
        int[] example2 ={-2, 1, -3, 4, -1, 2, 1, -5, 4}; // 6
        int[] example3 = {170, 188, 233, 86, 55, 30, 74, -71, -353, -113, -27, 141, 258, 114, 44, 23, -24, -124, -242, 93, 233, -17, -114, -320}; // 0,0233 milliseconds  |  0,0335 milliseconds
        //170, 188, 233, 86, 55, 30, 74, -71, -353, -113, -27, 141, 258, 114, 44, 23

        long start = System.nanoTime();
        int[] result = benchmark(example3);
        //int[] result = kadane(example3);
        long ens = System.nanoTime();

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
        long res = ens - start;
        System.out.println(res);
    }
}
