package rpc.Server;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import chord.DFS;
import chord.RemoteInputFileStream;


/**
 *
 * @author sovathana
 */
public class ServerModule extends Application{
    
    
    public static final int PORT = 3000;
    private static Dispatcher dispatcher;
    
    private final DatagramSocket socket;
    private byte[] buf = new byte[15000];
  //  private DatagramPacket request;
    private DatagramPacket reply;
    
    private DatagramPacket packetToBeReceived;
    private DatagramPacket packetToBeSent;
    private boolean currentlyRunning;
    private byte[] messageBuffer =null;
//	private Dispatcher dispatcher;
    
    private ArrayList<String> reqHistory;
    
    
    static final int DFS_PORT = 2000;
    public static DFS dfs;
    InitializeMusic mu = new InitializeMusic(dfs);
    
    
    
    public ServerModule() throws SocketException {
        socket = new DatagramSocket(PORT);
        reqHistory = new ArrayList<>();
        dispatcher = new Dispatcher();
        
        try{
            //starting the first peer
            dfs = new DFS(DFS_PORT);
            registerDispatchers();
           // creating 4 more peers and join the chord
            int join_port = DFS_PORT + 1;
            
//            for (int i = 0; i < 4; i++) {
//                DFS newDFS = new DFS(join_port);
//                newDFS.join("127.0.0.1", DFS_PORT);
//                join_port++;
//            }  
            
            
            // Add user.json to chord if not exist
            String userMetaFile = "user";
            String musicMetaFile = "music";
            String dfsList = dfs.lists();
            

            
//            File temp = new File("user.json");
//            boolean exists = temp.exists();
//            
//            System.out.println("Temp file exists : " + exists);
//            System.out.println("CWD Path : " + System.getProperty("user.dir") + "/src/files/user.json");
            
            
//            	System.out.println(">>>>>>>>>>>>>> path: " + ServerModule.class.getResource("user.json").getPath());
//            	
//            }catch(Exception e){
//                e.printStackTrace();
//            }
            
            
            if(!dfsList.contains(userMetaFile)) {
//            	dfs.delete();
                dfs.create(userMetaFile);
                dfs.append(userMetaFile, new RemoteInputFileStream(System.getProperty("user.dir") + "/src/files/users.json"));
  
            }
            
            if(!dfsList.contains(musicMetaFile)) {
            	System.out.println("Initial Print");
            	dfs.create(musicMetaFile);
            	mu.init();
            	for (int i = 1; i < 11; i++ ) {
                dfs.append(musicMetaFile, new RemoteInputFileStream(System.getProperty("user.dir") + "/src/files/music" + Integer.toString(i) + ".json"));
            	}
            	}
////            
      
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        registerDispatchers();
    }
    
    public void run() throws IOException{
        
        
        System.out.println("Server is listening on PORT: " + PORT);
        
        while(true) {
            byte[] veryBuff = new byte[15000];	
            DatagramPacket request = new DatagramPacket(veryBuff, veryBuff.length);
            socket.receive(request);
            String reqString = (new String(request.getData())).trim();
            System.out.println("Received a Request from " + request.getAddress());
            System.out.println("Request: " + reqString);
            
            if(!reqHistory.contains(reqString)){
                reqHistory.add(reqString);
                
                new Thread (new Runnable() {   
                    @Override
                    public void run() {
                        try {
    //                        String ret = dispatcher.dispatch(String.valueOf(request.getData()));
    //                        reply = new DatagramPacket(ret.getBytes(), ret.length(),
    //                                request.getAddress(), request.getPort());
                            String receivedMessage = new String(request.getData());
                            //JsonObject requestAsJsonObject = new JsonParser().parse(receivedMessage).getAsJsonObject();
                            //buf = dispatcher.dispatch(requestAsJsonObject.toString()).getBytes();
                            String res =  dispatcher.dispatch(receivedMessage.trim());
    //                       Dispatcher dis = new Dispatcher();
    //                       String res = dis.dispatch(receivedMessage.trim());
                                        System.out.println("Dispatcher called");
                            byte buff [] = "This is the response from server".getBytes();
                            int length = buff.length;
                            byte[] handle = res.getBytes();
                            reply = new DatagramPacket(handle, handle.length,
                                    request.getAddress(), request.getPort());
                            //String ret = dispatcher.dispatch(String.valueOf(request.getData()));
                            System.out.println("handle >>> " + new String(handle));                                               
                            socket.send(reply);
                            reqHistory.remove(reqString);
                            System.out.println("Reply is sent.");
                        		
                        } catch (IOException ex) {
                            Logger.getLogger(ServerModule.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }).start();
            }
            
            
            
       } 
    }
    
    
    
    private void registerDispatchers(){
        SongDispatcher songDispatcher = new SongDispatcher(this.dfs);
        dispatcher.registerObject(songDispatcher, "SongServices");
        LoginDispatcher userDispatcher = new LoginDispatcher(this.dfs);
        dispatcher.registerObject(userDispatcher, "LoginServices");
        RegisterDispatcher registerDispatcher = new RegisterDispatcher(this.dfs);
        dispatcher.registerObject(registerDispatcher, "RegisterServices");
        PlaylistDispatcher playlistDispatcher = new PlaylistDispatcher(this.dfs);
        dispatcher.registerObject(playlistDispatcher, "PlaylistServices");
        MusicDispatcher musicDispatcher = new MusicDispatcher(this.dfs);
        dispatcher.registerObject(musicDispatcher, "MusicServices");
    }
    
    
    
    public static void main(String[] args) throws SocketException, IOException {
        
        System.out.println("Server is listening on PORT: " + PORT);
        new ServerModule().run();
        
        
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//        public byte[] startDispatcher(JsonObject request) throws ParseException {
//		System.out.println("Dispatcher called");
//		dispatcher = new Dispatcher();
//		return (dispatcher.dispatch(request.toString())).getBytes();
//	}
//    public byte[] startDispatcher(JsonObject request) throws ParseException {
//            System.out.println("Dispatcher called");
//		dispatcher = new Dispatcher();
//		return (dispatcher.dispatch(request.toString())).getBytes();
//    }
    
}