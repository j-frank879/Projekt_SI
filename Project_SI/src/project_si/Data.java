package project_si;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Data {
    //0-NKB
    //1-Gray
    int code = -1;

    //kind of crossing
    int kindOfCrossing = -1;

    //probability of crossover mutation
    double crossover = 0;
    double mutation = 0;

    //population size
    int size_population = 0;

    //population saved in code
    ArrayList<Individual> dane = new ArrayList<>();

    // the adaptation function
    String function;
    int minX = 0, maxX = 0, minY = 0, maxY = 0;
    //number of generatons
    int generation = 1;
    //length of binary word example 01101 bin_lenght=5
    int bin_length = 0;

    void set_bin_length() {
        int a = (maxX > maxY) ? maxX : maxY;
        int x = 0;
        while ((a != 1) && (a != 0)) {
            a /= 2;
            x++;
        }
        x++;
        bin_length = x;
    }

    String decimal_to_binary(int x) {
        String s = Integer.toBinaryString(x);
        while ((s.length() < bin_length)) {
            s = "0" + s;
        }
        return s;
    }


    String binary_to_gray(String x) {
        String s = "";
        s = s + x.charAt(0);
        for (int i = 1; i < x.length(); i++) {
            if (x.charAt(i - 1) == x.charAt(i)) {
                s += "0";
            } else {
                s += "1";
            }
        }
        return s;
    }


    int gray_to_decimal(String x) {
        int a;
        String b = "";

        b += x.charAt(0);

        for (int i = 1; i < x.length(); i++) {

            if (x.charAt(i) == '0')
                b += b.charAt(i - 1);

            else {
                if (b.charAt(i - 1) == '0')
                    b += '1';
                else
                    b += '0';
            }

        }

        a = bin_to_decimal(b);
        return a;
    }


    int bin_to_decimal(String x) {
        int a = Integer.parseInt(x, 2);
        return a;
    }

    //rulet selection
    void Selection() {
        Random rand = new Random();

        int dl = dane.size();
        if (dl == 0) {
            return;
        }
        int[] prefix = new int[dl];

        int i = 0;
        for (Individual x : dane) {
            if (i == 0) {
                prefix[0] = x.adaptation;
                i++;
            } else {
                prefix[i] = prefix[i - 1] + x.adaptation;
            }

        }
        i = 0;
        int r, l = 0, h = dl - 1, mid, index;
        while (i < size_population) {
            r = rand.nextInt(prefix[dl - 1]) + 1;


            while (l < dl) {
                mid = l + ((h - l) >> 1);

                if (r > prefix[mid]) {
                    l = mid + 1;
                } else h = mid;
            }
            if (prefix[l] >= r) {
                index = l;
            } else {
                index = -1;
            }


            dane.add(new Individual(dane.get(index)));
            i++;
        }


        while (dane.size() > size_population) {
            dane.remove(0);

        }
    }


}
