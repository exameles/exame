/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Migueljr
 */
@Entity
@Table(name = "sg_exemplar", catalog = "fecn1", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SgExemplar.findAll", query = "SELECT s FROM SgExemplar s")
    , @NamedQuery(name = "SgExemplar.findByNrRegisto", query = "SELECT s FROM SgExemplar s WHERE s.nrRegisto = :nrRegisto")
    , @NamedQuery(name = "SgExemplar.findByDataAquisicao", query = "SELECT s FROM SgExemplar s WHERE s.dataAquisicao = :dataAquisicao")
    , @NamedQuery(name = "SgExemplar.findByDataRegisto", query = "SELECT s FROM SgExemplar s WHERE s.dataRegisto = :dataRegisto")
    , @NamedQuery(name = "SgExemplar.findByEstado", query = "SELECT s FROM SgExemplar s WHERE s.estado = :estado")
    , @NamedQuery(name = "SgExemplar.findByForma", query = "SELECT s FROM SgExemplar s WHERE s.forma = :forma")
    , @NamedQuery(name = "SgExemplar.findByMotivoRemocao", query = "SELECT s FROM SgExemplar s WHERE s.motivoRemocao = :motivoRemocao")})
public class SgExemplar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nr_registo")
    private Long nrRegisto;
    @Column(name = "data_aquisicao")
    @Temporal(TemporalType.DATE)
    private Date dataAquisicao;
    @Column(name = "data_registo")
    @Temporal(TemporalType.DATE)
    private Date dataRegisto;
    @Column(name = "estado")
    private String estado;
    @Column(name = "forma")
    private String forma;
    @Column(name = "motivo_remocao")
    private String motivoRemocao;
    @JoinTable(name = "sg_obra_sg_exemplar", joinColumns = {
        @JoinColumn(name = "getsgexemplarlist_nr_registo", referencedColumnName = "nr_registo")}, inverseJoinColumns = {
        @JoinColumn(name = "sgobra_idlivro", referencedColumnName = "idlivro")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<SgObra> sgObraCollection;
    @OneToMany(mappedBy = "exemplarRef", fetch = FetchType.LAZY)
    private Collection<SgEmprestimo> sgEmprestimoCollection;
    @JoinColumn(name = "obra_ref", referencedColumnName = "idlivro")
    @ManyToOne(fetch = FetchType.LAZY)
    private SgObra obraRef;
    @JoinColumn(name = "agente_registo", referencedColumnName = "utilizador")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users agenteRegisto;
    @OneToMany(mappedBy = "livro", fetch = FetchType.LAZY)
    private Collection<BReserva> bReservaCollection;

    public SgExemplar() {
    }

    public SgExemplar(Long nrRegisto) {
        this.nrRegisto = nrRegisto;
    }

    public Long getNrRegisto() {
        return nrRegisto;
    }

    public void setNrRegisto(Long nrRegisto) {
        this.nrRegisto = nrRegisto;
    }

    public Date getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public Date getDataRegisto() {
        return dataRegisto;
    }

    public void setDataRegisto(Date dataRegisto) {
        this.dataRegisto = dataRegisto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public String getMotivoRemocao() {
        return motivoRemocao;
    }

    public void setMotivoRemocao(String motivoRemocao) {
        this.motivoRemocao = motivoRemocao;
    }

    @XmlTransient
    public Collection<SgObra> getSgObraCollection() {
        return sgObraCollection;
    }

    public void setSgObraCollection(Collection<SgObra> sgObraCollection) {
        this.sgObraCollection = sgObraCollection;
    }

    @XmlTransient
    public Collection<SgEmprestimo> getSgEmprestimoCollection() {
        return sgEmprestimoCollection;
    }

    public void setSgEmprestimoCollection(Collection<SgEmprestimo> sgEmprestimoCollection) {
        this.sgEmprestimoCollection = sgEmprestimoCollection;
    }

    public SgObra getObraRef() {
        return obraRef;
    }

    public void setObraRef(SgObra obraRef) {
        this.obraRef = obraRef;
    }

    public Users getAgenteRegisto() {
        return agenteRegisto;
    }

    public void setAgenteRegisto(Users agenteRegisto) {
        this.agenteRegisto = agenteRegisto;
    }

    @XmlTransient
    public Collection<BReserva> getBReservaCollection() {
        return bReservaCollection;
    }

    public void setBReservaCollection(Collection<BReserva> bReservaCollection) {
        this.bReservaCollection = bReservaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nrRegisto != null ? nrRegisto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SgExemplar)) {
            return false;
        }
        SgExemplar other = (SgExemplar) object;
        if ((this.nrRegisto == null && other.nrRegisto != null) || (this.nrRegisto != null && !this.nrRegisto.equals(other.nrRegisto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SgExemplar[ nrRegisto=" + nrRegisto + " ]";
    }
    
}
