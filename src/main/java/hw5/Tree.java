package hw5;

import java.io.File;

public class Tree {

    public static void print(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();
        int subDirTotal = 0;
        int subFileTotal = 0;
        assert files != null;
        for (File value : files) {
            if (value.isDirectory())
                subDirTotal++;
            else if (value.isFile())
                subFileTotal++;
        }

        int subItemCounter = 0;
        for (File value : files) {
            subItemCounter++;
            if (value.isDirectory()) {
                print(value, indent, subItemCounter == (subDirTotal + subFileTotal));
            } else if (value.isFile()) {
                System.out.print(indent);
                if (subItemCounter == (subDirTotal + subFileTotal)) {
                    System.out.print("└─");
                } else {
                    System.out.print("├─");
                }
                System.out.println(value.getName());
            }
        }
    }
}
