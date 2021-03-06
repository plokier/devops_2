package devops.rrce.projet.maven_project;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;



public class DataframeTest {
	
		String[] labels;
		ArrayList<String> l1;
		ArrayList<Integer> l2;
		ArrayList<Object> l3;
		ArrayList<Float> l4;
		ArrayList<Object> l5;
		ArrayList<Object> l6;
		ArrayList<Integer> l7;
		@Before
	    public void init() {
			labels = new String[]{ "MyString", "MyInteger", "MyFloat" };
			l1 = new ArrayList<String>(Arrays.asList("oui", "non", "peut-etre"));
			l2 = new ArrayList<Integer>(Arrays.asList(0, 1, 2));
			l3 = new ArrayList<Object>(Arrays.asList("oui", 4, 1.2f));
			l5 = new ArrayList<Object>(Arrays.asList(4, 5, 1.2f));
			l6 = new ArrayList<Object>(Arrays.asList(1.2f, 6.9f, "AH"));
			l4 = new ArrayList<Float>(Arrays.asList(1.1f, 1.3f, 1.2f));
			l7 = new ArrayList<Integer>(Arrays.asList(0, 2));
	    }
		@Test
	    public void testNumberColumns()throws Exception {
				Dataframe dt = new Dataframe(labels,l1,l2,l4);
				assertEquals(dt.getColumnSize(),3);
	    }
		
		@Test(expected = TypeCheckingException.class)
		public void testTypeCheckingException()throws Exception {
			Dataframe dt = new Dataframe(labels,l1,l2,l3);
		}
		
		@Test(expected = TypeCheckingException.class)
		public void testTypeCheckingException2()throws Exception {
			Dataframe dt = new Dataframe(labels,l1,l2,l5);
		}
		
		@Test(expected = TypeCheckingException.class)
		public void testTypeCheckingException3()throws Exception {
			Dataframe dt = new Dataframe(labels,l1,l2,l6);
		}
		
		@Test(expected = VectorSizeException.class)
		public void testVectorSizeException_Column()throws Exception {
			Dataframe dt = new Dataframe("Input_files/input5.csv");
		}
		
		@Test(expected = VectorSizeException.class)
		public void testVectorSizeException_Line()throws Exception {
			Dataframe dt = new Dataframe(labels,l1,l7,l4);
		}
		
