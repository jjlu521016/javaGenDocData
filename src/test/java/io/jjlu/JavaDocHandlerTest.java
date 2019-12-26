package io.jjlu;

import io.jjlu.handler.ExtClassDoc;
import io.jjlu.handler.JavaDocHandler;
import org.junit.Test;

/**
 * @author jjlu521016
 * @since 20191226
 */
public class JavaDocHandlerTest {

    @Test
    public void getClassComment() {
        ExtClassDoc doc = JavaDocHandler.read("D:\\code\\gitcode\\javaGenDocData\\src\\main\\java\\io\\jjlu\\handler\\ExtClassDoc.java");
        doc.getClassComment();
    }

}
