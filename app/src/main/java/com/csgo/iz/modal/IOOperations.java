package com.csgo.iz.modal;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Yusuf on 11/12/2015.
 */
public class IOOperations {
    public static final String USERSTATEFILE = "userStatsFile.txt";
    public static  final String USERPROFILEFILE = "userProfileFile.txt";
    public static  final String USERACHIEVEMENTFILE = "userAchievementFile.txt";
    private Context context;
    public IOOperations(Context context){
        this.context = context;
    }
    public void writeToFile(String fileName, String json){
        try{
            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(fileName,Context.MODE_PRIVATE));
            writer.write(json);
            writer.close();

        }catch(IOException e){
            e.getStackTrace();
        }
    }
    public String readFile(String fileName){
        try{
            InputStream inputStream = context.openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line ="";
            String data = "";
            while((line= reader.readLine())!=null){
                data += line;
            }
            inputStream.close();
            reader.close();
            return data;
        }catch(IOException e){
            e.getStackTrace();
        }
        return null;
    }

}
