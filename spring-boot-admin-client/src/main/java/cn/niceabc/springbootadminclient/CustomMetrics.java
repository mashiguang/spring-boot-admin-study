package cn.niceabc.springbootadminclient;

import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Component
public class CustomMetrics implements PublicMetrics {
    @Override
    public Collection<Metric<?>> metrics() {
        List<Metric<?>> metrics = new LinkedList<Metric<?>>();

        metrics.add(new Metric("counter.custom.testMetric", 100));

        return metrics;
    }
}
