package rpc.Server;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import chord.DFS;
import chord.RemoteInputFileStream;
import model.Music;

public class InitializeMusic {
	
	DFS dfs;
	
	public InitializeMusic(DFS dfs) {
		this.dfs = dfs;
		
	}
	
	

public void init () throws Exception {
    Gson gson = new Gson();

    try (RemoteInputFileStream in = new RemoteInputFileStream("src/files/music.json", false) ){


        in.connect();

        Reader targetReader = new InputStreamReader(in);

        JsonReader jreader = new JsonReader(targetReader);

        Music[] music = gson.fromJson(jreader, Music[].class);

        Music[] partial= new Music[1000];



        int start = 0;

        Charset utf8 = StandardCharsets.UTF_8;

        for (int i = 0; i < music.length / 1000; i++) {

            try (Writer writer = new BufferedWriter(

                    new OutputStreamWriter(new FileOutputStream("music" + (i + 1) + ".json"), utf8)

                    

            )) {

                System.arraycopy(music, start, partial, 0, 1000);

                start += 1000;


                writer.write(gson.toJson(partial));

            }
            
//            String fileLocation = System.getProperty("user.dir") + "/src/files/" + "music" + Integer.toString((i + 1)) + ".json";
//            System.out.println(fileLocation);
//            dfs.append("music", new RemoteInputFileStream(fileLocation));
//            System.out.println("Added music");

        }

        System.out.println("Finished partitioning music.json!");
        
        

    } catch (IOException e) {

        System.out.println(e.getMessage());

    }
    
    
    
    

    
    





}


}
