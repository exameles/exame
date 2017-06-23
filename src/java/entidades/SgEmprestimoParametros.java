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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sg_emprestimo_parametros", catalog = "fecn1", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SgEmprestimoParametros.findAll", query = "SELECT s FROM SgEmprestimoParametros s")
    , @NamedQuery(name = "SgEmprestimoParametros.findByIdparametro", query = "SELECT s FROM SgEmprestimoParametros s WHERE s.idparametro = :idparametro")
    , @NamedQuery(name = "SgEmprestimoParametros.findByDataDefinicao", query = "SELECT s FROM SgEmprestimoParametros s WHERE s.dataDefinicao = :dataDefinicao")
    , @NamedQuery(name = "SgEmprestimoParametros.findByTaxa", query = "SELECT s FROM SgEmprestimoParametros s WHERE s.taxa = :taxa")
    , @NamedQuery(name = "SgEmprestimoParametros.findByDescricao", query = "SELECT s FROM SgEmprestimoParametros s WHERE s.descricao = :descricao")})
public class SgEmprestimoParametros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idparametro")
    private Long idparametro;
    @Column(name = "data_definicao")
    private String dataDefinicao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "taxa")
    private Float taxa;
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "parametrosRef", fetch = FetchType.LAZY)
    private Collection<SgEmprestimo> sgEmprestimoCollection;
    @OneToMany(mappedBy = "idParametroActualizacao", fetch = FetchType.LAZY)
    private Collection<BLeitor> bLeitorCollection;
    @OneToMany(mappedBy = "idParametroRegisto", fetch = FetchType.LAZY)
    private Collection<BLeitor> bLeitorCollection1;
    @JoinColumn(name = "agente_bibliotecario", referencedColumnName = "utilizador")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users agenteBibliotecario;

    public SgEmprestimoParametros() {
    }

    public SgEmprestimoParametros(Long idparametro) {
        this.idparametro = idparametro;
    }

    public Long getIdparametro() {
        return idparametro;
    }

    public void setIdparametro(Long idparametro) {
        this.idparametro = idparametro;
    }

    public String getDataDefinicao() {
        return dataDefinicao;
    }

    public void setDataDefinicao(String dataDefinicao) {
        this.dataDefinicao = dataDefinicao;
    }

    public Float getTaxa() {
        return taxa;
    }

    public void setTaxa(Float taxa) {
        this.taxa = taxa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<SgEmprestimo> getSgEmprestimoCollection() {
        return sgEmprestimoCollection;
    }

    public void setSgEmprestimoCollection(Collection<SgEmprestimo> sgEmprestimoCollection) {
        this.sgEmprestimoCollection = sgEmprestimoCollection;
    }

    @XmlTransient
    public Collection<BLeitor> getBLeitorCollection() {
        return bLeitorCollection;
    }

    public void setBLeitorCollection(Collection<BLeitor> bLeitorCollection) {
        this.bLeitorCollection = bLeitorCollection;
    }

    @XmlTransient
    public Collection<BLeitor> getBLeitorCollection1() {
        return bLeitorCollection1;
    }

    public void setBLeitorCollection1(Collection<BLeitor> bLeitorCollection1) {
        this.bLeitorCollection1 = bLeitorCollection1;
    }

    public Users getAgenteBibliotecario() {
        return agenteBibliotecario;
    }

    public void setAgenteBibliotecario(Users agenteBibliotecario) {
        this.agenteBibliotecario = agenteBibliotecario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idparametro != null ? idparametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SgEmprestimoParametros)) {
            return false;
        }
        SgEmprestimoParametros other = (SgEmprestimoParametros) object;
        if ((this.idparametro == null && other.idparametro != null) || (this.idparametro != null && !this.idparametro.equals(other.idparametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SgEmprestimoParametros[ idparametro=" + idparametro + " ]";
    }
    
}
