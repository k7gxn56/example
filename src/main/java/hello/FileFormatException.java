package hello;

import java.io.FileNotFoundException;

public class FileFormatException extends FileNotFoundException {
    public FileFormatException(){}
    public FileFormatException(String s){
        super(s);
    }
}
