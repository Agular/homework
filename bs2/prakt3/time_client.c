#include <stdlib.h>
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/types.h>
#include <sys/shm.h>
#include <time.h>
#include <unistd.h>
#include <sys/sem.h>
#include <sys/wait.h>


#define SHM_SIZE     8
#define N_CLIENTS    1
#define PERIOD       1
#define TIMES        10



//This is the time client that reads time from a memory segment.
//Refer to time_server.c for server side.

int semid;

union semun {
  int val;                    /* value for SETVAL */
  struct semid_ds *buf;       /* buffer for IPC_STAT, IPC_SET */
  unsigned short int *array;  /* array for GETALL, SETALL */
  struct seminfo *__buf;      /* buffer for IPC_INFO */
};

void P(int semid, int index){
    struct sembuf op;
        
    op.sem_num = index;
    op.sem_op = -1;
    op.sem_flg = 0;
    semop(semid, &op, 1);
}
void V(int semid, int index){
    struct sembuf op;
        
    op.sem_num = index;
    op.sem_op = +1;
    op.sem_flg = 0;
    semop(semid, &op, 1);
}

int main(){
    
    int shmid, i, j, status;
    key_t key;
    char *shm;
    union semun arg;
    pid_t client, wpid;
    
    //Name of the shared memory segment, get it with the server file.
    key = ftok("time_server.c", 1000);
    
    
    //Create the segment
    if((shmid = shmget(key, 0, 0)) < 0){
        perror("shmget");
        exit(1);
    }
    
    //Attach the segment to our data space
    if ((shm = shmat(shmid, NULL, 0)) == (char *) -1) {
        perror("shmat");
        exit(1);
    }
    
    semid = semget(key, 1, 0);
    
    for(i = 0; i < N_CLIENTS; i++){
        if((client = fork()) < 0){
            perror("fork");
            return -1;
        } else if(client == 0){
        /*CLIENT STUFF HERE*/     
        for(j= 0; j< TIMES; j++ ){
            P(semid, 0);
            system("clear");
            printf(shm);
            fflush(stdout);
            V(semid, 0);
            sleep(PERIOD);
        }
        exit(0);    
        }
    }
    
    while((wpid = wait(&status)) > 0);
    shmdt(shm);
    return 0;
}
