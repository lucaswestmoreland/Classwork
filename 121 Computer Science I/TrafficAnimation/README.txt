****************
* Project 1
* CS 121
* 9th Feb, 2015
* Lucas Westmoreland
**************** 

PROJECT OVERVIEW:

This Java application displays a moving Bus going across a paved black road with a grey sidewalk. There is a flag for an avatar 
as well as a red building in the background. This application also incorporates a sun as well as some text
describing the user as to what is actually moving across the road.


INCLUDED FILES:

 * README - this file
 * TrafficAnimation.java - source code


BUILDING AND RUNNING:

All project files should be in the same directory.

From the directory containing the .java source code, compile the program:
    $ javac TrafficAnimation.java
Run the program:
    $ java TrafficAnimation


PROJECT DESIGN NOTES:

The TrafficAnimation program extends the JPanel to draw out the animation. After this, the
code was mostly given to us. This includes the animation itself. For easy coding purposes,
the drawings are all anchored to one spot within the panel. This helps with resizing. The bus
is centered down the middle of the panel to make everything easier to code in reference to the
moving object. 

I made sure to use many different colors when designing this animation in order to give
a dynamic feel to the program and just make it look better all around. In addition to this,
I included two of my own personal colors (the sidewalk and sky). 


PROJECT DEVELOPMENT AND TESTING NOTES:

Aside from the utter frustration brought on by this project, I had some trouble making everything
scalable. My problems arose when attempting to make things like the avatar and the wheels scalable. 
I did not know how to give the proper scalable coordinates until I was instructed by one of my
classmates on how to actually do that. Even still, it seems weak and I am not confident in the scaling
of the things like the avatar.


EXTRA CREDIT:

The only extra credit I incorporated was smoothing out the wrapping by reducing the
refresh rate down to 20 ms.