package com.moviestore.elasticsearch.util;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.StringTokenizer;

public class ConfigUtil {

  public static void close(Reader reader) {
    if (reader != null) {
      try {
        reader.close();
      } catch (IOException e) {
      }
    }
  }

  public static URL findFile(String path) {
    return getContextOrClassLoader().getResource(path);
  }

  public static String getNextToken(StringTokenizer tokenizer) {
    if (tokenizer.hasMoreTokens()) {
      return tokenizer.nextToken();
    }
    return null;
  }

  public static String getNextToken(StringTokenizer tokenizer, String delim) {
    if (tokenizer.hasMoreTokens()) {
      return tokenizer.nextToken(delim);
    }
    return null;
  }

  public static <K> K loadAndCreateInstance(String className, Class<K> clazz)
      throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    return Class.forName(className, true, getContextOrClassLoader()).asSubclass(clazz)
        .newInstance();
  }

  public static ClassLoader getClassLoader() {
    return ConfigUtil.class.getClassLoader();
  }

  public static ClassLoader getContextOrClassLoader() {
    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    if (cl == null)
      return getClassLoader();
    else
      return cl;
  }

  // public static boolean getNextTokenAsBoolean(StringTokenizer tokenizer) {
  // String boolAsStr = getNextToken(tokenizer);
  // if (StringUtils.isBlank(boolAsStr)) {
  // return false;
  // }
  // return true;
  // }

  // public static <K, V> String buildProducerKey(Serializer<K> keySerializer,
  // Serializer<V> valSerializer) {
  // String producerKey = "";
  // producerKey += getParameterizedType(keySerializer);
  // producerKey += getParameterizedType(valSerializer);
  // return producerKey;
  // }
  //
  // public static <K> String getParameterizedType(Serializer<K> keySerializer) {
  // Type[] interfaces = keySerializer.getClass().getGenericInterfaces();
  // Type tType = ((ParameterizedType) interfaces[0]).getActualTypeArguments()[0];
  // String className = tType.toString().split(" ")[1];
  // return className;
  // }
  //
  //
  // public static void main(String[] args) {
  // System.out.println(getParameterizedType(new StringSerializer()));
  // }

}
