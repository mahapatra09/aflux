package de.tum.in.aflux.commandline;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tum.in.aflux.bigdata.config.SpringHadoopConfig;
import de.tum.in.aflux.services.bigdata.HDFS_Operations;

@Component
@Import(value = { SpringHadoopConfig.class })

//@ImportResource("hadoop-context.xml")
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})

// TODO: Fix these tests
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
public class HadoopPOCLoaderTest implements Ordered {

	
	@Autowired
	private HDFS_Operations hdfsOperations;
	
	
	//@Autowired
	//public HBaseIoTDataRepository hbaseIotDataRepository;
/*
	@Autowired
	public HadoopPOCLoaderTest(HBaseIoTDataRepository hbaseIotDataRepository) {
		this.hbaseIotDataRepository=hbaseIotDataRepository;
	}
*/
	@Override
	public int getOrder() {

		return 0;
	}

// 	@Override
// 	public void run(String... arg0) throws Exception {
	
	
	// TODO: fix these tests
	// @Test
	public void testHadoopLoader() {
		System.out.println("hadoop loader");
		//Long a=hdfsOperations.countRecords();
		//System.out.println("countRecords="+a);
		
		
		System.out.println("finish hadoop loader");
		
	}



}
