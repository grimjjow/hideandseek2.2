import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public abstract class Buffer {
    File file;


    public Buffer() {
    }

    // Creates a new FileReader, given the
    // file.
    public Buffer(File file){
        this.file = file;
    };


    protected static void log(Object msg){
        System.out.println(String.valueOf(msg));
    }

    public File getFile() {
        return file;
    }

}





