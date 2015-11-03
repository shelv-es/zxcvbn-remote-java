package wtf.password;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Result from calling <code>zxcvbn</code>.
 *
 * @since zxcvbn 4.1.1
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
	private Feedback feedback;
	@SerializedName("calc_time")
	private long calcTime;

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
	 * @see wtf.password.PasswordStrength.CrackTimeSeconds
	 * @return Same keys as {@link CrackTimeSeconds}, with friendlier display string values: "less than a second", "3 hours", "centuries", etc.
	 */
	public CrackTimeDisplay getCrackTimeDisplay() {
		return crackTimeDisplay;
	}

	/**
	 * Integer from 0-4 (useful for implementing a strength bar)
	 *
	 * <ul>
	 * <li><code>0</code> too guessable: risky password. (guesses &lt; 10^3)</li>
	 * <li><code>1</code> very guessable: protection from throttled online attacks. (guesses &lt; 10^6)</li>
	 * <li><code>2</code> somewhat guessable: protection from unthrottled online attacks. (guesses &lt; 10^8)</li>
	 * <li><code>3</code> safely unguessable: moderate protection from offline slow-hash scenario. (guesses &lt; 10^10)</li>
	 * <li><code>4</code> very unguessable: strong protection from offline slow-hash scenario. (guesses &gt;= 10^10)</li>
	 * </ul>
	 *
	 * @return Integer from 0-4 (useful for implementing a strength bar)
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @return Verbal feedback to help choose better passwords. Set when score &lt;= 2.
	 */
	public Feedback getFeedback() {
		return feedback;
	}

	/**
	 * @return How long it took zxcvbn to calculate an answer, in milliseconds.
	 */
	public long getCalcTime() {
		return calcTime;
	}

	public String toString() {
		return "PasswordStrength{" +
					   "guesses=" + guesses + ", " +
					   "guessesLog10=" + guessesLog10 + ", " +
					   "crackTimeSeconds=" + crackTimeSeconds + ", " +
					   "crackTimeDisplay=" + crackTimeDisplay + ", " +
					   "score=" + score + ", " +
					   "feedback=" + feedback + "}";
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

	/**
	 * Verbal feedback to help choose better passwords. Set when score &lt;= 2.
	 */
	public static class Feedback {
		private String warning;
		private String[] suggestions;

		public Feedback() {
		}

		/**
		 * @return Explains what's wrong, eg. 'this is a top-10 common password'. Not always set -- sometimes an empty string.
		 */
		public String getWarning() {
			return warning;
		}

		/**
		 * @return A possibly-empty list of suggestions to help choose a less guessable password, eg. 'Add another word or two'.
		 */
		public String[] getSuggestions() {
			return suggestions;
		}

		public String toString() {
			return "Feedback{" +
						   "warning='" + warning + "', " +
						   "suggestions=" + Arrays.toString(suggestions) + "}";
		}
	}
}
