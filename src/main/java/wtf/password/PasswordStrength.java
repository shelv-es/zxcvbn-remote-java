package wtf.password;

import com.google.gson.annotations.SerializedName;

/**
 * Result from calling <code>zxcvbn</code>.
 *
 * @since zxcvbn 4.0.1
 * @author <a href="mailto:erik.beeson@gmail.com">Erik Beeson</a>
 */
public class PasswordStrength {
	private double guesses;
	@SerializedName("guesses_log10")
	private float guessesLog10;
	@SerializedName("crack_times_seconds")
	private CrackTimeSeconds crackTimeSeconds;
	@SerializedName("crack_times_display")
	private CrackTimeDisplay crackTimeDisplay;
	private int score;

	public PasswordStrength() {
	}

	/**
	 * @return Estimated guesses needed to crack password.
	 */
	public double getGuesses() {
		return guesses;
	}

	/**
	 * @return Order of magnitude of {@link #getGuesses}.
	 */
	public float getGuessesLog10() {
		return guessesLog10;
	}

	/**
	 * @return Dictionary of back-of-the-envelope crack time estimations, in seconds, based on a few scenarios...
	 */
	public CrackTimeSeconds getCrackTimeSeconds() {
		return crackTimeSeconds;
	}

	/**
	 * Same keys as {@link CrackTimeSeconds}, with friendlier display string values: "less than a second", "3 hours", "centuries", etc.
	 *
	 * @see wtf.password.PasswordStrength.CrackTimeSeconds
	 */
	public CrackTimeDisplay getCrackTimeDisplay() {
		return crackTimeDisplay;
	}

	/**
	 * Integer from 0-4 (useful for implementing a strength bar)
	 *
	 * <ul>
	 * <li><code>0</code> too guessable: risky password. (guesses < 10^3)</li>
	 * <li><code>1</code> very guessable: protection from throttled online attacks. (guesses < 10^6)</li>
	 * <li><code>2</code> somewhat guessable: protection from unthrottled online attacks. (guesses < 10^8)</li>
	 * <li><code>3</code> safely unguessable: moderate protection from offline slow-hash scenario. (guesses < 10^10)</li>
	 * <li><code>4</code> very unguessable: strong protection from offline slow-hash scenario. (guesses >= 10^10)</li>
	 * </ul>
	 *
	 * @return Integer from 0-4 (useful for implementing a strength bar)
	 */
	public int getScore() {
		return score;
	}

	public String toString() {
		return "PasswordStrength{" + "guesses=" + guesses + ", guessesLog10=" + guessesLog10 + ", crackTimeSeconds=" + crackTimeSeconds + ", crackTimeDisplay=" + crackTimeDisplay + ", score=" + score + '}';
	}

	/**
	 * Dictionary of back-of-the-envelope crack time estimations, in seconds, based on a few scenarios...
	 */
	public static class CrackTimeSeconds {
		@SerializedName("online_throttling_100_per_hour")
		private double onlineThrottling100PerHour;
		@SerializedName("online_no_throttling_10_per_second")
		private double onlineNoThrottling10PerSecond;
		@SerializedName("offline_slow_hashing_1e4_per_second")
		private double offlineSlowHashing1e4PerSecond;
		@SerializedName("offline_fast_hashing_1e10_per_second")
		private double offlineFastHashing1e10PerSecond;

		public CrackTimeSeconds() {
		}

		/**
		 * @return Online attack on a service that ratelimits password auth attempts.
		 */
		public double getOnlineThrottling100PerHour() {
			return onlineThrottling100PerHour;
		}

		/**
		 * @return Online attack on a service that doesn't ratelimit, or where an attacker has outsmarted ratelimiting.
		 */
		public double getOnlineNoThrottling10PerSecond() {
			return onlineNoThrottling10PerSecond;
		}

		/**
		 * @return Offline attack. Assumes multiple attackers, proper user-unique salting, and a slow hash function w/ moderate work factor, such as bcrypt, scrypt, PBKDF2.
		 */
		public double getOfflineSlowHashing1e4PerSecond() {
			return offlineSlowHashing1e4PerSecond;
		}

		/**
		 * @return Offline attack with user-unique salting but a fast hash function like SHA-1, SHA-256 or MD5. A wide range of reasonable numbers anywhere from one billion - one trillion guesses per second, depending on number of cores and machines. Ballparking at 10B/sec.
		 */
		public double getOfflineFastHashing1e10PerSecond() {
			return offlineFastHashing1e10PerSecond;
		}

		public String toString() {
			return "CrackTimeSeconds{" +
						   "onlineThrottling100PerHour=" + onlineThrottling100PerHour + ", " +
						   "onlineNoThrottling10PerSecond=" + onlineNoThrottling10PerSecond + ", " +
						   "offlineSlowHashing1e4PerSecond=" + offlineSlowHashing1e4PerSecond + ", " +
						   "offlineFastHashing1e10PerSecond=" + offlineFastHashing1e10PerSecond + "}";
		}
	}

	/**
	 * Same keys as {@link CrackTimeSeconds}, with friendlier display string values: "less than a second", "3 hours", "centuries", etc.
	 *
	 * @see wtf.password.PasswordStrength.CrackTimeSeconds
	 */
	public static class CrackTimeDisplay {
		@SerializedName("online_throttling_100_per_hour")
		private String onlineThrottling100PerHour;
		@SerializedName("online_no_throttling_10_per_second")
		private String onlineNoThrottling10PerSecond;
		@SerializedName("offline_slow_hashing_1e4_per_second")
		private String offlineSlowHashing1e4PerSecond;
		@SerializedName("offline_fast_hashing_1e10_per_second")
		private String offlineFastHashing1e10PerSecond;

		public CrackTimeDisplay() {
		}

		/**
		 * @return Online attack on a service that ratelimits password auth attempts.
		 */
		public String getOnlineThrottling100PerHour() {
			return onlineThrottling100PerHour;
		}

		/**
		 * @return Online attack on a service that doesn't ratelimit, or where an attacker has outsmarted ratelimiting.
		 */
		public String getOnlineNoThrottling10PerSecond() {
			return onlineNoThrottling10PerSecond;
		}

		/**
		 * @return Offline attack. Assumes multiple attackers, proper user-unique salting, and a slow hash function w/ moderate work factor, such as bcrypt, scrypt, PBKDF2.
		 */
		public String getOfflineSlowHashing1e4PerSecond() {
			return offlineSlowHashing1e4PerSecond;
		}

		/**
		 * @return Offline attack with user-unique salting but a fast hash function like SHA-1, SHA-256 or MD5. A wide range of reasonable numbers anywhere from one billion - one trillion guesses per second, depending on number of cores and machines. Ballparking at 10B/sec.
		 */
		public String getOfflineFastHashing1e10PerSecond() {
			return offlineFastHashing1e10PerSecond;
		}

		public String toString() {
			return "CrackTimeDisplay{" +
						   "onlineThrottling100PerHour='" + onlineThrottling100PerHour + "', " +
						   "onlineNoThrottling10PerSecond='" + onlineNoThrottling10PerSecond + "', " +
						   "offlineSlowHashing1e4PerSecond='" + offlineSlowHashing1e4PerSecond + "', " +
						   "offlineFastHashing1e10PerSecond='" + offlineFastHashing1e10PerSecond + '}';
		}
	}
}
