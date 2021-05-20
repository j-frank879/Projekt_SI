package project_si;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.awt.*;
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
        b =b.substring(0,b.length()-1);

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

                next.adaptation(twoVariablesFunction);
                dane.add(new Individual(next));
            }
        }


    }
    void Mutation(TextArea results)
    {  int s=2*lengthOfBinaryTheGreatestWord;
        String temp="";
        int x=0;
        results.appendText("Mutation: \n");
    for (Individual ind1 : dane)
    {
        for(int p=0;p<s;p++)
        { if(!(probability(probabilityOfMutation)))
            continue;
        x=1;
             temp=ind1.in;

         if((Character.compare(temp.charAt(p), '0'))==0) {
             ind1.in= temp.substring(0,p)+'1';
             if(p+1<s)
                 ind1.in+=temp.substring(p+1);
         }
         else if((Character.compare(temp.charAt(p), '1'))==0)
         {ind1.in=temp.substring(0,p)+'0';
             if(p+1<s)
                 ind1.in+=temp.substring(p+1);}


         if(kindOfCoding==Coding.NKB)
        {ind1.x=bin_to_decimal(ind1.in.substring(0,lengthOfBinaryTheGreatestWord));

            ind1.y =bin_to_decimal(ind1.in.substring(lengthOfBinaryTheGreatestWord));
        }
        else
        {ind1.x=gray_to_decimal(ind1.in.substring(0,lengthOfBinaryTheGreatestWord));
            ind1.y =gray_to_decimal(ind1.in.substring(lengthOfBinaryTheGreatestWord));
        }

ind1.adaptation(twoVariablesFunction);

    }
if(x==1)
{results.appendText(temp+" | "+ind1.in+"\n");x=0;}
    }
    }
    void onePointCrossover(TextArea results) {
        ArrayList<Individual> new_gen = new ArrayList<>();

        Individual next = new Individual();
        //crossing
        results.appendText("One-point Crossover: \n");
        int i = -1;
        int j = -1;

        for (Individual ind1 : dane) {
            i++;
            for (Individual ind2 : dane) {
                j++;
                if ((j < i) || (!(probability(probabilityOfCrossover))))
                    continue;
                //first
                next.in = ind1.in.substring(0, lengthOfBinaryTheGreatestWord ) + ind2.in.substring(lengthOfBinaryTheGreatestWord);
                next.x = ind1.x;
                next.y = ind2.y;
                next.adaptation(twoVariablesFunction);
                new_gen.add(new Individual(next));
                results.appendText(ind1.in+"  "+ind2.in+" | "+next.in+"  ");
                //second
                next.in = ind2.in.substring(0, lengthOfBinaryTheGreatestWord ) + ind1.in.substring(lengthOfBinaryTheGreatestWord);
                next.x = ind2.x;
                next.y = ind1.y;
                next.adaptation(twoVariablesFunction);
                new_gen.add(new Individual(next));
                results.appendText(next.in+"\n");


            }
            j = -1;
        }
        for (Individual x : new_gen) {
            dane.add(new Individual(x));
        }
        new_gen.clear();
    }
    void twoPointCrossover(TextArea results) {
        ArrayList<Individual> new_gen = new ArrayList<>();

        Individual next = new Individual();
        //crossing
        results.appendText("Two-point Crossover: \n");
        int mix_size= (int) Math.ceil(lengthOfBinaryTheGreatestWord * 2/3);
        int point1=(2*lengthOfBinaryTheGreatestWord-1-mix_size)/2-1;
        int point2=point1+mix_size;
        int i = -1;
        int j = -1;

        for (Individual ind1 : dane) {
            i++;
            for (Individual ind2 : dane) {
                j++;
                if ((j < i) || (!(probability(probabilityOfCrossover))))
                    continue;
                //first
                next.in = ind1.in.substring(0, point1) + ind2.in.substring(point1,point2)+ind1.in.substring(point2);
                if(kindOfCoding==Coding.NKB)
                {next.x=bin_to_decimal(next.in.substring(0,lengthOfBinaryTheGreatestWord));
                    next.y =bin_to_decimal(next.in.substring(lengthOfBinaryTheGreatestWord));
                }
                else
                {next.x=gray_to_decimal(next.in.substring(0,lengthOfBinaryTheGreatestWord));
                    next.y =gray_to_decimal(next.in.substring(lengthOfBinaryTheGreatestWord));
                }
                next.adaptation(twoVariablesFunction);
                new_gen.add(new Individual(next));
                results.appendText(ind1.in+"  "+ind2.in+" | "+next.in+"  ");
                //second
                next.in = ind2.in.substring(0, point1) + ind1.in.substring(point1,point2)+ind2.in.substring(point2);
                if(kindOfCoding==Coding.NKB)
                {next.x=bin_to_decimal(next.in.substring(0,lengthOfBinaryTheGreatestWord));
                    next.y =bin_to_decimal(next.in.substring(lengthOfBinaryTheGreatestWord));
                }
                else
                {next.x=gray_to_decimal(next.in.substring(0,lengthOfBinaryTheGreatestWord));
                    next.y =gray_to_decimal(next.in.substring(lengthOfBinaryTheGreatestWord));
                }
                next.adaptation(twoVariablesFunction);
                new_gen.add(new Individual(next));
                results.appendText(next.in+"\n");

            }
            j = -1;
        }
        for (Individual x : new_gen) {
            dane.add(new Individual(x));
        }
        new_gen.clear();
    }
    void UniformCrossover(TextArea results) {
        ArrayList<Individual> new_gen = new ArrayList<>();

        Individual next = new Individual();
        Individual next1 = new Individual();

        //crossing
        int s = lengthOfBinaryTheGreatestWord * 2 ;
        int i = -1;
        int j = -1;
        results.appendText("Uniform Crossover: \n");
        for (Individual ind1 : dane) {
            i++;
            for (Individual ind2 : dane) {
                j++;
                if ((j < i) || (!(probability(probabilityOfCrossover))))
                    continue;
                next.in=next1.in="";
                for(int p=0;p<s;p++)
                {next.in+= ind1.in.charAt(p);
                    next1.in+= ind2.in.charAt(p);
                    p++;
                    if(p>s)
                        break;
                    next.in+= ind2.in.charAt(p);
                    next1.in+= ind1.in.charAt(p);
                }
                if(kindOfCoding==Coding.NKB)
                {next.x=bin_to_decimal(next.in.substring(0,lengthOfBinaryTheGreatestWord));
                    next.y =bin_to_decimal(next.in.substring(lengthOfBinaryTheGreatestWord));
                }
                else
                {next.x=gray_to_decimal(next.in.substring(0,lengthOfBinaryTheGreatestWord));
                    next.y =gray_to_decimal(next.in.substring(lengthOfBinaryTheGreatestWord));
                }
                next.adaptation(twoVariablesFunction);
                next1.adaptation(twoVariablesFunction);
                new_gen.add(new Individual(next));
                new_gen.add(new Individual(next1));
                results.appendText(ind1.in+"  "+ind2.in+" | "+next.in+"  "+next1.in+"\n");

            }
            j = -1;
        }
        for (Individual x : new_gen) {
            dane.add(new Individual(x));
        }
        new_gen.clear();
    }

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
        int population_adaptation=prefix[i-1];
        i = 0;
        int r;
        int j=0;
        while (i < sizeOfPopulation)
        {r = rand.nextInt(population_adaptation) + 1;

        while(r>prefix[j])
        {j++;

        }
            dane.add(new Individual(dane.get(j)));
        j=0;
            i++;
        }


        prefix=null;
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
    public static class Builder{

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
            if(kindOfCoding == null) kindOfCoding = Coding.GRAY;
            if(kindOfCrossing == null) kindOfCrossing = Crossover.UNIFORM;
            if(probabilityOfCrossover == null) probabilityOfCrossover = 0.5d;
            if(probabilityOfMutation == null) probabilityOfMutation = 0.1d;
            if(sizeOfPopulation == null) sizeOfPopulation = 20;
            if(numberOfGeneration == null) numberOfGeneration = 5;
            if(twoVariablesFunction == null) twoVariablesFunction = "x+y";
            if(lengthOfBinaryWord == null) lengthOfBinaryWord = 4;
            if(minX == null) minX = 5;
            if(maxX == null) maxX = 10;
            if(minY == null) minY = 6;
            if(maxY == null) maxY = 8;
            //TODO what default value should be here
            Data resultData = new Data(kindOfCoding,kindOfCrossing,probabilityOfCrossover,probabilityOfMutation,sizeOfPopulation,
                    numberOfGeneration,twoVariablesFunction,minX,maxX,minY,maxY);
            resultData.setLengthOfBinaryTheGreatestWord(lengthOfBinaryWord);

            return resultData;
        }
    }
}
