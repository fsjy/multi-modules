package org.activiti.engine.impl.variable;

public class MapType implements VariableType {
    /**
     * name of variable type (limited to 100 characters length)
     */
    @Override
    public String getTypeName() {
        return "map";
    }

    /**
     * <p>Indicates if this variable type supports caching.</p>
     * <p>If caching is supported, the result of {@link #getValue(ValueFields)} is saved for the
     * duration of the session and used for subsequent reads of the variable's value.</p>
     * <p>If caching is not supported, all reads of a variable's value require a
     * fresh call to {@link #getValue(ValueFields)}.</p>
     *
     * @return whether variables of this type are cacheable.
     */
    @Override
    public boolean isCachable() {
        return false;
    }

    /**
     * @param value
     * @return whether this variable type can store the specified value.
     */
    @Override
    public boolean isAbleToStore(Object value) {
        return true;
    }

    /**
     * Stores the specified value in the supplied {@link ValueFields}.
     *
     * @param value
     * @param valueFields
     */
    @Override
    public void setValue(Object value, ValueFields valueFields) {

    }

    /**
     * @param valueFields
     * @return the value of a variable based on the specified {@link ValueFields}.
     */
    @Override
    public Object getValue(ValueFields valueFields) {
        return null;
    }
}
