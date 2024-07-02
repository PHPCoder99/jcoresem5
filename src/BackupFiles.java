import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class BackupFiles {

    public static void createBackup(String sourceDir) {
        Path sourcePath = Paths.get(sourceDir);
        Path backupPath = Paths.get("backup");

        if (!Files.exists(backupPath)) {
            try {
                Files.createDirectory(backupPath);
            } catch (IOException e) {
                System.out.println("Ошибка при создании директории: " + e.getMessage());
                return;
            }
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(sourcePath)) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry)) {
                    Path dest = backupPath.resolve(entry.getFileName());
                    Files.copy(entry, dest, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при копировании файлов: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        createBackup("./");
    }
}
