package cn.niceabc.springbootadminclient.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "http_trace")
public class HttpTrace{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date timestamp;

    @Column(name = "time_taken")
    private long timeTaken;

    @Column(columnDefinition = "text")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
