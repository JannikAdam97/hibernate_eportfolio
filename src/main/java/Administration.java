import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Administration {
    public static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = HibernateUtil.getSessionFactory();

        Administration administrator = new Administration();

        /*
        HashSet set1 = new HashSet();
        Task taskOne = new Task("Hibernate Beispiel Implementation", "Eine beispielhafte Implementation, um den Gebrauch von Hibernate zu veranschaulichen", 50);
        Task taskTwo = new Task("Theorie lernen", "Theorie ist eine wichtige Grundlage für saubere Praxisarbeit", 99);
        set1.add(taskOne);
        set1.add(taskTwo);

        Mission missionOne = new Mission("Jannik", "E-Portfolio Hibernate", "Ziel ist die Präsentation von Hibernate", set1);
        Integer missionOneID = administrator.addMission(missionOne);

        HashSet set2 = new HashSet();
        Task taskThree = new Task("Fahrschule - Theorie", "Anwesenheit beim Fahrschulunterricht", 34);
        set2.add(taskThree);

        Mission missionTwo = new Mission("Alice", "Autoführerschein", "Ziel ist der Erhalt des Führerscheins", set2);
        Integer missionTwoID = administrator.addMission(missionTwo);
        */

        /*
        administrator.updateTask(2, 98);
        */

        /*
        Task taskFour = new Task("Fahrschule - Praxis", "Praxis muss sitzen!", 72);
        Integer taskFourID = administrator.addTask(taskFour, 2);
        */

        /*
        administrator.deleteTask(4);
        */

        administrator.listMissions();
    }

    public Integer addMission(Mission mission) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer missionID = null;

        try {
            tx = session.beginTransaction();
            missionID = (Integer) session.save(mission);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return missionID;
    }

    public Integer addTask(Task task, Integer missionID) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer taskID = null;

        try {
            tx = session.beginTransaction();
            Mission mission = (Mission) session.get(Mission.class, missionID);
            mission.getTasks().add(task);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return taskID;
    }

    public void listMissions( ){
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Mission").list();
            Iterator iterator1 = employees.iterator();
            while(iterator1.hasNext()){
                Mission mission = (Mission) iterator1.next();
                System.out.println("####### - " + mission.getId() + " - " + mission.getOwner() + " - #######");
                System.out.println("Missionname: " + mission.getMissionname());
                System.out.println("Mission description: " + mission.getDescription());
                Set tasks = mission.getTasks();
                Iterator iterator2 = tasks.iterator();
                while(iterator2.hasNext()){
                    Task task = (Task) iterator2.next();
                    System.out.println(">>>>>>> Task " + task.getId());
                    System.out.println(">>>>>>> " + task.getName());
                    System.out.println(">>>>>>> Schwierigkeit: " + task.getEffort() + " of 100");
                }
                System.out.println();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateTask(Integer taskID, int effort){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Task task = (Task)session.get(Task.class, taskID);
            task.setEffort(effort);
            session.update(task);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteTask(Integer taskID){
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Task task = (Task)session.get(Task.class, taskID);
            session.delete(task);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
