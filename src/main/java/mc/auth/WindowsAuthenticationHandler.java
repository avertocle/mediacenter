package mc.auth;

import com.sun.jna.platform.win32.Advapi32;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinNT;

public class WindowsAuthenticationHandler implements AuthenticationHandler {

		@Override
		public boolean authenticate(String username, String password) {
	        WinNT.HANDLEByReference handle = new WinNT.HANDLEByReference();
	        boolean successful = Advapi32.INSTANCE.LogonUser
	           (username, "", password, WinBase.LOGON32_LOGON_NETWORK,
	                   WinBase.LOGON32_PROVIDER_DEFAULT, handle);
	        if (successful) {
	           Advapi32.INSTANCE.RevertToSelf();
	           Kernel32.INSTANCE.CloseHandle(handle.getValue());
	        }
	        return successful;
		}

}
