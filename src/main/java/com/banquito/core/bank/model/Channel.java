package com.banquito.core.bank.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "CHANNEL")
public class Channel implements Serializable {

    @Id
    @Column(name = "CODE_CHANNEL", length = 10, nullable = false)
    private String code;

    @Column(name = "CODE_BANK", length = 20, nullable = false)
    private String codeBank;

    @Column(name = "NAME", length = 20, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "CODE_BANK", referencedColumnName = "CODE_BANK", insertable = false, updatable = false)
    private Bank bank;

    public Channel(String code) {
        this.code = code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((codeBank == null) ? 0 : codeBank.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Channel other = (Channel) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (codeBank == null) {
            if (other.codeBank != null)
                return false;
        } else if (!codeBank.equals(other.codeBank))
            return false;
        return true;
    }

}
