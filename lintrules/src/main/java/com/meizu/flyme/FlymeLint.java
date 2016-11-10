package com.meizu.flyme;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import java.util.Arrays;
import java.util.List;

/**
 * 注册Flyme的自定义Lint规则集合
 */
public class FlymeLint extends IssueRegistry {

    @Override
    public synchronized List<Issue> getIssues() {
        System.out.println("==== Flyme lint start ====");
        return Arrays.asList(
                LogDetector.ISSUE
        );
    }
}
