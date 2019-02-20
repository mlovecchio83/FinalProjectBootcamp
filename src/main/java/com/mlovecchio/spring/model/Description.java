package com.mlovecchio.spring.model;

import com.sun.scenario.effect.Offset;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Embeddable
@Getter @Setter @NoArgsConstructor
@Table(name="descriptions")
public class Description {

/*    //@Id
    Long idDescription;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="items_id", nullable=false)
    Item item;*/

    @Size(min = 4, max = 30)
    private String webDescription;

    @Size(min=4, max=30)
    private String mobileDescription;

    public String getWebDescription() {
        return webDescription;
    }

    public void setWebDescription(String webDescription) {
        this.webDescription = webDescription;
    }

    public String getMobileDescription() {
        return mobileDescription;
    }

    public void setMobileDescription(String mobileDescription) {
        this.mobileDescription = mobileDescription;
    }





}
