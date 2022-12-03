import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Sorszuro2 {
    public static void main(String[] args) throws Exception {
        BufferedReader in;
        PrintWriter out;
        String line;
        String input = null;
        String output = null;
        String pattern = "";

        boolean gin = false;
        boolean gout = false;

        for (int i = 0; i < args.length; i++) {
            if ((i + 1 < args.length) && args[i].equals("-i")) {
                i++;
                input = args[i];
            } else if ((i + 1 < args.length) && args[i].equals("-o")) {
                i++;
                output = args[i];
            } else if ((i + 1 < args.length) && args[i].equals("-p")) {
                i++;
                pattern = args[i];
            } else if ((i + 1 < args.length) && args[i].equals("-gi")) {
                i++;
                input = args[i];
                gin = true;
            } else if ((i + 1 < args.length) && args[i].equals("-go")) {
                i++;
                output = args[i];
                gout = true;
            }
        }

        if(input != null) {
            System.out.println("Input File: " + input);
            String wd = System.getProperty("user.dir");
            in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(wd + "\\" + input))));
        } else {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        if(output != null) {
            System.out.println("Output File: " + output);
            out = new PrintWriter(new File(output));
        } else {
            out = new PrintWriter(System.out);
        }

        while ((line = in.readLine()) != null) {
            if(line.matches("(.*)" + pattern + "(.*)")) out.println(line);
        }

        if (output != null && gout) {
            String subStr = output.replace(".gz", "");
            gzipOut(subStr, output);

            String wd = System.getProperty("user.dir");
            File toDel = new File(wd + "\\" + subStr);
            if (toDel.delete()) {
                System.out.println("Succesfully deleted the file");
            } else {
                System.out.println("Failed to delete the file");
            }
        }

        out.close();
        in.close();
    }

    public static void gzipOut(String fileToGzip, String outputFileName) throws Exception {
        byte[] buffer = new byte[1024];
        String wd = System.getProperty("user.dir");

        GZIPOutputStream gos = new GZIPOutputStream(new FileOutputStream(wd + "\\" + outputFileName));
        FileInputStream fis = new FileInputStream(wd + "\\" + fileToGzip);

        int length;
        while ((length = fis.read(buffer)) > 0) {
            gos.write(buffer, 0, length);
        }

        fis.close();
        gos.finish();
        gos.close();
    }

}
