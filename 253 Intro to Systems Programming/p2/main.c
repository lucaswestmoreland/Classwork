#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void printBits(unsigned int x); // decomposes an integer value and converts to binary. It then prints out the binary value
unsigned int bitCheck(unsigned int ch, int i); // checks to see if the bit is "activated"
unsigned int getBits(unsigned int x, unsigned int p, unsigned int n); // used to retrieve the binary value of the int 
unsigned int btoi(char []);  // converts a binary value to its integer value
unsigned int swapBytes(unsigned int x); // converts the "endianness" of an unsigned int and returns the converted value

int main(int argc, char **argv)
{

		if(argc != 3)
		{
				printf("Usage: homework -i|-b <x>\n");
				printf("-i x: positive integer less than 4294967295\n");
				printf("-b x: binary representation of positive integer less than 4294967295\n" );
		}		 

		if(strcmp(argv[1], "-i") == 0)
		{
				unsigned int num = atoi(argv[2]);
				unsigned int num2 = swapBytes(num);
				printf("Decimal      \tBinary                              Hexadecimal\n");
				printf("----------------------------------------------------------------\n");
				printf("x  %-10u\t", num);
				printBits(num);
				printf("    0x%08x\n", num);
				printf("x' %-10u\t", num2);
				printBits(num2);
				printf("    0x%08x\n", num2);
		}

		if(strcmp(argv[1], "-b") == 0)
		{
				unsigned int binary = btoi(argv[2]);
				unsigned int binary2 = swapBytes(binary);
				printf("Decimal      \tBinary                              Hexadecimal\n");
				printf("----------------------------------------------------------------\n");
				printf("x  %-10u\t", binary);
				printBits(binary);
				printf("    0x%08x\n", binary);
				printf("x' %-10u\t", binary2);
				printBits(binary2);
				printf("    0x%08x\n", binary2);

		}


		return 0;
}

unsigned int bitCheck(unsigned int ch, int i)
{
		unsigned int mask = 1U << i;
		return mask & ch;
}

unsigned int getBits(unsigned int x, unsigned int p, unsigned int n) //from the book
{
return (x >> (p + 1 - n)) & ~(~0U << n);
}


void printBits(unsigned int x)
{
		int i;
		for(i = (sizeof(int))*8 -1; i >= 0; i--)
		{
				if(bitCheck(x,i))
								printf("1");
				else
								printf("0");	
		}
}

unsigned int btoi(char b[])
{
		unsigned int rtv = 0;

		int i;
		for(i = 0; i <= strlen(b); i++)
		{
			if(b[i] == '1')
			{
				rtv = rtv | (1U << (strlen(b) - 1 - i));	
			}
		}
	return rtv;
}

unsigned int swapBytes(unsigned int x)
{
		unsigned int n = 8;
		unsigned int position = 8;
		unsigned int move = 0;
		int i;

		for(i = 1; i <= sizeof(unsigned int); i++)
		{
				move <<= 8;
				move += getBits(x, i * position - 1, n);
		}
	return move;
}
