#include <stdlib.h>
#include <stdio.h>


int main (){
    
    while(1){
     
        printf("%lu\n", malloc(sizeof(char) * 1024) );
    }
    return 0;
}
