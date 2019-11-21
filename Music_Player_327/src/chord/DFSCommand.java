package chord;

import java.io.*;
import com.google.gson.*;
import com.google.gson.stream.*;
import model.Music;

//import model.Music;




public class DFSCommand
{
    DFS dfs;
        
    public DFSCommand(int p, int portToJoin) throws Exception {
        dfs = new DFS(p);
        
        if (portToJoin > 0)
        {
            System.out.println("Joining "+ portToJoin);
            dfs.join("127.0.0.1", portToJoin);            
        }
        
        BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
        String line = buffer.readLine();  
        while (!line.equals("quit"))
        {
            String[] result = line.split("\\s");
            if (result[0].equals("join")  && result.length > 1)
            {
                dfs.join("127.0.0.1", Integer.parseInt(result[1]));     
            }
            if (result[0].equals("print"))
            {
                dfs.print();     
            }
            if (result[0].equals("ls"))
            {
                dfs.lists();     
            }
            
            if (result[0].equals("leave"))
            {
                dfs.leave();     
            }
            if (result[0].equals("touch") && result.length > 1)
            {
                dfs.create(result[1]);
            }
            
            if (result[0].equals("delete") && result.length > 1)
            {
                dfs.delete(result[1]);
            }
            
            if (result[0].equals("read") && result.length > 2)
            {
                int pageNum = Integer.parseInt(result[2]);
                String fileName = result[1];
                RemoteInputFileStream input = dfs.read(fileName,pageNum);
                printStream(input);
            }
            
            if (result[0].equals("head"))
            {
                RemoteInputFileStream input = dfs.head(result[1]);
                printStream(input);
            }
            
            if (result[0].equals("tail"))
            {
                RemoteInputFileStream input = dfs.tail(result[1]);
                printStream(input);
            }
            
            
            if (result[0].equals("move") && result.length > 2)
            {
                dfs.move(result[1], result[2]);
            }
            
            if(result[0].equals("append") && result.length > 2) {
            	RemoteInputFileStream input = new RemoteInputFileStream(result[2]);
            	dfs.append(result[1], input);
            }
            
            if(result[0].equals("write") && result.length > 3) {
            	RemoteInputFileStream input = new RemoteInputFileStream(result[2]);
                int pageNum = Integer.parseInt(result[3]);
            	dfs.write(result[1], input, pageNum);
            }
            
            line=buffer.readLine();  
        }
        
            // User interface:
            // join, ls, touch, delete, read, tail, head, append, move
    }
    
        public static void printStream(RemoteInputFileStream fileStream) throws Exception{
        fileStream.connect();
        System.out.println();
        while(fileStream.available() > 0){
            System.out.print((char)fileStream.read());
        }
        System.out.println();
    }
    
    static public void main(String args[]) throws Exception
    {
        Gson gson = new Gson();
        RemoteInputFileStream in = new RemoteInputFileStream("music.json", false);
        in.connect();
        Reader targetReader = new InputStreamReader(in);
        JsonReader jreader = new  JsonReader(targetReader);
        Music[] music = gson.fromJson(jreader, Music[].class);
        
        if (args.length < 1 ) {
            throw new IllegalArgumentException("Parameter: <port> <portToJoin>");
        }
        if (args.length > 1 ) {
            DFSCommand dfsCommand=new DFSCommand(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        }
        else
        {
            DFSCommand dfsCommand=new DFSCommand( Integer.parseInt(args[0]), 0);
        }
     } 
}
