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


@Entity
public class UmyndiggoerelseVaergeRelation extends CPREntity
{
	public enum VaergeRelationType
	{
		ikkeICPR, CPRFindes, adresseFindes
	}

	String cpr;
	Date umyndigStartDato;
	String umyndigStartDatoMarkering;
	Date umyndigSletteDato;
	String typeKode;
	VaergeRelationType type;
	String relationCpr;
	Date relationCprStartDato;
	String vaergesNavn;
	Date vaergesNavnStartDato;
	String relationsTekst1;
	String relationsTekst2;
	String relationsTekst3;
	String relationsTekst4;
	String relationsTekst5;

	@Id
	@Column
	public String getId()
	{
		return cpr + "-" + typeKode;
	}

	@Column
	public String getCpr()
	{

		return cpr;
	}

	public void setCpr(String cpr)
	{
		this.cpr = cpr;
	}

	public Date getUmyndigStartDato()
	{
		return umyndigStartDato;
	}

	public void setUmyndigStartDato(Date umyndigStartDato)
	{
		this.umyndigStartDato = umyndigStartDato;
	}

	public String getUmyndigStartDatoMarkering()
	{
		return umyndigStartDatoMarkering;
	}

	public void setUmyndigStartDatoMarkering(String umyndigStartDatoMarkering)
	{
		this.umyndigStartDatoMarkering = umyndigStartDatoMarkering;
	}

	public Date getUmyndigSletteDato()
	{
		return umyndigSletteDato;
	}

	public void setUmyndigSletteDato(Date umyndigSletteDato)
	{
		this.umyndigSletteDato = umyndigSletteDato;
	}

	@Column
	public String getTypeTekst()
	{
		if (type == null)
			return "Ukendt værge relation";
		else if (type == VaergeRelationType.ikkeICPR)
			return "Værge findes ikke i CPR";
		else if (type == VaergeRelationType.CPRFindes)
			return "Værges CPR findes";
		else if (type == VaergeRelationType.adresseFindes) return "Værges adresse findes";
		
		return null;
	}

	@Column
	public String getTypeKode()
	{
		return typeKode;
	}

	public void setType(String type)
	{
		if (type.equals("0000"))
			this.type = VaergeRelationType.ikkeICPR;
		else if (type.equals("0001"))
			this.type = VaergeRelationType.CPRFindes;
		else if (type.equals("0002"))
			this.type = VaergeRelationType.adresseFindes;
		else
			this.type = null;

		this.typeKode = type;
	}

	@Column
	public String getRelationCpr()
	{
		return relationCpr;
	}

	public void setRelationCpr(String relationCpr)
	{

		this.relationCpr = relationCpr;
	}

	@Column
	public Date getRelationCprStartDato()
	{
		return relationCprStartDato;
	}

	public void setRelationCprStartDato(Date relationCprStartDato)
	{
		this.relationCprStartDato = relationCprStartDato;
	}

	@Column
	public String getVaergesNavn()
	{
		return vaergesNavn;
	}

	public void setVaergesNavn(String vaergesNavn)
	{
		this.vaergesNavn = vaergesNavn;
	}

	@Column
	public Date getVaergesNavnStartDato()
	{
		return vaergesNavnStartDato;
	}

	public void setVaergesNavnStartDato(Date vaergesNavnStartDato)
	{
		this.vaergesNavnStartDato = vaergesNavnStartDato;
	}

	@Column
	public String getRelationsTekst1()
	{

		return relationsTekst1;
	}

	public void setRelationsTekst1(String relationsTekst1)
	{

		this.relationsTekst1 = relationsTekst1;
	}

	@Column
	public String getRelationsTekst2()
	{
		return relationsTekst2;
	}

	public void setRelationsTekst2(String relationsTekst2)
	{
		this.relationsTekst2 = relationsTekst2;
	}

	@Column
	public String getRelationsTekst3()
	{
		return relationsTekst3;
	}

	public void setRelationsTekst3(String relationsTekst3)
	{
		this.relationsTekst3 = relationsTekst3;
	}

	@Column
	public String getRelationsTekst4()
	{
		return relationsTekst4;
	}

	public void setRelationsTekst4(String relationsTekst4)
	{
		this.relationsTekst4 = relationsTekst4;
	}

	@Column
	public String getRelationsTekst5()
	{
		return relationsTekst5;
	}

	public void setRelationsTekst5(String relationsTekst5)
	{
		this.relationsTekst5 = relationsTekst5;
	}

	@Override
	public Date getValidFrom()
	{		
		return (umyndigStartDato == null) ? super.getValidFrom() : umyndigStartDato;
	}

	@Override
	public Date getValidTo()
	{
		return (umyndigSletteDato == null) ? super.getValidTo() : umyndigSletteDato;
	}
}
