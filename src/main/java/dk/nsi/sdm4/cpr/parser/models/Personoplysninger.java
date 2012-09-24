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
public class Personoplysninger extends CPREntity
{
	String cpr;
	String gaeldendeCpr;
	String status;
	Date statusDato;
	String statusMakering;
	String koen;
	Date foedselsdato;
	boolean foedselsdatoMarkering;
	Date startDato;
	String startDatoMarkering;
	Date slutDato;
	String slutDatoMarkering;
	String stilling;

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
	public String getGaeldendeCpr()
	{
		return gaeldendeCpr;
	}

	public void setGaeldendeCpr(String gaeldendeCpr)
	{
		this.gaeldendeCpr = gaeldendeCpr;
	}

	@Column
	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	@Column
	public Date getStatusDato()
	{
		return statusDato;
	}

	public void setStatusDato(Date statusDato)
	{
		this.statusDato = statusDato;
	}

	public String getStatusMakering()
	{
		return statusMakering;
	}

	public void setStatusMakering(String statusMakering)
	{
		this.statusMakering = statusMakering;
	}

	@Column
	public String getKoen()
	{
		return koen;
	}

	public void setKoen(String koen)
	{
		this.koen = koen;
	}

	@Column
	public Date getFoedselsdato()
	{
		return foedselsdato;
	}

	public void setFoedselsdato(Date foedselsdato)
	{
		this.foedselsdato = foedselsdato;
	}

	@Column
	public boolean getFoedselsdatoMarkering()
	{
		return foedselsdatoMarkering;
	}

	public void setFoedselsdatoMarkering(boolean isUncertain)
	{
		this.foedselsdatoMarkering = isUncertain;
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

	public Date getSlutDato()
	{
		return slutDato;
	}

	public void setSlutdato(Date slutDato)
	{
		this.slutDato = slutDato;
	}

	public String getSlutDatoMarkering()
	{
		return slutDatoMarkering;
	}

	public void setSlutDatoMarkering(String slutDatoMarkering)
	{
		this.slutDatoMarkering = slutDatoMarkering;
	}

	@Column
	public String getStilling()
	{
		return stilling;
	}

	public void setStilling(String stilling)
	{
		this.stilling = stilling;
	}
}
