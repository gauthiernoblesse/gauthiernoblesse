package fr.formation.inti.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import fr.formation.inti.utils.HibernateUtils;

/**
 * Notre classe EmployeeDaoImpl suit le modèle de conception Singleton qui
 * garantit qu'une seule instance de cette classe sera créée dans l'application.
 * Lors de la première création de classe, la méthode getEntityManager () est
 * chargée de créer une instance de EntityManager.
 * 
 * @author 
 * @param <T>
 * @param <I>
 *
 */
public class GenericDaoImp<T, I extends Serializable> implements IGenericDao<T,I> {
	private Session currentSession;
	private Transaction currentTransaction;
	
	private Class clazz;
	
	
	
	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }
 
    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
     
    public void closeCurrentSession() {
        currentSession.close();
    }
     
    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }
    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }
 
    public Session getCurrentSession() {
        return currentSession;
    }
 
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
 
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
 
    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

	@Override
	public List<T> getAll() {
		Query q = getCurrentSession().createQuery("FROM " + clazz.getSimpleName());		
		List<T> employees = q.list();
		return employees;
	}

	@Override
	public void persist(T e) {
		getCurrentSession().persist(e);
	}

	@Override
	public I save(T e) {
		
		return (I) getCurrentSession().save(e);
	}

	@Override
	public void update(T e) {
		getCurrentSession().update(e);
	}

	@Override
	public void delete(T e) {
		getCurrentSession().delete(e);
	}

	@Override
	public T findById(I id) {
		T emp = (T) getCurrentSession().get(clazz.getName(), id);
		return emp;
	}

	@Override
	public void deleteByID(I id) {
		try {
			T e = findById(id);
			delete(e);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void close() {
		HibernateUtils.shutdown();
	}

}
