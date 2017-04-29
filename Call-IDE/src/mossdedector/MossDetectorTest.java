/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mossdedector;

import it.zielke.moji.MossException;
import java.io.IOException;

/**
 * This class test the mos detector
 * @author mahmudsami
 */
public class MossDetectorTest {
    
    public static void main (String[] args ) throws MossException, IOException
    {
        MossDetector md = new MossDetector( "C:\\Users\\mahmudsami\\Desktop\\lablablab" );
        
        System.out.println(md.getMossLink());
    }
}
