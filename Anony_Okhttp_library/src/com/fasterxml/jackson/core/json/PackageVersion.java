/* 
 * Copyright 2012 ZTE.Ltd  All rights reserved.
 * ZTE.Ltd PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 * @PackageVersion.java - 2015-10-10 ÉÏÎç8:36:55 - 0232000078
 */

package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.util.VersionUtil;

/**
 * Automatically generated from PackageVersion.java.in during packageVersion-generate execution of maven-replacer-plugin in pom.xml.
 */
public final class PackageVersion implements Versioned {

	public final static Version VERSION = VersionUtil.parseVersion("@projectversion@", "@projectgroupid@", "@projectartifactid@");

	@Override
	public Version version() {
		return VERSION;
	}
}
