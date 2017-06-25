package svnHistoryMail;

import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;

import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import  org.tmatesoft.svn.core.SVNException;
/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

        //Set up connection protocols support:
        //http:// and https://
        DAVRepositoryFactory.setup();
        //svn://, svn+xxx:// (svn+ssh:// in particular)
        SVNRepositoryFactoryImpl.setup();
        //file:///
        FSRepositoryFactory.setup();

        String url="http://svn.collab.net/svn/trunk";
        String name="my name";
        String password="my password";
        SVNRepository repository = null;
        try {
            repository = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(url));
            ISVNAuthenticationManager authManager =
                         SVNWCUtil.createDefaultAuthenticationManager(name, password);
            repository.setAuthenticationManager(authManager);

        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}