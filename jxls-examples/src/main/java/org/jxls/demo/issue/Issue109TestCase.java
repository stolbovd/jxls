package org.jxls.demo.issue;

import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.formula.StandardFormulaProcessor;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wrong average on 2nd sheet
 */
public class Issue109TestCase {
    
    // --------- SETTINGS ---------
    static List<Double> EMPTY_LIST = new ArrayList<>();
    final static String EMPTY_LIST_NAME = "emptyList";

    final static String INPUT_FILE_PATH = "issue109_template.xls";
    final static String OUTPUT_FILE_PATH = "target/issue109_output.xls";

    // --------- -------- ---------

    public static void main(String[] args) throws IOException {

    	
        try(InputStream is = Issue109TestCase.class.getResourceAsStream(INPUT_FILE_PATH)) {
            try (OutputStream os = new FileOutputStream(OUTPUT_FILE_PATH)) {
                Context context = new Context();
                context.putVar(EMPTY_LIST_NAME, EMPTY_LIST);

                JxlsHelper.getInstance().processTemplate(is, os, context);
            }
        }
    }
}