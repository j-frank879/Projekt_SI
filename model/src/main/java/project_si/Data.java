package project_si;

import java.util.ArrayList;
import java.util.Random;


public class Data {
    private Coding kindOfCoding;
    private Crossover kindOfCrossing;
    private double probabilityOfCrossover;
    private double probabilityOfMutation;
    private int sizeOfPopulation;
    private int numberOfGeneration;
    private String twoVariablesFunction;
    private int minX, maxX, minY, maxY;
    private int lengthOfBinaryTheGreatestWord;

    //population saved in code
    private ArrayList<Individual> dane = new ArrayList<>();

    Data(){

    }

    Data(Coding aKindOfCoding, Crossover aKindOfCrossing, double aProbabilityOfCrossover, double aProbabilityOfMutation, int aSizeOfPopulation,
         int aNumberOfGeneration, String aTwoVariablesFunction, int aMinX, int aMaxX, int aMinY, int aMaxY) {
        kindOfCoding = aKindOfCoding;
        kindOfCrossing = aKindOfCrossing;
        probabilityOfCrossover = aProbabilityOfCrossover;
        probabilityOfMutation = aProbabilityOfMutation;
        sizeOfPopulation = aSizeOfPopulation;
        numberOfGeneration = aNumberOfGeneration;
        twoVariablesFunction = aTwoVariablesFunction;
        minX = aMinX;
        maxX = aMaxX;
        minY = aMinY;
        maxY = aMaxY;
        lengthOfBinaryTheGreatestWord = 4;
        //TODO what value should lengthOfBinaryWord have while initializing
    }


    void set_bin_length() {
        int a = (maxX > maxY) ? maxX : maxY;
        int x = 0;
        while ((a != 1) && (a != 0)) {
            a /= 2;
            x++;
        }
        x++;
        lengthOfBinaryTheGreatestWord = x;
    }

