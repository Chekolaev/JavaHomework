package org.example;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    ArrayList<Book> _books;
    private static File _file,_fileUpt;
    private static ObjectMapper _objectMapper;
    private static String _URI,_URIUpt;

    private static String _columnFormat = "%-40.40s";
    private static String _formatInfo = _columnFormat + "\t|\t";

    public static void main(String[] args) throws IOException, URISyntaxException {

        _URI = "bks.json";
        _URIUpt = "bksNew.json";

        _file = new File(Main.class.getClassLoader().getResource(_URI).toURI());
        _objectMapper = new ObjectMapper();

        ArrayList<Book> _Array = _GetBooks(_file,_objectMapper);
        _Print(_Array);

        String[] tag = {"50/50","Not_bad,not_good","For_free_time"};
        _Array.add(new Book("TestName", "TestAuthor", 1337,"TestIzd","TestGenre",tag));
        _fileUpt = new File(_URIUpt);

        JsonGenerator g = _objectMapper.getFactory().createGenerator(new FileOutputStream(_fileUpt));
        _objectMapper.writeValue(g, _Array);
        g.close();

        ArrayList<Book> _ArrayUpt = _GetBooks(_fileUpt,_objectMapper);
        _Print(_ArrayUpt);

        Map<String, List<Book>> _Group = _ArrayUpt.stream().collect(Collectors.groupingBy(w -> w.getAuthor()));
        _PrintGroup(_Group);

        _PrintGenre("Роман",_ArrayUpt);
        _PrintUnique(_ArrayUpt);
    }

    private static ArrayList<Book> _GetBooks(File _file, ObjectMapper _objectMapper) throws IOException {
        return _objectMapper.readValue(_file, new TypeReference<>(){});
    }
    private static void _Print(ArrayList<Book> _Array){
        System.out.print("\n\nНазвание:\t\t");
        for (Book emp:_Array
        ) {
            System.out.format(_formatInfo,emp.getName());
        }
        System.out.print("\nАвтор:\t\t\t");
        for (Book emp:_Array
        ) {
            System.out.format(_formatInfo, emp.getAuthor());
        }
        System.out.print("\nКол-во стр.:\t");
        for (Book emp:_Array
        ) {
            System.out.format(_formatInfo, emp.getPages());
        }
        System.out.print("\nЖанр:\t\t\t");
        for (Book emp:_Array
        ) {
            System.out.format(_formatInfo, emp.getGenre());
        }
        System.out.print("\nИздательство:\t");
        for (Book emp:_Array
        ) {
            System.out.format(_formatInfo, emp.getIzdat());
        }

    }
    private static void _PrintGroup(Map<String, List<Book>> _Map){
        for (Map.Entry<String, List<Book>> entry : _Map.entrySet())
        {
            System.out.println("\n\n"+entry.getKey() + ":");
            for (Book emp: entry.getValue())
            {
                System.out.print(emp.getName()+"\t\t");
            }
        }
    }
    private static void _PrintGenre(String _Genre, ArrayList<Book> _Array){
        System.out.println("\n\nЖанр: "+_Genre);
        for (Book emp:_Array
        ) {
            if(_Genre.equals(emp.getGenre()))
            {
                System.out.format(_formatInfo,emp.getName());
            }
        }
    }
    private static void _PrintUnique(ArrayList<Book> _Array){
        System.out.println("\n\nTags: ");
        HashSet<String> _out = new HashSet<>();
        for (Book emp: _Array) {
            for (String tag: emp.getTag()
            ) {
                _out.add(tag);
            }
        }// creates Iterator oblect.
        Iterator itr = _out.iterator();

        // check element is present or not. if not loop will
        // break.
        while (itr.hasNext()) {
            System.out.format(_formatInfo,itr.next());
        }
    }
}
