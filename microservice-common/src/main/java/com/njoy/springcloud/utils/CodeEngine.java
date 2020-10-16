package com.njoy.springcloud.utils;

import com.sun.tools.doclint.Checker;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.apache.commons.jexl3.internal.Engine;

import java.util.Map;

public class CodeEngine {
    private static JexlEngine jexlEngine = new Engine();

    public static Object convert(String exp, Map<String, Object> map) {
        JexlExpression jexlExpression = jexlEngine.createExpression(exp);
        JexlContext jexlContext = new MapContext();
        for (String key: map.keySet()) {
            jexlContext.set(key, map.get(key));
        }
        return jexlExpression.evaluate(jexlContext);
    }
}
