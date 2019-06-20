package de.tum.in.aflux.component.flink.api;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.squareup.javapoet.ClassName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API Mapper that makes use of JavaParser to automatically obtain class names, method names, etc.
 * from Flink API. JavaParser is used to process all files, which should be located in a zip
 * file under the <pre>resources</pre> folder.
 *
 * This class has been implemented according to the <strong>Singleton</strong> pattern.
 */
public class FlinkApiMapper {

    /**
     * Static attribute that contains the single instance.
     */
    private static FlinkApiMapper singleInstance = null;

    /**
     * Contains the mapping to classes and enums in the API. The keys are the simple name of the class.
     * For the purpose of scalability, the ClassName object is only created when required, so
     * this attribute serves as a cache. The complete mapping is available in <pre>classPackageMap</pre>,
     * which only contains Strings.
     */
    private Map<String, ClassName> classAndEnumCache;

    /**
     * Key is the simple name of the class. Value is the package name.
     */
    private Map<String, String> classPackageMap;

    /**
     * Contains the mapping to methods in the API.
     * The keys are the simple name of the class plus the name of the method, e.g "Tuple2.of".s
     */
    private Map<String, String> methodMap;

    /**
     * Contains all the compilation units processed by JavaParser.
     */
    private List<CompilationUnit> compilationUnits;

    /**
     * Name of the zip file containing the Flink source code. It should be placed under
     * the resources folder.
     */
    public static final String FLINK_RELEASE_FILE_NAME = "flink-release-1.4.2.zip";

    /**
     * Name of the zip file containing the Flink template source code. It should be placed under
     * the resources folder.
     */
    public static final String FLINK_TEMPLATE_FILE_NAME = "flink-template.zip";

    /**
     * Artifacts that will be processed for mapping. Please include just those that will really
     * be used, in order to ensure the best performance.
     */
    public static final String[] FLINK_API_ACCEPTED_ARTIFACTS = {
            "flink-connectors/flink-connector-kafka-0.8",
            "flink-core",
            "flink-java",
            "flink-libraries/flink-cep",
            "flink-streaming-java"
    };

    private FlinkApiMapper() {
        List<CompilationUnit> cuList = new ArrayList<CompilationUnit>();
        try {
            // process Flink API
            cuList.addAll(FlinkApiParser.processApiZipFile(
                    FLINK_RELEASE_FILE_NAME,
                    FLINK_API_ACCEPTED_ARTIFACTS));
            // process project template to include SmartSantander connector
            cuList.addAll(FlinkApiParser.processApiZipFile(
                    FLINK_TEMPLATE_FILE_NAME,
                    null));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> classesAndPackages = new HashMap<String, String>();
        Map<String, String> methods = new HashMap<String, String>();
        VoidVisitor<Map<String, String>> classNameVisitor = new FlinkApiVisitors.ClassAndEnumVisitor();
        VoidVisitor<Map<String, String>> methodNameVisitor = new FlinkApiVisitors.MethodAndEnumConstantVisitor();

        for (CompilationUnit cu : cuList) {
            classNameVisitor.visit(cu, classesAndPackages);
            methodNameVisitor.visit(cu, methods);
        }

        this.classPackageMap = classesAndPackages;
        this.classAndEnumCache = new HashMap<String, ClassName>();
        this.methodMap = methods;
        this.compilationUnits = cuList;
    }

    public static FlinkApiMapper getInstance() {
        if (singleInstance == null)
            singleInstance = new FlinkApiMapper();
        return singleInstance;
    }

    public ClassName getClassNameInstance(String simpleName) {
        if (classAndEnumCache.containsKey(simpleName))
            return classAndEnumCache.get(simpleName);
        else {
            String packageName = this.classPackageMap.get(simpleName);
            ClassName newClassName = ClassName.get(packageName, simpleName);
            classAndEnumCache.put(simpleName, newClassName); // store in cache
            return newClassName;
        }
    }

    public String getMethodName(String simpleName) {
        return this.methodMap.get(simpleName);
    }

    public List<ClassName> getChildClasses(String parentClassSimpleName) {
        List<String> childClassNames = new ArrayList<String>();
        VoidVisitor<List<String>> inheritanceVisitor = new FlinkApiVisitors.PolymorphismVisitor(parentClassSimpleName);
        for (CompilationUnit cu : compilationUnits) {
            inheritanceVisitor.visit(cu, childClassNames);
        }
        List<ClassName> result = new ArrayList<ClassName>();
        for (String s : childClassNames)
            result.add(ClassName.get(classPackageMap.get(s), s));
        return result;
    }
}
