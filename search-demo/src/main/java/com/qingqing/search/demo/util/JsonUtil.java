package com.qingqing.search.demo.util;


import org.codehaus.jackson.*;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 与xstream一样，反序列化时，遇到未知的field，会抛异常。必须设置 objectMapper线程安全
 */
public class JsonUtil {
	protected static final ObjectMapper objectMapper;
	static {
		objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(Inclusion.NON_NULL);
	}

	public static class CustomDateSerializer extends JsonSerializer<Date> {

		@Override
		public void serialize(Date value, JsonGenerator jgen, SerializerProvider arg2) throws IOException, JsonProcessingException {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String formattedDate = formatter.format(value);
			jgen.writeString(formattedDate);
		}
	}

	public static <T> T getObjectFromJson(InputStream instream, Class<T> cls) {
		try {
			JsonParser parser = objectMapper.getJsonFactory().createJsonParser(instream);
			T t = objectMapper.readValue(parser, cls);

			return t;
		} catch (JsonParseException e) {
			throw new RuntimeException("parse json error", e);
		} catch (IOException e) {
			throw new RuntimeException("parse json error", e);
		} finally {
			try {
				instream.close();
			} catch (Exception ignore) {
			}
		}
	}

	public static <T> T getObjectFromJson(String str, Class<T> cls) {
		try {
			JsonParser parser = objectMapper.getJsonFactory().createJsonParser(str);
			T t = objectMapper.readValue(parser, cls);
			return t;
		} catch (JsonParseException e) {
			throw new RuntimeException("parse json error, json=" + str + ", class=" + cls.getName(), e);
		} catch (IOException e) {
			throw new RuntimeException("parse json error, json=" + str + ", class=" + cls.getName(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public static String getValueByFieldFromJson(String json, String field) {
		Map<String, String> mapValue = getObjectFromJson(json, HashMap.class);
		return mapValue.get(field);
	}

	public static String getJsonFromObject(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			throw new RuntimeException("get json error", e);
		} catch (JsonMappingException e) {
			throw new RuntimeException("get json error", e);
		} catch (IOException e) {
			throw new RuntimeException("get json error", e);
		}
	}

	public static <T> List<T> parserJsonList(InputStream instream, Class<T> clsT) {
		try {
			JsonParser parser = objectMapper.getJsonFactory().createJsonParser(instream);

			JsonNode nodes = parser.readValueAsTree();
			List<T> list = new LinkedList<T>();
			for (JsonNode node : nodes) {
				list.add(objectMapper.readValue(node, clsT));
			}
			return list;
		} catch (JsonParseException e) {
			throw new RuntimeException("parse json error", e);
		} catch (IOException e) {
			throw new RuntimeException("parse json error", e);
		} finally {
			try {
				instream.close();
			} catch (Exception ignore) {
			}
		}
	}

	public static <T> List<T> parserJsonList(String str, Class<T> clsT) {
		try {
			JsonParser parser = objectMapper.getJsonFactory().createJsonParser(str);

			JsonNode nodes = parser.readValueAsTree();
			List<T> list = new LinkedList<T>();
			for (JsonNode node : nodes) {
				list.add(objectMapper.readValue(node, clsT));
			}
			return list;
		} catch (JsonParseException e) {
			throw new RuntimeException("parse json error str:" + str, e);
		} catch (IOException e) {
			throw new RuntimeException("parse json error str:" + str, e);
		}
	}

	public static <T> LinkedHashMap<String, T> parserJsonMap(String str, Class<T> clsT) {
		LinkedHashMap<String, T> map = new LinkedHashMap<String, T>();
		try {
			JsonParser parser = objectMapper.getJsonFactory().createJsonParser(str);

			JsonToken current;
			current = parser.nextToken();
			if (current != JsonToken.START_OBJECT) {
				throw new RuntimeException("parse json error: root should be object, quiting.");
			}
			while (parser.nextToken() != JsonToken.END_OBJECT) {
				String fieldName = parser.getCurrentName();
				current = parser.nextToken();
				T obj = parser.readValueAs(clsT);
				map.put(fieldName, obj);
			}

			return map;
		} catch (JsonParseException e) {
			throw new RuntimeException("parse json error str:" + str, e);
		} catch (IOException e) {
			throw new RuntimeException("parse json error str:" + str, e);
		}
	}

	public static <T extends Enum<T>> EnumSet<T> parserJsonEnum(String str, Class<T> clsT) {
		try {
			JsonParser parser = objectMapper.getJsonFactory().createJsonParser(str);

			JsonNode nodes = parser.readValueAsTree();
			EnumSet<T> enumSet = EnumSet.noneOf(clsT);
			for (JsonNode node : nodes) {
				enumSet.add(objectMapper.readValue(node, clsT));
			}
			return enumSet;
		} catch (JsonParseException e) {
			throw new RuntimeException("parse json error str:" + str, e);
		} catch (IOException e) {
			throw new RuntimeException("parse json error str:" + str, e);
		}
	}

	public static String format(Object object) {
		return format(getJsonFromObject(object));
	}

	/**
	 * 得到格式化json数据 退格用\t 换行用\r
	 */
	public static String format(String jsonStr) {
		int level = 0;
		StringBuffer jsonForMatStr = new StringBuffer();
		for (int i = 0; i < jsonStr.length(); i++) {
			char c = jsonStr.charAt(i);
			if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
				jsonForMatStr.append(getLevelStr(level));
			}
			switch (c) {
			case '{':
			case '[':
				jsonForMatStr.append(c + "\n");
				level++;
				break;
			case ',':
				jsonForMatStr.append(c + "\n");
				break;
			case '}':
			case ']':
				jsonForMatStr.append("\n");
				level--;
				jsonForMatStr.append(getLevelStr(level));
				jsonForMatStr.append(c);
				break;
			default:
				jsonForMatStr.append(c);
				break;
			}
		}

		return jsonForMatStr.toString();
	}

	private static String getLevelStr(int level) {
		StringBuffer levelStr = new StringBuffer();
		for (int levelI = 0; levelI < level; levelI++) {
			levelStr.append("\t");
		}
		return levelStr.toString();
	}
}
