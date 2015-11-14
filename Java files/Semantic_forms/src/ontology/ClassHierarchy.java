package ontology;



//Imports
///////////////
import org.apache.jena.ontology.*;

import org.apache.jena.rdf.model.*;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.util.iterator.Filter;
import org.apache.jena.ontology.Restriction;
import java.io.PrintStream;
import java.util.*;


/**
* <p>
* Simple demonstration program to show how to list a hierarchy of classes. This
* is not a complete solution to the problem (sub-classes of restrictions, for example,
* are not shown).  It is intended only to be illustrative of the general approach.
* </p>
*/
public class ClassHierarchy {
 // Constants
 //////////////////////////////////
 
 // Static variables
 //////////////////////////////////

 // Instance variables
 //////////////////////////////////

 protected OntModel m_model;
 private Map<AnonId,String> m_anonIDs = new HashMap<AnonId, String>();
 private int m_anonCount = 0;
 public int id_number = 1;
 //public Stack<Integer> stack= new Stack<Integer>();
// public int dummy_depth;
 //public char Stack[];
 //public int stack_pointer = 0;
 public Stack<Character> Char_stack = new Stack<Character>();

 // Constructors
 //////////////////////////////////

 // External signature methods
 //////////////////////////////////

 /** Show the sub-class hierarchy encoded by the given model */

 // THis is a method that determines the type of property; takes in the property as a parameter
 
/* public void Push(char element){
	 Stack[stack_pointer] = element;
	 stack_pointer++;
 }
 
 public char Pop(){
	 
	 if(stack_pointer != 0)
		 stack_pointer--;
	 
	 return Stack[stack_pointer];
 }*/
 public String ShortForm(String str){
	
	 String temp2[];
	 temp2 = str.split("#");
	 return temp2[1].toString();
	 
 }
 
 
 public String getDatatypeOfTheProperty(OntProperty property)
 {
	 String type = "";
	 if(property.isFunctionalProperty()){
		 type = "functional property";
		 //return type;
	 }
	 else if (property.isInverseFunctionalProperty()){
		 type = "Inverse Functional property";
	 }
	 else if (property.isDatatypeProperty()){
		 type = "datatype property";
	 }
	 else if (property.isSymmetricProperty()){
		 type = "symmetric property";
	 }
	 else if (property.isTransitiveProperty()){
		 type = "Transitive property";
	 }
	 else if (property.isObjectProperty()){
		 type = "Object property";
	 }
	return type;
 }
 
 
 
 
 public void showHierarchy( PrintStream out, OntModel m ) {
     // create an iterator over the root classes that are not anonymous class expressions
     Iterator<OntClass> i = m.listHierarchyRootClasses()
                   .filterDrop( new Filter<OntClass>() {
                                 @Override
                                 public boolean accept( OntClass r ) {
                                     return r.isAnon();
                                 }} );
     
     //=================================================================================================
   /*  Iterator<OntProperty> j = m.listAllOntProperties();
     while(j.hasNext())
     {
    	 OntProperty k;
    	 k = j.next();
    	 String temp = "";
    	 String temp2[];
    	 temp = k.toString();
    	 temp2 = temp.split("#");
    	 out.print(temp2[1] +": ");
    	 //out.print(j.next());
    	 //out.print("\n i am the part of the string" + temp.charAt(5) + "\n" );
    	 // out.print(j.next());
    	 out.print(getDatatypeOfTheProperty(k) + "\n");
     }*/
     //Iterator<OntProperty> l;
     //==================================================================================================
     //Char_stack.push('f');
     //Char_stack.push('g');
     //System.out.println("Element: "Char_stack);
     
     
     while (i.hasNext()) {
    	 showClass( out, i.next(), new ArrayList<OntClass>(), 0 );
        }
 }


 /* Internal implementation methods*/
 //////////////////////////////////

