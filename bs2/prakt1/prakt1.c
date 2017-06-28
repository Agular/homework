#include  <stdio.h>
#include  <stdlib.h>
#include  <string.h>
#include  <sys/types.h>
#include  <sys/wait.h>
#include  <unistd.h>
#include  <sys/sem.h>

#define   N_CHILD  1
#define   N_LOOPS 20000
//https://www.informatik.htw-dresden.de/~robge/bs2/vl/src/


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

int  main(void) {
    pid_t  son, wpid;
    int i, status;
    union semun arg;
    
    semid = semget(IPC_PRIVATE, 1, IPC_CREAT|0600);
    arg.val = 0;
    semctl(semid, 0, SETVAL, arg);
    
     
    for(i = 0; i < N_CHILD; i++){
        if((son = fork()) < 0) {
            perror("fork");
            return -1;
        } else if(son == 0){
        /* children stuff here*/
            sleep(3);
            printf("THIS IS THE SON! %d\n", getpid());
            V(semid, 0);
            
        
        } else {
            /*father stuff here*/
            printf("I AM YOUR FATHER! %d\n", getpid());
            P(semid, 0);
            printf("THANKS FOR FREEING ME, SON!\n");
            
        }
    }
    while((wpid = wait(&status)) > 0);
    return 0;
} 
