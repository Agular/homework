#include <stdlib.h>
#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <string.h>

int main (void){
    int size;
    char *mapQuell;
    char *mapZiel;
    int result;
    struct stat fileStat;
    
    int quell = open("ilikepie.txt", O_RDONLY);
    
    if(quell==-1){
        perror("Error opening the source file\n");
        exit(EXIT_FAILURE);
    }
    
    int ziel = open("what_does_the_fox_say.txt", O_RDWR | O_CREAT | O_TRUNC, 0700);
    if(ziel == -1){
        perror("Error opening the target file\n");
        exit(EXIT_FAILURE);
    }
    
    fstat(quell, &fileStat);
    size = fileStat.st_size;
    printf("Size of quell: %d bytes\n", size);
    
    lseek(ziel, size-1, SEEK_SET);
    result = write(ziel, " ", 1);
    
    if (result!= 1){
        close(quell);
        close(ziel);
        perror("Error writing to target file\n");
        exit(EXIT_FAILURE);
    }
    mapQuell = mmap(0, size, PROT_READ, MAP_SHARED, quell, 0);
    if(mapQuell == MAP_FAILED){
        close(quell);
        close(ziel);
        perror("Error mapping to source file\n");
        exit(EXIT_FAILURE);
    }
    mapZiel = mmap(0, size, PROT_READ | PROT_WRITE, MAP_SHARED, ziel, 0 );
    if(mapZiel == MAP_FAILED){
        close(quell);
        close(ziel);
        perror("Error mapping to target file\n");
        exit(EXIT_FAILURE);
    }
    
    memcpy(mapZiel, mapQuell, size);
    
    munmap(mapQuell, size);
    munmap(mapZiel, size);
    
    close(quell);
    close(ziel);
    return 0;
}