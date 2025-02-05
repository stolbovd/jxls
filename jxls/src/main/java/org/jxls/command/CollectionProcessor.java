package org.jxls.command;

import org.jxls.common.Context;
import org.jxls.expression.ExpressionEvaluator;
import org.jxls.util.UtilWrapper;

public abstract class CollectionProcessor {

    public void traverse(Context context, Iterable<?> itemsCollection, String varName, String varIndex,
            ExpressionEvaluator selectEvaluator, UtilWrapper util) {
        Object currentVarObject = varName == null ? null : context.getRunVar(varName);
        Object currentVarIndexObject = varIndex == null ? null : context.getRunVar(varIndex);
        int currentIndex = 0;
        for (Object obj : itemsCollection) {
            context.putVar(varName, obj);
            if (varIndex != null) {
                context.putVar(varIndex, currentIndex);
            }
            if (selectEvaluator != null && !util.isConditionTrue(selectEvaluator, context)) {
                continue;
            }
            if (processItem(obj)) {
                break;
            }
            currentIndex++;
        }
        restoreVarObject(context, varIndex, currentVarIndexObject);
        restoreVarObject(context, varName, currentVarObject);
    }
    
    /**
     * @param obj -
     * @return true: break, false: go on
     */
    protected abstract boolean processItem(Object obj);

    private void restoreVarObject(Context context, String varName, Object varObject) {
        if (varName == null) {
            return;
        }
        if (varObject != null) {
            context.putVar(varName, varObject);
        } else {
            context.removeVar(varName);
        }
    }
}
