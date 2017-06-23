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
@Table(name = "faculdade", catalog = "fecn1", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Faculdade.findAll", query = "SELECT f FROM Faculdade f")
    , @NamedQuery(name = "Faculdade.findByIdFaculdade", query = "SELECT f FROM Faculdade f WHERE f.idFaculdade = :idFaculdade")
    , @NamedQuery(name = "Faculdade.findByDesricao", query = "SELECT f FROM Faculdade f WHERE f.desricao = :desricao")
    , @NamedQuery(name = "Faculdade.findByAbreviatura", query = "SELECT f FROM Faculdade f WHERE f.abreviatura = :abreviatura")
    , @NamedQuery(name = "Faculdade.findByDirector", query = "SELECT f FROM Faculdade f WHERE f.director = :director")
    , @NamedQuery(name = "Faculdade.findByEndereco", query = "SELECT f FROM Faculdade f WHERE f.endereco = :endereco")})
public class Faculdade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_faculdade")
    private Integer idFaculdade;
    @Column(name = "desricao")
    private String desricao;
    @Column(name = "abreviatura")
    private String abreviatura;
    @Basic(optional = false)
    @Column(name = "director")
    private long director;
    @Column(name = "endereco")
    private String endereco;
    @OneToMany(mappedBy = "faculdade", fetch = FetchType.LAZY)
    private Collection<Funcionario> funcionarioCollection;
    @OneToMany(mappedBy = "faculdade", fetch = FetchType.LAZY)
    private Collection<Users> usersCollection;

    public Faculdade() {
    }

    public Faculdade(Integer idFaculdade) {
        this.idFaculdade = idFaculdade;
    }

    public Faculdade(Integer idFaculdade, long director) {
        this.idFaculdade = idFaculdade;
        this.director = director;
    }

    public Integer getIdFaculdade() {
        return idFaculdade;
    }

    public void setIdFaculdade(Integer idFaculdade) {
        this.idFaculdade = idFaculdade;
    }

    public String getDesricao() {
        return desricao;
    }

    public void setDesricao(String desricao) {
        this.desricao = desricao;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public long getDirector() {
        return director;
    }

    public void setDirector(long director) {
        this.director = director;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @XmlTransient
    public Collection<Funcionario> getFuncionarioCollection() {
        return funcionarioCollection;
    }

    public void setFuncionarioCollection(Collection<Funcionario> funcionarioCollection) {
        this.funcionarioCollection = funcionarioCollection;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFaculdade != null ? idFaculdade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Faculdade)) {
            return false;
        }
        Faculdade other = (Faculdade) object;
        if ((this.idFaculdade == null && other.idFaculdade != null) || (this.idFaculdade != null && !this.idFaculdade.equals(other.idFaculdade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Faculdade[ idFaculdade=" + idFaculdade + " ]";
    }
    
}
