package io.jjlu.handler;

import com.sun.javadoc.Doclet;
import com.sun.javadoc.LanguageVersion;
import com.sun.javadoc.RootDoc;

/**
 * @author jjlu521016
 * @since 20191226
 */
public class CustomizeDoclet extends Doclet {

    public static boolean start(RootDoc root) {
        JavaDocHandler.root = root;
        return true;
    }

    /**
     * 需要获取泛型的类型，需要先实现如下方法到Doclet类，否则将获取不到泛型内的类型
     * 默认的是1.1版本
     *
     * @return
     */
    public static LanguageVersion languageVersion() {
        return LanguageVersion.JAVA_1_5;
    }

}
