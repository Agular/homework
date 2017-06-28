#include <stdio.h>
#include <time.h>
#include <sys/time.h>

int main ()
{
   time_t seconds;
   seconds = time(NULL);
   struct timeval tv;

   while(1){
        //system("clear");  "evil"
        gettimeofday(&tv, NULL);
        //seconds = time(NULL);
        //printf("\r%02ld:%02ld:%02ld", (seconds % 86400) / 3600,(seconds % 3600) / 60 , (seconds % 60));
        printf("\r%02ld:%02ld:%02ld::%03ld", tv.tv_sec % 86400 / 3600 ,tv.tv_sec % 3600 / 60, tv.tv_sec % 60, tv.tv_usec / 1000);
        fflush(stdout);
        nanosleep(1L);
}
   return(0);
}
