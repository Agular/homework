#include <stdlib.h>
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/types.h>
#include <sys/shm.h>
#include <time.h>
#include  <unistd.h>
#include  <sys/sem.h>

//This is the time server that writes time into a memory segment.
//Refer to time_client.c for client side.


//The delta period in seconds how long the server waits to write a new value to the segment
#define PERIOD 1
#define SHM_SIZE 8

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
    
    int shmid;
    key_t key;
    char *shm, *s;
    time_t curtime;
    struct tm *timeinfo;
    union semun arg;
    
    //Name of the shared memory segment
    key = ftok("time_server.c", 1000);
    
    
    //Create the segment
    if((shmid = shmget(key, SHM_SIZE, IPC_CREAT | 0666)) < 0){
        perror("shmget");
        exit(1);
    }
    
    //Attach the segment to our data space
    
    if ((shm = shmat(shmid, NULL, 0)) == (char *) -1) {
        perror("shmat");
        exit(1);
    }
    semid = semget(key, 1, IPC_CREAT|0600);
    arg.val = 1;
    semctl(semid, 0, SETVAL, arg);

    
    while(1){

        P(semid, 0);
        time(&curtime);
        timeinfo = localtime (&curtime);
        system("clear");
        sprintf(shm,"%02d:%02d.%02d", timeinfo->tm_hour, timeinfo->tm_min, timeinfo->tm_sec);
        printf(shm);
        fflush(stdout);
        
        V(semid, 0);
        sleep(PERIOD);
    }
    
    shmdt(shm);
    shmctl(shmid, IPC_RMID, NULL);
    return 0;
}
