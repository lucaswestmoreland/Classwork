****************
* Project 2
* CS 121
* 25th Feb, 2015
* Lucas Westmoreland
**************** 

PROJECT OVERVIEW:

This java program asks the user for the title, artist, playtime, and filename of three songs in their playlist. 
It then sorts the songs based on their playtime.

INCLUDED FILES:

 * README - this file
 * PlayList.java - main file
 * Song.java - Class file
 * input.txt - Used for ease of testing



BUILDING AND RUNNING:

All project files should be in the same directory.

From the directory containing the .java source code, compile the program:
    $ javac PlayList.java


Run the program with the Song.java file in the same directory and you will be
prompted to input three songs including their title, artist, playtime, and filename.


PROJECT DESIGN NOTES:

The PlayList class will essentially sort out the users 3 inputted songs and organize them
based on their playtime. I designed the program with a series of if statements in order to 
figure out which playtime is the shortest, and then I have the program display the songlist
in order of playtime. 

When the information is inputted, the only modified information is the playtime;
which is converted from the mm:ss format, into seconds. I did this in order
to make the computer be able to read what I was coding.

The output includes a border comprised of "=" signs, in order to make it easier to 
read.



PROJECT DEVELOPMENT AND TESTING NOTES:

An issue that came up often was the incorporation of the class files in the Song.java file. 
This was difficult at first because I wanted to define the variables locally, instead of referencing
the class files within Song.java. I eventually countered this issue by defining all of the information
(title, artist, playtime, filename) into the Song class itself. This made it easier to display also
because o the formatting within the song class.

The issue I still have within my program is if there are two songs with the same playtime. I jut defaulted
in this case to it displaying the first song, second song, and third song in that order, regardless of song
length. This was just a quick fix and could be optimized later on in necessary using an other if statement.


EXTRA CREDIT:

N/A
