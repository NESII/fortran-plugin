package org.jetbrains.fortran.lang.parsing;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.psi.tree.IElementType;
import com.intellij.testFramework.ParsingTestCase;
import com.intellij.util.PathUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.fortran.lang.FortranNodeTypes;
import org.jetbrains.fortran.lang.parser.FortranParserDefinition;
import org.jetbrains.fortran.test.FortranTestDataFixture;

public abstract class FortranBaseParsingTestCase extends ParsingTestCase {
    @Override
    protected String getTestDataPath() {
        return FortranTestDataFixture.getLangTestData() + "/psi";
    }

    protected FortranBaseParsingTestCase() {
        super(".", "f", new FortranParserDefinition());
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }

    protected void doParsingTest(@NotNull String filePath) throws Exception {
        doBaseTest(filePath, FortranNodeTypes.FORTRAN_FILE);
    }

    private void doBaseTest(@NotNull String filePath, @NotNull IElementType fileType) throws Exception {
        myFileExt = FileUtilRt.getExtension(PathUtil.getFileName(filePath));
        myFile = createPsiFile(FileUtil.getNameWithoutExtension(PathUtil.getFileName(filePath)), loadFile(filePath));
        doCheckResult(myFullDataPath, filePath.replaceAll("\\.f", ".txt"), toParseTreeText(myFile, false, false).trim());
    }
}
