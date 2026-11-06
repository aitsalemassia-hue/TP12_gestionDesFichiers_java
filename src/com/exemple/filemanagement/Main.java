package com.exemple.filemanagement;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class Main {

	// Lecture de fichier avec FileReader et BufferedReader

	public static void readFileWithBufferedReader(String filepath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);

			}
		} catch (IOException e) {
			System.out.println("Erreur lors de la lecture du fichier .");

		}
	}

	// Écriture de fichier avec FileWriter et BufferedWriter
	public static void writeFileWithBufferedWriter(String filepath, String content) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))) {
			bw.write(content);
			bw.newLine();
			bw.write("ligne ajutee avec succes ");
		} catch (IOException e) {
			System.out.println("Erreur lors de l'ecriture du fichier  " + e.getMessage());

		}
	}

	// Utilisation de PrintWriter pour un écriture formatée
	public static void writeWithPrintWriter(String filepath) {
		try (PrintWriter pw = new PrintWriter(new FileOutputStream(filepath))) {
			pw.println("Rapport des employees :");
			pw.printf("Nom: %s , Age: %d , Poste: %s%n ", "Assiya", 25, "engineer");
			pw.printf("Nom: %s , Age: %d , Poste: %s%n ", "maryam", 23, "engineer");
			pw.printf("Nom: %s , Age: %d , Poste: %s%n ", "Bouchra", 27, "Manager");
			pw.printf("Fin du rapport .");
		} catch (IOException e) {
			System.out.println("Erreur lors de l'ecriture du fichier :" + e.getMessage());
		}
	}

	// Lecture et écriture avec l'API java.nio.file.Files

	public static void readWithFileApi(String filepath) {
		try {
			Path path = Paths.get(filepath);
			List<String> Lines = Files.readAllLines(path);
			for (String line : Lines) {
				System.out.println(line);

			}
		} catch (IOException e) {
			System.out.println("Erreur lors de la lecture :" + e.getMessage());
		}
	}

	public static void writeWithFileApi(String filepath, String content) {
		try {
			Path path = Paths.get(filepath);
			Files.write(path, content.getBytes());
			System.out.println("Fichier ecrit avec succes :" + filepath);

		} catch (IOException e) {
			System.out.println("Erreur lors de l'ecriture du fichier : " + e.getMessage());
		}
	}

	// Copie et déplacement de fichiers

	public static void copyFile(String source, String destination) {
		try {
			Path sourcePath = Paths.get(source);
			Path destPath = Paths.get(destination);
			Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("fichier copie de " + source + "vers" + destination);
		} catch (IOException e) {
			System.out.println("Erreur lors de la copie" + e.getMessage());

		}
	}

	//

	public static void moveFile(String source, String destination) {
		try {
			Path sourcePath = Paths.get(source);
			Path destPath = Paths.get(destination);
			Files.move(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Fichier déplacé de " + source + " vers " + destination);
		} catch (IOException e) {
			System.out.println("Erreur lors du déplacement : " + e.getMessage());
		}
	}

	// Suppression de fichiers et gestion des erreurs

	public static void deleteFile(String filepath) {
		try {
			Path path = Paths.get(filepath);
			if (Files.exists(path)) {
				Files.delete(path);
				System.out.println("Fichier supprime : " + filepath);
			} else {
				System.out.println("le fichier n'existe pas " + filepath);
			}

		} catch (NoSuchFileException e) {
			System.out.println("le fichier a ete supprime entre-temps :" + e.getMessage());

		} catch (IOException e) {
			System.out.println("Erreur lors de la suppression du fichier : " + e.getMessage());
		}

	}

	// Lecture de fichiers binaires avec FileInputStream

	public static void readBinaryFile(String filepath) {
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filepath))) {
			byte[] buffer = new byte[1024];
			int bytesRead;
			long totalBytes = 0;
			while ((bytesRead = bis.read(buffer)) != -1) {
				totalBytes += bytesRead;
			}

			System.out.println("Fichier binaire lu : " + filepath);
			System.out.println("Taille totale : " + totalBytes + " octets");
		} catch (IOException e) {
			System.err.println("Erreur lors de la lecture binaire : " + e.getMessage());
		}
	}

	// Écriture de fichiers binaires avec FileOutputStream
	public static void writeBinaryFile(String filepath, byte[] data) {
		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filepath))) {
			bos.write(data);
			bos.flush();
			System.out.println("Fichier binaire ecrit : " + filepath);
			System.out.println("Taille ecrite : " + data.length + " octets ");
		} catch (IOException e) {
			System.out.println("Erreur lors de l'ecriture binaire: " + e.getMessage());
		}
	}

	// Gestion des métadonnées de fichiers

	public static void getFileMetadata(String filepath) {
		try {
			Path path = Paths.get(filepath);
			BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
			System.out.println("Metadonnees du fichier : " + filepath);
			System.out.println("Taille : " + attrs.size() + " octets");
			System.out.println("Dernière modification : " + attrs.lastModifiedTime().toInstant());
			System.out.println("Est un répertoire : " + attrs.isDirectory());
			System.out.println("Est un fichier : " + attrs.isRegularFile());
		} catch (IOException e) {
			System.err.println("Erreur lors de la récupération des métadonnées : " + e.getMessage());
		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println("TP 12 : Gestion des fichiers en java");
		System.out.println("Test 1 :");
		readFileWithBufferedReader("data/employees.txt");
		System.out.println("Test 2 :");
		writeFileWithBufferedWriter("data/output.txt",
				"le fichier a ete cre avec succes , vous pouvez ecrire de dont ");
		System.out.println("Test 3 :");
		writeWithPrintWriter("data/raport.txt");
		System.out.println("Test 4 :");
		readWithFileApi("data/employees.txt");
		writeWithFileApi("data/nio_output.txt", "le fichier a ete cree avec succes.");
		System.out.println("Test 5 :");
		copyFile("data/employees.txt", "data/employees_backup.txt");
		System.out.println("Test 6 :");
		moveFile("data/nio_output.txt", "data/moved_output.txt");

		System.out.println("Test 7 :");
		deleteFile("data/moved_output.txt");

		System.out.println("Test 7 : suppresion d'un fichier qui n'existe pas .");
		deleteFile("data/test.txt");

		System.out.println("Test 8 :");
		readBinaryFile("data/image.png");

		System.out.println("Test 9 : ");
		byte[] original = Files.readAllBytes(Paths.get("data/image.png"));
		writeBinaryFile("data/copy_binary.bin", original);

		long tailleOriginal = Files.size(Paths.get("data/image.png"));
		long tailleCopie = Files.size(Paths.get("data/copy_binary.bin"));
		System.out.println("Taille originale : " + tailleOriginal + " octets");
		System.out.println("Taille copie     : " + tailleCopie + " octets");
		System.out.println(tailleOriginal == tailleCopie ? "OK : tailles identiques" : "ERREUR : tailles differentes");

		System.out.println("Test 10 : ");
		getFileMetadata("data/employees.txt");

	}

}
