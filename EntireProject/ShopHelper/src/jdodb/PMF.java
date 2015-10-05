package jdodb;
import javax.jdo.*;
public class PMF {
    private static final PersistenceManagerFactory pmfInstance =
            JDOHelper.getPersistenceManagerFactory("transactions-optional");

        private PMF() {}

        public static PersistenceManagerFactory get() {
            return pmfInstance;
        }
   /*
    * try{
    * 	//do stuff with pm...
    * }finally{
    * 	pm.close();
    * }
    * 
    */
}