

/*
 * aFlux: JVM based IoT Mashup Tool
 * Copyright [2019] [Tanmaya Mahapatra]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.tum.in.aflux.plugin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;

import de.tum.in.aflux.ContextProvider;
import de.tum.in.aflux.model.FlowPlugin;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;
import de.tum.in.aflux.tools.core.AbstractMainBaseTool;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.util.FileSystemUtil;

/**
 * 
 * 
 * Operations related to files / plugins and classloader
 * It could be replaced by an OSGI library like Apache Felix
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class PluginLoader {
	
	
	private static String[] exclusions={"org.xml.sax.","javax.xml.","org.apache.xmlcommons.Version","org.w3c.dom.","org.eclipse.paho." };
	
	/**
	 * Get an instance of a class provided by a plugin
	 * @param className
	 * @return
	 * @throws MalformedURLException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws URISyntaxException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	public static Object getInstance(String className) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, URISyntaxException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
	    final URLClassLoader sysloader = (URLClassLoader) AbstractMainExecutor.class.getClassLoader();
		Class classToLoad = Class.forName (className, true, sysloader);
		Object instance = classToLoad.newInstance ();		
		return  instance;
		
	}

	/**
	 * Return the names of the executor classes found in the jar
	 * @param fileLocation
	 * @param myJar
	 * @return
	 * @throws URISyntaxException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	public static List<String> loadJar(String fileLocation,String myJar) throws URISyntaxException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, ClassNotFoundException {
        String localFileName=FileSystemUtil.getUploadBaseDir()+fileLocation+myJar;
		String completeFileName=localFileName;
		File file=new File(completeFileName);
		boolean exists=file.exists();
		System.out.println("File Exists:"+exists);
		File[] files={file};
	    final URLClassLoader sysloader = (URLClassLoader) AbstractMainExecutor.class.getClassLoader();
        final Class<URLClassLoader> sysclass = URLClassLoader.class;
        final Method method = sysclass.getDeclaredMethod("addURL", new Class[] { URL.class });
        method.setAccessible(true);
        for (final File jar : files) {
            method.invoke(sysloader, new URL[] { jar.toURI().toURL() });
        }
        
        List<String> classNames=getClassNameList(localFileName);
        // addToSpringContext(classNames,sysloader);
        List<String> classExecutors=getExecutorsList(sysloader,classNames);
        return classExecutors;
	}

	/**
	 * Return the list of executors on a Jar that previously exists in current ClassLoader
	 * @throws IOException 
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	public static List<String> checkExecutorsList(String fileName) throws IOException  {
		List<String> totalClassNameList=getClassNameList(fileName);
		List<String> existentClasses=new ArrayList<String>();
		URLClassLoader classLoader=(URLClassLoader) AbstractMainBaseTool.class.getClassLoader();
		for (String className:totalClassNameList) {
			 Class<?> beanClass;
			try {
				if (!shouldBeExcluded(className)) {
					// Log.info("Loading class: "+className);
					beanClass = classLoader.loadClass(className);
					 if (beanClass!=null) {
							if (AbstractMainExecutor.class.isAssignableFrom(beanClass) || AbstractAFluxActor.class.isAssignableFrom(beanClass)) {
								existentClasses.add(className);
							}
					 }
				}
			} catch (ClassNotFoundException e) {
				// No problem. is a new class
			}
		}
		return existentClasses;
	}
	
	
	/**
	 * Identifies all the executors present in a JAR that are already present in classloader
	 * @param classLoader
	 * @param classNames
	 * @return
	 * @throws ClassNotFoundException
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	
	private static List<String> getExecutorsList(URLClassLoader classLoader,List<String> classNames) throws ClassNotFoundException {
		List<String> result=new ArrayList<String>();
		for (String className:classNames) {
			// Log.debug("getExecutorList(..."+className+"...)");
			if (!shouldBeExcluded(className)) {
				 Class<?> beanClass = classLoader.loadClass(className);
				 if (AbstractMainExecutor.class.isAssignableFrom(beanClass)  && !beanClass.getName().equals(AbstractMainExecutor.class.getName())) {
					 result.add(className);
				 }
			}
		}
		return result;
	}

	/**
	 * Deprecated: this method added actor instances got from plugins in Spring Context
	 * @param classNames
	 * @param classLoader
	 * @throws ClassNotFoundException
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	private static void addToSpringContext(List<String> classNames, URLClassLoader classLoader) throws ClassNotFoundException {
		for (String className:classNames) {
			 Class<?> beanClass = classLoader.loadClass(className);
			 
			 if (AbstractAFluxActor.class.isAssignableFrom(beanClass)  && !beanClass.getName().equals(AbstractAFluxActor.class.getName())) {
			        AutowireCapableBeanFactory factory = ContextProvider.getApplicationContext().getAutowireCapableBeanFactory();
			        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) factory;
				    BeanDefinition definition = new RootBeanDefinition(beanClass);
				    definition.setScope(ConfigurableBeanFactory.SCOPE_PROTOTYPE);
				    String beanName=beanClass.getCanonicalName();
				    // beanName = Character.toLowerCase(beanName.charAt(0)) + beanName.substring(1);
				    registry.registerBeanDefinition(beanName, definition);
				 
			 }
			 

		}
	}
	
	

	/**
	 * Get list of classes present in a jar file
	 * @param completeFileName
	 * @return
	 * @throws IOException
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	private static List<String> getClassNameList(String completeFileName) throws IOException {
		List<String> classNames = new ArrayList<String>();
		ZipInputStream zip = new ZipInputStream(new FileInputStream(completeFileName));
		for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
		    if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
		        // This ZipEntry represents a class. Now, what class does it represent?
		        String className = entry.getName().replace('/', '.'); // including ".class"
		        classNames.add(className.substring(0, className.length() - ".class".length()));
		    }
		}	
		return classNames;
	}

	/**
	 * Incorporates in classloader the classes in a given jar
	 * @param flowPlugin
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 * @throws URISyntaxException
	 * @throws IOException
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	public static List<String> loadJar(FlowPlugin flowPlugin) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, URISyntaxException, IOException {
		return loadJar(flowPlugin.getJarLocation(),flowPlugin.getJarName());
		
	}
	
	private static boolean shouldBeExcluded(String className) {
		boolean found=false;
		for (String exclusion:exclusions) {
			if (className.startsWith(exclusion)) {
				found=true;
			}
		}
		return found;
	} 
	
	
}
