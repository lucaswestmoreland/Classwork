#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#define MAX_LENGTH 2048
#define MAX_TOKENS 100

/* ps-logger 
 * Author: Lucas Westmoreland
 * Date: January 17, 2018
 * Class: CS 453
*/
int main(int argc, char **argv)
{
 int timer;
 char *buffer[MAX_LENGTH];
 char *token;
 int totalcount = 0; 
 int multicount = 0;
 
 
 if(argc != 2) 
 {
 fprintf(stdout, "Usage: ps-logger <sleep interval (seconds)>\n");
 exit(0);
 }
    
        timer = atoi(argv[1]); /* Sets as 0 if invalid input */
        
        if(timer < 1) {
        fprintf(stdout, "%s: Specified too small an interval. Must be at least 1 second.\n", argv[0]);
        exit(1);
        }
        
        fprintf(stdout, "%s: Using a default sleep time of ", argv[0]);
        fprintf(stdout, "%d", timer);
        fprintf(stdout, " seconds.\n");
        
        while (1) {
        
                totalcount = 0;
                multicount = 0;

                system("ps augx > ps.log");
                
                /* NEED TO PARSE PS.LOG HERE */
                
                char *fileName = "ps.log";
                FILE *file = fopen(fileName, "r");
                
                if(file == 0) {  /* if null */ 
                fprintf(stdout, "ps.log does not exist in this directory. Please create one.");
                exit(2);
                }
                
                /* We want the 8th value in each line because that is where the multithread flag is located */
                while(fgets(buffer, sizeof(buffer), file) != NULL) {           
                  token = strtok(buffer, " ");
                  int i = 0;        
                  while (token != NULL)
                  {
                    if(i == 7) 
                    {
                        totalcount++;
                        
                        if(strstr(token, "l")) /* if the substring contains 'l' as specified in the man pages */
                        {
                            multicount++;
                        }
                    }
                  
                  token = strtok(NULL, " ");
                  i++;
                  }
                           
                } 
                
                fclose(file);
                
                fprintf(stdout, "%d", multicount);
                fprintf(stdout, "/");
                fprintf(stdout,"%d", totalcount);
                fprintf(stdout," processes are multithreaded. [");
                fprintf(stdout,"%.2f",(double)multicount/totalcount * 100);
                fprintf(stdout, "\%]\n");
                system("> ps.log");
                sleep(timer);
        }

        exit(0);
}
