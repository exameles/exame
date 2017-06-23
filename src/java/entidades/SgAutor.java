/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "sg_autor", catalog = "fecn1", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SgAutor.findAll", query = "SELECT s FROM SgAutor s")
    , @NamedQuery(name = "SgAutor.findByIdautor", query = "SELECT s FROM SgAutor s WHERE s.idautor = :idautor")
    , @NamedQuery(name = "SgAutor.findByNome", query = "SELECT s FROM SgAutor s WHERE s.nome = :nome")})
public class SgAutor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idautor")
    private Long idautor;
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sgAutor", fetch = FetchType.LAZY)
    private Collection<SgObraAutor> sgObraAutorCollection;

    public SgAutor() {
    }

    public SgAutor(Long idautor) {
        this.idautor = idautor;
    }

    public Long getIdautor() {
        return idautor;
    }

    public void setIdautor(Long idautor) {
        this.idautor = idautor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public Collection<SgObraAutor> getSgObraAutorCollection() {
        return sgObraAutorCollection;
    }

    public void setSgObraAutorCollection(Collection<SgObraAutor> sgObraAutorCollection) {
        this.sgObraAutorCollection = sgObraAutorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idautor != null ? idautor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SgAutor)) {
            return false;
        }
        SgAutor other = (SgAutor) object;
        if ((this.idautor == null && other.idautor != null) || (this.idautor != null && !this.idautor.equals(other.idautor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SgAutor[ idautor=" + idautor + " ]";
    }
    
}
