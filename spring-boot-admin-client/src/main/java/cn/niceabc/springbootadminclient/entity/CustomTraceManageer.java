package cn.niceabc.springbootadminclient.entity;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.Trace;
import org.springframework.boot.actuate.trace.TraceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CustomTraceManageer implements TraceRepository {

    @Autowired
    private HttpTraceRepository httpTraceRepository;

    private Gson gson = new Gson();

    @Override
    public List<Trace> findAll() {

        List<Trace> result = new ArrayList<>();
        // List<HttpTrace> all = httpTraceRepository.findAll();
        List<HttpTrace> all = httpTraceRepository.findTop100OrOrderByIdDesc();
        all.forEach(httpTrace -> {
            Trace trace = gson.fromJson(httpTrace.getContent(), Trace.class);
            result.add(trace);
        });

        System.out.println("from db.");

        return result;
    }

    @Override
    public void add(Map<String, Object> traceInfo) {

        Date now = new Date();
        Trace trace = new Trace(now, traceInfo);

        HttpTrace httpTrace = new HttpTrace();
        httpTrace.setContent(gson.toJson(trace));
        httpTrace.setTimestamp(now);
        httpTrace.setTimeTaken(Long.valueOf(traceInfo.get("timeTaken").toString()));

        httpTraceRepository.save(httpTrace);
    }
}
