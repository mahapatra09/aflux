package de.tum.in.aflux.component.flink.api;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.*;

/**
 * This class includes the JavaParser Visitors that will be used to analyze the Flink API,
 * e.g. to extract package, class and method names.
 */
public class FlinkApiVisitors {

    /**
     * Returns the parent node of the parameter node, casted to the desired parent type to be searched for.
     * @param node child node.
     * @param parentType type of parent that is to be searched for.
     * @param <T> type of parent that is to be searched for.
     * @return the parent node or null if no parent of the specified type could be found.
     */
    public static <T> T getParentNode(Node node, Class<T> parentType) {
        Optional<Node> parent = node.getParentNode();
        T returnNode = null;
        while (parent.isPresent() && returnNode == null) {
            Node parentNode = parent.get();
            if (parentType.isInstance(parentNode))
                returnNode = (T)parentNode;
            else
                parent = parentNode.getParentNode();
        }
        return returnNode;
    }

    /**
     * Retrieves the list of children of a certain type of a given node.
     * @param node parent node.
     * @param childType type of children that is to be searched for.
     * @param <T> type of parent that is to be searched for.
     * @return a list of children nodes with the desired type.
     */
    public static <T> List<T> getChildNodes(Node node, Class<T> childType) {
        List<T> returnNodes = new ArrayList<T>();
        List<Node> childNodes = node.getChildNodes();

        Iterator<Node> childNodesIterator = childNodes.iterator();
        while (childNodesIterator.hasNext()) {
            Node n = childNodesIterator.next();
            if (childType.isInstance(n))
                returnNodes.add((T)n);
            returnNodes.addAll(getChildNodes(n, childType));
        }
        return returnNodes;
    }

    /**
     * This visitor is triggered in every declaration of Class, Interface or Enum. It stores
     * ([class name], [package full name]) in a Map that is later returned.
     */
    public static class ClassAndEnumVisitor extends VoidVisitorAdapter<Map<String, String>> {

        @Override
        public void visit(ClassOrInterfaceDeclaration classDeclaration, Map<String, String> collector) {
            super.visit(classDeclaration, collector);

            CompilationUnit cu = getParentNode(classDeclaration, CompilationUnit.class);
            PackageDeclaration packageDeclaration = cu.getPackageDeclaration().get();
            ClassOrInterfaceDeclaration superClass = getParentNode(classDeclaration, ClassOrInterfaceDeclaration.class);
            String packageName = packageDeclaration.getNameAsString();
            if (superClass != null) {
                packageName += "." + superClass.getNameAsString();
            }
            collector.put(classDeclaration.getNameAsString(), packageName);
        }

        @Override
        public void visit(EnumDeclaration enumDeclaration, Map<String, String> collector) {
            super.visit(enumDeclaration, collector);

            CompilationUnit cu = getParentNode(enumDeclaration, CompilationUnit.class);
            PackageDeclaration packageDeclaration = cu.getPackageDeclaration().get();
            ClassOrInterfaceDeclaration superClass = getParentNode(enumDeclaration, ClassOrInterfaceDeclaration.class);
            String packageName = packageDeclaration.getNameAsString();
            if (superClass != null) {
                packageName += "." + superClass.getNameAsString();
            }
            collector.put(enumDeclaration.getNameAsString(), packageName);
        }
    }

    /**
     * This visitor is triggered in every method and enum constant declaration. It stores
     * ([class name].[method/enum constant name], [method/enum constant name]) in a Map that is later returned.
     */
    public static class MethodAndEnumConstantVisitor extends VoidVisitorAdapter<Map<String, String>> {
        @Override
        public void visit(MethodDeclaration methodDeclaration, Map<String, String> collector) {
            super.visit(methodDeclaration, collector);

            ClassOrInterfaceDeclaration classDeclaration = getParentNode(methodDeclaration, ClassOrInterfaceDeclaration.class);
            if (classDeclaration != null)
                collector.put(classDeclaration.getNameAsString() + "." + methodDeclaration.getNameAsString(),
                        methodDeclaration.getNameAsString());
        }

        @Override
        public void visit(EnumConstantDeclaration enumConstantDeclaration, Map<String, String> collector) {
            super.visit(enumConstantDeclaration, collector);

            EnumDeclaration enumDeclaration = getParentNode(enumConstantDeclaration, EnumDeclaration.class);
            if (enumDeclaration != null)
                collector.put(enumDeclaration.getNameAsString() + "." + enumConstantDeclaration.getNameAsString(),
                        enumConstantDeclaration.getNameAsString());
        }
    }

    /**
     * This visitor is triggered in every class or interface declaration. It stores the list of classes
     * that extend/implement a certain class/interface (specified in the field).
     */
    public static class PolymorphismVisitor extends VoidVisitorAdapter<List<String>> {
        private String parentClassSimpleName;

        public PolymorphismVisitor(String parentClassSimpleName) {
            this.parentClassSimpleName = parentClassSimpleName;
        }

        @Override
        public void visit(ClassOrInterfaceDeclaration classDeclaration, List<String> collector) {
            super.visit(classDeclaration, collector);

            for (ClassOrInterfaceType type : classDeclaration.getImplementedTypes()) {
                if (type.getName().toString().equals(parentClassSimpleName))
                    collector.add(classDeclaration.getNameAsString());
            }
            for (ClassOrInterfaceType type : classDeclaration.getExtendedTypes()) {
                if (type.getName().toString().equals(parentClassSimpleName))
                    collector.add(classDeclaration.getNameAsString());
            }
        }
    }

}
