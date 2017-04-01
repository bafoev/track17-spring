package track.container;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import track.container.config.Bean;
import track.container.config.Property;
import track.container.config.ValueType;


/**
 * Основной класс контейнера
 * У него определено 2 публичных метода, можете дописывать свои методы и конструкторы
 */
public class Container {
    private Map<String, Object> objId;
    private Map<String, Object> objByClassName;
    private Map<String, Bean> beanId;
    private Map<String, Bean> beanClassName;


    public Container(List<Bean> beans) {

        objId = new HashMap<>();
        objByClassName = new HashMap<>();
        beanId = new HashMap<>();
        beanClassName = new HashMap<>();

        for (Bean bi : beans) {
            beanId.put(bi.getId(), bi);
            beanClassName.put(bi.getClassName(), bi);
        }

    }

    /**
     * Вернуть объект по имени бина из конфига
     * Например, Car car = (Car) container.getById("carBean")
     */
    public Object getByid(String id) throws BeanException {
        return beanToObject(objId, beanId, id);
    }

    /**
     * Вернуть объект по имени класса
     * Например, Car car = (Car) container.getByClass("track.container.beans.Car")
     */
    public Object getByClass(String className) throws BeanException {
        return beanToObject(objByClassName, beanClassName, className);

    }

    private Object beanToObject(Map<String, Object> objMap, Map<String, Bean> beanMap, String name)
            throws BeanException {
        if (objMap.containsKey(name)) {
            return objMap.get(name);
        } else {
            if (beanMap.containsKey(name)) {
                try {
                    Bean bean = beanMap.get(name);
                    Class<?> classes = Class.forName(bean.getClassName());
                    Object object = classes.newInstance();

                    objId.put(bean.getId(), object);
                    objByClassName.put(bean.getClassName(), object);


                    setProperties(object, bean);
                    return object;
                } catch (Exception e) {
                    throw new BeanException(e.getMessage());
                }
            } else {
                throw new BeanException("нет такого бина:" + name);
            }

        }
    }

    private void setProperties(Object obj, Bean bean) throws ClassNotFoundException, InvocationTargetException,
            IllegalAccessException, BeanException {
        Class<?> classes = Class.forName(bean.getClassName());
        for (String propName : bean.getProperties().keySet()) {
            Method setter = getSetterFor(classes.getMethods(), propName);
            Property property = bean.getProperties().get(propName);
            Class<?> propClass = setter.getParameterTypes()[0];

            if (property.getType().equals(ValueType.REF)) {
                setter.invoke(obj, propClass.cast(getByid(property.getValue())));
            } else {
                setter.invoke(obj, toObject(property.getValue(), propClass));

            }
        }

    }

    private Object toObject(String value, Class clazz) {
        if (Boolean.class == clazz || Boolean.TYPE == clazz) {
            return Boolean.parseBoolean(value);

        }

        if (Byte.class == clazz || Boolean.TYPE == clazz) {
            return Byte.parseByte(value);
        }

        if (Short.class == clazz || Short.TYPE == clazz) {
            return Short.parseShort(value);
        }

        if (Integer.class == clazz || Integer.TYPE == clazz) {
            return Integer.parseInt(value);
        }

        if (Long.class == clazz || Long.TYPE == clazz) {
            return Long.parseLong(value);
        }

        if (Float.class == clazz || Float.TYPE == clazz) {
            return Float.parseFloat(value);
        }

        if (Double.class == clazz || Double.TYPE == clazz) {
            return Double.parseDouble(value);
        }
        return value;
    }

    private Method getSetterFor(Method[] methods, String parName) throws BeanException {
        parName = StringUtils.capitalize(parName);
        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith("set") && name.equalsIgnoreCase("set" + parName)) {
                return method;
            }
        }
        throw new BeanException("нет сеттера для" + parName);


    }
}


