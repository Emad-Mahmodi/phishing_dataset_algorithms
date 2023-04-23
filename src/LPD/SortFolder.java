package LPD;

import org.apache.commons.io.comparator.LastModifiedFileComparator;

import java.io.File;
import java.util.Arrays;
 
public class SortFolder {
    public static void main(String[] args) {
        File dir = new File("E:\\Wamp\\wamp64\\www\\Phish\\dlweb_phish");
        File[] files = dir.listFiles();
 
        Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_COMPARATOR);
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            System.out.printf("File %s - %2$tm %2$te,%2$tY%n= ", file.getName(),
                    file.lastModified());
        }
 
        Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            System.out.printf("File %s - %2$tm %2$te,%2$tY%n= ", file.getName(),
                    file.lastModified());
        }
    }
}