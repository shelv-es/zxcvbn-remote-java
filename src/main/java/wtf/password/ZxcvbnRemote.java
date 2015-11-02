package wtf.password;

import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Main client for accessing zxcvbn-server
 *
 * @author <a href="mailto:erik.beeson@gmail.com">Erik Beeson</a>
 */
public class ZxcvbnRemote {
	private final ZxcvbnRemoteService service;

	public ZxcvbnRemote(String endpoint) {
		// todo: warn if endpoint is not https
		this.service = new RestAdapter.Builder()
							   .setEndpoint(endpoint)
							   .build()
							   .create(ZxcvbnRemoteService.class);
	}

	/**
	 * Execute remote call to zxcvbn-server
	 *
	 * @param password Password to check
	 * @return Result from call to zxcvbn-server
	 * @throws IllegalArgumentException If <code>password</code> is null or empty
	 */
	public PasswordStrength zxcvbn(String password) throws IllegalArgumentException {
		if(password != null && password.length() > 0) {
			return service.zxcvbn(password);
		} else {
			throw new IllegalArgumentException("Invalid password: " + password);
		}
	}

	/**
	 * Retrofit interface to zxcvbn-server
	 */
	interface ZxcvbnRemoteService {
		@POST("/zxcvbn")
		@FormUrlEncoded
		PasswordStrength zxcvbn(@Field("password") String password);
	}
}
