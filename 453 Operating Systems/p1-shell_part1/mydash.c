#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <readline/readline.h>
#include <readline/history.h>

#include "errno.h"
#include "pwd.h"
#include "mydash.h"
#include "log.h"
#include "common.h"




/*******INITIAL DECLARATIONS*******/
int emptyLine(const char *string);
char** tokenizeEntry(char *line);
void freeArray(char **array);
/*******INITIAL DECLARATIONS*******/



/* MAIN FUNCTION */
int main(int argc, char **argv)
{

    char   *prompt;
    char   *line; 
    pid_t   pid;
    int     status;
    char  **entries;
    struct passwd *userInfo;





    /**************** Prints Version ****************/
    if(argc == 2 && strstr(argv[1], "-v")) { 
        const char *v = version();
        fprintf(stdout,"Version 1: Revision %s", v);
        fprintf(stdout," (author: Lucas Westmoreland lucaswestmoreland@u.boisestate.edu)\n");
        
        exit(EXIT_SUCCESS);
    }
    
    
    
    /**************** Set Prompt ****************/
    if(!getenv("DASH_PROMPT")) {
        prompt = "HENLO?: ";
    }
    else {
        prompt = getenv("DASH_PROMPT");
    }
    
    
    

    
    using_history();       
    while ((line = (char *)readline(prompt))) { 
        
        if(strcmp(line,"exit") == 0) {
            exit(EXIT_SUCCESS);
        }
            
        if(emptyLine(line)){
            continue;
        }
                
        add_history(line);    
        entries = tokenizeEntry(line);
            
            


    /**************** CD Command ****************/            
        if(strcmp(entries[0], "cd") == 0) {
         
            if(entries[1] != NULL) {
                chdir(entries[1]);
                freeArray(entries);
                continue;
            }
              
            else {          
                errno = 0;    
                userInfo = getpwuid(getuid());
              
                if(errno == EINTR || errno == EIO || errno == EMFILE || errno == ENFILE || errno == ENOMEM || errno == ERANGE) {
                    printf("Could not retrieve home directory. Navigating to root...\n");
                    freeArray(entries);
                    free(line);
                    chdir("/");
                    continue;
                    }
              
                else {
                    freeArray(entries);
                    free(line);
                    chdir(userInfo->pw_dir);
                    continue;
                }
                
                free(line);
                freeArray(entries);
                continue;
            }
        }
    




        if ((pid = fork()) < 0)
            err_sys("fork error");
            
      /**************** Child ****************/
        else if (pid == 0) {
            execvp(entries[0], entries);
            err_ret("couldn't execute: %s", line);
            freeArray(entries);
            exit(EXIT_FAILURE);
            continue;
        }
       freeArray(entries);
       
       
       
       /**************** Parent ****************/
        if ((pid = waitpid(pid, &status, 0)) < 0){
            err_sys("waitpid error");
            free(line);
            freeArray(entries);
        }
    }
    
    
  exit(EXIT_SUCCESS);
  return 0;
}






int emptyLine(const char *str) {
  
  while(*str != '\0') {
      if(!isspace(*str)) {
      return 0;
    }
    str++;
  }
return 1;
}



char** tokenizeEntry(char *line) {
    char *nextToken;
    int i=0;
    char **entries;
    char *token;
    char *temp;

    temp = malloc(strlen(line) * sizeof(char) + 1);
    strcpy(temp, line);
    entries = malloc(MAXLINE * sizeof(char*));
    nextToken = strtok(temp, " ");
    
    while (nextToken != NULL) {
        entries[i] = malloc(strlen(nextToken) + 1);
        token = entries[i++];
        strcpy(token, nextToken);
        nextToken = strtok(NULL, " ");
    }
    
    entries[i] = '\0';
    free(temp);
    return entries;
}



void freeArray(char **array) {

  int i = 0;
  while (array[i] != NULL) {
    free(array[i]);
    i++;
  } 
   
  free(array);
}



