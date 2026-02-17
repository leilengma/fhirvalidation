package com.fgu.fhir.validation;

import java.util.Set;
import java.util.function.Function;

import org.hl7.fhir.instance.model.api.IBaseResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.support.IValidationSupport;

/**
 * Fallback for versioned canonical URLs (e.g. ".../StructureDefinition/Medication|4.0.1").
 * Tries the exact URL first, then falls back to the non-versioned URL.
 */
public class VersionedUrlFallbackValidationSupport implements IValidationSupport {
	private static final Logger ourLog = LoggerFactory.getLogger(VersionedUrlFallbackValidationSupport.class);

	private final FhirContext myFhirContext;
	private final IValidationSupport myChain;
	private final Set<String> myUrlPrefixes;

	public VersionedUrlFallbackValidationSupport(FhirContext theFhirContext, IValidationSupport theChain) {
		this(theFhirContext, theChain, Set.of(URL_PREFIX_STRUCTURE_DEFINITION));
	}

	public VersionedUrlFallbackValidationSupport(
			FhirContext theFhirContext, IValidationSupport theChain, Set<String> theUrlPrefixes) {
		myFhirContext = theFhirContext;
		myChain = theChain;
		myUrlPrefixes = theUrlPrefixes;
	}

	@Override
	public FhirContext getFhirContext() {
		return myFhirContext;
	}

	@Override
	public <T extends IBaseResource> T fetchResource(Class<T> theClass, String theUri) {
		return doFetchWithFallback(theUri, uri -> myChain.fetchResource(theClass, uri));
	}

	@Override
	public IBaseResource fetchStructureDefinition(String theUrl) {
		return doFetchWithFallback(theUrl, myChain::fetchStructureDefinition);
	}

	private <T extends IBaseResource> T doFetchWithFallback(String theUrl, Function<String, T> theFetcher) {
		int pipeIndex = theUrl.indexOf('|');
		if (pipeIndex <= 0) {
			return null;
		}

		String baseUrl = theUrl.substring(0, pipeIndex);
		if (!matchesPrefix(baseUrl)) {
			return null;
		}

		T result = theFetcher.apply(theUrl);
		if (result != null) {
			return result;
		}

		result = theFetcher.apply(baseUrl);
		if (result != null) {
			ourLog.warn(
				"Requested versioned canonical '{}' not found, falling back to non-versioned '{}'",
				theUrl,
				baseUrl);
		}
		return result;
	}

	private boolean matchesPrefix(String theUrl) {
		if (myUrlPrefixes.isEmpty()) {
			return true;
		}
		for (String prefix : myUrlPrefixes) {
			if (theUrl.startsWith(prefix)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getName() {
		return "VersionedUrlFallbackValidationSupport";
	}
}
