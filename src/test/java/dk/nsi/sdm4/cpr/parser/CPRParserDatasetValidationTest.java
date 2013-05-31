/**
 * The MIT License
 *
 * Original work sponsored and donated by National Board of e-Health (NSI), Denmark
 * (http://www.nsi.dk)
 *
 * Copyright (C) 2011 National Board of e-Health (NSI), Denmark (http://www.nsi.dk)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package dk.nsi.sdm4.cpr.parser;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CPRParserDatasetValidationTest {
	@Rule
	public TemporaryFolder tmpDir = new TemporaryFolder();
	private CPRParser parser;

	@Before
	public void instatiateParser() {
		parser = new CPRParser();
	}

	@Test()
	public void shouldComplainIfPassedNull() throws IOException {
		try {
			parser.process(null, "");
		} catch (NullPointerException expectedException) {
			assertTrue(expectedException.getMessage().contains("dataset"));
			return;
		}

		fail("Expected NullPointerException");
	}

	@Test()
	public void shouldComplainIfPassedFileInsteadOfDirectory() throws IOException {
		File aFile = tmpDir.newFile();
		try {
			parser.process(aFile, "");
		} catch (IllegalStateException expectedException) {
			assertTrue(expectedException.getMessage().contains("not a directory"));
			assertTrue("Error message contains path to file", expectedException.getMessage().contains(aFile.getAbsolutePath()));
			return;
		}

		fail("Expected IllegalStateException");
	}

	@Test()
	public void shouldComplainIfPassedUnreadableDirectory() throws IOException {
		File aDirectory = tmpDir.newFolder();
		aDirectory.setReadable(false);
		try {
			parser.process(aDirectory, "");
		} catch (IllegalStateException expectedException) {
			assertTrue(expectedException.getMessage().contains("not readable"));
			assertTrue("Error message contains path to directory", expectedException.getMessage().contains(aDirectory.getAbsolutePath()));
			return;
		}

		fail("Expected IllegalStateException");
	}

}
