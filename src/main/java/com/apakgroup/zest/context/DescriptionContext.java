package com.apakgroup.zest.context;

import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DescriptionContext implements LoggableOutcome {

	private final TestDescriptor descriptor;

	private final Logger logger;

	public DescriptionContext(final TestDescriptor descriptor) {
		this.descriptor = descriptor;
		logger = LoggerFactory.getLogger(descriptor.className());
	}

	public Description asDescription() {
		return Description.createTestDescription(descriptor.className(), descriptor.methodName());
	}

	@Override
	public void logFailure(final Throwable e) {
		String errorMessage = String.format("FAILED [%s.%s] associated with requirement [%s]", descriptor.className(),
				descriptor.methodName(), descriptor.describe());
		logger.error(errorMessage, e);
	}

	@Override
	public void logSuccess() {
		logger.info("PASSED SCENARIO : {}", descriptor.describe());
	}

}
