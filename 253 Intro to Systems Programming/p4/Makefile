CC = gcc
CFLAGS = -Wall -g

hello: main.o
	$(CC) $(CFLAGS) $^ -o $@

run: hello
	hello


clean:
	rm -f *.o hello
