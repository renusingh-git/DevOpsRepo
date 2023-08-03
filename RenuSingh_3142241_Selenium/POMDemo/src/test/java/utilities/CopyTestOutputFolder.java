package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import tests.BaseTest;

public class CopyTestOutputFolder extends BaseTest {

	public static void perform_TestOutput_Backup() throws IOException {
		// Creating a Folder with Timestamp to Copy the results of the Test Output
		String Foldername = new SimpleDateFormat("dd-MMM-YYYY_hh.mm.ss").format(new Date());
		// Path of the Source (test-output) folder
		File srcFolder = new File("./test-output");
		// Path of the Destination folder

		File destFolder = new File(
				"C:\\Users\\renu2143\\Desktop\\SeleniumWorkspace\\POMDemo\\TestExecution_ArchiveResults\\"
						+ "Archived test results_" + Foldername);
		File currentFolder = new File(
				"C:\\Users\\renu2143\\Desktop\\SeleniumWorkspace\\POMDemo\\TestExecution_CurrentResults\\"
						+ "Current test results_" + Foldername);

		// Check for Source folder existence
		if (!srcFolder.exists()) {
			System.out.println("Directory does not exist at the mentioned location.");
			System.exit(0);
		} else {
			try {
				// Clearing the Current Test Result Folder: TestExecution_CurrentResults
				// FileUtils.cleanDirectory(new File("Current test results/"));
				System.out.println("\nClearing the Current Test Result 'TestExecution_CurrentResults' folder.");
				FileUtils.cleanDirectory(new File("TestExecution_CurrentResults/"));
				System.out.println("Deleted the previous results in 'TestExecution_CurrentResults' folder.");

				// Call for performing Copy operation.
				System.out.println("\nCoping the 'test-output' in the 'TestExecution_ArchiveResults' folder.");
				copy_Folder(srcFolder, destFolder);
				System.out.println("\nCoping the 'test-output' in the 'TestExecution_CurrentResults' folder.");
				copy_Folder(srcFolder, currentFolder);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	public static void copy_Folder(File src, File dest) throws IOException {
		// Copy Folder
		if (src.isDirectory()) {
			// If the Destination directory not exists, then create it
			if (!dest.exists()) {
				dest.mkdir();
				System.out.println("Directory copied from " + src + " to " + dest);
			}
			// list all the directory contents
			String files[] = src.list();

			for (String file : files) {
				// Construct the Source (src) and Destination (dest) file structure
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				// Recursive copy
				copy_Folder(srcFile, destFile);
			}

		} else {
			// If the Destination folder exists, then perform copy operation
			// Using bytes stream to support all file types
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];
			int length;
			// Copying the file content in bytes
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			// Set the folder name and format the date time
			String folderName = "Current test results";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
			String dateTime = LocalDateTime.now().format(formatter);
			String folderPath = folderName + "/" + dateTime;
		}
	}
}
