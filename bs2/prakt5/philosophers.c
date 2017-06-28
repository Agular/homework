#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <time.h>

#define N 5

char *names [N] = {
  "Socrates",
  "Thomas von Aquin",
  "Immanuel Kant",
  "Friedrich Hegel",
  "Karl Marx"
};
 
pthread_mutex_t forks[N] = { 
  PTHREAD_MUTEX_INITIALIZER, 
  PTHREAD_MUTEX_INITIALIZER, 
  PTHREAD_MUTEX_INITIALIZER, 
  PTHREAD_MUTEX_INITIALIZER, 
  PTHREAD_MUTEX_INITIALIZER
};
pthread_t philos[N];
int philid[N] = {0, 1, 2, 3, 4};

void think (int id) {
  int j;
  j = 1 + rand()%N; 
  printf("%s thinks\n", names[id]);
  usleep(j);
}

void eat (int id) {
  int j;
  j = 2 + rand()%N;
  printf ("%s eats \n", names[id]);
  usleep(j);
}

void *philosopher (void *arg)
{
  int i, j, ret;
  
  
  while(1) {    
    
    i = *(int *) arg;
    think(i);
    
    int f1, f2;
    if(i!=4){
        f1 = i;
        f2 = i+1;
    } else{
        f1 = i+1;
        f2 = i;
    }
    
    printf("%s tries to grab fork %d\n", names[i], (f1)%N);
    ret = pthread_mutex_lock(&(forks[(f1)%N]));   
    if (ret != 0) {
      printf("%s, mutex %i, mutex_lock() failed. return val: %d \n", 
	     names[i], (i)%N, ret);
      pthread_exit(NULL);
    }
    printf("%s tries to grab fork %d\n", names[i], (f2)%N);
    ret = pthread_mutex_lock(&(forks[(f2)%N]));
    if (ret != 0) {
      printf("%s, mutex %i, mutex_lock() failed. return val: %d \n", 
	     names[i], (i)%N, ret);
      pthread_exit(NULL);
    }
    
    eat(i);
          
    printf("%s tries to return fork %d\n", names[i], (f2)%N);
    ret = pthread_mutex_unlock(&(forks[(f2)%N]));    
    if (ret != 0) {
      printf("%s, mutex %i, mutex_unlock() failed\n",  names[i], (f2)%N);
      pthread_exit(NULL);
    }
    printf("%s tries to return fork %d\n", names[i], (f1)%N);
    ret = pthread_mutex_unlock(&(forks[(f1)%N]));
    if (ret != 0) {
      printf("%s, mutex %i, mutex_unlock() failed\n", names[i], (f1)%N);
      pthread_exit(NULL);
    }
  }
}
   
int main (int argc, char* argv[])
{
  int rc, i;
  int *arg;
  int status;
  pthread_attr_t attr;

  for (i = 0; i<N; i++) {
    arg = &philid[i];
    rc = pthread_create(&philos[i], NULL, philosopher, arg);
    if (rc) {
      printf("ERROR; return code from pthread_create() is %d\n",rc);
      exit(EXIT_FAILURE);
    }
  }
  printf("Thread creation finished\n");

  for (i=0; i<N; i++) {
    pthread_join(philos[i], NULL);
  } 

  /* Never reached */
  for (i = 0; i<N; i++) {
    pthread_mutex_destroy(&forks[i]); 
  } 
  exit(EXIT_SUCCESS);
}
