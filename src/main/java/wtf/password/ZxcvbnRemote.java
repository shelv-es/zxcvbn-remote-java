package wtf.password;

import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * @author <a href="mailto:erik.beeson@gmail.com">Erik Beeson</a>
 */
public class ZxcvbnRemote {
	private final ZxcvbnRemoteService service;

	public ZxcvbnRemote(String endpoint) {
		this.service = new RestAdapter.Builder()
							   .setEndpoint(endpoint)
							   .build()
							   .create(ZxcvbnRemoteService.class);
	}

	public PasswordStrength zxcvbn(String password) throws IllegalArgumentException {
		if(password != null && password.length() > 0) {
			return service.zxcvbn(password);
		} else {
			throw new IllegalArgumentException("Invalid password: " + password);
		}
	}

	interface ZxcvbnRemoteService {
		@POST("/zxcvbn")
		@FormUrlEncoded
		PasswordStrength zxcvbn(@Field("password") String password);
	}
}
