package com.intellij.idea.plugin.hybris.tools.remote.action;

import com.intellij.idea.plugin.hybris.tools.remote.console.ExecuteHybrisConsole;
import com.intellij.idea.plugin.hybris.tools.remote.http.HybrisHacHttpClient;
import com.intellij.idea.plugin.hybris.tools.remote.http.impex.HybrisHttpResult;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.vfs.VirtualFile;


public class ExecuteFlexibleSearchAction extends AnAction implements DumbAware {

    @Override
    public void actionPerformed(final AnActionEvent e) {
        final Editor editor = CommonDataKeys.EDITOR.getData(e.getDataContext());
        if (editor != null) {
            final SelectionModel selectionModel = editor.getSelectionModel();
            final HybrisHacHttpClient client = HybrisHacHttpClient.getInstance(e.getProject());
            String content = selectionModel.getSelectedText();
            if (content == null || content.trim().isEmpty()) {
                content = editor.getDocument().getText();
            }
            final HybrisHttpResult hybrisHttpResult = client.executeFlexibleSearch(e.getProject(),true,false,"100", content);

            ExecuteHybrisConsole.getInstance().show(hybrisHttpResult, e.getProject());
        }
    }


    @Override
    public void update(final AnActionEvent e) {
        super.update(e);
        final VirtualFile file = e.getDataContext().getData(CommonDataKeys.VIRTUAL_FILE);
        final boolean enabled = file != null && file.getName().endsWith(".fxs");
        e.getPresentation().setEnabledAndVisible(enabled);
    }
}
