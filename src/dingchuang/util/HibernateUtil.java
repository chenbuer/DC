package dingchuang.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil  
{  
    public static final SessionFactory sessionFactory;  
    //����sessionFactory  
    static  
    {  
        try  
        {  
            sessionFactory = new Configuration().configure().buildSessionFactory();  
        }  
        catch (Throwable ex)  
        {  
            System.err.println("Initial SessionFactory creation failed." + ex);  
            throw new ExceptionInInitializerError(ex);  
        }  
    }  
  
    // ThreadLocal���Ը������̵߳����ݹ�����˲�����Ҫ���߳�ͬ��  
    public static final ThreadLocal<Session> session  
        = new ThreadLocal<Session>();  
    //����Session  
    public static Session currentSession()  
        throws HibernateException  
    {  
        //ͨ���̶߳���.get()������ȫ����Session  
        Session s = session.get();  
        // ������̻߳�û��Session,�򴴽�һ���µ�Session  
        if (s == null)  
        {  
            s = sessionFactory.openSession();  
            // ����õ�Session�����洢��ThreadLocal����session��  
            session.set(s);  
        }  
        return s;  
    }  
    //�ر�Session  
    public static void closeSession()  
        throws HibernateException  
    {  
        Session s = session.get();  
        if (s != null)  
            s.close();  
        session.set(null);  
    }  
}  
