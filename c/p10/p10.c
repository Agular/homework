#include <stdio.h>
#include "mat.h"

int main(){
    
    FILE *ptr_myfile;
    tArt var;
    ptr_myfile = fopen("mat.dat.intel64","rb");
    int len, number, i;
    
    
    if(!ptr_myfile){
        printf("Unable to open file!");
        return -1;
    }
    fseek(ptr_myfile,0,SEEK_END);
    len=ftell(ptr_myfile);
    fseek(ptr_myfile,0,SEEK_SET);
    
    printf("Filelength: %d bytes\n",len);
    number=len/sizeof(tArt);
    printf("number of Art: %d\n",number);
    
    
    for(i=0; i<number;i++){
        fread(&var, sizeof(tArt),1,ptr_myfile);
        printf("%-25s,%12s,%ld\n", var.vBez, var.vNr, var.Lbst);
    }
    fclose(ptr_myfile);
    return 0;
}
