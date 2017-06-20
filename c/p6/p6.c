#include <stdio.h>
#include <stdlib.h>

char vBuf[128];

int main(){
	printf("Enter some text to be analysed\n");
	fgets(vBuf, 128, stdin);
	printf("The system function value of sin(%lf): %.4lf \n",x,sin(x));	
	printf("The user function value of sin(%lf): %.4lf \n",x,userSin(x));


	return 0;
}
