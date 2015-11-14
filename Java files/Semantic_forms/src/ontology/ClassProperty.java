package ontology;

//import com.hp.hpl.jena.ontology.*;
//import com.hp.hpl.jena.rdf.model.*;
//import com.hp.hpl.jena.util.FileManager;
//import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.util.iterator.Filter;

import java.io.PrintStream;
import java.util.*;


public class ClassProperty
{
    public static String NS = "/home/akkisinghpanchaal/Downloads/pizza.owl";

    public static void main( String[] args ) {
        OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_DL_MEM);
        FileManager.get().readModel( m, "/home/akkisinghpanchaal/Downloads/pizza.owl" );
        System.out.println("hello world");
        OntClass equipe = m.getOntClass( NS + "Equipe" );
        OntProperty nome = m.getOntProperty( NS + "nome" );
        //System.out.println(equipe.listInstances());
        //System.out.println(instances.hasnext());
        

        for (ExtendedIterator<? extends OntResource> instances = equipe.listInstances(); instances.hasNext(); ) {
            OntResource equipeInstance = instances.next();
            System.out.println( "Equipe instance: " + equipeInstance.getProperty( nome ).getString() );

            // find out the resources that link to the instance
            for (StmtIterator stmts = m.listStatements( null, null, equipeInstance ); stmts.hasNext(); ) {
                Individual ind = stmts.next().getSubject().as( Individual.class );

                // show the properties of this individual
                System.out.println( "  " + ind.getURI() );
                for (StmtIterator j = ind.listProperties(); j.hasNext(); ) {
                    Statement s = j.next();
                    System.out.print( "    " + s.getPredicate().getLocalName() + " -> " );

                    if (s.getObject().isLiteral()) {
                        System.out.println( s.getLiteral().getLexicalForm() );
                    }
                    else {
                        System.out.println( s.getObject() );
                    }
                }
            }
        }
    }
}