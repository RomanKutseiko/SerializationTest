package appFX;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Loader {
	
	private File jar;
	
	public Loader(File chosenFile){
		jar = chosenFile;
	}
	
	
	
	public Class getClassFromPlugin(){	
	try {
        URL jarURL = jar.toURI().toURL();
        URLClassLoader classLoader = new URLClassLoader(new URL[]{jarURL});
        JarFile jf = new JarFile(jar);
        Enumeration<JarEntry> entries = jf.entries();
        while (entries.hasMoreElements()) {
          String e = entries.nextElement().getName();
          if (!e.endsWith(".class")) continue;
          e = e.replaceAll("/", ".");
          e = e.replaceAll(".class", "");
          Class<?> plugCan = classLoader.loadClass(e);
          Class<?> superClass = plugCan.getSuperclass();
	      if (superClass.getName().endsWith(".Organism")) {
	        Class c = classLoader.loadClass(plugCan.getName());
	        return c;
	      } 
        }
      } catch (Exception e) {
        e.printStackTrace(System.err);
      }
	return null;
}
}