 /** Present a class, then recurse down to the sub-classes.
  *  Use occurs check to prevent getting stuck in a loop
  */
 protected void showClass( PrintStream out, OntClass cls, List<OntClass> occurs, int depth ) {
   //dummy_depth = depth;
   // System.out.println("DEPTH IS: "+depth);
   //System.out.println("DUMMY DEPTH IS: " + dummy_depth);4
	 
	 renderClassDescription( out, cls, depth );
     out.println();
     //System.out.println(" Checkpoint1 ");
     
     // capture the properties of the class in an iterator
     
     Iterator<OntProperty> properties, dup_properties;
     properties = cls.listDeclaredProperties(false);
     dup_properties = properties;
     //======================================================================================
     
     /*Iterator iter = cls.listInstances();
     System.out.println("=================================");
     while(iter.hasNext()){
    	 System.out.println("Ihavenothing");
    	 System.out.println(iter.next());
     }
     System.out.println("=================================");*/
     
     
     
     ExtendedIterator instances, dup_instances; 
     instances = cls.listInstances();
    dup_instances = instances;
    
    int instance_number = 1;
    boolean started; //this will keep track of starting and ending of the instances for a particular class
    
    if(dup_instances.hasNext() ||  dup_properties.hasNext()){
   	 out.print(", items:[");
   	 //======================================================
   	 
   	 //Push(']');
   	 
   	 //======================================================
   	 started = true;
    }
    
while (instances.hasNext()){
	//Individual dup_thisinstance = (Individual) dup_instances.next();
	Individual thisinstance = (Individual) instances.next();
	out.print( '{' + "id: " + id_number++ + ',' + " text: " + '"' + ShortForm(thisinstance.toString()) + '"' +'}');
	if(dup_instances.hasNext()){
		out.print(',');
	}
	else if(properties.hasNext()){
		out.print(',');
	}


}


     
     //======================================================================================
     
     
     //print the properties along with their type
     
     while(properties.hasNext()){
    	 OntProperty k;
    	 k = properties.next();
    	   	    	 
    	
    	 int spc;
    	 
    	 // provide indentation
    	 for(spc = 0; spc<depth+1;spc++){
    		 out.print("   ");
    		 //out.print(' ');
    	 }
    	 
    	 out.println('{' + "id: " + id_number++ + ','+ "text:" + '"' + ShortForm(k.toString())+'"'+ '}');
    	 //out.println(properties.next());
    	 if(properties.hasNext()){
    		 out.print(',');
    	 }
    	 
     }
     if(!properties.hasNext()){
    	 out.print("]},");
    	 //out.print(Pop());
     }
          
     
     // recurse to the next level down
     if (cls.canAs( OntClass.class )  &&  !occurs.contains( cls )) {
         for (Iterator<OntClass> i = cls.listSubClasses( true );  i.hasNext(); ) {
             OntClass sub = i.next();
             //System.out.println(" How are you guys ");
             																								
             // we push this expression on the occurs list before we recurse
             occurs.add( cls );
             showClass( out, sub, occurs, depth + 1 );
             occurs.remove( cls );
         }
     }
 }

 public void showIndividuals(OntModel model){
	 Iterator individuals = model.listIndividuals();
	 while(individuals.hasNext()){
		 Individual individual = (Individual) individuals.next();
		 System.out.println(individual);
	 }
 }

 /**
  * <p>Render a description of the given class to the given output stream.</p>
  * @param out A print stream to write to
  * @param c The class to render
  */
 public void renderClassDescription( PrintStream out, OntClass c, int depth ) {
     indent( out, depth );

     if (c.isRestriction()) {
         renderRestriction( out, c.as( Restriction.class ) );
     }
     
     else {
         if (!c.isAnon()) {
             out.print('{' + "id : " + id_number++ + ',' + "text :" );
             renderURI( out, c.getModel(), c.getURI() );
             out.print( ' ' );
            // out.print(c.isClass());
             
         }
         
        
         else {
             renderAnonymous( out, c, "class" );
         }
              	 
         
     }
 }

 /**
  * <p>Handle the case of rendering a restriction.</p>
  * @param out The print stream to write to
  * @param r The restriction to render
  */
 protected void renderRestriction( PrintStream out, Restriction r ) {
     if (!r.isAnon()) {
         out.print( "Restriction " );
         renderURI( out, r.getModel(), r.getURI() );
     }
     else {
         renderAnonymous( out, r, "restriction" );
     }

     out.print( " on property " );
     renderURI( out, r.getModel(), r.getOnProperty().getURI() );
 }

 /** Render a URI */
 protected void renderURI( PrintStream out, PrefixMapping prefixes, String uri ) {
     String modified_str = prefixes.shortForm(uri);
	 out.print('"' + modified_str.split(":")[1] + '"');
     //out.print("");
 }

 /** Render an anonymous class or restriction */
 protected void renderAnonymous( PrintStream out, Resource anon, String name ) {
     String anonID = m_anonIDs.get( anon.getId() );
     if (anonID == null) {
         anonID = "a-" + m_anonCount++;
         m_anonIDs.put( anon.getId(), anonID );
     }

     out.print( "Anonymous ");
     out.print( name );
     out.print( " with ID " );
     out.print( anonID );
 }

 /** Generate the indentation */
 protected void indent( PrintStream out, int depth ) {
     for (int i = 0;  i < depth; i++) {
         out.print( "  " );
     }
 }
}


 //==============================================================================
 // Inner class definitions
 //==============================================================================

