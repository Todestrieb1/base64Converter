/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas64converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Base64;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Java FX module for "dialog" encoding/decoding files in BASE64
 * @author Pan-Kawia
 */
public class Base64FX {
    
    /**
     * Encodes selected file to BASE64, and then allow user to save it as *.txt
     * file. This method should be put in JavaFX GUI.
     * @param primaryStage
     * @throws IOException 
     */
    public static void EncoderWithDialog(Stage primaryStage) throws IOException {
        //Read file
        FileChooser openFile = new FileChooser();
        File fileToEncode = openFile.showOpenDialog(primaryStage);

        RandomAccessFile myFile = new RandomAccessFile(fileToEncode, "r");
        byte[] b = new byte[ (int)myFile.length() ];
        myFile.readFully(b);
        myFile.close();

        //Encode and save file
        FileChooser saveFile = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Base64 encoded file", "*.txt");
        saveFile.getExtensionFilters().add(extFilter);
        saveFile.setInitialFileName( fileToEncode.getName() );
        File file = saveFile.showSaveDialog(primaryStage);
                
        Base64.Encoder encoder = Base64.getEncoder();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write( encoder.encodeToString(b) );
        fileWriter.close();
    }
    
    /**
     * Decodes BASE64 *.txt file and allow user to save it as binary file.
     * This method should be put in JavaFX GUI.
     * @param primaryStage
     * @throws IOException 
     */
    public static void DecoderWithDialog(Stage primaryStage) throws IOException {
        FileChooser openFile = new FileChooser();
        File fileToDecode = openFile.showOpenDialog(primaryStage);
                
        BufferedReader reader = new BufferedReader( new FileReader(fileToDecode));
        StringBuilder stringBuilder = new StringBuilder();

        //Read BASE64 text file
        String line;
        while( ( line = reader.readLine() ) != null ) {
            stringBuilder.append( line );
        }
                
        String base64String = stringBuilder.toString();
        Base64.Decoder decoder = Base64.getDecoder();
                
        //Save decoded file
        FileChooser saveFile = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Decoded binary file", "*.bin");
        saveFile.getExtensionFilters().add(extFilter);
        saveFile.setInitialFileName( fileToDecode.getName() );
        File decodedFile = saveFile.showSaveDialog(primaryStage);
                
        FileOutputStream outputStream = new FileOutputStream(decodedFile);
        outputStream.write( decoder.decode(base64String) );
        outputStream.close();        
    }
    
}
