package org.aiwolf.client.lib;

public enum UtteranceVerb {
	declare, request, agree;

	public static UtteranceVerb fromString(String text) {
		if (text != null) {
			for (UtteranceVerb b : UtteranceVerb.values()) {
				if (text.equalsIgnoreCase(b.name())) {
					return b;
				}
			}
		}
		return null;
	}

}
