#!/bin/bash

rm *.class
rm visitor/*.class
rm syntaxtree/*.class

java -jar ./jtb.jar FriendTJMem.jj

# Generate Parser
javacc jtb.out.jj

# Compile Classes
javac Main.java

java Main < ../testcase-output/TC02.java
java Main < ../tcOUT/TC02.java
