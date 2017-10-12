#include <string.h>
#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include "ring.h"
#include <pthread.h>

void dump_buffer();


static struct {
		int curr;
		char log[MAX_LOG_ENTRY][MAX_STRING_LENGTH];
		pthread_mutex_t mutex;
} buff;

void init_buffer()
{
		printf("Initialize the ring buffer\n");
		int i;
		for(i =0; i<MAX_LOG_ENTRY; i++) {
				buff.log[i][0]='\0';
		}
		signal(SIGALRM, dump_buffer);
		alarm(alarm_interval);
		pthread_mutex_init(&(buff.mutex), NULL); //init
}

void log_msg(char *entry)
{

		pthread_mutex_lock(&(buff.mutex)); //lock
		if(entry == NULL)

		{
				entry = "Null entry";
		}


		if(buff.curr < 0 || buff.curr > MAX_STRING_LENGTH)
		{
				buff.curr = 0;

		}

		printf("Adding log entry into buffer\n");
		printf("Thread #: %x\n", (unsigned int)pthread_self());
		int idx = buff.curr % MAX_LOG_ENTRY;
		strncpy(buff.log[idx],entry,MAX_STRING_LENGTH);

		buff.log[idx][MAX_STRING_LENGTH-1]='\0';
		buff.curr++;

		pthread_mutex_unlock(&(buff.mutex)); //unlock

}


/*
 * This method writes all the current entries to disk. We will use
 * the constant log_name as the name of the file.
 */
void dump_buffer()
{
		FILE *out = fopen(log_name, "w");
		int i;
		pthread_mutex_lock(&(buff.mutex)); //lock

		for(i =0; i<MAX_LOG_ENTRY; i++) {
				fprintf(out, "%s\n", buff.log[i]); //1
		}
		pthread_mutex_unlock(&(buff.mutex)); //unlock
}
