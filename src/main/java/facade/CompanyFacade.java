package facade;

import entities.Address;
import entities.CityInfo;
import entities.Company;
import entities.Person;
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
public class CompanyFacade implements ICompanyFacade {

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

            if (company.getAddress() != null) {

                CityInfo city = getCityInfo(company.getAddress().getCity().getZipCode());

                if (city != null) {
                    company.getAddress().setCity(city);
                    em.merge(company);
                    em.getTransaction().commit();
                    return company;
                }
            }

            em.persist(company);

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return company;
    }

    private CityInfo getCityInfo(int zipCode) {
        CityInfo cityInfo = null;
        EntityManager em = getEntityManager();

        try {
            cityInfo = em.createQuery("SELECT c FROM CityInfo c WHERE c.zip = :zip", CityInfo.class).setParameter("zip", zipCode).getResultList().get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cityInfo;
    }

    @Override
    public Company getCompanyOnCvr(String cvr) {
        Company c = null;
        EntityManager em = getEntityManager();

        try {
            c = em.createQuery("SELECT c FROM Company c WHERE c.cvr = :cvr", Company.class).setParameter("cvr", cvr).getResultList().get(0);

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
        } finally {
            em.close();
        }

        return companies;

    }

    @Override
    public Company addPhoneToCompany(Phone phone, long id) {
        EntityManager em = getEntityManager();
        Phone p = null;
        Company c = null;

        try {
            em.getTransaction().begin();

            c = em.find(Company.class, id);
            p = em.find(Phone.class, phone.getId());

            if (p == null) {
                p = phone;

            }

            if (c != null) {
                c.addPhone(p);
                em.merge(c);

                em.getTransaction().commit();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return c;
    }

    @Override
    public Company addAddressToCompany(Address address, long id) {
        EntityManager em = getEntityManager();
        Address a = null;
        Company c = null;

        try {
            em.getTransaction().begin();

            c = em.find(Company.class, id);
            a = em.find(Address.class, address.getId());

            if (a == null) {
                a = address;

            }

            if (c != null) {
                c.setAddress(a);
                em.merge(c);
                em.getTransaction().commit();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return c;
    }

    @Override
    public Company deleteCompany(long id) {
        EntityManager em = getEntityManager();
        Company c = null;

        try {

            em.getTransaction().begin();

            c = em.find(Company.class, id);

            em.remove(c);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public Company editCompany(Company company, long id) {
        EntityManager em = getEntityManager();
        Company c = null;

        try {
            em.getTransaction().begin();

            company.setId(id);
            em.merge(company);

            em.getTransaction().commit();

            c = em.find(Company.class, id);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return c;
    }

    @Override
    public Company getCompanyOnId(long id) {
        Company c = null;
        EntityManager em = getEntityManager();

        try {
            c = em.find(Company.class, id);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return c;
    }

    @Override
    public Company getCompanyOnPhone(String number) {
        EntityManager em = getEntityManager();
        Company c = null;
        long phoneID = -1;
        long companyID = -1;

        try {
            phoneID = em.createQuery("SELECT p.id FROM Phone p WHERE p.number = :number", Long.class).setParameter("number", number).getResultList().get(0);
            companyID = (long) em.createNativeQuery("SELECT company_phone.`Company_ID` FROM company_phone WHERE `phones_ID` = ?id").setParameter("id", phoneID).getResultList().get(0);

            c = em.find(Company.class, companyID);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return c;
    }

    @Override
    public List<Company> getCompaniesEmployeesMoreThan(int employeeNumber) {
        EntityManager em = getEntityManager();
        List<Company> companies = new ArrayList();

        try {
            companies = em.createQuery("SELECT c FROM Company c WHERE c.numEmployees > :num", Company.class).setParameter("num", employeeNumber).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return companies;
    }
}