    String decimal_to_binary(int x) {
        String s = Integer.toBinaryString(x);
        while ((s.length() < lengthOfBinaryTheGreatestWord)) {
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

    //calculating if crossover/mutation happened

    Boolean probability(double x) {
        int q = 1;
        while (x % 1 > 0) {
            x *= 10;
            q *= 10;
        }
        Random rand = new Random();
        int r = rand.nextInt(q);
        if (r < x) {
            return true;
        } else
            return false;

    }
    //generating first generation in dane list

    void makeFirstGeneration() {
        set_bin_length();
        dane.clear();
        Individual next = new Individual();
        for (int i = minX; i < maxX; i++) {
            for (int j = minY; j < maxY; j++) {
                next.x = i;
                next.y = j;
                if (kindOfCoding == Coding.NKB)
                    next.in = decimal_to_binary(i) + decimal_to_binary(j);
                else
                    next.in = binary_to_gray(decimal_to_binary(i)) + binary_to_gray(decimal_to_binary(j));

                next.adaptation();
                dane.add(new Individual(next));
            }
        }


    }
    void onePointCrossover() {
        ArrayList<Individual> new_gen = new ArrayList<>();

        Individual next = new Individual();
        //crossing
        int s = lengthOfBinaryTheGreatestWord * 2 - 1;
        int i = -1;
        int j = -1;

        for (Individual ind1 : dane) {
            i++;
            for (Individual ind2 : dane) {
                j++;
                if ((j < i) || (!(probability(probabilityOfCrossover))))
                    continue;
                //first
                next.in = ind1.in.substring(0, lengthOfBinaryTheGreatestWord - 1) + ind2.in.substring(lengthOfBinaryTheGreatestWord, s);
                next.x = ind1.x;
                next.y = ind2.y;
                next.adaptation();
                new_gen.add(new Individual(next));
                //second
                next.in = ind2.in.substring(0, lengthOfBinaryTheGreatestWord - 1) + ind1.in.substring(lengthOfBinaryTheGreatestWord, s);
                next.x = ind2.x;
                next.y = ind1.y;
                next.adaptation();
                new_gen.add(new Individual(next));

            }
            j = -1;
        }
        for (Individual x : new_gen) {
            dane.add(x);
        }
    }

    //roulette  selection

    void rouletteSelection() {
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
        while (i < sizeOfPopulation) {
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

        while (dane.size() > sizeOfPopulation) {
            dane.remove(0);
        }
    }

    void setLengthOfBinaryTheGreatestWord(int aLengthOfBinaryTheGreatestWord) {
        lengthOfBinaryTheGreatestWord = aLengthOfBinaryTheGreatestWord;
    }

    Coding getKindOfCoding() {
        return kindOfCoding;
    }

    Crossover getKindOfCrossing() {
        return kindOfCrossing;
    }

    double getProbabilityOfCrossover() {
        return probabilityOfCrossover;
    }

    double getProbabilityOfMutation() {
        return probabilityOfMutation;
    }

    int getSizeOfPopulation() {
        return sizeOfPopulation;
    }

    int getNumberOfGeneration() {
        return numberOfGeneration;
    }

    String getTwoVariablesFunction() {
        return twoVariablesFunction;
    }

    int getMinX() {
        return minX;
    }

    int getMaxX() {
        return maxX;
    }

    int getMinY() {
        return minY;
    }

    int getMaxY() {
        return maxY;
    }

    int getLengthOfBinaryTheGreatestWord() {
        return lengthOfBinaryTheGreatestWord;
    }

    ArrayList<Individual> getDane() {
        return dane;
    }
    public class Builder{

        Coding kindOfCoding;
        Crossover kindOfCrossing;
        Double probabilityOfCrossover;
        Double probabilityOfMutation;
        Integer sizeOfPopulation;
        Integer numberOfGeneration;
        String twoVariablesFunction;
        Integer minX, maxX, minY, maxY;
        Integer lengthOfBinaryWord;

        public Builder kindOfCoding(Coding aKindOfCoding){
            kindOfCoding = aKindOfCoding;
            return this;
        }
        public Builder kindOfCrossing(Crossover aKindOfCrossing){
            kindOfCrossing = aKindOfCrossing;
            return this;
        }
        public Builder probabilityOfCrossover(Double aProbabilityOfCrossover){
            probabilityOfCrossover = aProbabilityOfCrossover;
            return this;
        }
        public Builder probabilityOfMutation(Double aProbabilityOfMutation){
            probabilityOfMutation = aProbabilityOfMutation;
            return this;
        }
        public Builder sizeOfPopulation(Integer aSizeOfPopulation){
            sizeOfPopulation = aSizeOfPopulation;
            return this;
        }
        public Builder numberOfGeneration(Integer aNumberOfGeneration){
            numberOfGeneration = aNumberOfGeneration;
            return this;
        }
        public Builder twoVariablesFunction(String aTwoVariablesFunction){
            twoVariablesFunction = aTwoVariablesFunction;
            return this;
        }
        public Builder minX(Integer aMinX){
            minX = aMinX;
            return this;
        }
        public Builder maxX(Integer aMaxX){
            maxX = aMaxX;
            return this;
        }
        public Builder minY(Integer aMinY){
            minY = aMinY;
            return this;
        }
        public Builder maxY(Integer aMaxY){
            maxY = aMaxY;
            return this;
        }
        public Builder lengthOfBinaryWord(Integer aLengthOfBinaryWord){
            lengthOfBinaryWord = aLengthOfBinaryWord;
            return this;
        }
        public Data build(){
            if(kindOfCoding == null) kindOfCoding = Coding.NKB;
            if(kindOfCrossing == null) kindOfCrossing = Crossover.ONE_POINT;
            if(probabilityOfCrossover == null) probabilityOfCrossover = 0d;
            if(probabilityOfMutation == null) probabilityOfMutation = 0d;
            if(sizeOfPopulation == null) sizeOfPopulation = 0;
            if(numberOfGeneration == null) numberOfGeneration = 0;
            if(twoVariablesFunction == null) twoVariablesFunction = "x+y";
            if(lengthOfBinaryWord == null) lengthOfBinaryWord = 0;
            if(minX == null) minX = 0;
            if(maxX == null) maxX = 0;
            if(minY == null) minY = 0;
            if(maxY == null) maxY = 0;

            Data resultData = new Data(kindOfCoding,kindOfCrossing,probabilityOfCrossover,probabilityOfMutation,sizeOfPopulation,
                    numberOfGeneration,twoVariablesFunction,minX,maxX,minY,maxY);
            resultData.setLengthOfBinaryTheGreatestWord(lengthOfBinaryTheGreatestWord);

            return resultData;
        }
    }
}
