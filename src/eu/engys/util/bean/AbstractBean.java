/*******************************************************************************
 *  |       o                                                                   |
 *  |    o     o       | HELYX-OS: The Open Source GUI for OpenFOAM             |
 *  |   o   O   o      | Copyright (C) 2012-2016 ENGYS                          |
 *  |    o     o       | http://www.engys.com                                   |
 *  |       o          |                                                        |
 *  |---------------------------------------------------------------------------|
 *  |   License                                                                 |
 *  |   This file is part of HELYX-OS.                                          |
 *  |                                                                           |
 *  |   HELYX-OS is free software; you can redistribute it and/or modify it     |
 *  |   under the terms of the GNU General Public License as published by the   |
 *  |   Free Software Foundation; either version 2 of the License, or (at your  |
 *  |   option) any later version.                                              |
 *  |                                                                           |
 *  |   HELYX-OS is distributed in the hope that it will be useful, but WITHOUT |
 *  |   ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or   |
 *  |   FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License   |
 *  |   for more details.                                                       |
 *  |                                                                           |
 *  |   You should have received a copy of the GNU General Public License       |
 *  |   along with HELYX-OS; if not, write to the Free Software Foundation,     |
 *  |   Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA            |
 *******************************************************************************/
package eu.engys.util.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.apache.commons.lang.ArrayUtils;

public class AbstractBean {

    private transient PropertyChangeSupport support;

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        support.addPropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        support.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        if (support != null) {
            support.removePropertyChangeListener(listener);
        }
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        if (support != null) {
            support.removePropertyChangeListener(propertyName, listener);
        }
    }

    /**
     * If the property change listener has been added with the propertyName is better to use the 
     * "isListenedBy(String propertyName, PropertyChangeListener listener)" method otherwise an instance of
     * PropertyChangeListenerProxy may be returned (read the javadoc of PropertyChangeSupport for more details) 
     */
    @Deprecated
    public boolean isListenedBy(PropertyChangeListener listener) {
        return ArrayUtils.contains(support.getPropertyChangeListeners(), listener);
    }

    public boolean isListenedBy(String propertyName, PropertyChangeListener listener) {
        return ArrayUtils.contains(support.getPropertyChangeListeners(propertyName), listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        support.firePropertyChange(propertyName, oldValue, newValue);
    }

    protected void firePropertyChange(String propertyName, int oldValue, int newValue) {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        support.firePropertyChange(propertyName, oldValue, newValue);
    }

    protected void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        support.firePropertyChange(propertyName, oldValue, newValue);
    }
}
