package com.amos;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.ParameterDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import com.amos.model.Person;

/**
 * @ClassName: IntroSpectorTest
 * @Description: javaBean的内省操作
 * @author: amosli
 * @email:amosli@infomorrow.com
 * @date Mar 18, 2014 11:06:14 PM
 */
public class IntroSpectorTest {

	public static void main(String[] args) throws Exception {
		Person person = new Person();
		handle(person);

	}

	private static void handle(Person person) throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
		// MethodDescriptor[] methodDescriptors =
		// beanInfo.getMethodDescriptors();
		for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
			String name = propertyDescriptor.getName();
			if (name.equals("name") || name.equals("age")) {
				Method method = propertyDescriptor.getWriteMethod();
				// System.out.println(method);
				Class<?>[] parameterTypes = method.getParameterTypes();
				if (parameterTypes.equals(Integer.class))
					method.invoke(person, 13);
				else if (parameterTypes.equals(String.class))
					method.invoke(person, "amosli");// setter
			}
			Method readMethod = propertyDescriptor.getReadMethod();
			Object object = readMethod.invoke(person);
			System.out.println("object:" + object);
		}

	}

}
