import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        Functions func = new Functions();

        String wd = System.getProperty("user.dir");
        File workingDir = new File(wd);

        while ((line = br.readLine()) != null) {
            String data[] = line.split(" ");
            if (data[0].equals("exit")) {
                func.exit();
            } else if (data[0].equals("pwd")) {
                func.pwd(workingDir);
            } else if (data[0].equals("ls")) {
                if (data.length > 1) {
                    func.ls(workingDir, data[1]);
                } else {
                    func.ls(workingDir, "");
                }
            } else if (data[0].equals("cd")) {
                workingDir = func.cd(workingDir, data[1]);
            } else if (data[0].equals("length")) {
                func.length(workingDir, data[1]);
            } else if (data[0].equals("grep")) {
                if (data.length > 2) {
                    func.grep(workingDir, data[1], data[2]);
                } else {
                    System.out.println("Hiba, nincs elég argumentum");
                }
            } else if (data[0].equals("tail")) {
                func.tail(workingDir, data);
            }
        }
        br.close();
    }
}
