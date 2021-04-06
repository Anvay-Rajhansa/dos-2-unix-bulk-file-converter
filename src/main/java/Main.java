import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            return;
        }

        File inputDirectory = new File(args[0]);

        if (!inputDirectory.exists() && !inputDirectory.isDirectory()) {
            return;
        }

        List<File> fileList = new ArrayList<>();
        listAllShellFiles(inputDirectory, fileList);

        if (fileList.size() == 0) {
            return;
        }

        System.out.println("Found total " + fileList.size() + " no. of shell files in given directory.");

        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(0);

        int failureCount = 0;

        for (File file : fileList) {
            CommandLine commandLine = CommandLine.parse("dos2unix.exe " + file.getAbsolutePath());
            try {
                int exitValue = executor.execute(commandLine);
                if (exitValue != 0) {
                    failureCount++;
                }
            } catch (Exception ex) {
                failureCount++;
            }
        }

        System.out.println("Successfully parse no. of files : " + (fileList.size() - failureCount));
        System.out.println("Failure in parse no. of file : " + failureCount);
    }

    private static void listAllShellFiles(File inputDirectory, List<File> fileList) {
        for (File file : inputDirectory.listFiles()) {
            if (file.isFile() && isShellFile(file)) {
                fileList.add(file);
            } else if (file.isDirectory()) {
                listAllShellFiles(file, fileList);
            }
        }
    }

    private static boolean isShellFile(File file) {
        return file.getName().endsWith(".sh");
    }
}
