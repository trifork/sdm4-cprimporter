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


@Entity(name = "Person")
public class Klarskriftadresse extends CPREntity
{
	String cpr;
	String adresseringsNavn;
	String coNavn;
	String lokalitet;
	String adresseringsVejnavn;
	String byNavn;
	Long postNummer;
	String postDistrikt;
	Long kommuneKode;
	Long vejKode;
	String husNummer;
	String etage;
	String sideDoerNummer;
	String bygningsNummer;
	String vejNavn;

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
	public String getNavnTilAdressering()
	{
		return adresseringsNavn;
	}

	public void setNavnTilAdressering(String adresseringsNavn)
	{
		this.adresseringsNavn = adresseringsNavn;
	}

	@Column
	public String getCoNavn()
	{
		return coNavn;
	}

	public void setCoNavn(String coNavn)
	{
		this.coNavn = coNavn;
	}

	@Column
	public String getLokalitet()
	{
		return lokalitet;
	}

	public void setLokalitet(String lokalitet)
	{
		this.lokalitet = lokalitet;
	}

	@Column
	public String getVejnavnTilAdressering()
	{
		return adresseringsVejnavn;
	}

	public void setVejnavnTilAdressering(String adresse)
	{
		this.adresseringsVejnavn = adresse;
	}

	@Column
	public String getByNavn()
	{
		return byNavn;
	}

	public void setByNavn(String byNavn)
	{
		this.byNavn = byNavn;
	}

	@Column
	public Long getPostNummer()
	{
		return postNummer;
	}

	public void setPostNummer(Long postNummer)
	{
		this.postNummer = postNummer;
	}

	@Column
	public String getPostDistrikt()
	{
		return postDistrikt;
	}

	public void setPostDistrikt(String postDistrikt)
	{
		this.postDistrikt = postDistrikt;
	}

	@Column
	public Long getKommuneKode()
	{
		return kommuneKode;
	}

	public void setKommuneKode(Long kommuneKode)
	{
		this.kommuneKode = kommuneKode;
	}

	@Column
	public Long getVejKode()
	{
		return vejKode;
	}

	public void setVejKode(Long vejKode)
	{
		this.vejKode = vejKode;
	}

	@Column
	public String getHusNummer()
	{
		return husNummer;
	}

	public void setHusNummer(String husNummer)
	{
		this.husNummer = husNummer;
	}

	@Column
	public String getEtage()
	{
		return etage;
	}

	public void setEtage(String etage)
	{
		this.etage = etage;
	}

	@Column
	public String getSideDoerNummer()
	{
		return sideDoerNummer;
	}

	public void setSideDoerNummer(String sideDoerNummer)
	{
		this.sideDoerNummer = sideDoerNummer;
	}

	@Column
	public String getBygningsNummer()
	{
		return bygningsNummer;
	}

	public void setBygningsNummer(String bygningsNummer)
	{
		this.bygningsNummer = bygningsNummer;
	}

	@Column
	public String getVejNavn()
	{
		return vejNavn;
	}

	public void setVejNavn(String vejNavn)
	{
		this.vejNavn = vejNavn;
	}
}
