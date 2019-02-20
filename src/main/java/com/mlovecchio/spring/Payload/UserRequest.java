package com.mlovecchio.spring.Payload;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UserRequest {

    private long id;

    private String name;

    private Long item;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGame() {
        return item;
    }

    public void setGame(Long game) {
        this.item = item;
    }


}
