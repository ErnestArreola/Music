import java.rmi.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.math.BigInteger;
import java.security.*;
import com.google.gson.Gson;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import model.DateTime;


/* JSON Format

{"file":
  [
     {"name":"MyFile",
      "size":128000000,
      "pages":
      [
         {
            "guid":11,
            "size":64000000
         },
         {
            "guid":13,
            "size":64000000
         }
      ]
      }
   ]
} 
*/


public class DFS
{
    

    public class PagesJson
    {
        Long guid;
        Long size;
        String creationTimeStamp;
        String readTimeStamp;
        String writeTimeStamp;
        String referenceCount;
        String upperBound;
        String lowerBound;
                
                
        public PagesJson()
        {
            
        }
        
        
        public PagesJson(String text) {

		}

		public void setGuid(long guid) {
			this.guid = guid;
		}

		public Long getGuid() {
			return guid;
		}

		public void setSize(Long size) {
			this.size = size;
		}

		public Long getSize() {
			return size;
		}

		public void setCreationTimeStamp(String creationTS) {
			this.creationTimeStamp = creationTS;
		}

		public String getCreationTimeStap() {
			return creationTimeStamp;
		}

		public void setReadTimeStamp(String readTS) {
			this.readTimeStamp = readTS;
		}

		public String getReadTimeStamp() {
			return readTimeStamp;
		}

		public void setWriteTimeStamp(String writeTS) {
			this.writeTimeStamp = writeTS;
		}

		public String getWriteTimeStamp(String readTS) {
			return writeTimeStamp;
		}

		public void setReferenceCount(String referenceCount) {
			this.referenceCount = referenceCount;
		}

		public String getReferenceCount() {
			return referenceCount;
		}

		public void setUpperBound(String upperBound) {
			this.upperBound = upperBound;
		}

		public String getUpperBound() {
			return upperBound;
		}

		public void setLowerBound(String lowerBound) {
			this.lowerBound = lowerBound;
		}

		public String getLowerBound() {
			return lowerBound;
		}

		@Override
		public String toString() {
			String result = "GUID: " + guid + "\n" + "Size: " + size + "\n" + "Creation TimeStamp: " + creationTimeStamp
					+ "\n" + "Read Time: " + readTimeStamp + "\n" + "Write Time: " + writeTimeStamp + "\n"
					+ "Reference Count: " + referenceCount + "\n";
			return result;
		}

        // getters
        // setters
    };

    public class FileJson 
    {
		String name;

		Long size;
		String creationTimeStamp;
		String readTimeStamp;
		String writeTimeStamp;
		String referenceCount;
		ArrayList<PagesJson> pages;
        public FileJson()
        {
            
        }
        
        	public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setSize(Long size) {
			this.size = size;
		}

		public Long getSize() {
			return size;
		}

		public void setCreationTimeStamp(String creationTS) {
			this.creationTimeStamp = creationTS;
		}

		public String getCreationTimeStap() {
			return creationTimeStamp;
		}

		public void setReadTimeStamp(String readTS) {
			this.readTimeStamp = readTS;
		}

		public String getReadTimeStamp() {
			return readTimeStamp;
		}

		public void setWriteTimeStamp(String writeTS) {
			this.writeTimeStamp = writeTS;
		}

		public String getWriteTimeStamp(String readTS) {
			return writeTimeStamp;
		}

		public void setReferenceCount(String refCount) {
			this.referenceCount = refCount;
		}

		public String getReferenceCount() {
			return referenceCount;
		}

		public void setPages(ArrayList<PagesJson> pages) {
			this.pages = pages;
		}

		public ArrayList<PagesJson> getPages() {
			return this.pages;
		}

		@Override
		public String toString() {
			String result = "Name: " + name + "\n" + "Size: " + size + "\n" + "Creation TimeStamp: " + creationTimeStamp
					+ "\n" + "Read Time: " + readTimeStamp + "\n" + "Write Time: " + writeTimeStamp + "\n"
					+ "Reference Count: " + referenceCount + "\n" + "Pages: {\n";
			for (PagesJson tpages : pages) {
				result += (tpages.toString() + "\n");
			}
			result += "}\n";
			return result;
		}
        // getters
        // setters
    };
    
    public class FilesJson 
    {
		List<FileJson> files;


		public FilesJson() {

		}
		public void setFiles(ArrayList<FileJson> files) {
			this.files = files;
		}

		public FileJson getFileJsonAt(int i) {
			if (i < files.size()) {
				return files.get(i);
			}
			return new FileJson();
		}

		public List<FileJson> getFiles() {
			return files;
		}

		public int getSize() {
			return files.size();
		}

		@Override
		public String toString() {
			String str = "";
			for (FileJson j : files) {
				str += j.toString();
			}
			return str;
		}
                
                
    };
    
    
    int port;
    Chord  chord;
    
    
    private long md5(String objectName)
    {
        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(objectName.getBytes());
            BigInteger bigInt = new BigInteger(1,m.digest());
            return Math.abs(bigInt.longValue());
        }
        catch(NoSuchAlgorithmException e)
        {
                e.printStackTrace();
                
        }
        return 0;
    }
    
    
    
    public DFS(int port) throws Exception
    {
        
        
        this.port = port;
        long guid = md5("" + port);
        chord = new Chord(port, guid);
        Files.createDirectories(Paths.get(guid+"/repository"));
        Files.createDirectories(Paths.get(guid+"/tmp"));
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                chord.leave();
            }
        });
        
    }
    
  
/**
 * Join the chord
  *
 */
    public void join(String Ip, int port) throws Exception
    {
        chord.joinRing(Ip, port);
        chord.print();
    }
    
    
   /**
 * leave the chord
  *
 */ 
    public void leave() throws Exception
    {        
       chord.leave();
    }
  
   /**
 * print the status of the peer in the chord
  *
 */
    public void print() throws Exception
    {
        chord.print();
    }
    
