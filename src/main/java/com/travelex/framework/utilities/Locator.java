package com.travelex.framework.utilities;

public class Locator {
	
		private String keyName;
		private String value;
		private String byType;
		
		public Locator(String key,String byType, String value) {
			this.keyName = key;
			this.value = value;
			this.byType=byType;	
		}
		
		public String getKey() {
			return this.keyName;
		}

		public String getValue() {
			return this.value;
		}
		
		public String getbyType() {
			return this.byType;
		}
}


