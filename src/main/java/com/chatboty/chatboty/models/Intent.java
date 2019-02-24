package com.chatboty.chatboty.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Intent {

    @Id
    private ObjectId _id;

    private String tag;

    private List<String> patterns;

    private List<String> responses;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<String> getPatterns() {
        return patterns;
    }

    public void setPatterns(List<String> patterns) {
        this.patterns = patterns;
    }

    public List<String> getResponses() {
        return responses;
    }

    public void setResponses(List<String> responses) {
        this.responses = responses;
    }
}

