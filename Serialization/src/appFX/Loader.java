package appFX;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import Hierarchi.Plant;

public class Loader extends ClassLoader{
	
	private File jar;
	
	public Loader(File chosenFile){
		jar = chosenFile;
	}
	
	
	
	public Class getClassFromPlugin(){	
	try {
        URL jarURL = jar.toURI().toURL();
        //URLClassLoader classLoader1 = (URLClassLoader)ClassLoader.getSystemClassLoader();//Plant.class.getClassLoader();
        //URLClassLoader  classLoader2 = new URLClassLoader(new URL[]{jarURL}, Plant.class.getClassLoader());
        //classLoader1.ne;
        //classLoader.loadClass(jar.getAbsolutePath());
        
        ClassLoader classLoader = new URLClassLoader(new URL[]{jarURL});
        JarFile jf = new JarFile(jar);
        Enumeration<JarEntry> entries = jf.entries();
        String s = jf.getName();
        while (entries.hasMoreElements()) {
          String e = entries.nextElement().getName();
          if (!e.endsWith(".class")) continue;
          e = e.replaceAll("/", ".");
          e = e.replaceAll(".class", "");
          Class<?> plugCan = classLoader2.loadClass(e);
          //Class<?> plugCan = classLoader.loadClass("/Helminths.src.Hierarchi.Helminths.class");
          Class<?> superClass = plugCan.getSuperclass();
	      if (superClass.getName().endsWith(".Organism")) {
	        Class c = classLoader.loadClass(plugCan.getName());
	        //Class c = classLoader.loadClass("Helminths.src." + plugCan.getName());
	        return c;
	      } 
        }
      } catch (Exception e) {
        e.printStackTrace(System.err);
      }
	return null;
}
}
