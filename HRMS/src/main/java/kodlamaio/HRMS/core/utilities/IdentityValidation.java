package kodlamaio.HRMS.core.utilities;

import kodlamaio.HRMS.services.FakeMernis;

public class IdentityValidation {

		
		public static boolean isRealPerson(String tcNo) {
				FakeMernis fakeMernis = new FakeMernis();
				return fakeMernis.validation(tcNo);
		}
}
