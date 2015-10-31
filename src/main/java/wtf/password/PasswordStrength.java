package wtf.password;

import com.google.gson.annotations.SerializedName;

/**
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

	public double getGuesses() {
		return guesses;
	}

	public float getGuessesLog10() {
		return guessesLog10;
	}

	public CrackTimeSeconds getCrackTimeSeconds() {
		return crackTimeSeconds;
	}

	public CrackTimeDisplay getCrackTimeDisplay() {
		return crackTimeDisplay;
	}

	public int getScore() {
		return score;
	}

	public String toString() {
		return "PasswordStrength{" + "guesses=" + guesses + ", guessesLog10=" + guessesLog10 + ", crackTimeSeconds=" + crackTimeSeconds + ", crackTimeDisplay=" + crackTimeDisplay + ", score=" + score + '}';
	}

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

		public double getOnlineThrottling100PerHour() {
			return onlineThrottling100PerHour;
		}

		public double getOnlineNoThrottling10PerSecond() {
			return onlineNoThrottling10PerSecond;
		}

		public double getOfflineSlowHashing1e4PerSecond() {
			return offlineSlowHashing1e4PerSecond;
		}

		public double getOfflineFastHashing1e10PerSecond() {
			return offlineFastHashing1e10PerSecond;
		}

		public String toString() {
			return "CrackTimeSeconds{" + "onlineThrottling100PerHour=" + onlineThrottling100PerHour + ", onlineNoThrottling10PerSecond=" + onlineNoThrottling10PerSecond + ", offlineSlowHashing1e4PerSecond=" + offlineSlowHashing1e4PerSecond + ", offlineFastHashing1e10PerSecond=" + offlineFastHashing1e10PerSecond + '}';
		}
	}

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

		public String getOnlineThrottling100PerHour() {
			return onlineThrottling100PerHour;
		}

		public String getOnlineNoThrottling10PerSecond() {
			return onlineNoThrottling10PerSecond;
		}

		public String getOfflineSlowHashing1e4PerSecond() {
			return offlineSlowHashing1e4PerSecond;
		}

		public String getOfflineFastHashing1e10PerSecond() {
			return offlineFastHashing1e10PerSecond;
		}

		public String toString() {
			return "CrackTimeDisplay{" + "onlineThrottling100PerHour='" + onlineThrottling100PerHour + '\'' + ", onlineNoThrottling10PerSecond='" + onlineNoThrottling10PerSecond + '\'' + ", offlineSlowHashing1e4PerSecond='" + offlineSlowHashing1e4PerSecond + '\'' + ", offlineFastHashing1e10PerSecond='" + offlineFastHashing1e10PerSecond + '\'' + '}';
		}
	}
}
