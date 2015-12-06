import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.*;
public class helloworld{
	public static void main(String[] args) throws IOException {
		File file=new File("/var/www/html/semantic_web_forms/ontospy/test_file.txt");
        FileOutputStream fos=new FileOutputStream(file);
        PrintStream ps=new PrintStream(fos);
        System.setOut(ps);
		System.out.println("helloworld");
	}
}