import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class Functions {
    protected void exit() {
        System.exit(0);
    }

    protected void pwd(File currDir) throws Exception {
        File[] folderLength = currDir.listFiles();
        System.out.println(currDir.getCanonicalPath() + " nr of files and subdirectories: " + folderLength.length);
    }

    protected void ls(File currDir, String param) throws Exception {
        File files[] = currDir.listFiles();
        if (param.equals("-l")) {
            for (int i = 0; i < files.length; i++) {
                System.out.print(files[i].getName());
                if (files[i].isFile()) System.out.println(" (f)" + "    " + files[i].length() + " bytes");
                else if (files[i].isDirectory()) System.out.println(" (d)" + "    " + files[i].length() + " bytes");
            }
        } else {
            for (int i = 0; i < files.length; i++) {
                System.out.println(files[i].getName());
            }
        }

    }

    protected File cd(File currDir, String cdTo) throws Exception {
        // We check if the path is to a file
        while (currDir.isFile()) {
            currDir = currDir.getParentFile();
        }
        if (cdTo.equals("..")) {
            File f1 = currDir.getParentFile();
            if (f1 != null)
                currDir = f1;
        } else {
            File f1 = new File(currDir.getCanonicalPath(), cdTo);
            if (f1 != null)
                currDir = f1;
        }
        return currDir;
    }

    protected void length(File currDir, String file) throws Exception {
        File f = new File(currDir.getCanonicalPath() + "\\" + file);
        if (f.isFile()) {
            System.out.println(f.length() + " bytes");
        } else {
            throw new Exception("File not found");
        }
    }

    protected void grep(File currDir, String pattern, String file) throws Exception {
        File f = new File(currDir + "\\" + file);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String s = null;

        while ((s = br.readLine()) != null) {
            if(s.matches("(.*)" + pattern + "(.*)")) System.out.println(s);
        }
        br.close();
    }

    protected  void tail(File currDir, String cmd[]) throws Exception {
        if (cmd.length < 2)
            System.out.println("Hiba");
        else {
            File source = new File(currDir, cmd[cmd.length - 1]);
            BufferedReader br = new BufferedReader(new FileReader(source));
            String s = null;
            LinkedList<String> list = new LinkedList<String>();
            int j = 0;
            while ((s = br.readLine()) != null) {
                list.add(s);
                j++;
            }
            br.close();
            list = reverseLinkedList(list);
            ListIterator<String> iter = list.listIterator();
            if (cmd[1].equals("-n")) {
                int i = 0;
                while (iter.hasNext() && (i < Integer.parseInt(cmd[2]))) {
                    System.out.println(iter.next());
                    i++;
                }
            } else {
                int i = 0;
                while (iter.hasNext() && (i < 10)) {
                    System.out.println(iter.next());
                    i++;
                }
            }
        }
    }

    //! Used for tail function
    public static LinkedList<String> reverseLinkedList(LinkedList<String> llist) {
        LinkedList<String> revLinkedList = new LinkedList<String>();
        for (int i = llist.size() - 1; i >= 0; i--) {
            revLinkedList.add(llist.get(i));
        }
        return revLinkedList;
    }
}
