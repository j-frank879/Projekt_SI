package project_si;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Data {
    //0-NKB
    //1-Gray
    int code = -1;

    //kind of crossing
    int k = -1;

    //probability of crossover mutation
    double crossover = 0;
    double mutation = 0;

    //population size
    int size_population = 0;

    //population saved in code
    ArrayList<Individual> dane = new ArrayList<>();
// the adaptation function
String function;
//
int minX=0,maxX=0,minY=0,maxY=0;
//number of generatons 
int generation=1;

  String decimal_to_binary(int x)
  {String s="";
  for(int i=0;dane.size()>0;i++)    
{  s=s+x%2;    
x=x/2;    
}
      return s;
	 
  };
  //rulet selection
  void Selection()
  {Random rand = new Random();
	 
	  int dl=dane.size();
	  if(dl==0)
	  {return;}
	  int[] prefix=new int[dl] ;
  
int i=0;
  for (Individual x : dane) {
   if(i==0)
   {prefix[0]=x.adaptation;
   i++;
  }
else {prefix[i]=prefix[i-1]+x.adaptation;}

}
i=0;
int r,l=0,h=dl-1,mid,index;
while(i<size_population)
{
r= rand.nextInt(prefix[dl - 1])+1;


    while (l < dl)
    {
        mid = l + ((h - l) >> 1); 
        
        if(r >prefix[mid]) 
        {l = mid + 1;} 
        
        else h = mid;
    }
    if(prefix[l] >= r)
    {index=l;}
    else{index=-1;}
   

	dane.add(new Individual(dane.get(index)));
        i++;
	}




while(dane.size()>size_population)
{dane.remove(0);
	
}
}

	  
  

}
