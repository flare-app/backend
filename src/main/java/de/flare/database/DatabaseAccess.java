package de.flare.database;


import de.flare.database.definition.Authorization;
import de.flare.database.model.DatabaseEntity;
import de.flare.database.model.Unit;
import de.flare.database.model.User;
import de.flare.http.RequestContext;
import de.flare.properties.PropertyEditor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public final class DatabaseAccess {

	//region private static members
	private static SessionFactory sessionFactory;
	private static int sessionBatchSize = 1;
	//endregion

	//region private const
	private static final Logger logger = LoggerFactory.getLogger(DatabaseAccess.class);
	private static final String USERNAME_PROPERTY_KEY = "dbUsername";
	private static final String PASSWORD_PROPERTY_KEY = "dbPassword";
	private static final String HOST_PROPERTY_KEY = "dbHost";
	//endregion

	//region ctor
	private DatabaseAccess() { }
	//endregion

	//region public static methods
	public static boolean establishConnection() {
		Configuration configuration = new Configuration();
		configuration.configure();

		configuration.setProperty("hibernate.connection.username", PropertyEditor.getString(USERNAME_PROPERTY_KEY));
		configuration.setProperty("hibernate.connection.password", PropertyEditor.getString(PASSWORD_PROPERTY_KEY));

		configuration.setProperty("hibernate.connection.url",
				String.format("jdbc:mysql://%1$s/flare-backend?createDatabaseIfNotExist=true", PropertyEditor.getString(HOST_PROPERTY_KEY)));

		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		sessionBatchSize = Integer.parseInt(configuration.getProperty("hibernate.jdbc.batch_size"));

		boolean connected = false;
		do {
			try {
				sessionFactory = configuration.buildSessionFactory();
				connected = true;
			} catch (Exception e) {
				logger.error("cannot connect to database", e);

				try {
					Thread.sleep(5000);
				} catch (Exception ex) {
					break;
				}
			}
		} while (!connected);

		return connected;
	}
	//endregion

	//region public static methods
	public static boolean save(RequestContext context, DatabaseEntity... entities) {
		return save(getSession(context));
	}

	public static boolean save(DatabaseEntity... entities) {
		Session session = sessionFactory.openSession();
		boolean success = save(session, entities);
		session.close();
		return success;
	}

	public static User getUser(RequestContext context, String eMail, String unitName, String unitCity) {
		Session session = getSession(context);
		Transaction transaction = session.beginTransaction();

		Query<User> query = session.createQuery("FROM User user " +
				"WHERE user.eMail = :eMail AND user.unit.name = :unitName AND user.unit.location.city = :unitCity")
				.setParameter("eMail", eMail)
				.setParameter("unitName", unitName)
				.setParameter("unitCity", unitCity);

		List<User> users = get(transaction, query, query::getResultList, new ArrayList<>());

		if (users.size() > 1) {
			throw new IllegalStateException("more than one user found!");
		} else if (users.isEmpty()) {
			return null;
		}

		return users.get(0);
	}

	public static User getUser(RequestContext context, String authenticationToken) {
		if (authenticationToken == null || authenticationToken.isEmpty()) {
			return null;
		}

		Session session = getSession(context);
		Transaction transaction = session.beginTransaction();

		Query<User> query = session.createQuery("FROM User user " +
				"WHERE user.authenticationToken = :authenticationToken")
				.setParameter("authenticationToken", authenticationToken);

		List<User> users = get(transaction, query, query::getResultList, new ArrayList<>());

		if (users.size() > 1) {
			throw new IllegalStateException("more than one user found!");
		} else if (users.isEmpty()) {
			return null;
		}

		return users.get(0);
	}

	public static User getFlareAdminUser(RequestContext context, String eMail) {
		Session session = getSession(context);
		Transaction transaction = session.beginTransaction();

		Query<User> query = session.createQuery("FROM User user " +
				"WHERE user.authorization = :authorization AND user.eMail = :eMail")
				.setParameter("authorization", Authorization.FLARE_ADMINISTRATOR)
				.setParameter("eMail", eMail);

		List<User> users = get(transaction, query, query::getResultList, new ArrayList<>());

		if (users.size() > 1) {
			throw new IllegalStateException("more than one user found!");
		} else if (users.isEmpty()) {
			return null;
		}

		return users.get(0);
	}

	public static List<User> getUsers(Authorization authorization) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Query<User> query = session.createQuery("FROM User user " +
				"WHERE user.authorization = :authorization")
				.setParameter("authorization", authorization);

		List<User> users = get(transaction, query, query::getResultList, new ArrayList<>());
		session.close();

		return users;
	}

	public static List<Unit> getUnits(RequestContext context) {
		Session session = getSession(context);
		Transaction transaction = session.beginTransaction();

		Query<Unit> query = session.createQuery("FROM Unit");

		return get(transaction, query, query::getResultList, new ArrayList<>());
	}
	//endregion

	//region private static methods
	private static Session getSession(RequestContext context) {
		if (context.getSession() == null) {
			context.setSession(sessionFactory.openSession());
		}

		return context.getSession();
	}

	private static <ResultType, QueryType> ResultType get(Transaction transaction,
	                                                      Query<QueryType> query,
	                                                      Callable<ResultType> getValue,
	                                                      ResultType defaultValue) {
		try {
			ResultType result = getValue.call();
			transaction.commit();
			return result;
		} catch (NoResultException e) {
			//suppress exception
			return defaultValue;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

			logger.error("cannot get entity for query: " + query.getQueryString(), e);
			return defaultValue;
		}
	}

	private static boolean save(Session session, DatabaseEntity... entities) {
		Transaction transaction = session.beginTransaction();

		try {
			int currentBatchSize = 0;

			for (DatabaseEntity entity : entities) {
				session.saveOrUpdate(entity);

				if (++currentBatchSize % sessionBatchSize == 0) {
					session.flush();
					session.clear();
				}
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

			logger.error("cannot save entities", e);
			return false;
		}

		return true;
	}
	//endregion
}
