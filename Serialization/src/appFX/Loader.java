package appFX;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JOptionPane;

import Hierarchi.Plant;

public class Loader extends ClassLoader{
	
	/*private File jar;
	
	public Loader(File chosenFile){
		jar = chosenFile;
	}
	
	private Method resolve, define;
    public void PluginLoader() throws NoSuchMethodException, SecurityException
    {
        Class<ClassLoader> c=ClassLoader.class;
        define = c.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
        resolve = c.getDeclaredMethod("resolveClass", Class.class);
        define.setAccessible(true);//������ - protected
        resolve.setAccessible(true);
    }*/
	
    
    private String pathToHierarchiClass;


    public Loader(String pathToHierarchiClass, ClassLoader loader)
    {
    	super(loader);
    	this.pathToHierarchiClass = pathToHierarchiClass;
    }
        

    public Class<?> loadClass() throws ClassNotFoundException, IOException
    {
    	int classNameBeginIndex = pathToHierarchiClass.lastIndexOf('\\') + 1;
    	int classNameEndIndex = pathToHierarchiClass.lastIndexOf(".class");
    	String className = pathToHierarchiClass.substring(classNameBeginIndex, classNameEndIndex);
    	Class cl = super.loadClass("Hierarchi." + className);  
    	//super.resolveClass(cl);
    	//resolveClass(cl);
    	return cl;//super.loadClass("Hierarchi." + className);
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException 
    {
    	try {
		    byte b[] = fetchClassFromFS(this.pathToHierarchiClass);
		    Class cl = defineClass(className, b, 0, b.length);
		    //super.resolveClass(cl);
		    resolveClass(cl);
		    return cl;
	    } catch (FileNotFoundException ex) {
	    	return super.findClass(className);
	    } catch (IOException ex) {
    		return super.findClass(className);
	    }
    }


    private byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException 
    {
    	InputStream is = new FileInputStream(new File(path));

	    long length = new File(path).length(); 
	    if (length > Integer.MAX_VALUE) {
	    is.close();
	    throw new IOException("File is too large");
    }

	    byte[] bytes = new byte[(int)length]; 
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	    && (numRead = is.read(bytes, offset, bytes.length-offset)) >= 0) {
	    offset += numRead;
    }

    is.close();

    if (offset < bytes.length) {
    	throw new IOException("Could not completely read file " + path);
    }

    return bytes;
    }
    
    
    
    
    
    
	
	
	/*public Class getClassFromPlugin(){	
    /*    //ArrayList<IPlugin> res = new ArrayList<IPlugin>();//���������
		try {
			PluginLoader();
		} catch (NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			 e1.printStackTrace(System.err);//PrintErrorInfo(e1);
		}
        FileInputStream reader;//����� ��� ������
        ClassLoader c = ClassLoader.getSystemClassLoader();//��������� ClassLoader
        Class<?> cl;   //����������� �����
        Object instance;  //��� ���������
        //for (File jar)
        //{
            try
            {
            	 reader = new FileInputStream(jar);//�������������� 
            	    byte[] b = new byte[reader.available()];//����� ��.
            	    reader.read(b);
            	    reader.close();
            	                
            	    cl = (Class<?>) define.invoke(c, null, b, 0, b.length);//�������� ��������� ����� defineClass. �� ����
            	    //���������� ������������, ��� �� �� �����, ������ ��������� �� 0 �� b.length 
            	    resolve.invoke(c, cl);/// c.resolveClass(cl)
            	    instance = cl.newInstance();//�������� ����������� ��� ����������

            	                if (instance instanceof IPlugin)//���� ��� ������, �� ��������� � ���������
            	                {
            	                    res.add((IPlugin)instance);
            	                }
            	    return cl;
            }
            catch (Exception e)
            {
            	 e.printStackTrace(System.err);//PrintErrorInfo(e);//���������� ������ � ��� � ��� ������
            }
        //}
        return null;//���������� �� ��� ���������
        */
		
	/*try {
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
}*/



	private void PrintErrorInfo(Exception e) {
		Object a = null;
		Component b = null;
		JOptionPane.showMessageDialog(b, a, "Class load error", 5);
		
	}
}
