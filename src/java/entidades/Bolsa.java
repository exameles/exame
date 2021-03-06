/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Migueljr
 */
@Entity
@Table(name = "bolsa", catalog = "fecn1", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bolsa.findAll", query = "SELECT b FROM Bolsa b")
    , @NamedQuery(name = "Bolsa.findByIdBolsa", query = "SELECT b FROM Bolsa b WHERE b.idBolsa = :idBolsa")
    , @NamedQuery(name = "Bolsa.findByDescricao", query = "SELECT b FROM Bolsa b WHERE b.descricao = :descricao")})
public class Bolsa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_bolsa")
    private Long idBolsa;
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "bolsa", fetch = FetchType.LAZY)
    private Collection<Estudante> estudanteCollection;

    public Bolsa() {
    }

    public Bolsa(Long idBolsa) {
        this.idBolsa = idBolsa;
    }

    public Long getIdBolsa() {
        return idBolsa;
    }

    public void setIdBolsa(Long idBolsa) {
        this.idBolsa = idBolsa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<Estudante> getEstudanteCollection() {
        return estudanteCollection;
    }

    public void setEstudanteCollection(Collection<Estudante> estudanteCollection) {
        this.estudanteCollection = estudanteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBolsa != null ? idBolsa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bolsa)) {
            return false;
        }
        Bolsa other = (Bolsa) object;
        if ((this.idBolsa == null && other.idBolsa != null) || (this.idBolsa != null && !this.idBolsa.equals(other.idBolsa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Bolsa[ idBolsa=" + idBolsa + " ]";
    }
    
}
