package web.tools;

import web.dal.*;
import web.model.*;

import javax.naming.ldap.LdapContext;

import java.sql.SQLException;
//import java.util.Date;
import java.util.List;
import java.sql.*;
import java.sql.Date;

/**
 * main() runner, used for the app demo.
 */
public class InsertKAI {
    public static void main(String[] args) throws SQLException{
        // DAO instances.
        UsersDao usersDao = UsersDao.getInstance();
        ResearchersDao researchersDao = ResearchersDao.getInstance();
        PostsDao postsDao = PostsDao.getInstance();
        CommentsDao commentsDao = CommentsDao.getInstance();
        ResharesDao resharesDao = ResharesDao.getInstance();
        CollectionsDao collectionsDao = CollectionsDao.getInstance();
        LikesDao likesDao = LikesDao.getInstance();
        
        SitesDao sitesDao = SitesDao.getInstance();
        ParticipatesDao participatesDao = ParticipatesDao.getInstance();

        
        
        
        //kai
	    /*** Sites.java/SitesDao.java ***/
	    System.out.println("Test Sites");
	    Sites sites0 = new Sites(1,"STOK", Date.valueOf("2019-01-15"));
	    sitesDao.create(sites0);
	    System.out.println("Create Recommendation Successfully");
	    Sites sites1 = sitesDao.getSitesBySiteId(1);
	    System.out.println("getSitesByName:\nSiteId: "+sites1.getSiteId()
	    +" Name: "+sites1.getName()+" Date: "+sites1.getDate());
	    sitesDao.updateAbout(sites1, "AAA");
	    System.out.println("updateAbout:\nSiteId: "+sites1.getSiteId()
	    +" Name: "+sites1.getName()+" Date: "+sites1.getDate());
	    //sitesDao.delete(sites0);
	    System.out.println();
        
        
	    
	    
	    // create researchers
        System.out.println("-------------------------------");
        System.out.println("Test Researchers");
        System.out.println("-------------------------------");
        Researchers researcher1 = new Researchers("jordan","123",Users.Status.valueOf("Researcher"),
                "smith","jordan",true,"paper1","institute1");
        researchersDao.create(researcher1);
        System.out.println("Create researchers successful");
	    
        // Create Participates
	    Participates participate1 = new Participates(sites0,researcher1);
	    participatesDao.create(participate1);
        
    }
}
