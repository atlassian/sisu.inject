/*******************************************************************************
 * Copyright (c) 2010, 2012 Sonatype, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stuart McCulloch (Sonatype, Inc.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.sisu.scanners.index;

import org.eclipse.sisu.scanners.AbstractClassFinder;
import org.eclipse.sisu.scanners.ClassFinder;

/**
 * {@link ClassFinder} that uses the qualified class index to select implementations to scan.
 */
public final class SisuIndexFinder extends AbstractClassFinder
{
    public SisuIndexFinder(final boolean globalIndex)
    {
        super(globalIndex, SisuIndex.NAMED);
    }
}
