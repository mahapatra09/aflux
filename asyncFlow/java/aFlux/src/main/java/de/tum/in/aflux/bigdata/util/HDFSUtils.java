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

package de.tum.in.aflux.bigdata.util;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.core.io.Resource;
import org.springframework.data.hadoop.fs.HdfsResourceLoader;

public class HDFSUtils {

	

	public static Resource writeToFS(Configuration cfg, String filename) {
		return writeToFS(new HdfsResourceLoader(cfg), filename);
	}	
	
	public static Resource writeToFS(HdfsResourceLoader loader, String filename) {
		try {
			Resource resource = loader.getResource(filename);
			//System.out.println("Writing resource " + resource.getURI());
			//WritableResource wr = (WritableResource) resource;

			FileSystem fs = ((HdfsResourceLoader) loader).getFileSystem();

			byte[] bytes = filename.getBytes();
			OutputStream out = fs.create(new Path(resource.getURI()));
			out.write(bytes);
			out.flush();
			out.close();

			return loader.getResource(filename);
		} catch (IOException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	
}
