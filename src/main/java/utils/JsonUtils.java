package utils;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static ObjectMapper mapper;

    static {
	mapper = new ObjectMapper();
	mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
	try {
	    return mapper.readValue(json, classOfT);
	} catch (Exception e) {
	    throw new RuntimeException(e.getMessage(), e);
	}

    }

    public static String toJson(Object src) {
	try {
	    return mapper.writeValueAsString(src);
	} catch (Exception e) {
	    throw new RuntimeException(e.getMessage(), e);
	}
    }

}
