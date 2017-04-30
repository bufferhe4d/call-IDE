package helputils;

/**
 * This class enables the user reach reasons and solutions of run-time errors 
 * @author Ataberk Gözkaya
 * @version 1.00 14/04/2017
 */
public class ExceptionHelper 
{
    /**
     * This method compares run-time error names and 
     * directs users to solutions via links
     * @param exception the exception's name
     * @return a proper helper link to the exception
     */
    public static String getHelpLink( String exception) 
    {
        String helperLink;
        helperLink = "";
        
        if (exception.equals("ArrayIndexOutOfBoundsException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#ARRAYINDEXOUTOFBOUNDS";
        
        else if (exception.equals("NullPointerException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NULLPOINTEREXCEPTION";
        
        else if (exception.equals("NumberFormatException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NUMBERFORMATEXCEPTION";
        
        else if (exception.equals("ArithmeticException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#ARITHMETICEXCEPTION";
        
        else if (exception.equals("InvalidArgumentException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#INVALIDARGUMENTEXCEPTION";
        
        else if (exception.equals("IOException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#IOEXCEPTION";
        
        else if (exception.equals("OutOfMemoryError"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#OUTOFMEMORYERROR";
        
        else if (exception.equals("StackOverflowError"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#STACKOVERFLOWERROR";
        
        else if (exception.equals("StringIndexOutOfBoundsException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#STRINGINDEXOUTOFBOUNDSEXCEPTION";
        
        else if (exception.equals("StreamCorruptedException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#STREAMCORRUPTEDEXCEPTION";
        
        else if (exception.equals("ClassNotFoundException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#CLASSNOTFOUNDEXCEPTION";
        
        else if (exception.equals("ClassCastException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#CLASSCASTEXCEPTION";
        
        else if (exception.equals("InvalidClassException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#INVALIDCLASS";
        
        else if (exception.equals("NoClassDefFoundError"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NOCLASSDEFFOUNDERROR";
        
        else if (exception.equals("NotSerializableException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NOTSERIALIAZABLEEXCEPTION";
        
        else if (exception.equals("UnsupportedDataTypeException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#UNSUPPORTEDDATATYPEEXCEPTION";
        
        else if (exception.equals("ZipException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#ZIPEXCEPTION";
        
        else if (exception.equals("ClassFormatError"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#CLASSFORMATERROR";
        
        else if (exception.equals("ArrayStoreException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#ARRAYSTOREEXCEPTION";
        
        else if (exception.equals("AbstractMethodError"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#ABSTRACTMETHODERROR";
        
        else if (exception.equals("AccessControlException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#ACCESSCONTROLEXCEPTION";
        
        else if (exception.equals("OptionalDataException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#OPTIONALDATAEXCEPTION";
        
        else if (exception.equals("InvocationTargetException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#INVOCATIONTARGET";
        
        else if (exception.equals("IncompatibleClassChangeError"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#INCOMPATIBLECLASSCHANGEERROR";
        
        else if (exception.equals("NoInitialContextException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NOINITIALCONTEXTEXCEPTION";
        
        else if (exception.equals("NoSuchElementException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NOSUCHELEMENTEXCEPTION";
        
        else if (exception.equals("NoSuchFieldError"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NOSUCHFIELDERROR";
        
        else if (exception.equals("NoSuchMethodError"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NOSUCHMETHODERROR";
        
        else if (exception.equals("NoSuchProviderException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NOSUCHPROVIDEREXCEPTION";
        
        else if (exception.equals("ExceptionInInitializerError"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#EXCEPTIONINITALIZERERROR";
        
        else if (exception.equals("Incompatible types"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#INCOMPATIBLETYPES";
        
        else if (exception.equals("Exception in thread"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#EXCEPTIONINTHREAD";
        
        else if (exception.equals("UnmarshalException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#UNMARSHALEXCEPTION";
        
        else if (exception.equals("IllegalMonitorStateException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#ILLEGALMONITORSTATEEXCEPTION";
        
        else if (exception.equals("HeadlessException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#HEADLESSEXCEPTION";
        
        else if (exception.equals("IllegalBlockSizeException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#ILLEGALBLOCKSIZEEXCEPTION";
        
        else if (exception.equals("VerifyError"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#VERIFYERROR";
        
        else if (exception.equals("UnsupportedClassVersionError"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#UNSUPPORTEDCLASSVERSIONERROR";
        
        else if (exception.equals("UnrecoverableKeyException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#UNRECOVERABLEKEYEXCEPTION";
        
        else if (exception.equals("UnsatisfiedLinkError"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#UNSATISFIEDLINKERROR";
        
        else if (exception.equals("ConcurrentModificationException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#CONCURRENTMOD";
        
        else if (exception.equals("ConnectException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#CONNECTEXCEPTION";
        
        else if (exception.equals("Could not find main-class"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NOMAINCLASS";
        
        else if (exception.equals("CertificateException"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#CERTIFICATEEXCEPTION";
        
        else if (exception.equals("Handshake Alert"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#HANDSHAKEALERT";
        
        else if (exception.equals("EOFException in ZIP"))
            helperLink = "http://mindprod.com/jgloss/runerrormessages.html#EOFEXCEPTION";
        
        return helperLink;
    }
    
}
