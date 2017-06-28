#include "ccl.h"
#include <stdio.h>

/*
   Sobel filter

        N               N          M
   y = Sum n(x)*m(x) + Sum n(x) + Sum m(x)
       x=1             x=1        x=1

 */

#define N 100000000

int n[N], m[N];

int main(void)
{
  int i;
  long y;
  
  uint64_t start, stop;
  unsigned long long duration;
  
  /* init n[] and m[] with some values */
  for (i=0; i<N; i++) {
    n[i] = i;
    m[i] = N-i;
  }
  
  /* Sobel operation */
  

  y = 0;
  start = rdtsc();
  for (i=0; i<N; i++) {
    y = y + n[i]*m[i] + n[i] + m[i];
  }
  stop = rdtsc();
  duration=stop-start;
  printf("Length in Cycles: %llu\n", duration);
  
  return 0;
}
