package com.meizu.flyme;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;

import java.util.Arrays;
import java.util.List;

/**
 * 测试自定义lint规则功能,参考自自带的LogDetector
 * <p>
 * Updated by hujiawei on 11/11/16.
 */
public class LogDetector extends Detector implements Detector.JavaPsiScanner {

    private static final Implementation IMPLEMENTATION = new Implementation(
            LogDetector.class, Scope.JAVA_FILE_SCOPE);

    public static final Issue LOGUSAGEERROR = Issue.create(
            "LogUsageError",
            "请不要使用System.out.println",
            "请不要使用System.out.println,推荐使用android.util.Log",
            Category.SECURITY, 5, Severity.WARNING,
            IMPLEMENTATION);

    private static final String PRINTLN = "println";

    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList(PRINTLN);
    }

    @Override
    public void visitMethod(JavaContext context, JavaElementVisitor visitor, PsiMethodCallExpression node, PsiMethod method) {
        String name = method.getName();
        if (context.isEnabled(LOGUSAGEERROR) && PRINTLN.equals(name)){
            String message = "请不要使用System.out.println,推荐使用android.util.Log";
            context.report(LOGUSAGEERROR, node, context.getLocation(node), message);
        }
    }

}