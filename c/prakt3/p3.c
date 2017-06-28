#include <stdio.h>
#include <stdlib.h>

char vBuf[128];

int main(){
    signed char    my_sc;
    unsigned char  my_uc;
    signed short   my_ss;
    unsigned short my_us;
    signed int     my_si;
    unsigned int   my_ui;
    signed long    my_sl;
    unsigned long  my_ul;
    float          my_f;
    double         my_d;

    fgets(vBuf,128,stdin); my_sc = atoi(vBuf);
    fgets(vBuf,128,stdin); my_uc = atoi(vBuf);
    fgets(vBuf,128,stdin); my_ss = atoi(vBuf);
    fgets(vBuf,128,stdin); my_us = atoi(vBuf);
    fgets(vBuf,128,stdin); my_si = atoi(vBuf);
    fgets(vBuf,128,stdin); my_ui = atoi(vBuf);
    fgets(vBuf,128,stdin); my_sl = atoi(vBuf);
    fgets(vBuf,128,stdin); my_ul = atoi(vBuf);
    fgets(vBuf,128,stdin); my_f = atoi(vBuf);
    fgets(vBuf,128,stdin); my_d = atoi(vBuf);    
    
    
    printf("signed   char  %p %8d %08X\n", &my_sc,my_sc,my_sc);
    printf("unsigned char  %p %8d %08X\n", &my_uc,my_uc,my_uc);
    printf("signed   short %p %8d %08X\n", &my_ss,my_ss,my_ss);
    printf("unsigned short %p %8d %08X\n", &my_us,my_us,my_us);
    printf("signed   int   %p %8d %08X\n", &my_si,my_si,my_si);
    printf("unsigned int   %p %8d %08X\n", &my_ui,my_ui,my_ui);
    printf("signed   long  %p %8d %08X\n", &my_sl,my_sl,my_sl);
    printf("unsigned long  %p %8d %08X\n", &my_ul,my_ul,my_ul);
    return 0;
    
}
