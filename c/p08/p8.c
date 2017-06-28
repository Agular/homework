#include <stdio.h>

typedef struct Country {
    char name[31];
    char code[4];
    char currency[4];
    double buyPrice;
    double sellPrice;
} tWrg; 

tWrg vWrg[]= {
#include "data.dat"
};


int main(){
    int i;
    for(i=0;i<10;i++) vWrg[i].sellPrice = 0.92 * vWrg[i].buyPrice;


    tWrg temp;
    int sorted;
    for(i =0; i < 9; i++){
        int j;
        for(j = i+1; j<10; j++){
		int n;
		for(n = 0; (vWrg[i].name[n]!='\0') && (vWrg[i].name[n]==vWrg[j].name[n]);n++);
                    if(vWrg[i].name[n] > vWrg[j].name[n]){
			temp = vWrg[i];
			vWrg[i] = vWrg[j];
			vWrg[j] = temp;
		    }
	    
        }
    }
    for(i=0;i<10;i++) printf("%-64s,%4s,%4s\n", vWrg[i].name, vWrg[i].code, vWrg[i].currency);

    return 0;
}
