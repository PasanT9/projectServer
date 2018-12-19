import java.io.*;
import java.util.*;

class StockDataBase { 

    private Map<String, StockItem> stockList; 
    private String [] fields; 

    public StockDataBase(String csvFile, String key, String val1,String val2)  { 
	FileReader fileRd=null; 
	BufferedReader reader=null; 

	try { 
	    fileRd = new FileReader(csvFile); 
	    reader = new BufferedReader(fileRd); 

	    /* read the CSV file's first line which has 
	     * the names of fields. 
	     */

	    String header = reader.readLine(); 
	    fields = header.split(",");// keep field names 

	    // find where the key and the value are 
	    int keyIndex = findIndexOf(key);
	    int val1Index = findIndexOf(val1);
	    int val2Index = findIndexOf(val2);

	    if(keyIndex == -1 || val1Index == -1 || val2Index == -1) 
		{	
			throw new IOException("CSV file does not have data");
		}
	    // note how you can throw a new exception 

	    // get a new hash map
	    stockList = new HashMap<String, StockItem>(); 

	    /* read each line, getting it split by , 
	     * use the indexes to get the key and value 
	     */
	    String [] tokens; 
	    for(String line = reader.readLine(); 
		line != null; 
		line = reader.readLine()) { 
		tokens = line.split(",");
		StockItem entree = new StockItem(tokens[val1Index], Double.parseDouble(tokens[val2Index]));
		stockList.put(tokens[keyIndex], entree); 
	    }
	    
	    if(fileRd != null) fileRd.close();
	    if(reader != null) reader.close();
	    
	    // I can catch more than one exceptions 
	} catch (IOException e) { 
	    System.out.println(e);
	    System.exit(-1); 
	} catch (ArrayIndexOutOfBoundsException e) { 
	    System.out.println("Malformed CSV file");
	    System.out.println(e);
	}
    }

    private int findIndexOf(String key) { 
	for(int i=0; i < fields.length; i++) 
	    if(fields[i].equals(key)) return i; 
	return -1; 
    }

    public boolean findSymbol(String key){
	return stockList.containsKey(key);
    }
    
    public double findPrice(String key){
	return stockList.get(key).price;
    }

}
	    
