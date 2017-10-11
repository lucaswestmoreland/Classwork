'''
Author: Lucas Westmoreland

This script reads in a file and adds the words to a dictionary.
It keeps a running count of the frequency of each term and prints 
those values out. 
'''
import sys


#initialize empty
dictionary = {}

#read in command line args
text = open(sys.argv[1], "r")

#for each word in the input text file
for word in text.read().split():
    

    word = word.replace('\'','')
    word = word.replace('\t','')
    word = word.replace(',','')
    word = word.replace('.','')
    
    #handle casing
    word = word.lower()

#if the word isn't empty string
    if word != '':
        #if the word is in the dictionary
        if word in dictionary:
            dictionary[word] +=1.0
        else:
            dictionary[word] = 1.0

#sorts the dictionary in alphabetical order
for i in sorted(dictionary):
    print (i, dictionary[i])

