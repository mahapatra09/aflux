package org.codeIndection.sample;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractBaseJavaComponent {
	
	@Autowired
	String serverResource1;
	
	public abstract Object execute(Object parameter1);
	
}