package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Utilities {
    public static List<String> getInputAsStringList(Object obj) {
        try(Scanner s = new Scanner(new File(obj.getClass().getResource("input.txt").getPath())).useDelimiter(System.lineSeparator())) {
            List<String> list = new ArrayList<String>();
            while (s.hasNext()){
                list.add(s.next());
            }
            s.close();
            return list;
        }
        catch (FileNotFoundException e) {
            System.out.println("cant find it bro");
        }
        return null;
    }

    public static List<Integer> getInputAsIntegerList(Object obj) {
        return getInputAsStringList(obj).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
    }
}
