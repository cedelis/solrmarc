package playground.solrmarc.index.mapping.impl;

import java.util.ArrayList;
import java.util.Collection;

import playground.solrmarc.index.extractor.methodcall.MultiValueMappingMethodCall;
import playground.solrmarc.index.extractor.methodcall.SingleValueMappingMethodCall;
import playground.solrmarc.index.mapping.AbstractMultiValueMapping;

public class MethodCallSingleValueMapping extends AbstractMultiValueMapping
{

    private final Object[] parameters;
    private final SingleValueMappingMethodCall methodCall;

    public MethodCallSingleValueMapping(SingleValueMappingMethodCall methodCall, String[] parameters)
    {
        this.methodCall = methodCall;
        this.parameters = new Object[parameters.length + 1];
        System.arraycopy(parameters, 0, this.parameters, 1, parameters.length);
    }

    @Override
    public Collection<String> map(Collection<String> values) throws Exception
    {
        Collection<String> result = new ArrayList<String>(values.size());
        for (String value : values)
        {
            String oneResult = (String) (methodCall.invoke(value, parameters));
            
            if (oneResult != null) result.add(oneResult);
        }
        return(result);
    }

}