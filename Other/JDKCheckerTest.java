import java.util.ArrayList;

public class JDKCheckerTest
{
   public static void main( String[] args)
   {
      JDKChecker checker = new JDKChecker();
      String jdk;
      ArrayList<String> jdks;
      
      System.out.println( "Check started");
      
      // check java_home
      System.out.println( "*************************************************");
      jdk = checker.checkJAVA_HOME();
         
      if ( jdk != null)
         System.out.println( "jdk found in java home: " + jdk);
      else
         System.out.println( "jdk couldn't found in java home");
      
      // check path variable
      System.out.println( "*************************************************");
      jdk = checker.checkPathVar();
      
      if ( jdk != null)
         System.out.println( "jdk found in path: " + jdk);
      else
         System.out.println( "jdk couldn't found in path");
      
      // check default path
      System.out.println( "*************************************************");
      
      jdks = checker.checkDefaultPath();
      
      if ( jdks.size() == 0 )
         System.out.println( "you don't have jdk in default path");
      else if ( jdks.size() == 1 )
         System.out.println( "jdk found in default path: " + jdks.get(0) );
      else
         System.out.println( "you have multiple jdks: " + jdks );
      
      System.out.println( "*************************************************");
      
      System.out.println( "Check finished");
   }
}