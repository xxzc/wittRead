#!/usr/bin/python
import sys
from string import maketrans
f = open(sys.argv[1])
lines = f.readlines();
f.close();
for i,line in enumerate(lines):
    lines[i] = line.partition(' ')[0].translate(maketrans("123456789","#########"),"0.")[:-1]+line

f = open("out.txt","w")
f.writelines(lines);
f.close();
