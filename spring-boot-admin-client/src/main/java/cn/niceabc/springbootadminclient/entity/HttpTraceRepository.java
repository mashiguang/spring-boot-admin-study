package cn.niceabc.springbootadminclient.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HttpTraceRepository extends JpaRepository<HttpTrace, Long> {

    List<HttpTrace> findTop100OrOrderByIdDesc();
}
