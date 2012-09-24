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
public class ForaeldreMyndighedRelation extends CPREntity
{
	public enum ForaeldreMyndighedRelationsType
	{
		mor, far, andenIndehaver1, andenIndehaver2
	}

	String cpr;
	String typeKode;
	ForaeldreMyndighedRelationsType type;
	Date foraeldreMyndighedStartDato;
	String foraeldreMyndighedMarkering;
	Date foraeldreMyndighedSlettedato;
	String relationCpr; // Hvis relationstypen ikke er mor eller far
	Date relationCprStartDato;

	@Id
	@Column
	public String getId()
	{

		if (type == ForaeldreMyndighedRelationsType.mor)
			return cpr + "-mor";
		else if (type == ForaeldreMyndighedRelationsType.far) return cpr + "-far";
		return cpr + "-" + relationCpr;
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

	@Column
	public String getTypeTekst()
	{

		if (type == null)
			return "Ukendt forældre myndigheds relation";
		else if (type == ForaeldreMyndighedRelationsType.mor)
			return "Mor";
		else if (type == ForaeldreMyndighedRelationsType.far)
			return "Far";
		else if (type == ForaeldreMyndighedRelationsType.andenIndehaver1)
			return "Anden indenhaver 1";
		else if (type == ForaeldreMyndighedRelationsType.andenIndehaver2) return "Anden indenhaver 1";
		return null;
	}

	@Column
	public String getTypeKode()
	{

		return typeKode;
	}

	public void setType(String type)
	{

		if (type.equals("0003"))
			this.type = ForaeldreMyndighedRelationsType.mor;
		else if (type.equals("0004"))
			this.type = ForaeldreMyndighedRelationsType.far;
		else if (type.equals("0005"))
			this.type = ForaeldreMyndighedRelationsType.andenIndehaver1;
		else if (type.equals("0006"))
			this.type = ForaeldreMyndighedRelationsType.andenIndehaver2;
		else
			this.type = null;

		this.typeKode = type;
	}

	public Date getForaeldreMyndighedStartDato()
	{
		return foraeldreMyndighedStartDato;
	}

	public void setForaeldreMyndighedStartDato(Date foraeldreMyndighedStartDato)
	{
		this.foraeldreMyndighedStartDato = foraeldreMyndighedStartDato;
	}

	public String getForaeldreMyndighedMarkering()
	{
		return foraeldreMyndighedMarkering;
	}

	public void setForaeldreMyndighedMarkering(String foraeldreMyndighedMarkering)
	{
		this.foraeldreMyndighedMarkering = foraeldreMyndighedMarkering;
	}

	public Date getForaeldreMyndighedSlettedato()
	{
		return foraeldreMyndighedSlettedato;
	}

	public void setForaeldreMyndighedSlettedato(Date foraeldreMyndighedSlettedato)
	{
		this.foraeldreMyndighedSlettedato = foraeldreMyndighedSlettedato;
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

	public Date getRelationCprStartDato()
	{
		return relationCprStartDato;
	}

	public void setRelationCprStartDato(Date relationCprStartDato)
	{
		this.relationCprStartDato = relationCprStartDato;
	}

	@Override
	public Date getValidFrom()
	{
		return (foraeldreMyndighedStartDato == null) ? super.getValidFrom() : foraeldreMyndighedStartDato;
	}

	@Override
	public Date getValidTo()
	{
		return (foraeldreMyndighedSlettedato == null) ? super.getValidTo() : foraeldreMyndighedSlettedato;
	}
}
