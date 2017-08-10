package cn.niceabc.springbootadminclient.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HttpTraceRepository extends JpaRepository<HttpTrace, Long> {
}
