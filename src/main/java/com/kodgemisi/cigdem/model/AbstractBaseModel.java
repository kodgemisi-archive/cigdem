/*
 * Copyright Â© November, 2015 by Kod Gemisi Ltd. <foss@kodgemisi.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.kodgemisi.cigdem.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@MappedSuperclass
public abstract class AbstractBaseModel implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    @Column(nullable = false)
    private Boolean deleted = false;

    @Column(nullable = false)
    private Calendar creationDate;

    private Calendar updateDate;

    private Calendar deletionDate;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Calendar getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public Calendar getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Calendar updateDate) {
        this.updateDate = updateDate;
    }

    public Calendar getDeletionDate() {
        return this.deletionDate;
    }

    public void setDeletionDate(Calendar deletionDate) {
        this.deletionDate = deletionDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Overriding this method is <strong>strongly encouraged</strong>. See {@link #equals(Object)}
     */
    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return super.hashCode();
        }
    }

    /**
     * Overriding this method is <strong>strongly encouraged</strong>.
     * <p>
     * Use a business key that is a combination of unique, usually immutable attributes
     * instead of the id.
     * <p>
     * <strong>Don't forget</strong> to change {@link #hashCode()} accordingly.
     *
     * @see <a href="http://docs.jboss.org/hibernate/orm/5.0/manual/en-US/html_single/#persistent-classes-equalshashcode">Hibernate docs on equals()</a>
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AbstractBaseModel)) {
            return false;
        }

        // unsaved objects are never equal
        if (this.id == null) {
            return false;
        }

        AbstractBaseModel other = (AbstractBaseModel) obj;
        return this.id.equals(other.id);
    }
}