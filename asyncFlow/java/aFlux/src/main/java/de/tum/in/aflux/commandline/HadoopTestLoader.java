

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

package de.tum.in.aflux.commandline;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

 

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;

import org.springframework.data.hadoop.fs.FsShell;
import org.springframework.data.hadoop.pig.PigOperations;
import org.springframework.data.hadoop.store.output.TextFileWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import de.tum.in.aflux.bigdata.util.HDFSUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.backend.executionengine.ExecJob;
import org.apache.pig.backend.executionengine.ExecJob.JOB_STATUS;
import org.apache.pig.data.Tuple;



@ImportResource("classpath:hadoop-context.xml")
@Component
public class HadoopTestLoader {
	
	@Autowired
    private  org.apache.hadoop.conf.Configuration hadoopConfiguration;

	@Autowired
	FsShell fsShell;
	
	@Autowired
    private JdbcTemplate hiveTemplate;
	
	@Autowired
	PigOperations pigTemplate;
	
	public void runHive() {
		List<Map<String, Object>> rows=hiveTemplate.queryForList("SELECT * FROM default.student");
		System.out.println(rows);
	}
	
	
	public void runPig() throws ExecException {
		String scripts="student = LOAD 'student.txt' USING PigStorage(',') as (id:int,name:chararray,city:chararray);";
		// scripts+="student = LOAD 'hdfs://localhost:8020/pig_data/student.txt' USING PigStorage(',') as (id:int,name:chararray,city:chararray);";
		scripts+="Dump student;";
		
		
		
		
		// List<ExecJob> listResult=pigTemplate.executeScript("A = LOAD 'foo.txt' AS (key, value);");
		// List<ExecJob> listResult=pigTemplate.executeScript("A = LOAD 'hdfs://localhost:9001/user/root/input/student.txt' as (id:int,name:chararray,city:chararray);");
		//List<ExecJob> jobs=pigTemplate.executeScript("A = LOAD '/user/root/input/student.txt' using PigStorage(',') as (id:int,name:chararray,city:chararray);Dump A;");
		// working but getResults() empty
		 // could not find schema file
		// List<ExecJob> listResult=pigTemplate.executeScript("A = LOAD '/student.txt' using PigStorage('-noschema') as (id:int,name:chararray,city:chararray);Dump A;");
		 // could not instantiate 'PigStorage' with arguments '[-noschema]'
		List<ExecJob> jobs=pigTemplate.executeScript("A = LOAD '/user/root/input/student.txt' using PigStorage(',') as (id:int,name:chararray,city:chararray);STORE A into 'output';");
		System.out.println("Rsult of pig job --------script ----------------------------------------------------------------------");
		for (int i=0;i<jobs.size();i++) {
			ExecJob job=jobs.get(i);
			int j=0;
			while (job.getStatus()!=JOB_STATUS.COMPLETED && j<10000) {
				j++;
			}
			if (job.getStatus()==JOB_STATUS.COMPLETED) {
				System.out.println(job.getStatus());
				Iterator<Tuple> tuples=job.getResults();
				while (tuples.hasNext()) {
					Tuple t=tuples.next();
					System.out.println(t);
				}
				
			}
		}
		
		System.out.println(jobs);
		List<ExecJob> jobs2=pigTemplate.executeScript("fs -ls /");
		System.out.println("Rsult of pig job --------script ----------------------------------------------------------------------");
		for (int i=0;i<jobs2.size();i++) {
			ExecJob job=jobs2.get(i);
			int j=0;
			while (job.getStatus()!=JOB_STATUS.COMPLETED && j<10000) {
				j++;
			}
			if (job.getStatus()==JOB_STATUS.COMPLETED) {
				System.out.println(job.getStatus());
				Iterator<Tuple> tuples=job.getResults();
				while (tuples.hasNext()) {
					Tuple t=tuples.next();
					System.out.println(t);
				}
				
			}
		}
		
		
		
	}
    
	public void copyToLocal(String sourcePath,String sourceName,String targetPath,String targetName) throws Exception {
		
		
		
		/*
		FsShell fsShell= new FsShell(hadoopConfiguration,
					(new org.springframework.data.hadoop.fs.FileSystemFactoryBean()).getObject());
					*/
		
		String sourceFileName = sourcePath+"/"+sourceName;
		String targetFileName = targetPath+"/" + targetName;
		//Resource res = HDFSUtils.writeToFS(hadoopConfiguration, name);
		fsShell.copyToLocal(sourceFileName, targetFileName);
	}

	private void runTextFileWriter(TextFileWriter textFileWriter) {
		List<String> content=new ArrayList<String>();
		content.add("first line");
		content.add("SecondLine");
		
		try {
			textFileWriter.write("fistLine\n");
			textFileWriter.flush();
			textFileWriter.close();
			// delimitedTextFileWriter.write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    
	/*
	@Autowired
	DelimitedTextFileWriter delimitedTextFileWriter;
	*/
	
	/*
	@Autowired
	TextFileWriter textFileWriter;
	*/
	public void run() throws Exception {
		/*
		 * Test runTextFileWriter()
		 * 
		 * runTextFileWriter(textFileWriter);
		*/
		/*  Test FsShell
		copyToLocal("/user/root/input/spring/","newdata.txt","/usr/local/share","gotData.txt");
		*/
		// runPig();
		//runHive();
	}
	
	
	
	
}
