package first;

import java.lang.reflect.Field;

import org.bson.Document;

public class ConvertUtil {
	
	public static Document convertDoc(Student student)
			throws IllegalArgumentException, IllegalAccessException {
		Document document = new Document();
		Field[] fields = student.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			document.append(field.getName(), field.get(student));
		}
		return document;
	}


}
