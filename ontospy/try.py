import ontospy, sys
from ontospy import *
onto = Ontology(sys.argv[1])
f = open('data.json','w')
f.write("data = ")
f.write(onto.ontologyJsonObject()[9:] + "")