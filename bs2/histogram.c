#include <sys/types.h>
#include <dirent.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>

#define BINS 1024*1024

char current[1024];

int search_n_list(char *name, int* bins){
    DIR *dir;
    struct dirent *dp;
    struct stat st;
    char cwd[1024];
    
    if ((dir = opendir (name)) == NULL) {
        printf("Cannot open directory: %s\n", name);
        exit(EXIT_FAILURE);
    }

    
    while (dp = readdir(dir)) {
        if(strcmp(dp->d_name, ".") && strcmp(dp->d_name, "..")){
            if(dp->d_name[0] == '.'){
                continue;
            }
            if(dp->d_type != DT_DIR){
                char *filedir = malloc(strlen(name) + strlen(dp->d_name) + 2);
                strcpy(filedir, name);
                strcat(filedir, "/");
                strcat(filedir, dp->d_name);
                if(stat(filedir, &st) < 0){
                    printf("Could not open file!\n");
                    exit(EXIT_FAILURE);
                }
                bins[st.st_size/1024]+=1;
            	printf("%-80s %lu\n",filedir, st.st_size);
            } else{
                
                getcwd(cwd, sizeof(cwd));
                char *newdir = malloc(strlen(name) + strlen(dp->d_name) + 2);
                strcpy(newdir, name);
                strcat(newdir, "/");
                strcat(newdir, dp->d_name);
                search_n_list(newdir, bins);
            }	
        }
    }
    return 0;
}



int main(){
    int *bins = malloc(BINS * sizeof(int));
    getcwd(current, sizeof(current));
    search_n_list(current, bins);
    for(int i=0; i<10; i++){
        printf("%d\n", bins[i]);
    }
    return 0;
}
