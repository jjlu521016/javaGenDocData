package io.jjlu.handler;

import com.sun.javadoc.RootDoc;
import io.jjlu.util.StringUtils;
import io.jjlu.util.ValidateUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jjlu521016
 * @since 20191226
 */
public class JavaDocHandler {

    public static RootDoc root = null;

    private static String sourcePath;

    private static String[] sourcePathList = new String[0];

    private static String classPath;

    private static final List<String> ARG_LIST = Arrays.asList("-quiet", "-Xmaxerrs", "1", "-Xmaxwarns", "1", "-encoding", "utf-8");

    /**
     * 解析指定的java源文件返回javadoc对象 {@link RootDoc}<br>
     * 参见 <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/javadoc/standard-doclet.html#runningprogrammatically">Running the Standard Doclet Programmatically</a>
     *
     * @param source a java source file or package name
     * @param classpath value for  '-classpath',{@code source}的class位置,可为{@code null},如果不提供,无法获取到完整的注释信息(比如annotation)
     * @param sourcePath value for '-sourcepath'
     * @return {@link RootDoc}对象
     */
    public synchronized static RootDoc readDocs(String source, String classpath, String sourcePath) {

        List<String> argsOption = new ArrayList<>();
        argsOption.add("-doclet");
        argsOption.add(CustomizeDoclet.class.getName());
        argsOption.addAll(ARG_LIST);
        ValidateUtils.checkArgument(StringUtils.isBlank(source), "source is null");
        if (StringUtils.isNotBlank(classpath)) {
            argsOption.add("-classpath");
            argsOption.add(classpath);
        }
        if (StringUtils.isNotBlank(sourcePath)) {
            argsOption.add("-sourcepath");
            argsOption.add(sourcePath);
        }
        argsOption.add(source);
        int returnCode = com.sun.tools.javadoc.Main.execute(JavaDocHandler.class.getClassLoader(),
                argsOption.toArray(new String[argsOption.size()]));
        if (0 != returnCode) {
            throw new IllegalStateException();
        }
        return root;
    }

    /**
     * 读取{@code source}指定源文件的注释
     *
     * @param source
     * @param classPath
     * @param sourcePath
     * @return
     */
    public synchronized static ExtClassDoc read(String source, String classPath, String sourcePath) {
        try {
            RootDoc rootDoc = readDocs(source, classPath, sourcePath);
            return new ExtClassDoc(rootDoc.classes()[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param prefix 文件路径
     * @param clazz class名字
     * @return
     */
    public static ExtClassDoc read(String prefix, Class<?> clazz) {
        return read(getSourceFile(prefix, clazz), classPath);
    }

    public synchronized static ExtClassDoc read(String source, String classPath) {
        return read(source, classPath, sourcePath);
    }

    /**
     * @return sourcePath
     */
    public static String getSourcePath() {
        return sourcePath;
    }

    /**
     * @param sourcePath 要设置的 sourcePath
     */
    public static void setSourcePath(String sourcePath) {
        JavaDocHandler.sourcePath = sourcePath;
        if (StringUtils.isNotBlank(sourcePath)) {
            sourcePathList = sourcePath.split("\\s*[,;]\\s*");
        }
    }

    /**
     * 返回类的源文件位置
     *
     * @param prefix 源文件夹,可为{@code null}
     * @param clazz 为{@code null}则返回{@code null}
     * @return 返回'/'分隔'.java'结尾的路径名,for example, '/home/src/java/awt/Graphics*java‘
     */
    public static String getSourceFile(String prefix, Class<?> clazz) {
        if (null != clazz) {
            String source = clazz.getName().replace('.', File.separatorChar) + ".java";
            return prefix == null ? source : prefix + File.separator + source;
        }
        return null;
    }

    /**
     * @see #read(String, String)
     */
    public static ExtClassDoc read(String source) {
        return read(source, classPath);
    }

}
