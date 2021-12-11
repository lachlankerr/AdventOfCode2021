package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Input {
    /**
     * Converts the input file to a string list
     * @precondition Assumes input.txt is a file in the calling classes package
     * @param obj The calling classes object instance, used to get the correct input.txt
     * @return The input file in a string list
     */
    public static List<String> getAsStringList(Object obj) {
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

    /**
     * Converts the input file to an integer list
     * @precondition Assumes input.txt is a file in the calling classes package
     * @param obj The calling classes object instance, used to get the correct input.txt
     * @return The input file in an integer list
     */
    public static List<Integer> getAsIntegerList(Object obj) {
        return getAsStringList(obj).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
    }
}

