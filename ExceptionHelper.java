/**
 * this class enables the user reach reasons and solutions of run-time errors 
 * @author Ataberk Gözkaya
 * @version 1.00 14/04/2017
 */
public class ExceptionHelper 
{
	/**
	 * this method compares run-time error names and 
	 * directs users to solutions via links
	 * @param   exception
	 * @return  helperLink 
	 */
	public static String exceptionHelper(String exception) 
	{
		String helperLink;
		helperLink = "";
		try
		{
			if(exception.equals("ArrayIndexOutOfBoundsException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#ARRAYINDEXOUTOFBOUNDS";
			}
			if(exception.equals("NullPointerException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NULLPOINTEREXCEPTION";
			}
			if(exception.equals("NumberFormatException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NUMBERFORMATEXCEPTION";
			}
			if(exception.equals("ArithmeticException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#ARITHMETICEXCEPTION";
			}
			if(exception.equals("InvalidArgumentException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#INVALIDARGUMENTEXCEPTION";
			}
			if(exception.equals("IOException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#IOEXCEPTION";
			}
			if(exception.equals("OutOfMemoryError"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#OUTOFMEMORYERROR";
			}
			if(exception.equals("StackOverflowError"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#STACKOVERFLOWERROR";
			}
			if(exception.equals("StringIndexOutOfBoundsException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#STRINGINDEXOUTOFBOUNDSEXCEPTION";
			}
			if(exception.equals("StreamCorruptedException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#STREAMCORRUPTEDEXCEPTION";
			}
			if(exception.equals("ClassNotFoundException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#CLASSNOTFOUNDEXCEPTION";
			}
			if(exception.equals("ClassCastException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#CLASSCASTEXCEPTION";
			}
			if(exception.equals("InvalidClassException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#INVALIDCLASS";
			}
			if(exception.equals("NoClassDefFoundError"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NOCLASSDEFFOUNDERROR";
			}
			if(exception.equals("NotSerializableException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NOTSERIALIAZABLEEXCEPTION";
			}
			if(exception.equals("UnsupportedDataTypeException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#UNSUPPORTEDDATATYPEEXCEPTION";
			}
			if(exception.equals("ZipException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#ZIPEXCEPTION";
			}
			if(exception.equals("ClassFormatError"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#CLASSFORMATERROR";
			}
			if(exception.equals("ArrayStoreException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#ARRAYSTOREEXCEPTION";
			}
			if(exception.equals("AbstractMethodError"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#ABSTRACTMETHODERROR";
			}
			if(exception.equals("AccessControlException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#ACCESSCONTROLEXCEPTION";
			}
			if(exception.equals("OptionalDataException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#OPTIONALDATAEXCEPTION";
			}
			if(exception.equals("InvocationTargetException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#INVOCATIONTARGET";
			}
			if(exception.equals("IncompatibleClassChangeError"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#INCOMPATIBLECLASSCHANGEERROR";
			}
			if(exception.equals("NoInitialContextException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NOINITIALCONTEXTEXCEPTION";
			}
			if(exception.equals("NoSuchElementException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NOSUCHELEMENTEXCEPTION";
			}
			if(exception.equals("NoSuchFieldError"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NOSUCHFIELDERROR";
			}
			if(exception.equals("NoSuchMethodError"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NOSUCHMETHODERROR";
			}
			if(exception.equals("NoSuchProviderException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NOSUCHPROVIDEREXCEPTION";
			}
			if(exception.equals("ExceptionInInitializerError"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#EXCEPTIONINITALIZERERROR";
			}
			if(exception.equals("Incompatible types"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#INCOMPATIBLETYPES";
			}
			if(exception.equals("Exception in thread"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#EXCEPTIONINTHREAD";
			}
			if(exception.equals("UnmarshalException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#UNMARSHALEXCEPTION";
			}
			if(exception.equals("IllegalMonitorStateException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#ILLEGALMONITORSTATEEXCEPTION";
			}
			if(exception.equals("HeadlessException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#HEADLESSEXCEPTION";
			}
			if(exception.equals("IllegalBlockSizeException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#ILLEGALBLOCKSIZEEXCEPTION";
			}
			if(exception.equals("VerifyError"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#VERIFYERROR";
			}
			if(exception.equals("UnsupportedClassVersionError"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#UNSUPPORTEDCLASSVERSIONERROR";
			}
			if(exception.equals("UnrecoverableKeyException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#UNRECOVERABLEKEYEXCEPTION";
			}
			if(exception.equals("UnsatisfiedLinkError"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#UNSATISFIEDLINKERROR";
			}
			if(exception.equals("ConcurrentModificationException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#CONCURRENTMOD";
			}
			if(exception.equals("ConnectException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#CONNECTEXCEPTION";
			}
			if(exception.equals("Could not find main-class"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#NOMAINCLASS";
			}
			if(exception.equals("CertificateException"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#CERTIFICATEEXCEPTION";
			}
			if(exception.equals("Handshake Alert"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#HANDSHAKEALERT";
			}
			if(exception.equals("EOFException in ZIP"))
			{
				helperLink = "http://mindprod.com/jgloss/runerrormessages.html#EOFEXCEPTION";
			}
			return helperLink;
		}
		catch(Exception e)
		{
			return "";
		}
	}
}
