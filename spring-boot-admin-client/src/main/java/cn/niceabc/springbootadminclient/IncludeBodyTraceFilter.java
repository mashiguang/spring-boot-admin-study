package cn.niceabc.springbootadminclient;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.actuate.trace.TraceProperties;
import org.springframework.boot.actuate.trace.TraceRepository;
import org.springframework.boot.actuate.trace.WebRequestTraceFilter;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public class IncludeBodyTraceFilter extends WebRequestTraceFilter {

    public IncludeBodyTraceFilter(TraceRepository repository,
                                  TraceProperties properties,
                                  ObjectProvider<ErrorAttributes> errorAttributes) {
        super(repository, properties);

        if (errorAttributes != null)
            super.setErrorAttributes(errorAttributes.getIfAvailable());
    }

    @Override
    protected Map<String, Object> getTrace(HttpServletRequest request) {
        Map<String, Object> trace = super.getTrace(request);

        if ("POST".equals(request.getMethod()) || "PUT".equals(request.getMethod())) {
            try {
                request.getReader().mark(0);

                trace.put("body", IOUtils.toString(request.getReader()));

                request.getReader().reset();
            } catch (IOException e) {
                e.printStackTrace();
                trace.put("body", e.getMessage());
            }
        }

        return trace;
    }
}