		@Test
		public void testNumberColumnscsv()throws Exception {
				Dataframe dt = new Dataframe("Input_files/input1.csv");
				assertEquals(dt.getColumnSize(),3);
	    }
		@Test
		public void testData() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input1.csv");
			assertEquals(dt.getColumn(0).getElement(1),"non");
			assertEquals(dt.getColumn(2).getElement(2),"c");
		}
		@Test
		public void testfirstlines() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input2.csv");
			assertEquals(dt.printFirstLines(3),3);	
		}
		@Test
		public void testlastlines() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input2.csv");
			assertEquals(dt.printLastLines(2),2);	
		}
		@Test
		public void testfirstlines2() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input2.csv");
			assertEquals(dt.printFirstLines(70),7);	
		}
		@Test
		public void testlastlines2() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input2.csv");
			assertEquals(dt.printLastLines(70),7);	
		}
		@Test
		public void getcolumnstest() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input2.csv");
			assertEquals(dt.getColumnSize(),3);	
		}
		
		@Test
		public void printalltest() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input2.csv");
			assertEquals(dt.printall(),8);	
		}
		
		@Test
		public void getlinesizetest() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input2.csv");
			assertEquals(dt.getLineSize(),7);	
		}
		
		@Test
		public void dataframetest() throws Exception{
			ArrayList<String> liste1 = new ArrayList<String>();
			liste1.add("Jean");
			liste1.add("Paul");
			liste1.add("Arnaud");
			ArrayList<Integer> liste2 = new ArrayList<Integer>();
			liste2.add(18);
			liste2.add(22);
			liste2.add(23);
			String labels[] = {"Nom","Age"};
			Dataframe df = new Dataframe(labels,liste1,liste2);
			assertEquals(df.columns.size(),2);
		}
		
		@Test(expected = TypeCheckingException.class)
		public void TypeCheckingException() throws Exception {
				Dataframe dt;
				String labels[] = {"Nom Du Dataframe"};
				dt = new Dataframe("Input_files/input2.csv");
				ArrayList<Dataframe> dfarray = new ArrayList<Dataframe>();
				dfarray.add(dt);
				Dataframe dtdt = new Dataframe(labels,dfarray);
			
		}
		
		@Test(expected = TypeCheckingException.class)
		public void testTypeCheckingExceptioncsv() throws Exception{
				Dataframe dt = new Dataframe("Input_files/input3.csv");		
		}
		
		@Test(expected = TypeCheckingException.class)
		public void testTypeCheckingExceptioncsv2() throws Exception{
				Dataframe dt = new Dataframe("Input_files/input6.csv");		
		}
		
		@Test(expected = TypeCheckingException.class)
		public void testTypeCheckingExceptioncsv3() throws Exception{
				Dataframe dt = new Dataframe("Input_files/input7.csv");		
		}
		
		@Test
		public void selectColumn_ColumnSize() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input4.csv");
			Dataframe dt2 = dt.selectColumns("MyString","MyFloat");
			assertEquals(dt2.getColumnSize(),2);
		}
		
		@Test
		public void selectColumn_LineSize() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input4.csv");
			Dataframe dt2 = dt.selectColumns("MyInteger","MyFloat");
			assertEquals(dt2.getLineSize(),7);
		}
		
		@Test
		public void meanstestString() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input4.csv");
			assertEquals(dt.means()[0],5.0,0.01);			
		}
		
		@Test
		public void meanstestInt() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input4.csv");
			assertEquals(dt.means()[1],3.0,0.01);			
		}
		
		@Test
		public void meanstestFloat() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input4.csv");
			assertEquals(dt.means()[2],4.4,0.01);				
		}
		
		@Test 
		public void maxtestString() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input4.csv");
			assertEquals(dt.max()[0],"peut-etre");
		}
		
		@Test 
		public void maxtestInt() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input4.csv");
			assertEquals(dt.max()[1],6);
		}
		
		@Test 
		public void maxtestFloat() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input4.csv");
			assertEquals(dt.max()[2],7.7f);
		}
		
		@Test
		public void mintestString() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input4.csv");
			assertEquals(dt.min()[0],"oui");
		}
		
		@Test
		public void mintestInt() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input4.csv");
			assertEquals(dt.min()[1],0);
		}
		
		@Test
		public void mintestFloat() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input4.csv");
			assertEquals(dt.min()[2],1.1f);
		}
		
		@Test
		public void export() throws Exception{
			
			Dataframe dt = new Dataframe("Input_files/input2.csv");					
			dt.export("./result.csv");
	        BufferedInputStream fis1 = new BufferedInputStream(new FileInputStream("Input_files/input2.csv"));
	        BufferedInputStream fis2 = new BufferedInputStream(new FileInputStream("./result.csv"));
	        int b1 = 0, b2 = 0, pos = 1;
	        Boolean bool = true;
	        while (b1 != -1 && b2 != -1) {
	            if (b1 != b2) {
	                System.out.println("Files differ at position " + pos);
	                bool = false;
	            } 
	            pos++;
	            b1 = fis1.read();
	            b2 = fis2.read();
	        }
			assertEquals(bool,true);
		}
		
		
		@Test
		public void selectlinesizetest() throws Exception{
			Dataframe dt = new Dataframe("Input_files/input2.csv");	
			Dataframe df = dt.selectLines(1);
			assertEquals(df.getColumn(0).getLabel(),"MyString");
		}
		
		
		
}
