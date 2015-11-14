package ontology;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.*;
import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.ModelFactory;


public class Main {

    public static void main( String[] args ) throws IOException {
        OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );

        // we have a local copy of the wine ontology
        m.getDocumentManager().addAltEntry( "http://www.w3.org/2001/sw/WebOnt/guide-src/wine",
                                            "file:testing/reasoners/bugs/wine.owl" );
        m.getDocumentManager().addAltEntry( "http://www.w3.org/2001/sw/WebOnt/guide-src/wine.owl",
                                            "file:testing/reasoners/bugs/wine.owl" );
        m.getDocumentManager().addAltEntry( "http://www.w3.org/2001/sw/WebOnt/guide-src/food",
                                            "file:testing/reasoners/bugs/food.owl" );
        m.getDocumentManager().addAltEntry( "http://www.w3.org/2001/sw/WebOnt/guide-src/food.owl",
                                            "file:testing/reasoners/bugs/food.owl" );

//================================================================================
//	The input path should be customised accordingly
//================================================================================
        m.read( "/home/akkisinghpanchaal/Downloads/travel.owl" );
        
        
        // Setting the output destination to a file
        // The output destination should be customised accordingly
        File file=new File("/home/akkisinghpanchaal/ontospy/check5.json");
        FileOutputStream fos=new FileOutputStream(file);
        PrintStream ps=new PrintStream(fos);
        System.setOut(ps);
        
        System.out.println("data = [");

        new ClassHierarchy3().showHierarchy( System.out, m );

        System.out.println("]");
    }



}
