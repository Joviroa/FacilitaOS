package app.fos.utils;

import java.io.File;

public class FileUtils {

	// Obtém um arquivo com base no caminho fornecido
    public static File getFile(String path) {
        return new File(path);
    }

    // Renomeia um arquivo para um novo nome
    public static boolean renameFile(String oldName, String newName) {
        File oldFile = new File(oldName);
        File newFile = new File(newName);

        if (oldFile.exists()) {
            return oldFile.renameTo(newFile);
        } else {
            System.err.println("Arquivo não encontrado: " + oldName);
            return false;
        }
    }

    // Verifica se um arquivo existe
    public static boolean fileExists(String path) {
        File file = new File(path);
        return file.exists();
    }
}
