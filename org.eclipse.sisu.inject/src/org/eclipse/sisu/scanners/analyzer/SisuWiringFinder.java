package org.eclipse.sisu.scanners.analyzer;

import com.google.inject.spi.ElementVisitor;
import org.eclipse.sisu.scanners.AbstractClassFinder;

/**
 * Finds all instances of {@link WiringFactory}
 */
public class SisuWiringFinder extends AbstractClassFinder
{
    public SisuWiringFinder(boolean global)
    {
        super(global, WiringFactory.class.getName());
    }
}
