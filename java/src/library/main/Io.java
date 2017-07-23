package library.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import library.catalogue.Element;

public class Io {
	static String stripExtension(String str) {
        // Handle null case specially.

        if (str == null) return null;

        // Get position of last '.'.

        int pos = str.lastIndexOf(".");

        // If there wasn't any '.' just return the string as is.

        if (pos == -1) return str;

        // Otherwise return the string, up to the dot.

        return str.substring(0, pos);
    }
	public static void saveLibrary(Library library) {
		ObjectOutputStream oos = null;
		File file = new File("./libraries/" + library.getName() + ".ser");
		File folder = new File("./libraries");
		if (folder.mkdir()) System.out.println("The directory has been created.");
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(library);
			oos.flush();
		} catch (FileNotFoundException e) {
			System.err.print("The file does not exist.");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("The library \"" + library.getName() + "\" has been saved successfully.");
		}
	}
	public static List<Library> readLibraries() {
		File folder = new File("./libraries/");
		List<Library> libraries = new ArrayList<Library>();
		for (File fileEntry: folder.listFiles()) {
			if (!fileEntry.isDirectory() && fileEntry != null) libraries.add(readLibrary(fileEntry.getName().trim()));
		}
		return libraries;
	}
	public static Library readLibrary(String libraryName) {
		ObjectInputStream ois = null;
		Library library = null;
		try	{
			// checks if the param. has a .ser at the start
			int index = libraryName.indexOf('.');
			if (!(index < 0) && libraryName.substring(index, libraryName.length()).equals(".ser"))
				ois = new ObjectInputStream(new FileInputStream("./libraries/" + libraryName));
			else
				ois = new ObjectInputStream(new FileInputStream("./libraries/" + libraryName + ".ser"));
			library = (Library)ois.readObject();
		} catch (FileNotFoundException e) {
			System.err.println("Library \"" + libraryName + "\" not found.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return library;
	}
	public static void saveElementToLibrary(Element element, String libraryName) {
		Library library = readLibrary(libraryName);
		library.addElement(element);
		saveLibrary(library);
	}
}
