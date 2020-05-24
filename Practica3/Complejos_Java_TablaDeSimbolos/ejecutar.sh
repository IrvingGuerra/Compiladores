#!/bin/bas
jflex reglas.l
byaccj -J complejos.y
javac Complejo.java 
javac Auxiliar.java 
javac Parser.java 
javac Yylex.java
javac Symbol.java
javac Cadena.java
java Parser
