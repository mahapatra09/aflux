package org.codeIndection.sample;

public class GeneartedClass1111 extends AbstractBaseJavaComponent {



	@Override
	public Object execute(Object parameter1) {
		Customer var1=(Customer) parameter1;
		String newName=repeateString(var1.getName());
		Vendor result=new Vendor(newName,var1.getAge().floatValue()*2);
		System.out.println(serverResource1);
		return result;
	}

	
	private String repeateString(String s) {
		return s+s;
	}
	
}
