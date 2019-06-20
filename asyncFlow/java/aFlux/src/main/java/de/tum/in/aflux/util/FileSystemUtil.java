/*
 *
 *  *
 *  * aFlux: JVM based IoT Mashup Tool
 *  * Copyright (C) 2018  Tanmaya Mahapatra
 *  *
 *  * This file is part of aFlux.
 *  *
 *  * aFlux is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, version 3 of the License.
 *  *
 *  * aFlux is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with aFlux.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
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
