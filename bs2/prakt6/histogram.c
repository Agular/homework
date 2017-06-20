#include <sys/types.h>
#include <dirent.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

char current[1024];

int search_n_list(char *name){
    DIR *dir;
    struct dirent *dp;
    char cwd[1024];
    
    if ((dir = opendir (name)) == NULL) {
        printf("Cannot open directory: %s\n", name);
        exit(EXIT_FAILURE);
    }
    getcwd(cwd, sizeof(cwd));
    
    while (dp = readdir(dir)) {
        if(strcmp(dp->d_name, ".") && strcmp(dp->d_name, "..")){
            if(dp->d_name[0] == '.'){
                continue;
            }
            if(dp->d_type != DT_DIR){
            	printf("%s/%-60s File\n",cwd,dp->d_name);
            } else{
                char *newdir = malloc(strlen(cwd) + strlen(dp->d_name) + 2);
                strcpy(newdir, cwd);
                strcat(newdir, "/");
                strcat(newdir, dp->d_name);
            	printf("Newdir: %s/%s\n",cwd,dp->d_name);
                search_n_list(newdir);
            }	
        }
    }
    return 0;
}



int main(){
    getcwd(current, sizeof(current));
    search_n_list(".");
    return 0;
}
