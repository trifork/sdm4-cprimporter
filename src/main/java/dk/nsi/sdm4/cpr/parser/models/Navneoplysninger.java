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

package dk.nsi.sdm4.cpr.parser.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity(name = "Person")
public class Navneoplysninger extends CPREntity
{
	String cpr;
	String fornavn;
	String fornavnMarkering;
	String mellemnavn;
	String mellemnavnMarkering;
	String efternavn;
	String efternavnMarkering;
	Date startDato;
	String startDatoMarkering;
	String adresseringsNavn;

	@Id
	@Column
	public String getCpr()
	{
		return cpr;
	}

	public void setCpr(String cpr)
	{
		this.cpr = cpr;
	}

	@Column
	public String getFornavn()
	{
		return fornavn;
	}

	public void setFornavn(String fornavn)
	{
		this.fornavn = fornavn;
	}

	public String getFornavnMarkering()
	{
		return fornavnMarkering;
	}

	public void setFornavnMarkering(String fornavnMarkering)
	{
		this.fornavnMarkering = fornavnMarkering;
	}

	@Column
	public String getMellemnavn()
	{
		return mellemnavn;
	}

	public void setMellemnavn(String mellemnavn)
	{
		this.mellemnavn = mellemnavn;
	}

	public String getMellemnavnMarkering()
	{
		return mellemnavnMarkering;
	}

	public void setMellemnavnMarkering(String mellemnavnMarkering)
	{
		this.mellemnavnMarkering = mellemnavnMarkering;
	}

	@Column
	public String getEfternavn()
	{
		return efternavn;
	}

	public void setEfternavn(String efternavn)
	{
		this.efternavn = efternavn;
	}

	public String getEfternavnMarkering()
	{
		return efternavnMarkering;
	}

	public void setEfternavnMarkering(String efternavnMarkering)
	{
		this.efternavnMarkering = efternavnMarkering;
	}

	public Date getStartDato()
	{
		return startDato;
	}

	public void setStartDato(Date startDato)
	{
		this.startDato = startDato;
	}

	public String getStartDatoMarkering()
	{
		return startDatoMarkering;
	}

	public void setStartDatoMarkering(String startDatoMarkering)
	{
		this.startDatoMarkering = startDatoMarkering;
	}

	public String getAdresseringsNavn()
	{
		return adresseringsNavn;
	}

	public void setAdresseringsNavn(String adresseringsNavn)
	{
		this.adresseringsNavn = adresseringsNavn;
	}
}
