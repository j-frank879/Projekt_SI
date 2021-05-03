package projekt_si;

import java.util.ArrayList;
import java.util.List;

public class Data {
    //0-NKB
    //1-Gray
    int code = -1;

    //kind of crossing
    int k = -1;

    //probability of crossover mutation
    float crossover = 0;
    float mutation = 0;

    //population size
    int size_population = 0;

    //population saved in code
    ArrayList<Individual> dane = new ArrayList<Individual>();


}
