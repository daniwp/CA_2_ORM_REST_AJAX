package facade;

import entities.Address;
import entities.Company;
import entities.Phone;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Daniel
 */
public class CompanyFacade implements ICompanyFacade{
    
    EntityManagerFactory emf;

    private static CompanyFacade instance = null;

    public static CompanyFacade getInstance() {
        if (instance == null) {
            instance = new CompanyFacade(Persistence.createEntityManagerFactory("CA2REST_PU"));
        }
        return instance;
    }

    public CompanyFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Company addCompany(Company company) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            
            em.persist(company);
            
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return company;
    }

    @Override
    public Company getCompany(String cvr) {
        Company c = null;
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            c = em.createQuery("SELECT c FROM Company c WHERE c.cvr = :cvr", Company.class).setParameter("cvr", cvr).getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();

        }

        return c;
    }

    @Override
    public List<Company> getCompanies() {

        List<Company> companies = new ArrayList();
        EntityManager em = getEntityManager();

        try {
            companies = em.createQuery("SELECT c FROM Company c", Company.class).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return companies;

    }

    @Override
    public Company addPhoneToCompany(Phone phone, long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Company addAddressToCompany(Address address, long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Company deleteCompany(String cvr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
