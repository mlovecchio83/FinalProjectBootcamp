package com.mlovecchio.spring.Payload;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ModRequest {

    private long id;

    private String name;

    private String webDescription;

    private String mobileDescription;

    private Long game;

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

    public String getWebDescription() {
        return webDescription;
    }

    public void setWebDescription(String webDescritpion) {
        this.webDescription = webDescritpion;
    }

    public String getMobileDescription() {
        return mobileDescription;
    }

    public void setMobileDescription(String mobileDescription) {
        this.mobileDescription = mobileDescription;
    }

    public Long getGame() {
        return game;
    }

    public void setGame(Long game) {
        this.game = game;
    }


}
