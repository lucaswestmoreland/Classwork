#include <stdio.h>
#include <stdlib.h>

#define IN 1 
#define OUT 0
int main(int argc, char **argv)

{
		int c;
		long nc, nw, nl;
		int state;
		int digits[10];

		int i;
		for(i = 0; i < 10; i++)
		{
				digits[i] = 0;
		}


		state = OUT;
		nl = nw = nc = 0;
		while ((c = getchar()) != EOF ) { //while there are still chars in file

				if(c  == '0')
					{
					digits[0]++;
					}

				if(c  == '1')
                    {
                    digits[1]++;
                    }

				if(c  == '2')
                    {
					digits[2]++;
                    }

                if(c  == '3')
                    {
                    digits[3]++;
                    }

				if(c  == '4')
                    {
                    digits[4]++;
                    }

                if(c  == '5')
                    {
                    digits[5]++;
                    }

                if(c  == '6')
                    {
                    digits[6]++;
                    }

                if(c  == '7')
                    {
                    digits[7]++;
                    }

				if(c  == '8')
                    {
                    digits[8]++;
                    }

                if(c  == '9')
                    {
                    digits[9]++;
                    }










				nc++;
				if (c == '\n') { //This code logic was taken from the cs253 resources that were given to us.
						nl++;
				}

				if (c == ' ' || c == '\n' || c == '\t') {
						state = OUT;
				} else if (state == OUT) {
						state = IN;
						nw++;
				}
		}

		printf("words: %ld\nchars: %ld\nlines: %ld\ndigit 0: %d\ndigit 1: %d\ndigit 2: %d\ndigit 3: %d\ndigit 4: %d\ndigit 5: %d\ndigit 6: %d\ndigit 7: %d\ndigit 8: %d\ndigit 9: %d\n", nw, nc, nl, digits[0], digits[1], digits[2], 					digits[3], digits[4], digits[5], digits[6], digits[7], digits[8], digits[9]);
		return 0;
}




