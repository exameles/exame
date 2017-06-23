/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;

import Controladora.exceptions.NonexistentEntityException;
import Controladora.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.Categoria;
import entidades.Grupo;
import entidades.Item;
import entidades.Roles;
import entidades.RolesPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Migueljr
 */
public class RolesJpaController implements Serializable {

    public RolesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Roles roles) throws PreexistingEntityException, Exception {
        if (roles.getRolesPK() == null) {
            roles.setRolesPK(new RolesPK());
        }
        roles.getRolesPK().setIdCategoria(roles.getCategoria().getIdCategoria());
        roles.getRolesPK().setIdItem(roles.getItem().getItem());
        roles.getRolesPK().setIdGrupo(roles.getGrupo().getIdGrupo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria categoria = roles.getCategoria();
            if (categoria != null) {
                categoria = em.getReference(categoria.getClass(), categoria.getIdCategoria());
                roles.setCategoria(categoria);
            }
            Grupo grupo = roles.getGrupo();
            if (grupo != null) {
                grupo = em.getReference(grupo.getClass(), grupo.getIdGrupo());
                roles.setGrupo(grupo);
            }
            Item item = roles.getItem();
            if (item != null) {
                item = em.getReference(item.getClass(), item.getItem());
                roles.setItem(item);
            }
            em.persist(roles);
            if (categoria != null) {
                categoria.getRolesCollection().add(roles);
                categoria = em.merge(categoria);
            }
            if (grupo != null) {
                grupo.getRolesCollection().add(roles);
                grupo = em.merge(grupo);
            }
            if (item != null) {
                item.getRolesCollection().add(roles);
                item = em.merge(item);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRoles(roles.getRolesPK()) != null) {
                throw new PreexistingEntityException("Roles " + roles + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Roles roles) throws NonexistentEntityException, Exception {
        roles.getRolesPK().setIdCategoria(roles.getCategoria().getIdCategoria());
        roles.getRolesPK().setIdItem(roles.getItem().getItem());
        roles.getRolesPK().setIdGrupo(roles.getGrupo().getIdGrupo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles persistentRoles = em.find(Roles.class, roles.getRolesPK());
            Categoria categoriaOld = persistentRoles.getCategoria();
            Categoria categoriaNew = roles.getCategoria();
            Grupo grupoOld = persistentRoles.getGrupo();
            Grupo grupoNew = roles.getGrupo();
            Item itemOld = persistentRoles.getItem();
            Item itemNew = roles.getItem();
            if (categoriaNew != null) {
                categoriaNew = em.getReference(categoriaNew.getClass(), categoriaNew.getIdCategoria());
                roles.setCategoria(categoriaNew);
            }
            if (grupoNew != null) {
                grupoNew = em.getReference(grupoNew.getClass(), grupoNew.getIdGrupo());
                roles.setGrupo(grupoNew);
            }
            if (itemNew != null) {
                itemNew = em.getReference(itemNew.getClass(), itemNew.getItem());
                roles.setItem(itemNew);
            }
            roles = em.merge(roles);
            if (categoriaOld != null && !categoriaOld.equals(categoriaNew)) {
                categoriaOld.getRolesCollection().remove(roles);
                categoriaOld = em.merge(categoriaOld);
            }
            if (categoriaNew != null && !categoriaNew.equals(categoriaOld)) {
                categoriaNew.getRolesCollection().add(roles);
                categoriaNew = em.merge(categoriaNew);
            }
            if (grupoOld != null && !grupoOld.equals(grupoNew)) {
                grupoOld.getRolesCollection().remove(roles);
                grupoOld = em.merge(grupoOld);
            }
            if (grupoNew != null && !grupoNew.equals(grupoOld)) {
                grupoNew.getRolesCollection().add(roles);
                grupoNew = em.merge(grupoNew);
            }
            if (itemOld != null && !itemOld.equals(itemNew)) {
                itemOld.getRolesCollection().remove(roles);
                itemOld = em.merge(itemOld);
            }
            if (itemNew != null && !itemNew.equals(itemOld)) {
                itemNew.getRolesCollection().add(roles);
                itemNew = em.merge(itemNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RolesPK id = roles.getRolesPK();
                if (findRoles(id) == null) {
                    throw new NonexistentEntityException("The roles with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RolesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles roles;
            try {
                roles = em.getReference(Roles.class, id);
                roles.getRolesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The roles with id " + id + " no longer exists.", enfe);
            }
            Categoria categoria = roles.getCategoria();
            if (categoria != null) {
                categoria.getRolesCollection().remove(roles);
                categoria = em.merge(categoria);
            }
            Grupo grupo = roles.getGrupo();
            if (grupo != null) {
                grupo.getRolesCollection().remove(roles);
                grupo = em.merge(grupo);
            }
            Item item = roles.getItem();
            if (item != null) {
                item.getRolesCollection().remove(roles);
                item = em.merge(item);
            }
            em.remove(roles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Roles> findRolesEntities() {
        return findRolesEntities(true, -1, -1);
    }

    public List<Roles> findRolesEntities(int maxResults, int firstResult) {
        return findRolesEntities(false, maxResults, firstResult);
    }

    private List<Roles> findRolesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Roles as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Roles findRoles(RolesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Roles.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolesCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Roles as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