/**
 * readMetaData read the metadata from the chord
  *
 */
    public FilesJson readMetaData() throws Exception
    {
        FilesJson filesJson = null;
        try {
            Gson gson = new Gson();
            long guid = md5("Metadata");

            System.out.println("GUID " + guid);
            ChordMessageInterface peer = chord.locateSuccessor(guid);
            RemoteInputFileStream metadataraw = peer.get(guid);
            metadataraw.connect();
            Scanner scan = new Scanner(metadataraw);
            scan.useDelimiter("\\A");
            String strMetaData = scan.next();
            System.out.println(strMetaData);
            filesJson= gson.fromJson(strMetaData, FilesJson.class);
        } catch (Exception ex)
        {
            filesJson = new FilesJson();
        }
        return filesJson;
    }
    
/**
 * writeMetaData write the metadata back to the chord
  *
 */
    public void writeMetaData(FilesJson filesJson) throws Exception
    {
        long guid = md5("Metadata");
        ChordMessageInterface peer = chord.locateSuccessor(guid);
        
        Gson gson = new Gson();
        peer.put(guid, gson.toJson(filesJson));
    }
   
/**
 * Change Name
  *
 */
    public void move(String oldName, String newName) throws Exception
    {
        // TODO:  Change the name in Metadata
        // Write Metadata
    }

  
/**
 * List the files in the system
  *
 * @param filename Name of the file
 */
	public String lists() throws Exception {
		Gson gson = new Gson();

		FilesJson allFiles = gson.fromJson("", FilesJson.class);
		// traverse the current files in the metadata
		for (int i = 0; i < allFiles.getFiles().size(); i++) {
			FileJson currentFile = allFiles.getFiles().get(i);
			System.out.println(currentFile.getName() + " " + currentFile.getSize());
			// traverse the pages in the current file
			for (int j = 0; j < currentFile.getPages().size(); j++) {
				PagesJson currentPage = currentFile.getPages().get(j);
				// System.out.println(currentPage.getGuid() + " " + currentPage.getSize());
			}
		}
		String listOfFiles = "";

		return listOfFiles;
	}

/**
 * create an empty file 
  *
 * @param filename Name of the file
 */
    public void create(String fileName) throws Exception
    {

	String formattedTS = DateTime.retrieveCurrentDate();

		// Set the creation, read, write time stamps accordingly
	FileJson newFile = new FileJson();
	newFile.setCreationTimeStamp(formattedTS);
	newFile.setReadTimeStamp(formattedTS);
	newFile.setWriteTimeStamp(formattedTS);

		// Add the file to the metadata
        FilesJson retrievedMetadata = this.readMetaData();
	retrievedMetadata.getFiles().add(newFile);
	this.writeMetaData(retrievedMetadata);
         // TODO: Create the file fileName by adding a new entry to the Metadata
        // Write Metadata

        
    }
    
/**
 * delete file 
  *
 * @param filename Name of the file
 */
    public void delete(String fileName) throws Exception
    {
		FilesJson retrievedMetadata = this.readMetaData();

		boolean fileFound = false;
		int index = 0;
		// traverse all the files in the metadata to see if the desired file is in the
		// list of files
		for (int i = 0; i < retrievedMetadata.getFiles().size(); i++) {
			if (retrievedMetadata.getFiles().get(i).equals(fileName)) {
				fileFound = true;
			}
			index++;
		}
		// if file has been found, then remove it from the list of files
		if (fileFound == true) {
			FileJson selectedFile = retrievedMetadata.getFiles().get(index);
			for (PagesJson page : selectedFile.getPages()) {
				long guid = page.getGuid();
				ChordMessageInterface peer = chord.locateSuccessor(guid);
				peer.delete(guid);
			}
			retrievedMetadata.getFiles().remove(index);
		}
		// update the meta data
		this.writeMetaData(retrievedMetadata);
     
        
    }
    
/**
 * Read block pageNumber of fileName 
  *
 * @param filename Name of the file
 * @param pageNumber number of block. 
 */
    public RemoteInputFileStream read(String fileName, int pageNumber) throws Exception
    {
        return null;
    }
    
 /**
 * Add a page to the file                
  *
 * @param filename Name of the file
 * @param data RemoteInputStream. 
 */
	public void append(String fileName, RemoteInputFileStream data) throws Exception {
                Gson gson = new Gson();
		FilesJson allFiles = this.readMetaData();
		FileJson foundFile = null;

		int index = 0;
		for (int i = 0; i < allFiles.getFiles().size(); i++) {
			if (allFiles.getFiles().get(i).getName().equals(fileName)) {
				foundFile = allFiles.getFiles().get(i);

				data.connect();
				Scanner scan = new Scanner(data);
				scan.useDelimiter("\\A");
				String strPageData = "";
				while (scan.hasNext()) {
					strPageData += scan.next();
				}

				// get the current DateTime
				Date currentDate = new Date();
				DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss a");
				String formattedTS = dateFormat.format(currentDate);

				PagesJson newPage = gson.fromJson(strPageData, PagesJson.class);
				foundFile.getPages().add(newPage);
				foundFile.setReadTimeStamp(formattedTS);
				foundFile.setWriteTimeStamp(formattedTS);
				allFiles.getFiles().set(i, foundFile);

				ChordMessageInterface peer = chord.locateSuccessor(newPage.getGuid());
				peer.put(newPage.getGuid(), gson.toJson(newPage));

				this.writeMetaData(allFiles);
				scan.close();
				break;

			}

		}
			}


    

    
}