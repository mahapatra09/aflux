

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

package de.tum.in.aflux.util;

import java.io.File;

public class FileSystemUtil {

	/**
	 * Stablish the baseDir where plugins are uploaded
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	public static String getUploadBaseDir() {
		String uploadBaseDir=getBaseDir()+"uploads/";
        if(! new File(uploadBaseDir).exists())
        {
            new File(uploadBaseDir).mkdir();
        }
		return uploadBaseDir;
	}
	
	
	/**
	 * Gets the dir parent of upload folder
	 * 
	 * @return
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	public static String getBaseDir() {
        final String dir = System.getProperty("user.dir");
        File file1=new File(dir);
        String baseDir=file1.getParent()+"/";
        return baseDir;
	}
	
	
	/**
	 * Stablish the baseDir where plugins are uploaded
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	public static String getJobsBaseDir(String newDir) {
		String jobBaseDir=getBaseDir()+"/jobs/";
        if(! new File(jobBaseDir).exists())
        {
            new File(jobBaseDir).mkdir();
        }
        String fullNewDir=jobBaseDir+newDir;
        if(! new File(fullNewDir).exists())
        {
            new File(fullNewDir).mkdir();
        }
		return fullNewDir;
	}
	
	public static String getJobsBaseSubDir(String baseDir,String newDir) {
		String fullBaseDir=getJobsBaseDir(baseDir);
        String fullNewDir=fullBaseDir+"/"+newDir;
        if(! new File(fullNewDir).exists())
        {
            new File(fullNewDir).mkdir();
        }
		return fullNewDir;
	}
	
	

}
