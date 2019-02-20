package com.mlovecchio.spring.Payload;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransactionRequest {

    long id;

    long idUser;

    long idGame;

    long idMod;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdGame() {
        return idGame;
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }

    public long getIdMod() {
        return idMod;
    }

    public void setIdMod(long idMod) {
        this.idMod = idMod;
    }


}
